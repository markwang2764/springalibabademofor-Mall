package goods.service.web.controller;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.Result;
import cloud.common.springcloud.dto.ResultGenerator;
import cloud.common.springcloud.enums.ServiceResultEnum;
import cloud.common.springcloud.util.BeanUtil;
import cloud.common.springcloud.util.TecentCDNUtil;
import goods.service.web.config.annotation.TokenToAdminUser;
import goods.service.web.controller.param.BatchIdParam;
import goods.service.web.controller.param.GoodsAddParam;
import goods.service.web.controller.param.GoodsEditParam;
import goods.service.web.entity.GoodsCategory;
import goods.service.web.entity.GoodsInfo;
import goods.service.web.entity.UserAdmin;
import goods.service.web.service.GoodsCategoryService;
import goods.service.web.service.GoodsInfoService;
import goods.service.web.service.TencentCDNService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * (GoodsInfo)表控制层
 *
 * @author makejava
 * @since 2023-09-10 15:31:43
 */
@RestController
@Api(value = "v1", tags = "后台管理系统商品模块接口")
@RequestMapping("/goods/admin")
public class GoodsInfoController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsInfoController.class);
    /**
     * 服务对象
     */
    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    private GoodsCategoryService goodsCategoryService;


    /**
     * 分页查询
     */
    @GetMapping("/list")
    @ApiOperation(value = "商品列表", notes = "可根据名称和上架状态筛选")
    public Result<?> queryByPage(
            @RequestParam(required = false, defaultValue = "1") @ApiParam(value = "页码") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") @ApiParam(value = "每页条数") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") @ApiParam(value = "商品名称") String goodsName,
            @RequestParam(required = false, defaultValue = "0") @ApiParam(value = "上架状态 0-上架 1-下架") Integer goodsSellStatus,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser:{}", userAdmin.toString());
        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 10) {
            return ResultGenerator.genFailResult("分页参数异常");
        }
        Map<String, Object> params = new HashMap<>(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);
        if (!goodsName.isEmpty()) {
            params.put("goodsName", goodsName);
        }
        if (goodsSellStatus != null) {
            params.put("goodsSellStatus", goodsSellStatus);
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(goodsInfoService.queryByPage(pageQueryUtil));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取单条商品信息", notes = "根据id查询")
    public Result<?> queryById(@PathVariable("id") Long id, @TokenToAdminUser UserAdmin userAdmin) {
        logger.info("userAdmin: {}", userAdmin.toString());
        Map<String, Object> goodsInfoMap = new HashMap<>(8);
        GoodsInfo goodsInfo = goodsInfoService.queryById(id);
        if (goodsInfo == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        goodsInfoMap.put("goods", goodsInfo);
        GoodsCategory thirdCategory;
        GoodsCategory secondCategory;
        GoodsCategory firstCategory;
        thirdCategory = goodsCategoryService.queryById(goodsInfo.getGoodsCategoryId());
        if (thirdCategory != null) {
            goodsInfoMap.put("thirdCategory", thirdCategory);
            secondCategory = goodsCategoryService.queryById(thirdCategory.getParentId());
            if (secondCategory != null) {
                goodsInfoMap.put("secondCategory", secondCategory);
                firstCategory = goodsCategoryService.queryById(secondCategory.getParentId());
                if (firstCategory != null) {
                    goodsInfoMap.put("firstCategory", firstCategory);
                }
            }
        }
        return ResultGenerator.genSuccessResult(goodsInfoMap);
    }

    /**
     * 通过主键查询单条数据
     *@RequestParam("goodsId")
     */
    @GetMapping("/goodsDetail")
    @ApiOperation(value = "获取单条商品信息", notes = "根据id查询")
    public Result<?> queryGoodsDetail(@RequestParam("goodsId") Long goodsId) {
        GoodsInfo goodsInfo = goodsInfoService.queryById(goodsId);
        return ResultGenerator.genSuccessResult(goodsInfo);
    }

    /**
     * 新增数据
     * GoodsAddParam goodsAddParam
     *
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增商品信息", notes = "新增商品信息")
    public Result<?> add(@RequestBody @Valid GoodsAddParam goodsAddParam, @TokenToAdminUser UserAdmin userAdmin) {
        logger.info("adminUser:{}", userAdmin.toString());
        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtil.copyProperties(goodsAddParam, goodsInfo);
        String result = goodsInfoService.insert(goodsInfo);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(result);
    }

    /**
     * 编辑数据
     * GoodsEditParam goodsEditParam
     *
     * @return 编辑结果
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改商品信息", notes = "需改商品信息")
    public Result<?> edit(
            @RequestBody @Valid GoodsEditParam goodsEditParam,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser: {}", userAdmin.toString());
        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtil.copyProperties(goodsEditParam, goodsInfo);
        String res = goodsInfoService.update(goodsInfo);
        if (ServiceResultEnum.SUCCESS.getResult().equals(res)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(res);
        }
    }

    @PutMapping("/updateStatus/{sellStatus}")
    @ApiOperation(value = "批量修改销售状态", notes = "批量修改销售状态")
    public Result<?> deleteBatchSellStatus(
            @RequestBody BatchIdParam batchIdParam,
            @PathVariable("sellStatus") int sellStatus,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser: {}", userAdmin.toString());
        if (batchIdParam == null || batchIdParam.getIds().length < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        if (sellStatus != 0 && sellStatus != 1) {
            return ResultGenerator.genFailResult("状态异常");
        }
        if (goodsInfoService.BatchUpdateSellStatus(batchIdParam.getIds(), sellStatus)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("修改失败");
    }


//    @DeleteMapping
//    public ResponseEntity<Boolean> deleteById(Object id) {
//        return ResponseEntity.ok(this.goodsInfoService.deleteById(id));
//    }
    @ApiOperation(value = "上传图片", notes = "上传图片", httpMethod = "POST")
    @PostMapping(value = "/uploadImage")
    public Result<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        if (file == null) {
            return ResultGenerator.genFailResult("文件为空");
        }
        String r = TecentCDNUtil.uploadImages(file);
        return ResultGenerator.genSuccessResult(new StringBuilder(r));
    }
}

