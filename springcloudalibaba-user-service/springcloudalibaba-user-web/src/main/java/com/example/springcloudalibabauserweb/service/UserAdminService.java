package com.example.springcloudalibabauserweb.service;

import com.example.springcloudalibabauserweb.entity.UserAdmin;


import java.util.List;

/**
 * (UserAdmin)表服务接口
 *
 * @author makejava
 * @since 2023-09-06 12:35:54
 */
public interface UserAdminService {

    /**
     * @Author: Mr.markwang 2764
     * @Date: 2023/9/6
     * 登录接口
     */
    String login(String userName, String passwrod);

    /**
     * @Author: Mr.markwang 2764
     * @Date: 2023/9/9
     * feignClient 调用 获取用户信息
     */
    UserAdmin getUserDetailByToken(String token);

    /**
     * 通过ID查询单条数据
     *
     * @param adminUserId 主键
     * @return 实例对象
     */
    UserAdmin queryById(Long adminUserId);

    /**
     * 分页查询
     *
     * @param userAdmin   筛选条件
     * @return 查询结果
     */
    List<UserAdmin> queryAll(UserAdmin userAdmin);

    /**
     * 新增数据
     *
     * @param userAdmin 实例对象
     * @return 实例对象
     */
    UserAdmin insert(UserAdmin userAdmin);

    /**
     * 修改数据
     *
     * @param userAdmin 实例对象
     * @return 实例对象
     */
    UserAdmin update(UserAdmin userAdmin);

    /**
     * 通过主键删除数据
     *
     * @param adminUserId 主键
     * @return 是否成功
     */
    boolean deleteById(Long adminUserId);


}
