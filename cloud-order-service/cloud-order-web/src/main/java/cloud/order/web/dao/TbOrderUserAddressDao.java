package cloud.order.web.dao;

import cloud.order.web.entity.TbOrderUserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收货地址表(TbOrderUserAddress)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
public interface TbOrderUserAddressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param addressId 主键
     * @return 实例对象
     */
    TbOrderUserAddress queryById(Long addressId);

    /**
     * 查询指定行数据
     *
     * @param tbOrderUserAddress 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<TbOrderUserAddress> queryAllByLimit(TbOrderUserAddress tbOrderUserAddress);

    /**
     * 统计总行数
     *
     * @param tbOrderUserAddress 查询条件
     * @return 总行数
     */
    long count(TbOrderUserAddress tbOrderUserAddress);

    /**
     * 新增数据
     *
     * @param tbOrderUserAddress 实例对象
     * @return 影响行数
     */
    int insert(TbOrderUserAddress tbOrderUserAddress);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbOrderUserAddress> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbOrderUserAddress> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbOrderUserAddress> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TbOrderUserAddress> entities);

    /**
     * 修改数据
     *
     * @param tbOrderUserAddress 实例对象
     * @return 影响行数
     */
    int update(TbOrderUserAddress tbOrderUserAddress);

    /**
     * 通过主键删除数据
     *
     * @param addressId 主键
     * @return 影响行数
     */
    int deleteById(Long addressId);

}

