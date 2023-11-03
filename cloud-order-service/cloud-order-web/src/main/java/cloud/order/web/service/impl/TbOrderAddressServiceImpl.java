package cloud.order.web.service.impl;

import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.dao.TbOrderAddressDao;
import cloud.order.web.entity.TbOrderAddress;
import cloud.order.web.service.TbOrderAddressService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 订单收货地址关联表(TbOrderAddress)表服务实现类
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
@Service("tbOrderAddressService")
public class TbOrderAddressServiceImpl implements TbOrderAddressService {
    @Resource
    private TbOrderAddressDao tbOrderAddressDao;

    @Override
    public PageResult<?> queryByPage(TbOrderAddress tbOrderAddress) {
        return null;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public TbOrderAddress queryById(Long orderId) {
        return this.tbOrderAddressDao.queryById(orderId);
    }

    /**
     * 新增数据
     *
     * @param tbOrderAddress 实例对象
     * @return 实例对象
     */
    @Override
    public TbOrderAddress insert(TbOrderAddress tbOrderAddress) {
        this.tbOrderAddressDao.insert(tbOrderAddress);
        return tbOrderAddress;
    }

    /**
     * 修改数据
     *
     * @param tbOrderAddress 实例对象
     * @return 实例对象
     */
    @Override
    public TbOrderAddress update(TbOrderAddress tbOrderAddress) {
        this.tbOrderAddressDao.update(tbOrderAddress);
        return this.queryById(tbOrderAddress.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long orderId) {
        return this.tbOrderAddressDao.deleteById(orderId) > 0;
    }
}
