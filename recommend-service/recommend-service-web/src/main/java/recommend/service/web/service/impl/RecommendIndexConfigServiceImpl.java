package recommend.service.web.service.impl;

import recommend.service.web.entity.RecommendIndexConfig;
import recommend.service.web.dao.RecommendIndexConfigDao;
import recommend.service.web.service.RecommendIndexConfigService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (RecommendIndexConfig)表服务实现类
 *
 * @author makejava
 * @since 2023-09-17 23:41:50
 */
@Service("recommendIndexConfigService")
public class RecommendIndexConfigServiceImpl implements RecommendIndexConfigService {
    @Resource
    private RecommendIndexConfigDao recommendIndexConfigDao;

    /**
     * 通过ID查询单条数据
     *
     * @param configId 主键
     * @return 实例对象
     */
    @Override
    public RecommendIndexConfig queryById(Long configId) {
        return this.recommendIndexConfigDao.queryById(configId);
    }

    /**
     * 分页查询
     *
     * @param recommendIndexConfig 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<RecommendIndexConfig> queryByPage(RecommendIndexConfig recommendIndexConfig, PageRequest pageRequest) {
        long total = this.recommendIndexConfigDao.count(recommendIndexConfig);
        return new PageImpl<>(this.recommendIndexConfigDao.queryAllByLimit(recommendIndexConfig, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param recommendIndexConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RecommendIndexConfig insert(RecommendIndexConfig recommendIndexConfig) {
        this.recommendIndexConfigDao.insert(recommendIndexConfig);
        return recommendIndexConfig;
    }

    /**
     * 修改数据
     *
     * @param recommendIndexConfig 实例对象
     * @return 实例对象
     */
    @Override
    public RecommendIndexConfig update(RecommendIndexConfig recommendIndexConfig) {
        this.recommendIndexConfigDao.update(recommendIndexConfig);
        return this.queryById(recommendIndexConfig.getConfigId());
    }

    /**
     * 通过主键删除数据
     *
     * @param configId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long configId) {
        return this.recommendIndexConfigDao.deleteById(configId) > 0;
    }
}
