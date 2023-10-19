package cloud.user.web.service.impl;

import cloud.common.springcloud.pojo.AdminUserToken;
import cloud.common.springcloud.util.NumberUtil;
import cloud.common.springcloud.util.SystemUtil;
import cloud.user.web.entity.UserAdmin;
import cloud.user.web.dao.UserAdminDao;
import cloud.user.web.service.UserAdminService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (UserAdmin)表服务实现类
 *
 * @author makejava
 * @since 2023-09-06 12:35:54
 */
@Service("userAdminService")
public class UserAdminServiceImpl implements UserAdminService {
    @Resource
    private UserAdminDao userAdminDao;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @Author: Mr.markwang 2764
     * @Date: 2023/9/6
     * 登录
     */
    @Override
    public String login(String userName, String passwrod) {
        UserAdmin userAdmin = userAdminDao.login(userName, passwrod);
        if (userAdmin != null){
            String token = getNewToken(System.currentTimeMillis()+"", userAdmin.getAdminUserId());
            AdminUserToken userToken = new AdminUserToken();
            userToken.setAdminUserId(userAdmin.getAdminUserId());
            userToken.setToken(token);
            ValueOperations<String, Object> setToken = redisTemplate.opsForValue();
            setToken.set(token, userToken, 2, TimeUnit.DAYS);
            return token;
        }
        return "登录失败";
    }

    /**
     * @Author: Mr.markwang 2764
     * @Date: 2023/9/9
     * 根据token 获取用户信息
     */
    @Override
    public UserAdmin getUserDetailByToken(String token) {
        ValueOperations<String, Object> opsForAdminUserToken = redisTemplate.opsForValue();
        AdminUserToken adminUserToken = (AdminUserToken) opsForAdminUserToken.get(token);
        if (adminUserToken != null){
            return userAdminDao.queryById(adminUserToken.getAdminUserId());
        }
        return null;
    }

    private String getNewToken(String timeStr, Long userId){
        String src = timeStr + userId + NumberUtil.genRandomNum(6);
        return SystemUtil.genToken(src);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param adminUserId 主键
     * @return 实例对象
     */
    @Override
    public UserAdmin queryById(Long adminUserId) {
        return this.userAdminDao.queryById(adminUserId);
    }

    /**
     * 分页查询
     *
     * @param userAdmin   筛选条件
     * @return 查询结果
     */
    @Override
    public List<UserAdmin> queryAll(UserAdmin userAdmin) {
        long total = this.userAdminDao.count(userAdmin);
        return null;
    }

    /**
     * 新增数据
     *
     * @param userAdmin 实例对象
     * @return 实例对象
     */
    @Override
    public UserAdmin insert(UserAdmin userAdmin) {
        this.userAdminDao.insert(userAdmin);
        return userAdmin;
    }

    /**
     * 修改数据
     *
     * @param userAdmin 实例对象
     * @return 实例对象
     */
    @Override
    public UserAdmin update(UserAdmin userAdmin) {
        this.userAdminDao.update(userAdmin);
        return this.queryById(userAdmin.getAdminUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param adminUserId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long adminUserId) {
        return this.userAdminDao.deleteById(adminUserId) > 0;
    }

    @Override
    public boolean updatePassword(Long adminUserId, String originalPassword, String newPassword) {
        UserAdmin userAdmin = userAdminDao.queryById(adminUserId);
        if (userAdmin != null) {
            if (originalPassword.equals(userAdmin.getLoginPassword())) {
                userAdmin.setLoginUserName(newPassword);
                return userAdminDao.update(userAdmin) > 0;
            }
        }
        return false;
    }

    @Override
    public boolean updateName(Long adminUserId, String loginUserName, String nickName) {
        UserAdmin userAdmin = userAdminDao.queryById(adminUserId);
        if (userAdmin != null) {
            userAdmin.setLoginUserName(loginUserName);
            userAdmin.setNickName(nickName);
            return userAdminDao.update(userAdmin) > 0;
        }
        return false;
    }

    @Override
    public Boolean logout(String token) {
        redisTemplate.delete(token);
        return true;
    }
}
