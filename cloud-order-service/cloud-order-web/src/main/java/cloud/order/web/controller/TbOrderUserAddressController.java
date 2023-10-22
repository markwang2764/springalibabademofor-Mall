package cloud.order.web.controller;

import cloud.order.web.entity.TbOrderUserAddress;
import cloud.order.web.service.TbOrderUserAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 收货地址表(TbOrderUserAddress)表控制层
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
@RestController
@RequestMapping("tbOrderUserAddress")
public class TbOrderUserAddressController {
    /**
     * 服务对象
     */
    @Resource
    private TbOrderUserAddressService tbOrderUserAddressService;

    /**
     * 分页查询
     *
     * @param tbOrderUserAddress 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<TbOrderUserAddress>> queryByPage(TbOrderUserAddress tbOrderUserAddress, PageRequest pageRequest) {
        return ResponseEntity.ok(this.tbOrderUserAddressService.queryByPage(tbOrderUserAddress, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TbOrderUserAddress> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tbOrderUserAddressService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tbOrderUserAddress 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<TbOrderUserAddress> add(TbOrderUserAddress tbOrderUserAddress) {
        return ResponseEntity.ok(this.tbOrderUserAddressService.insert(tbOrderUserAddress));
    }

    /**
     * 编辑数据
     *
     * @param tbOrderUserAddress 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<TbOrderUserAddress> edit(TbOrderUserAddress tbOrderUserAddress) {
        return ResponseEntity.ok(this.tbOrderUserAddressService.update(tbOrderUserAddress));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tbOrderUserAddressService.deleteById(id));
    }

}

