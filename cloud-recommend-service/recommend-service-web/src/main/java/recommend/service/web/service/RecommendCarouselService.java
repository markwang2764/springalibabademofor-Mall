package recommend.service.web.service;

import recommend.service.web.entity.RecommendCarousel;

import java.util.List;

/**
 * (RecommendCarousel)表服务接口
 *
 * @author makejava
 * @since 2023-09-17 23:41:47
 */
public interface RecommendCarouselService {

    /**
     * 通过ID查询单条数据
     *
     * @param carouselId 主键
     * @return 实例对象
     */
    RecommendCarousel queryById(Integer carouselId);


    List<RecommendCarousel> list();
    /**
     * 新增数据
     *
     * @param recommendCarousel 实例对象
     * @return 实例对象
     */
    String insert(RecommendCarousel recommendCarousel);

    /**
     * 修改数据
     *
     * @param recommendCarousel 实例对象
     * @return 实例对象
     */
    String update(RecommendCarousel recommendCarousel);

    /**
     * 通过主键删除数据
     *
     * @param carouselId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer carouselId);
    Boolean deleteBatch(Long[] ids);

}
