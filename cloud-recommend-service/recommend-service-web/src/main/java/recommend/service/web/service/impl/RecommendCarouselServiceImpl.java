package recommend.service.web.service.impl;

import cloud.common.springcloud.enums.ServiceResultEnum;
import recommend.service.web.entity.RecommendCarousel;
import recommend.service.web.dao.RecommendCarouselDao;
import recommend.service.web.service.RecommendCarouselService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (RecommendCarousel)表服务实现类
 *
 * @author makejava
 * @since 2023-09-17 23:41:47
 */
@Service("recommendCarouselService")
public class RecommendCarouselServiceImpl implements RecommendCarouselService {
    @Resource
    private RecommendCarouselDao recommendCarouselDao;

    /**
     * 通过ID查询单条数据
     *
     * @param carouselId 主键
     * @return 实例对象
     */
    @Override
    public RecommendCarousel queryById(Integer carouselId) {
        return this.recommendCarouselDao.queryById(carouselId);
    }

    @Override
    public List<RecommendCarousel> list() {
        return recommendCarouselDao.carouseList();
    }

    /**
     * 新增数据
     *
     * @param recommendCarousel 实例对象
     * @return 实例对象
     */
    @Override
    public String insert(RecommendCarousel recommendCarousel) {
        if (recommendCarouselDao.insertSelective(recommendCarousel) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * 修改数据
     *
     * @param recommendCarousel 实例对象
     * @return 实例对象
     */
    @Override
    public String update(RecommendCarousel recommendCarousel) {
        RecommendCarousel temp = recommendCarouselDao.queryById(recommendCarousel.getCarouselId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * 通过主键删除数据
     *
     * @param carouselId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer carouselId) {
        return this.recommendCarouselDao.deleteById(carouselId) > 0;
    }

    @Override
    public Boolean deleteBatch(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return recommendCarouselDao.deleteBatch(ids) > 0;
    }
}
