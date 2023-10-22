package cloud.order.web.service.impl;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.dao.TbOrderDao;
import cloud.order.web.entity.TbOrder;
import cloud.order.web.service.TbOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbOrder)表服务实现类
 *
 * @author makejava
 * @since 2023-10-21 13:09:21
 */
@Service("tbOrderService")
public class TbOrderServiceImpl implements TbOrderService {
    @Resource
    private TbOrderDao tbOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public TbOrder queryById(Long orderId) {
        return this.tbOrderDao.queryById(orderId);
    }

    @Override
    public PageResult<TbOrder> getMyOrders(PageQueryUtil pageQueryUtil) {
        int total = tbOrderDao.count(pageQueryUtil);
        List<TbOrder> orders = tbOrderDao.queryAllByLimit(pageQueryUtil);

        return null;
    }



    /**
     * 新增数据
     *
     * @param tbOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TbOrder insert(TbOrder tbOrder) {
        this.tbOrderDao.insert(tbOrder);
        return tbOrder;
    }

    /**
     * 修改数据
     *
     * @param tbOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TbOrder update(TbOrder tbOrder) {
        this.tbOrderDao.update(tbOrder);
        return this.queryById(tbOrder.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long orderId) {
        return this.tbOrderDao.deleteById(orderId) > 0;
    }
}
