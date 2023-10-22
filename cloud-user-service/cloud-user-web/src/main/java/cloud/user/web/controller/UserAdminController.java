package cloud.user.web.controller;

import cloud.common.springcloud.dto.Result;
import cloud.common.springcloud.dto.ResultGenerator;
import cloud.common.springcloud.pojo.AdminUserToken;
import cloud.user.web.config.annotation.TokenToAdminUser;
import cloud.user.web.controller.param.LoginParam;
import cloud.user.web.controller.param.UpdateAdminNameParam;
import cloud.user.web.entity.UserAdmin;
import cloud.user.web.controller.param.UpdateAdminPasswordParam;
import cloud.user.web.service.UserAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * (UserAdmin)表控制层
 *
 * @author makejava
 * @since 2023-09-06 12:35:54
 */
@RestController
@RequestMapping("users/admin")
@Api(value="v1", tags="用户管理")
public class UserAdminController {
    private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

    @Resource
    private UserAdminService userAdminService;

    /**
     * @Author: Mr.markwang 2764
     * @Date: 2023/9/8
     * 登录
     */
    @PostMapping(value = "/login")
    @ApiOperation("用户登录")
    public Result<?> login(@RequestBody @Valid LoginParam loginParam){
        String loginResult = userAdminService.login(loginParam.getUserName(), loginParam.getPasswordMd5());
        logger.info("manage login api, adminName = {}, loginResult={}", loginParam.getUserName(), loginResult);
        if (loginResult.length() == 32){
            Result<String> r = ResultGenerator.genSuccessResult();
            r.setData(loginResult);
            return r;
        }
        return ResultGenerator.genFailResult(loginResult);
    }

    /**
     * @Author: Mr.markwang 2764
     * @Date: 2023/9/8
     * 获取用户详情
     */
    @PostMapping("/profile")
    @ApiOperation("用户详情")
    public Result<?> queryById(@TokenToAdminUser AdminUserToken adminUserToken) {
        logger.info("adminUser: {}", adminUserToken.toString());
        UserAdmin userAdminEntity = userAdminService.queryById(adminUserToken.getAdminUserId());
        if (userAdminEntity != null){
            userAdminEntity.setLoginPassword("*****");
            return ResultGenerator.genSuccessResult(userAdminEntity);
        }
        return ResultGenerator.genFailResult("无此用户数据");
    }

    @PutMapping("/password")
    @ApiOperation("修改管理员密码接口")
    public Result<?> passwordUpdate(
            @TokenToAdminUser AdminUserToken adminUserToken,
            @RequestBody @Valid UpdateAdminPasswordParam adminPasswordParam
            ) {
        logger.info("adminUser: {}", adminUserToken.toString());
        if (userAdminService.updatePassword(
                adminUserToken.getAdminUserId(),
                adminPasswordParam.getOriginalPassword(),
                adminPasswordParam.getNewPassword()
        )){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("DB ERROR");
    }

    @PutMapping("/name")
    @ApiOperation("修改管理员信息接口")
    public Result<?> nameUpdate(
            @TokenToAdminUser AdminUserToken adminUserToken,
            @RequestBody @Valid UpdateAdminNameParam adminNameParam
    ) {
        logger.info("adminUser: {}", adminUserToken.toString());
        if (userAdminService.updateName(
                adminUserToken.getAdminUserId(),
                adminNameParam.getLoginUserName(),
                adminNameParam.getNickName()
        )){
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("DB ERROR");
    }

    @DeleteMapping("/logout")
    @ApiOperation("管理员退出登录接口")
    public Result<?> logout(
            @TokenToAdminUser AdminUserToken adminUserToken
    ) {
        logger.info("adminUser: {}", adminUserToken.toString());
       userAdminService.logout(adminUserToken.getToken());
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{token}")
    @ApiOperation("获取用户token")
    public Result<?> getAdminUserByToken(@PathVariable("token") String token) {
        UserAdmin userAdminEntity = userAdminService.getUserDetailByToken(token);
        if (userAdminEntity != null){
            userAdminEntity.setLoginPassword("***********");
            return ResultGenerator.genSuccessResult(userAdminEntity);
        }
        return ResultGenerator.genFailResult("无此用户数据");
    }


}

