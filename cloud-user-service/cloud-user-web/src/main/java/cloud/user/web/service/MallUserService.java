package cloud.user.web.service;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.PageResult;
import cloud.user.web.controller.param.MallUserUpdateParam;
import cloud.user.web.entity.MallUser;

/**
 * (MallUser)表服务接口
 *
 * @author makejava
 * @since 2023-10-19 21:25:04
 */
public interface MallUserService {

    String register(String loginName, String password);
    String login(String loginName, String password);

    Boolean updateUserInfo(MallUserUpdateParam mallUserUpdateParam, Long userId);

    MallUser getUserDetailByToken(String token);

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    MallUser queryById(Long userId);

    /**
     * 分页查询
     * @return 查询结果
     */
    PageResult<MallUser> queryByPage(PageQueryUtil pageQueryUtil);

    /**
     * 新增数据
     *
     * @param mallUser 实例对象
     * @return 实例对象
     */
    MallUser insert(MallUser mallUser);

    /**
     * 修改数据
     *
     * @param mallUser 实例对象
     * @return 实例对象
     */
    MallUser update(MallUser mallUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);
    Boolean lockUsers(Integer[] ids, Integer lockStatus);

}
