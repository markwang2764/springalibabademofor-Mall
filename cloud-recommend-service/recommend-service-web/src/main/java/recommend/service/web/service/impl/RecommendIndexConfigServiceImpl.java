package recommend.service.web.service.impl;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.PageResult;
import cloud.common.springcloud.dto.Result;
import cloud.common.springcloud.enums.ServiceResultEnum;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import goods.service.api.dto.GoodsDTO;
import goods.service.api.openfeign.GoodsServiceApiOpenFeign;
import recommend.service.web.entity.RecommendIndexConfig;
import recommend.service.web.dao.RecommendIndexConfigDao;
import recommend.service.web.service.RecommendIndexConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private GoodsServiceApiOpenFeign goodsServiceApiOpenFeign;

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

    @Override
    public PageResult<RecommendIndexConfig> queryByPage(PageQueryUtil pageQueryUtil) {
        List<RecommendIndexConfig> indexConfigs = recommendIndexConfigDao.queryAllByLimit(pageQueryUtil);
        int total = recommendIndexConfigDao.getTotalIndexConfigs(pageQueryUtil);
        return new PageResult<>(indexConfigs, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
    }

    /**
     * 新增数据
     *
     * @param recommendIndexConfig 实例对象
     * @return 实例对象
     */
    @Override
    public String insert(RecommendIndexConfig recommendIndexConfig) {
        Result<GoodsDTO> goodDetail = goodsServiceApiOpenFeign.getGoodsDetail(recommendIndexConfig.getGoodsId());
        if (goodDetail == null || goodDetail.getResultCode() != 200) {
            return ServiceResultEnum.GOODS_NOT_EXIST.getResult();
        }
        if (recommendIndexConfigDao.selectByTypeAndGoodsId(recommendIndexConfig.getConfigType(), recommendIndexConfig.getGoodsId()) != null) {
            return ServiceResultEnum.SAME_INDEX_CONFIG_EXIST.getResult();
        }
        if (recommendIndexConfigDao.insertSelective(recommendIndexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
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
    public boolean deleteByIds(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return recommendIndexConfigDao.deleteBatch(ids) > 0;
    }
}
