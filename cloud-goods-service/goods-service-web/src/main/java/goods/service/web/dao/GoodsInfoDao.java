package goods.service.web.dao;

import cloud.common.springcloud.dto.PageQueryUtil;
import goods.service.web.entity.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GoodsInfo)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-10 15:31:43
 */
public interface GoodsInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    GoodsInfo queryById(Object goodsId);
    GoodsInfo queryByCategoryIdAndName(@Param("goodsCategoryId") Long goodsCategoryId,
                                       @Param("goodsName") String goodsName);

    /**
     * 查询指定行数据
     * @return 对象列表
     */
    List<GoodsInfo> queryAllByLimit(PageQueryUtil pageQueryUtil);

    /**
     * 统计总行数
     * @return 总行数
     */
    int count(GoodsInfo goodsInfo);

    /**
     * 新增数据
     * @param goodsInfo 实例对象
     * @return 影响行数
     */
    int insert(GoodsInfo goodsInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     * @param entities List<GoodsInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<GoodsInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<GoodsInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<GoodsInfo> entities);

    /**
     * 修改数据
     * @param goodsInfo 实例对象
     * @return 影响行数
     */
    int update(GoodsInfo goodsInfo);

    /**
     * 通过主键删除数据
     * @param goodsId 主键
     * @return 影响行数
     */
    int deleteById(Object goodsId);

    int batchUpdateSellStatus(@Param("orderIds") Long[] orderIds, @Param("sellStatus") int sellStatus);
}

