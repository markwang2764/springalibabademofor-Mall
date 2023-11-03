package cloud.order.web.service.impl;

import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.dao.TbOrderItemDao;
import cloud.order.web.entity.TbOrderItem;
import cloud.order.web.service.TbOrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TbOrderItem)表服务实现类
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
@Service("tbOrderItemService")
public class TbOrderItemServiceImpl implements TbOrderItemService {
    @Resource
    private TbOrderItemDao tbOrderItemDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderItemId 主键
     * @return 实例对象
     */
    @Override
    public TbOrderItem queryById(Long orderItemId) {
        return this.tbOrderItemDao.queryById(orderItemId);
    }

    /**
     * 分页查询
     *
     * @param tbOrderItem 筛选条件
     * @return 查询结果
     */
    @Override
    public PageResult<?> queryByPage(TbOrderItem tbOrderItem) {
        long total = this.tbOrderItemDao.count(tbOrderItem);
        return null;
    }

    /**
     * 新增数据
     *
     * @param tbOrderItem 实例对象
     * @return 实例对象
     */
    @Override
    public TbOrderItem insert(TbOrderItem tbOrderItem) {
        this.tbOrderItemDao.insert(tbOrderItem);
        return tbOrderItem;
    }

    /**
     * 修改数据
     *
     * @param tbOrderItem 实例对象
     * @return 实例对象
     */
    @Override
    public TbOrderItem update(TbOrderItem tbOrderItem) {
        this.tbOrderItemDao.update(tbOrderItem);
        return this.queryById(tbOrderItem.getOrderItemId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderItemId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long orderItemId) {
        return this.tbOrderItemDao.deleteById(orderItemId) > 0;
    }
}
