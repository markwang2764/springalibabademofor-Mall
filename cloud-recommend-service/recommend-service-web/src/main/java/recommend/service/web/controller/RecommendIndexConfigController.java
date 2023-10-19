package recommend.service.web.controller;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.Result;
import cloud.common.springcloud.dto.ResultGenerator;
import cloud.common.springcloud.enums.IndexConfigTypeEnum;
import cloud.common.springcloud.enums.ServiceResultEnum;
import cloud.common.springcloud.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import recommend.service.web.config.annotation.TokenToAdminUser;
import recommend.service.web.controller.param.IndexConfigAddParam;
import recommend.service.web.entity.RecommendIndexConfig;
import recommend.service.web.entity.UserAdmin;
import recommend.service.web.service.RecommendIndexConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * (RecommendIndexConfig)表控制层
 *
 * @author makejava
 * @since 2023-09-17 23:41:50
 */
@RestController
@Api(value = "v1", tags = "后台管理系统首页配置模块接口")
@RequestMapping("/indexConfigs/admin")
public class RecommendIndexConfigController {

    private static final Logger logger = LoggerFactory.getLogger(RecommendIndexConfigController.class);
    /**
     * 服务对象
     */
    @Resource
    private RecommendIndexConfigService recommendIndexConfigService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("/list")
    @ApiOperation(value = "首页配置列表", notes = "首页配置列表")
    public Result<?> queryByPage(
            @RequestParam(required = false) @ApiParam(value = "页码") Integer pageNumber,
            @RequestParam(required = false) @ApiParam(value = "每条页数") Integer pageSize,
            @RequestParam(required = false) @ApiParam(value = "1-搜索框热搜 2-搜索下拉框热搜 3-（首页）热销商品 4-（首页）新品上线 5-(首页)为你推荐") Integer configType,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser:{}", userAdmin.toString());
        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 10) {
            return ResultGenerator.genFailResult("分页参数异常！");
        }
        IndexConfigTypeEnum indexConfigTypeEnum = IndexConfigTypeEnum.getIndexConfigTypeEnumByType(configType);
        if (indexConfigTypeEnum.equals(IndexConfigTypeEnum.DEFAULT)) {
            return ResultGenerator.genFailResult("非法参数！");
        }
        Map<String, Object> params = new HashMap<>(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);
        params.put("configType", configType);
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(recommendIndexConfigService.queryByPage(pageQueryUtil));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<RecommendIndexConfig> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.recommendIndexConfigService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param indexConfigAddParam 实体
     * @return 新增结果
     */
    @PostMapping("/add")
    @ApiOperation(value = "新增首页配置项", notes = "新增首页配置项")
    public Result<?> add(
            @RequestBody @Valid IndexConfigAddParam indexConfigAddParam,
            @TokenToAdminUser UserAdmin userAdmin
    ) {
        logger.info("adminUser:{}", userAdmin.toString());
        RecommendIndexConfig indexConfig = new RecommendIndexConfig();
        BeanUtil.copyProperties(indexConfigAddParam, indexConfig);
        String result = recommendIndexConfigService.insert(indexConfig);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult(result);
    }

    /**
     * 编辑数据
     *
     * @param recommendIndexConfig 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<RecommendIndexConfig> edit(RecommendIndexConfig recommendIndexConfig) {
        return ResponseEntity.ok(this.recommendIndexConfigService.update(recommendIndexConfig));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.recommendIndexConfigService.deleteById(id));
    }

}

