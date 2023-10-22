package cloud.order.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TbOrderItem)实体类
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
public class TbOrderItem implements Serializable {
    private static final long serialVersionUID = 261764798728564111L;
    /**
     * 订单关联购物项主键id
     */
    private Long orderItemId;
    /**
     * 订单主键id
     */
    private Long orderId;
    /**
     * 关联商品id
     */
    private Long goodsId;
    /**
     * 下单时商品的名称(订单快照)
     */
    private String goodsName;
    /**
     * 下单时商品的主图(订单快照)
     */
    private String goodsCoverImg;
    /**
     * 下单时商品的价格(订单快照)
     */
    private Integer sellingPrice;
    /**
     * 数量(订单快照)
     */
    private Integer goodsCount;
    /**
     * 创建时间
     */
    private Date createTime;


    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCoverImg() {
        return goodsCoverImg;
    }

    public void setGoodsCoverImg(String goodsCoverImg) {
        this.goodsCoverImg = goodsCoverImg;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

