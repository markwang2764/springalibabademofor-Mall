package cloud.order.web.controller;

import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.entity.TbOrderAddress;
import cloud.order.web.service.TbOrderAddressService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单收货地址关联表(TbOrderAddress)表控制层
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
@RestController
@RequestMapping("tbOrderAddress")
public class TbOrderAddressController {
    /**
     * 服务对象
     */
    @Resource
    private TbOrderAddressService tbOrderAddressService;

    /**
     * 分页查询
     *
     * @param tbOrderAddress 筛选条件
     * @return 查询结果
     */
    @GetMapping
    public PageResult<?> queryByPage(TbOrderAddress tbOrderAddress) {
        return null;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TbOrderAddress> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tbOrderAddressService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbOrderAddress 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TbOrderAddress> add(TbOrderAddress tbOrderAddress) {
        return ResponseEntity.ok(this.tbOrderAddressService.insert(tbOrderAddress));
    }

    /**
     * 编辑数据
     *
     * @param tbOrderAddress 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TbOrderAddress> edit(TbOrderAddress tbOrderAddress) {
        return ResponseEntity.ok(this.tbOrderAddressService.update(tbOrderAddress));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tbOrderAddressService.deleteById(id));
    }

}

