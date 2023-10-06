package cloud.user.web.dao;

import cloud.user.web.entity.UserAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserAdmin)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-06 12:35:54
 */
public interface UserAdminDao {

    UserAdmin login(@Param("userName") String userName, @Param("password") String password);

    /**
     * 通过ID查询单条数据
     *
     * @param adminUserId 主键
     * @return 实例对象
     */
    UserAdmin queryById(Long adminUserId);

    /**
     * 查询指定行数据
     *
     * @param userAdmin 查询条件
     * @return 对象列表
     */
    List<UserAdmin> queryAll(UserAdmin userAdmin);

    /**
     * 统计总行数
     *
     * @param userAdmin 查询条件
     * @return 总行数
     */
    long count(UserAdmin userAdmin);

    /**
     * 新增数据
     *
     * @param userAdmin 实例对象
     * @return 影响行数
     */
    int insert(UserAdmin userAdmin);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserAdmin> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<UserAdmin> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<UserAdmin> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<UserAdmin> entities);

    /**
     * 修改数据
     *
     * @param userAdmin 实例对象
     * @return 影响行数
     */
    int update(UserAdmin userAdmin);

    /**
     * 通过主键删除数据
     *
     * @param adminUserId 主键
     * @return 影响行数
     */
    int deleteById(Long adminUserId);

}

