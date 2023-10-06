package goods.service.web.service.impl;

import cloud.common.springcloud.dto.PageQueryUtil;
import cloud.common.springcloud.dto.PageResult;
import cloud.common.springcloud.enums.CategoryLevelEnum;
import cloud.common.springcloud.enums.ServiceResultEnum;
import goods.service.web.dao.GoodsCategoryDao;
import goods.service.web.entity.GoodsCategory;
import goods.service.web.entity.GoodsInfo;
import goods.service.web.dao.GoodsInfoDao;
import goods.service.web.service.GoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (GoodsInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-09-10 15:31:44
 */
@Service("goodsInfoService")
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Resource
    private GoodsInfoDao goodsInfoDao;
    @Resource
    private GoodsCategoryDao goodsCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    @Override
    public GoodsInfo queryById(Object goodsId) {
        return this.goodsInfoDao.queryById(goodsId);
    }

    /**
     * 分页查询
     * @return 查询结果
     */
    @Override
    public PageResult<?> queryByPage(PageQueryUtil pageQueryUtil) {
        List<GoodsInfo> goodsInfoList = goodsInfoDao.queryAllByLimit(pageQueryUtil);
        GoodsInfo blankg = new GoodsInfo();
        int total = goodsInfoDao.count(blankg);
        return new PageResult<>(goodsInfoList, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
    }

    /**
     * 新增数据
     *
     * @param goodsInfo 实例对象
     * @return 实例对象
     */
    @Override
    public String insert(GoodsInfo goodsInfo) {
        GoodsCategory goodsCategory = goodsCategoryDao.queryById(goodsInfo.getGoodsCategoryId());
        if (goodsCategory == null || goodsCategory.getCategoryLevel().intValue() != CategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ServiceResultEnum.GOODS_CATEGORY_ERROR.getResult();
        }
        if (goodsInfoDao.queryByCategoryIdAndName(goodsInfo.getGoodsCategoryId(), goodsInfo.getGoodsName()) != null) {
            return ServiceResultEnum.SAME_GOODS_EXIST.getResult();
        }
        if (goodsInfoDao.insert(goodsInfo) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    /**
     * 修改数据
     *
     * @param goodsInfo 实例对象
     * @return 实例对象
     */
    @Override
    public String update(GoodsInfo goodsInfo) {
        GoodsCategory goodsCategory = goodsCategoryDao.queryById(goodsInfo.getGoodsCategoryId());
        if (goodsCategory == null || goodsCategory.getCategoryLevel().intValue() != CategoryLevelEnum.LEVEL_THREE.getLevel()) {
            return ServiceResultEnum.GOODS_CATEGORY_ERROR.getResult();
        }
        GoodsInfo temp = goodsInfoDao.queryById(goodsInfo.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        GoodsInfo temp2 = goodsInfoDao.queryByCategoryIdAndName(goodsInfo.getGoodsCategoryId(), goodsInfo.getGoodsName());
        if (temp2 != null && !temp2.getGoodsId().equals(goodsInfo.getGoodsId())) {
            return ServiceResultEnum.SAME_GOODS_EXIST.getResult();
        }
        goodsInfo.setUpdateTime(new Date());
        if (goodsInfoDao.update(goodsInfo) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Boolean BatchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsInfoDao.batchUpdateSellStatus(ids, sellStatus)>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object goodsId) {
        return this.goodsInfoDao.deleteById(goodsId) > 0;
    }
}
