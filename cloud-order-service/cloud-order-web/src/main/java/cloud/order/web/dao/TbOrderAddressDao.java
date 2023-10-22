package cloud.order.web.dao;

import cloud.order.web.entity.TbOrderAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单收货地址关联表(TbOrderAddress)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
public interface TbOrderAddressDao {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    TbOrderAddress queryById(Long orderId);

    /**
     * 查询指定行数据
     *
     * @param tbOrderAddress 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<TbOrderAddress> queryAllByLimit(TbOrderAddress tbOrderAddress, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param tbOrderAddress 查询条件
     * @return 总行数
     */
    long count(TbOrderAddress tbOrderAddress);

    /**
     * 新增数据
     *
     * @param tbOrderAddress 实例对象
     * @return 影响行数
     */
    int insert(TbOrderAddress tbOrderAddress);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbOrderAddress> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbOrderAddress> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbOrderAddress> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<TbOrderAddress> entities);

    /**
     * 修改数据
     *
     * @param tbOrderAddress 实例对象
     * @return 影响行数
     */
    int update(TbOrderAddress tbOrderAddress);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(Long orderId);

}

