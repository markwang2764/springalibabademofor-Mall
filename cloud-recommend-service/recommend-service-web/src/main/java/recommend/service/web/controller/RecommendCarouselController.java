package recommend.service.web.controller;

import cloud.common.springcloud.dto.Result;
import cloud.common.springcloud.dto.ResultGenerator;
import cloud.common.springcloud.enums.ServiceResultEnum;
import cloud.common.springcloud.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recommend.service.web.config.annotation.TokenToAdminUser;
import recommend.service.web.controller.param.BatchIdParam;
import recommend.service.web.controller.param.CarouselAddParam;
import recommend.service.web.controller.param.CarouselEditParam;
import recommend.service.web.entity.RecommendCarousel;
import recommend.service.web.entity.UserAdmin;
import recommend.service.web.service.RecommendCarouselService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * (RecommendCarousel)表控制层
 *
 * @author makejava
 * @since 2023-09-17 23:41:42
 */
@RestController
@Api(value = "v1", tags = "后台管理系统轮播图模块接口")
@RequestMapping("/carousels/admin")
public class RecommendCarouselController {

    private static final Logger logger = LoggerFactory.getLogger(RecommendCarouselController.class);
    @Resource
    RecommendCarouselService recommendCarouselService;


    /**
     * 轮播图列表
     *
     * @return 查询结果
     */
    @GetMapping("/list")
    @ApiOperation(value = "轮播图列表", notes = "轮播图列表")
    public Result<?> list(
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser:{}", userAdmin.toString());
        List<RecommendCarousel> lists = recommendCarouselService.list();
        if (lists == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(lists);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation(value = "获取单条轮播图信息", notes = "根据id查询")
    public Result<?> queryById(@PathVariable("id") Integer id, @TokenToAdminUser UserAdmin userAdmin) {
        logger.info("adminUser:{}", userAdmin.toString());
        RecommendCarousel carousel = recommendCarouselService.queryById(id);
        if (carousel == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(carousel);
    }

    /**
     * 新增数据
     * CarouselAddParam
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增轮播图", notes = "新增轮播图")
    public Result<?> add(
            @RequestBody @Valid CarouselAddParam carouselAddParam,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser:{}", userAdmin.toString());
        RecommendCarousel recommendCarousel = new RecommendCarousel();
        BeanUtil.copyProperties(carouselAddParam, recommendCarousel);
        String result = recommendCarouselService.insert(recommendCarousel);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(result);
    }

    /**
     * 编辑数据
     * <p>
     * carouselEditParam 实体
     *
     * @return 编辑结果
     */
    @PutMapping(value = "/update")
    @ApiOperation(value = "修改轮播图信息", notes = "修改轮播图信息")
    public Result<?> edit(
            @RequestBody CarouselEditParam carouselEditParam,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser:{}", userAdmin.toString());
        RecommendCarousel recommendCarousel = new RecommendCarousel();
        BeanUtil.copyProperties(carouselEditParam, recommendCarousel);
        String result = recommendCarouselService.update(recommendCarousel);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 删除数据
     *
     * @param batchIdParam 主键
     * @return 删除是否成功
     */
    @DeleteMapping("/batchDelete")
    @ApiOperation(value = "批量删除轮播图信息", notes = "批量删除轮播图信息")
    public Result<?> delete(@RequestBody BatchIdParam batchIdParam, @TokenToAdminUser UserAdmin userAdmin) {
        logger.info("adminUser:{}", userAdmin.toString());
        if (batchIdParam == null || batchIdParam.getIds().length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (recommendCarouselService.deleteBatch(batchIdParam.getIds())) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

}

