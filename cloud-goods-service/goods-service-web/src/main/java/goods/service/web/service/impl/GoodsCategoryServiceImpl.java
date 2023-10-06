package goods.service.web.service.impl;

import com.example.springcloudalibabacommon.dto.PageQueryUtil;
import com.example.springcloudalibabacommon.dto.PageResult;
import com.example.springcloudalibabacommon.enums.ServiceResultEnum;
import goods.service.web.entity.GoodsCategory;
import goods.service.web.dao.GoodsCategoryDao;
import goods.service.web.service.GoodsCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * (GoodsCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-09-10 15:31:42
 */
@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Resource
    private GoodsCategoryDao goodsCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    @Override
    public GoodsCategory queryById(Long categoryId) {
        return this.goodsCategoryDao.queryById(categoryId);
    }

    @Override
    public PageResult<GoodsCategory> queryAllGoodsCategoriesByLimit(PageQueryUtil pageQueryUtil) {
        List<GoodsCategory> goodsCategories = goodsCategoryDao.queryAllGoodsCategoriesByLimit(pageQueryUtil);
        int total = this.goodsCategoryDao.countGoodsCategories(pageQueryUtil);
        return new PageResult<>(goodsCategories, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
    }

    @Override
    public List<GoodsCategory> selectByLevelAndParentIdsAndNumber(List<Long> parentIds, Byte categoryLevel) {
        return goodsCategoryDao.selectByLevelAndParentIdsAndNumber(parentIds, categoryLevel, 0);
    }

    /**
     * 新增数据
     *
     * @param goodsCategory 实例对象
     * @return 实例对象
     */
    @Override
    public String insert(GoodsCategory goodsCategory) {
        GoodsCategory temp = goodsCategoryDao.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp != null) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        if (goodsCategoryDao.insert(goodsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * 修改数据
     *
     * @param goodsCategory 实例对象
     * @return 实例对象
     */
    @Override
    public String update(GoodsCategory goodsCategory) {
        GoodsCategory temp = goodsCategoryDao.queryById(goodsCategory.getCategoryId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        GoodsCategory temp2 = goodsCategoryDao.selectByLevelAndName(goodsCategory.getCategoryLevel(), goodsCategory.getCategoryName());
        if (temp2 != null && !temp2.getCategoryId().equals(goodsCategory.getCategoryId())) {
            return ServiceResultEnum.SAME_CATEGORY_EXIST.getResult();
        }
        goodsCategory.setUpdateTime(new Date());
        if (goodsCategoryDao.update(goodsCategory) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long categoryId) {
        return this.goodsCategoryDao.deleteById(categoryId) > 0;
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        return goodsCategoryDao.deleteBatch(ids) > 0;
    }
}
