package recommend.service.web.dao;

import recommend.service.web.entity.RecommendCarousel;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (RecommendCarousel)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-17 23:41:42
 */
public interface RecommendCarouselDao {

    /**
     * 通过ID查询单条数据
     *
     * @param carouselId 主键
     * @return 实例对象
     */
    RecommendCarousel queryById(Integer carouselId);

    List<RecommendCarousel> carouseList();

    /**
     * 统计总行数
     *
     * @param recommendCarousel 查询条件
     * @return 总行数
     */
    long count(RecommendCarousel recommendCarousel);

    /**
     * 新增数据
     *
     * @param recommendCarousel 实例对象
     * @return 影响行数
     */
    int insert(RecommendCarousel recommendCarousel);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RecommendCarousel> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RecommendCarousel> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RecommendCarousel> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RecommendCarousel> entities);

    /**
     * 修改数据
     *
     * @param recommendCarousel 实例对象
     * @return 影响行数
     */
    int update(RecommendCarousel recommendCarousel);

    /**
     * 通过主键删除数据
     *
     * @param carouselId 主键
     * @return 影响行数
     */
    int deleteById(Integer carouselId);

}

