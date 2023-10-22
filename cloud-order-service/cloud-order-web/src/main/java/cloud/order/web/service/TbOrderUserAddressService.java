package cloud.order.web.service;

import cloud.order.web.entity.TbOrderUserAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 收货地址表(TbOrderUserAddress)表服务接口
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
public interface TbOrderUserAddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param addressId 主键
     * @return 实例对象
     */
    TbOrderUserAddress queryById(Long addressId);

    /**
     * 分页查询
     *
     * @param tbOrderUserAddress 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<TbOrderUserAddress> queryByPage(TbOrderUserAddress tbOrderUserAddress, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tbOrderUserAddress 实例对象
     * @return 实例对象
     */
    TbOrderUserAddress insert(TbOrderUserAddress tbOrderUserAddress);

    /**
     * 修改数据
     *
     * @param tbOrderUserAddress 实例对象
     * @return 实例对象
     */
    TbOrderUserAddress update(TbOrderUserAddress tbOrderUserAddress);

    /**
     * 通过主键删除数据
     *
     * @param addressId 主键
     * @return 是否成功
     */
    boolean deleteById(Long addressId);

}
