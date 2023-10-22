package cloud.user.web.service.impl;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.PageResult;
import cloud.common.springcloud.enums.ServiceResultEnum;
import cloud.common.springcloud.util.MD5Util;
import cloud.user.web.controller.param.MallUserUpdateParam;
import cloud.user.web.service.MallUserService;
import cloud.user.web.entity.MallUser;
import cloud.user.web.dao.MallUserDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MallUser)表服务实现类
 *
 * @author makejava
 * @since 2023-10-19 21:25:04
 */
@Service("mallUserService")
public class MallUserServiceImpl implements MallUserService {
    @Resource
    private MallUserDao mallUserDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public String register(String loginName, String password) {
        if (mallUserDao.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        MallUser registerUser = new MallUser();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        registerUser.setIntroduceSign("虽千万人，吾往矣");
        String passwordMD5 = MD5Util.MD5Encode(password,"UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (mallUserDao.ins)
        return null;
    }

    @Override
    public String login(String loginName, String password) {
        return null;
    }

    @Override
    public Boolean updateUserInfo(MallUserUpdateParam mallUserUpdateParam, Long userId) {
        return null;
    }

    @Override
    public MallUser getUserDetailByToken(String token) {
        return null;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public MallUser queryById(Long userId) {
        return this.mallUserDao.queryById(userId);
    }

    /**
     * 分页查询
     * @param pageQueryUtil      分页对象
     * @return 查询结果
     */
    @Override
    public PageResult<MallUser> queryByPage(PageQueryUtil pageQueryUtil) {
        List<MallUser> users = mallUserDao.queryAllByLimit(pageQueryUtil);
        Integer total = this.mallUserDao.count(pageQueryUtil);
        return new PageResult<>(users, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
    }

    @Override
    public Boolean lockUsers(Integer[] ids, Integer lockStatus) {
        return mallUserDao.lockUserBatch(ids, lockStatus) > 0;
    }

    /**
     * 新增数据
     *
     * @param mallUser 实例对象
     * @return 实例对象
     */
    @Override
    public MallUser insert(MallUser mallUser) {
        this.mallUserDao.insert(mallUser);
        return mallUser;
    }

    /**
     * 修改数据
     *
     * @param mallUser 实例对象
     * @return 实例对象
     */
    @Override
    public MallUser update(MallUser mallUser) {
        this.mallUserDao.update(mallUser);
        return this.queryById(mallUser.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.mallUserDao.deleteById(userId) > 0;
    }
}
