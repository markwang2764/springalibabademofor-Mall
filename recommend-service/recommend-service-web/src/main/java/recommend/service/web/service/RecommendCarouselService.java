package recommend.service.web.service;

import recommend.service.web.entity.RecommendCarousel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

    /**
     * 分页查询
     *
     * @param recommendCarousel 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<RecommendCarousel> queryByPage(RecommendCarousel recommendCarousel, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param recommendCarousel 实例对象
     * @return 实例对象
     */
    RecommendCarousel insert(RecommendCarousel recommendCarousel);

    /**
     * 修改数据
     *
     * @param recommendCarousel 实例对象
     * @return 实例对象
     */
    RecommendCarousel update(RecommendCarousel recommendCarousel);

    /**
     * 通过主键删除数据
     *
     * @param carouselId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer carouselId);

}
