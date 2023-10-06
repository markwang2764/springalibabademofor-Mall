package recommend.service.web.service;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.PageResult;
import recommend.service.web.entity.RecommendIndexConfig;

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
     * @return 查询结果
     */
    PageResult<RecommendIndexConfig> queryByPage(PageQueryUtil pageQueryUtil);

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
