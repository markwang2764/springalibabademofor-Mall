package recommend.service.web.service.impl;

import recommend.service.web.entity.RecommendCarousel;
import recommend.service.web.dao.RecommendCarouselDao;
import recommend.service.web.service.RecommendCarouselService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

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

    /**
     * 分页查询
     *
     * @param recommendCarousel 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<RecommendCarousel> queryByPage(RecommendCarousel recommendCarousel, PageRequest pageRequest) {
        long total = this.recommendCarouselDao.count(recommendCarousel);
        return new PageImpl<>(this.recommendCarouselDao.queryAllByLimit(recommendCarousel, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param recommendCarousel 实例对象
     * @return 实例对象
     */
    @Override
    public RecommendCarousel insert(RecommendCarousel recommendCarousel) {
        this.recommendCarouselDao.insert(recommendCarousel);
        return recommendCarousel;
    }

    /**
     * 修改数据
     *
     * @param recommendCarousel 实例对象
     * @return 实例对象
     */
    @Override
    public RecommendCarousel update(RecommendCarousel recommendCarousel) {
        this.recommendCarouselDao.update(recommendCarousel);
        return this.queryById(recommendCarousel.getCarouselId());
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
}
