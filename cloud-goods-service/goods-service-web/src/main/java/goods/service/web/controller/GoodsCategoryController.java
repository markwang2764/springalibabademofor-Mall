package goods.service.web.controller;

import com.example.springcloudalibabacommon.dto.PageQueryUtil;
import com.example.springcloudalibabacommon.dto.Result;
import com.example.springcloudalibabacommon.dto.ResultGenerator;
import com.example.springcloudalibabacommon.enums.CategoryLevelEnum;
import com.example.springcloudalibabacommon.enums.ServiceResultEnum;
import com.example.springcloudalibabacommon.util.BeanUtil;
import goods.service.web.config.annotation.TokenToAdminUser;
import goods.service.web.controller.param.BatchIdParam;
import goods.service.web.controller.param.GoodsCategoryAddParam;
import goods.service.web.controller.param.GoodsCategoryEditParam;
import goods.service.web.entity.GoodsCategory;
import goods.service.web.entity.UserAdmin;
import goods.service.web.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (GoodsCategory)表控制层
 *
 * @author markwang 2764
 * @since 2023-09-10 15:31:40
 */
@RestController
@Api(value = "v1", tags = "后台管理分类系统模块接口")
@RequestMapping("/goods/categories")
public class GoodsCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsCategoryController.class);
    /**
     * 服务对象
     */
    @Resource
    private GoodsCategoryService goodsCategoryService;

    /**
     * @Author: Mr.markwang 2764
     * @Date: 2023/9/11
     * 查询商品分类
     */
    @GetMapping("/list")
    @ApiOperation(value = "商品分类列表", notes = "根据级别和上级分类id查询")
    public Result<?> queryByPage(
            @RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber,
            @RequestParam(required = false) @ApiParam(value = "每页条数") Integer pageSize,
            @RequestParam(required = false) @ApiParam(value = "分类级别") Integer categoryLevel,
            @RequestParam(required = false) @ApiParam(value = "上级分类的id") Integer parentId,
            @TokenToAdminUser UserAdmin userInfo
    ) {

        logger.info("adminUser:{}", userInfo.toString());

        if (
                pageNumber == null ||
                        pageNumber < 1 ||
                        pageSize == null ||
                        pageSize < 10 ||
                        categoryLevel == null ||
                        categoryLevel < 0 ||
                        categoryLevel > 3 ||
                        parentId == null ||
                        parentId < 0
        ) {
            return ResultGenerator.genFailResult("分页参数异常！");
        }
        Map<String, Object> params = new HashMap<>(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);
        params.put("categoryLevel", categoryLevel);
        params.put("parentId", parentId);
        PageQueryUtil pageQuery = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(goodsCategoryService.queryAllGoodsCategoriesByLimit(pageQuery));
    }

    /**
     * @Author: Mr.markwang 2764
     * @Date: 2023/9/11
     * 查询商品分类
     * 列表级联查询
     */
    @GetMapping("/list4Select")
    @ApiOperation(value = "商品分类列表", notes = "根据级别和上级分类id查询")
    public Result<?> list4Select(
            @RequestParam("categoryId") Long categoryId,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser: {}", userAdmin.toString());

        if (categoryId == null || categoryId < 1) {
            return ResultGenerator.genFailResult("缺少参数！");
        }

        GoodsCategory category = goodsCategoryService.queryById(categoryId);

        if (category == null || category.getCategoryLevel() == CategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ResultGenerator.genFailResult("参数异常！");
        }

        Map<String, Object> categoryResult = new HashMap<>(4);

        if (category.getCategoryLevel() == CategoryLevelEnum.LEVEL_ONE.getLevel()) {
            List<GoodsCategory> secondLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(
                    Collections.singletonList(categoryId), (byte) CategoryLevelEnum.LEVEL_TWO.getLevel()
            );
            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
                categoryResult.put("secondLevelCategories", secondLevelCategories);
                List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(
                        Collections.singletonList(secondLevelCategories.get(0).getCategoryId()),
                        (byte) CategoryLevelEnum.LEVEL_THREE.getLevel()
                );
                if (!CollectionUtils.isEmpty(thirdLevelCategories)) {
                    categoryResult.put("thirdLevelCategories", thirdLevelCategories);
                }
            }
        }

        if (category.getCategoryLevel() == CategoryLevelEnum.LEVEL_TWO.getLevel()) {
            List<GoodsCategory> thirdLevelCategories = goodsCategoryService.selectByLevelAndParentIdsAndNumber(
                    Collections.singletonList(categoryId),
                    (byte) CategoryLevelEnum.LEVEL_THREE.getLevel()
            );
            categoryResult.put("thirdLevelCategories", thirdLevelCategories);
        }

        return ResultGenerator.genSuccessResult(categoryResult);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "商品详情", notes = "根据类id查询商品详情")
    public Result<?> queryById(@PathVariable("id") Long id, @TokenToAdminUser UserAdmin userAdmin) {
        logger.info("adminUser: {}", userAdmin.toString());
        GoodsCategory goodsCategory = goodsCategoryService.queryById(id);
        if (goodsCategory == null) {
            return ResultGenerator.genFailResult("未查到数据");
        }
        return ResultGenerator.genSuccessResult(goodsCategory);
    }

    /**
     * 新增数据
     * GoodsCategoryAddParam goodsCategoryAddParam
     *
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增分类", notes = "新增分类")
    public Result<?> add(
            @RequestBody @Valid GoodsCategoryAddParam goodsCategoryAddParam,
            @TokenToAdminUser UserAdmin userAdmin
    ) {

        logger.info("adminUser: {}", userAdmin.toString());

        GoodsCategory goodsCategory = new GoodsCategory();

        BeanUtil.copyProperties(goodsCategoryAddParam, goodsCategory);

        String result = goodsCategoryService.insert(goodsCategory);

        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 编辑数据
     * GoodsCategoryEditParam goodsCategoryEditParam
     *
     * @return 编辑结果
     */
    @PutMapping("/update")
    @ApiOperation(value = "修改分类信息", notes = "修改分类信息")
    public Result<?> edit(
            @RequestBody @Valid GoodsCategoryEditParam goodsCategoryEditParam,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser: {}", userAdmin.toString());

        GoodsCategory goodsCategory = new GoodsCategory();

        BeanUtil.copyProperties(goodsCategoryEditParam, goodsCategory);

        String result = goodsCategoryService.update(goodsCategory);

        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping("batchDelete")
    @ApiOperation(value = "批量删除分类信息", notes = "批量删除分类信息")
    public Result<?> delete(
            @RequestBody BatchIdParam batchIdParam,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser: {}", userAdmin.toString());
        if (batchIdParam == null || batchIdParam.getIds().length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (goodsCategoryService.deleteBatch(batchIdParam.getIds())) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }


}

