package goods.service.web.dao;

import cloud.common.springcloud.dto.PageQueryUtil;
import goods.service.web.entity.GoodsCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (GoodsCategory)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-10 15:31:40
 */
public interface GoodsCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    GoodsCategory queryById(Long categoryId);

    List<GoodsCategory> queryAllGoodsCategoriesByLimit(PageQueryUtil pageQueryUtil);

    int countGoodsCategories(PageQueryUtil pageQueryUtil);

    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(
            @Param("parentIds") List<Long> parentIds,
            @Param("categoryLevel") byte categoryLevel,
            @Param("number") int number
    );

    GoodsCategory selectByLevelAndName(
            @Param("categoryLevel") byte categoryLevel,
            @Param("categoryName") String categoryName
    );


    /**
     * 新增数据
     *
     * @param goodsCategory 实例对象
     * @return 影响行数
     */
    int insert(GoodsCategory goodsCategory);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<GoodsCategory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<GoodsCategory> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<GoodsCategory> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<GoodsCategory> entities);

    /**
     * 修改数据
     *
     * @param goodsCategory 实例对象
     * @return 影响行数
     */
    int update(GoodsCategory goodsCategory);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 影响行数
     */
    int deleteById(Long categoryId);

    int deleteBatch(Long[] categoryIds);

}

