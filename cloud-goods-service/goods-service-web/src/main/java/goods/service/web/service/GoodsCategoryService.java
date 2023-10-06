package goods.service.web.service;

import com.example.springcloudalibabacommon.dto.PageQueryUtil;
import com.example.springcloudalibabacommon.dto.PageResult;
import goods.service.web.dao.GoodsCategoryDao;
import goods.service.web.entity.GoodsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (GoodsCategory)表服务接口
 *
 * @author makejava
 * @since 2023-09-10 15:31:42
 */
public interface GoodsCategoryService {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    GoodsCategory queryById(Long categoryId);
    PageResult<GoodsCategory> queryAllGoodsCategoriesByLimit(PageQueryUtil pageQueryUtil);

    List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, Byte categoryLevel);
    /**
     * 新增数据
     *
     * @param goodsCategory 实例对象
     * @return 实例对象
     */
    String insert(GoodsCategory goodsCategory);

    /**
     * 修改数据
     *
     * @param goodsCategory 实例对象
     * @return 实例对象
     */
    String update(GoodsCategory goodsCategory);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    boolean deleteById(Long categoryId);
    boolean deleteBatch(Long[] ids);

}
