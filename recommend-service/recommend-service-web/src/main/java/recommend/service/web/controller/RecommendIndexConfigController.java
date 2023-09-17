package recommend.service.web.controller;

import recommend.service.web.entity.RecommendIndexConfig;
import recommend.service.web.service.RecommendIndexConfigService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (RecommendIndexConfig)表控制层
 *
 * @author makejava
 * @since 2023-09-17 23:41:50
 */
@RestController
@RequestMapping("recommendIndexConfig")
public class RecommendIndexConfigController {
    /**
     * 服务对象
     */
    @Resource
    private RecommendIndexConfigService recommendIndexConfigService;

    /**
     * 分页查询
     *
     * @param recommendIndexConfig 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<RecommendIndexConfig>> queryByPage(RecommendIndexConfig recommendIndexConfig, PageRequest pageRequest) {
        return ResponseEntity.ok(this.recommendIndexConfigService.queryByPage(recommendIndexConfig, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<RecommendIndexConfig> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.recommendIndexConfigService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param recommendIndexConfig 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<RecommendIndexConfig> add(RecommendIndexConfig recommendIndexConfig) {
        return ResponseEntity.ok(this.recommendIndexConfigService.insert(recommendIndexConfig));
    }

    /**
     * 编辑数据
     *
     * @param recommendIndexConfig 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<RecommendIndexConfig> edit(RecommendIndexConfig recommendIndexConfig) {
        return ResponseEntity.ok(this.recommendIndexConfigService.update(recommendIndexConfig));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.recommendIndexConfigService.deleteById(id));
    }

}

