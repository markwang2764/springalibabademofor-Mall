package cloud.order.web.service;

import cloud.order.web.entity.TbOrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<TbOrderItem> queryByPage(TbOrderItem tbOrderItem, PageRequest pageRequest);

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
