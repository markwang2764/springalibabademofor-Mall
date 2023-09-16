package goods.service.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-09-16 21:17
 **/
@Data
public class GoodsDTO implements Serializable {
    /**
     * 商品表主键id
     */
    private Object goodsId;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品简介
     */
    private String goodsIntro;
    /**
     * 关联分类id
     */
    private Long goodsCategoryId;
    /**
     * 商品主图
     */
    private String goodsCoverImg;
    /**
     * 商品轮播图
     */
    private String goodsCarousel;
    /**
     * 商品详情
     */
    private String goodsDetailContent;
    /**
     * 商品价格
     */
    private Integer originalPrice;
    /**
     * 商品实际售价
     */
    private Integer sellingPrice;
    /**
     * 商品库存数量
     */
    private Integer stockNum;
    /**
     * 商品标签
     */
    private String tag;
    /**
     * 商品上架状态 1-下架 0-上架
     */
    private Integer goodsSellStatus;
}
