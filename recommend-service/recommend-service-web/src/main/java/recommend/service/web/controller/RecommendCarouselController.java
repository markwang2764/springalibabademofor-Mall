package recommend.service.web.controller;

import recommend.service.web.entity.RecommendCarousel;
import recommend.service.web.service.RecommendCarouselService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (RecommendCarousel)表控制层
 *
 * @author makejava
 * @since 2023-09-17 23:41:42
 */
@RestController
@RequestMapping("recommendCarousel")
public class RecommendCarouselController {
    /**
     * 服务对象
     */
    @Resource
    private RecommendCarouselService recommendCarouselService;

    /**
     * 分页查询
     *
     * @param recommendCarousel 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<RecommendCarousel>> queryByPage(RecommendCarousel recommendCarousel, PageRequest pageRequest) {
        return ResponseEntity.ok(this.recommendCarouselService.queryByPage(recommendCarousel, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<RecommendCarousel> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.recommendCarouselService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param recommendCarousel 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<RecommendCarousel> add(RecommendCarousel recommendCarousel) {
        return ResponseEntity.ok(this.recommendCarouselService.insert(recommendCarousel));
    }

    /**
     * 编辑数据
     *
     * @param recommendCarousel 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<RecommendCarousel> edit(RecommendCarousel recommendCarousel) {
        return ResponseEntity.ok(this.recommendCarouselService.update(recommendCarousel));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.recommendCarouselService.deleteById(id));
    }

}

