package cloud.order.web.service;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.entity.TbOrder;

/**
 * (TbOrder)表服务接口
 *
 * @author makejava
 * @since 2023-10-21 13:09:19
 */
public interface TbOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    TbOrder queryById(Long orderId);

    /**
     * 分页查询
     * @return 查询结果
     */
    PageResult<TbOrder> getMyOrders(PageQueryUtil pageQueryUtil);

    /**
     * 新增数据
     *
     * @param tbOrder 实例对象
     * @return 实例对象
     */
    TbOrder insert(TbOrder tbOrder);

    /**
     * 修改数据
     *
     * @param tbOrder 实例对象
     * @return 实例对象
     */
    TbOrder update(TbOrder tbOrder);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    boolean deleteById(Long orderId);

}
