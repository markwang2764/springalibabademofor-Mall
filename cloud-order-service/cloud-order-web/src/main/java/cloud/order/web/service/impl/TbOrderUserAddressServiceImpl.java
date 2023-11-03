package cloud.order.web.service.impl;

import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.dao.TbOrderUserAddressDao;
import cloud.order.web.entity.TbOrderUserAddress;
import cloud.order.web.service.TbOrderUserAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 收货地址表(TbOrderUserAddress)表服务实现类
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
@Service("tbOrderUserAddressService")
public class TbOrderUserAddressServiceImpl implements TbOrderUserAddressService {
    @Resource
    private TbOrderUserAddressDao tbOrderUserAddressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param addressId 主键
     * @return 实例对象
     */
    @Override
    public TbOrderUserAddress queryById(Long addressId) {
        return this.tbOrderUserAddressDao.queryById(addressId);
    }

    @Override
    public PageResult<?> queryByPage(TbOrderUserAddress tbOrderUserAddress) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param tbOrderUserAddress 实例对象
     * @return 实例对象
     */
    @Override
    public TbOrderUserAddress insert(TbOrderUserAddress tbOrderUserAddress) {
        this.tbOrderUserAddressDao.insert(tbOrderUserAddress);
        return tbOrderUserAddress;
    }

    /**
     * 修改数据
     *
     * @param tbOrderUserAddress 实例对象
     * @return 实例对象
     */
    @Override
    public TbOrderUserAddress update(TbOrderUserAddress tbOrderUserAddress) {
        this.tbOrderUserAddressDao.update(tbOrderUserAddress);
        return this.queryById(tbOrderUserAddress.getAddressId());
    }

    /**
     * 通过主键删除数据
     *
     * @param addressId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long addressId) {
        return this.tbOrderUserAddressDao.deleteById(addressId) > 0;
    }
}
