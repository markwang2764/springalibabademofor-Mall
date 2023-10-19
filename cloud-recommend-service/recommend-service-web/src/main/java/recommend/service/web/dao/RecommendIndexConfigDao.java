package recommend.service.web.dao;

import cloud.common.springcloud.dto.PageQueryUtil;
import recommend.service.web.entity.RecommendIndexConfig;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (RecommendIndexConfig)表数据库访问层
 *
 * @author makejava
 * @since 2023-09-17 23:41:50
 */
public interface RecommendIndexConfigDao {

    /**
     * 通过ID查询单条数据
     *
     * @param configId 主键
     * @return 实例对象
     */
    RecommendIndexConfig queryById(Long configId);
    int getTotalIndexConfigs(PageQueryUtil pageQueryUtil);

    /**
     * 查询指定行数据
     * @return 对象列表
     */
    List<RecommendIndexConfig> queryAllByLimit(PageQueryUtil pageQueryUtil);

    /**
     * 统计总行数
     *
     * @param recommendIndexConfig 查询条件
     * @return 总行数
     */
    long count(RecommendIndexConfig recommendIndexConfig);

    /**
     * 新增数据
     *
     * @param recommendIndexConfig 实例对象
     * @return 影响行数
     */
    int insert(RecommendIndexConfig recommendIndexConfig);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RecommendIndexConfig> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RecommendIndexConfig> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RecommendIndexConfig> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<RecommendIndexConfig> entities);

    /**
     * 修改数据
     *
     * @param recommendIndexConfig 实例对象
     * @return 影响行数
     */
    int update(RecommendIndexConfig recommendIndexConfig);

    /**
     * 通过主键删除数据
     *
     * @param configId 主键
     * @return 影响行数
     */
    int deleteById(Long configId);
    RecommendIndexConfig selectByTypeAndGoodsId(@Param("configType") int configType, @Param("goodsId") Long goodsId);
    int insertSelective(RecommendIndexConfig record);

}

