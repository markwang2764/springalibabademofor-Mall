package cloud.order.web.service;

import cloud.common.springcloud.dto.PageResult;
import cloud.order.web.entity.TbOrderItem;
/**
 * (TbOrderItem)表服务接口
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
public interface TbOrderItemService {

    /**
     * 通过ID查询单条数据
     *
     * @param orderItemId 主键
     * @return 实例对象
     */
    TbOrderItem queryById(Long orderItemId);

    /**
     * 分页查询
     *
     * @param tbOrderItem 筛选条件
     * @return 查询结果
     */
    PageResult<?> queryByPage(TbOrderItem tbOrderItem);

    /**
     * 新增数据
     *
     * @param tbOrderItem 实例对象
     * @return 实例对象
     */
    TbOrderItem insert(TbOrderItem tbOrderItem);

    /**
     * 修改数据
     *
     * @param tbOrderItem 实例对象
     * @return 实例对象
     */
    TbOrderItem update(TbOrderItem tbOrderItem);

    /**
     * 通过主键删除数据
     *
     * @param orderItemId 主键
     * @return 是否成功
     */
    boolean deleteById(Long orderItemId);

}
