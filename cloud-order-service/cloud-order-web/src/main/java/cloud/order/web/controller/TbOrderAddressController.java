package cloud.order.web.controller;

import cloud.order.web.entity.TbOrderAddress;
import cloud.order.web.service.TbOrderAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<TbOrderAddress>> queryByPage(TbOrderAddress tbOrderAddress, PageRequest pageRequest) {
        return ResponseEntity.ok(this.tbOrderAddressService.queryByPage(tbOrderAddress, pageRequest));
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

