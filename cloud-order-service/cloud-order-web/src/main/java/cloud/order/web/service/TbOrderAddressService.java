package cloud.order.web.service;

import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.entity.TbOrderAddress;


/**
 * 订单收货地址关联表(TbOrderAddress)表服务接口
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
public interface TbOrderAddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    TbOrderAddress queryById(Long orderId);

    /**
     * 分页查询
     *
     * @param tbOrderAddress 筛选条件
     * @return 查询结果
     */
    PageResult<?> queryByPage(TbOrderAddress tbOrderAddress);

    /**
     * 新增数据
     *
     * @param tbOrderAddress 实例对象
     * @return 实例对象
     */
    TbOrderAddress insert(TbOrderAddress tbOrderAddress);

    /**
     * 修改数据
     *
     * @param tbOrderAddress 实例对象
     * @return 实例对象
     */
    TbOrderAddress update(TbOrderAddress tbOrderAddress);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(Long orderId);

}
