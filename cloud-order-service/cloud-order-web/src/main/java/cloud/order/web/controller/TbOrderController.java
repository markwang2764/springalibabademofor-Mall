package cloud.order.web.controller;

import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.entity.TbOrder;
import cloud.order.web.service.TbOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbOrder)表控制层
 *
 * @author makejava
 * @since 2023-10-21 13:09:12
 */
@RestController
@RequestMapping("tbOrder")
public class TbOrderController {
    /**
     * 服务对象
     */
    @Resource
    private TbOrderService tbOrderService;

    /**
     * 分页查询
     *
     * @param tbOrder 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public PageResult<?> queryByPage(TbOrder tbOrder) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TbOrder> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tbOrderService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TbOrder> add(TbOrder tbOrder) {
        return ResponseEntity.ok(this.tbOrderService.insert(tbOrder));
    }

    /**
     * 编辑数据
     *
     * @param tbOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TbOrder> edit(TbOrder tbOrder) {
        return ResponseEntity.ok(this.tbOrderService.update(tbOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tbOrderService.deleteById(id));
    }

}

