package cloud.order.web.controller;

import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.entity.TbOrderItem;
import cloud.order.web.service.TbOrderItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbOrderItem)表控制层
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
@RestController
@RequestMapping("tbOrderItem")
public class TbOrderItemController {
    /**
     * 服务对象
     */
    @Resource
    private TbOrderItemService tbOrderItemService;

    /**
     * 分页查询
     *
     * @param tbOrderItem 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public PageResult<?> queryByPage(TbOrderItem tbOrderItem) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TbOrderItem> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tbOrderItemService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbOrderItem 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TbOrderItem> add(TbOrderItem tbOrderItem) {
        return ResponseEntity.ok(this.tbOrderItemService.insert(tbOrderItem));
    }

    /**
     * 编辑数据
     *
     * @param tbOrderItem 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TbOrderItem> edit(TbOrderItem tbOrderItem) {
        return ResponseEntity.ok(this.tbOrderItemService.update(tbOrderItem));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tbOrderItemService.deleteById(id));
    }

}

