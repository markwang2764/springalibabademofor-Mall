package cloud.user.web.controller;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.Result;
import cloud.common.springcloud.dto.ResultGenerator;
import cloud.user.web.service.MallUserService;
import cloud.user.web.controller.param.BatchIdParam;
import cloud.user.web.entity.MallUser;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * (MallUser)表控制层
 *
 * @author makejava
 * @since 2023-10-19 21:25:04
 */
@RestController
@RequestMapping("users/guests")
public class MallUserController {
    /**
     * 服务对象
     */
    @Resource
    private MallUserService mallUserService;

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @GetMapping("/list")
    @ApiOperation("用户列表")
    public Result<?> queryByPage(
            @RequestParam(required = false, defaultValue = "1") @ApiParam(value = "页码") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") @ApiParam(value = "每页条数") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") @ApiParam(value = "商品名称") String loginName
    ) {
        Map<String, Object> params = new HashMap<>(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);
        if (!loginName.isEmpty()) {
            params.put("loginName", loginName);
        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(mallUserService.queryByPage(pageQueryUtil));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<MallUser> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.mallUserService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param mallUser 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<MallUser> add(MallUser mallUser) {
        return ResponseEntity.ok(this.mallUserService.insert(mallUser));
    }

    /**
     * 编辑数据
     *
     * @param mallUser 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<MallUser> edit(MallUser mallUser) {
        return ResponseEntity.ok(this.mallUserService.update(mallUser));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.mallUserService.deleteById(id));
    }

    @PutMapping("/lock/{lockStatus}")
    @ApiOperation("禁用账户")
    public Result<?> lockBatch(@RequestBody BatchIdParam batchIdParam, @PathVariable int lockStatus) {
        Integer[] ids = batchIdParam.getIds();
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常");
        }
        if (lockStatus != 0 && lockStatus != 1) {
            return ResultGenerator.genFailResult("操作非法");
        }
        if (mallUserService.lockUsers(ids, lockStatus)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("禁用失败");
        }
    }
}

