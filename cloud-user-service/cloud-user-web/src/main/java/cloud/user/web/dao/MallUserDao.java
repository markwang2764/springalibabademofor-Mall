package cloud.user.web.dao;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.user.web.entity.MallUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (MallUser)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-19 21:25:04
 */
public interface MallUserDao {
    String selectByLoginName(String loginName);

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    MallUser queryById(Long userId);

    /**
     * 查询指定行数据
     * @return 对象列表
     */
    List<MallUser> queryAllByLimit(PageQueryUtil pageQueryUtil);

    /**
     * 统计总行数
     * @return 总行数
     */
    Integer count(PageQueryUtil pageQueryUtil);

    /**
     * 新增数据
     *
     * @param mallUser 实例对象
     * @return 影响行数
     */
    int insert(MallUser mallUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MallUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MallUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MallUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MallUser> entities);

    /**
     * 修改数据
     *
     * @param mallUser 实例对象
     * @return 影响行数
     */
    int update(MallUser mallUser);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

    int lockUserBatch(@Param("ids") Integer[] ids, @Param("lockStatus") Integer lockStatus);
}

