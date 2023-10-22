package goods.service.web.service;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.PageResult;
import goods.service.web.entity.GoodsInfo;

/**
 * (GoodsInfo)表服务接口
 *
 * @author makejava
 * @since 2023-09-10 15:31:44
 */
public interface GoodsInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    GoodsInfo queryById(Object goodsId);

    /**
     * 分页查询
     *
     * @return 查询结果
     */
    PageResult<?> queryByPage(PageQueryUtil pageQueryUtil);

    /**
     * 新增数据
     *
     * @param goodsInfo 实例对象
     * @return 实例对象
     */
    String insert(GoodsInfo goodsInfo);

    /**
     * 修改数据
     *
     * @param goodsInfo 实例对象
     * @return 实例对象
     */
    String update(GoodsInfo goodsInfo);

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 是否成功
     */
    boolean deleteById(Object goodsId);

    Boolean batchUpdateSellStatus(Long[] ids, int sellStatus);

}
