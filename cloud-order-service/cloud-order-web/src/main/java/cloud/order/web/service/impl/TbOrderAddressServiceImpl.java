package cloud.order.web.service.impl;

import cloud.order.web.dao.TbOrderAddressDao;
import cloud.order.web.entity.TbOrderAddress;
import cloud.order.web.service.TbOrderAddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
     * 分页查询
     *
     * @param tbOrderAddress 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<TbOrderAddress> queryByPage(TbOrderAddress tbOrderAddress, PageRequest pageRequest) {
        long total = this.tbOrderAddressDao.count(tbOrderAddress);
        return new PageImpl<>(this.tbOrderAddressDao.queryAllByLimit(tbOrderAddress, pageRequest), pageRequest, total);
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
