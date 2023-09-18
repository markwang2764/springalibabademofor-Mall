package recommend.service.web.controller;

import com.example.springcloudalibabacommon.dto.PageQueryUtil;
import com.example.springcloudalibabacommon.dto.Result;
import com.example.springcloudalibabacommon.dto.ResultGenerator;
import com.example.springcloudalibabacommon.enums.ServiceResultEnum;
import com.example.springcloudalibabacommon.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recommend.service.web.config.annotation.TokenToAdminUser;
import recommend.service.web.controller.param.CarouselAddParam;
import recommend.service.web.entity.RecommendCarousel;
import recommend.service.web.entity.UserAdmin;
import recommend.service.web.service.RecommendCarouselService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ApiOperation(value = "获取单挑轮播图信息", notes = "根据id查询")
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
     *
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
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(result);
    }

    /**
     * 编辑数据
     *
     * @param recommendCarousel 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<RecommendCarousel> edit(RecommendCarousel recommendCarousel) {
        return ResponseEntity.ok(this.recommendCarouselService.update(recommendCarousel));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.recommendCarouselService.deleteById(id));
    }

}

