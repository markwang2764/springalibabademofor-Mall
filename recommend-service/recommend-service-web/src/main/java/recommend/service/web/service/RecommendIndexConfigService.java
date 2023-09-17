package recommend.service.web.service;

import recommend.service.web.entity.RecommendIndexConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (RecommendIndexConfig)表服务接口
 *
 * @author makejava
 * @since 2023-09-17 23:41:50
 */
public interface RecommendIndexConfigService {

    /**
     * 通过ID查询单条数据
     *
     * @param configId 主键
     * @return 实例对象
     */
    RecommendIndexConfig queryById(Long configId);

    /**
     * 分页查询
     *
     * @param recommendIndexConfig 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<RecommendIndexConfig> queryByPage(RecommendIndexConfig recommendIndexConfig, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param recommendIndexConfig 实例对象
     * @return 实例对象
     */
    RecommendIndexConfig insert(RecommendIndexConfig recommendIndexConfig);

    /**
     * 修改数据
     *
     * @param recommendIndexConfig 实例对象
     * @return 实例对象
     */
    RecommendIndexConfig update(RecommendIndexConfig recommendIndexConfig);

    /**
     * 通过主键删除数据
     *
     * @param configId 主键
     * @return 是否成功
     */
    boolean deleteById(Long configId);

}
