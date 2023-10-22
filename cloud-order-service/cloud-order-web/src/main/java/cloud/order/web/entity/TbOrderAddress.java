package cloud.order.web.entity;

import java.io.Serializable;

/**
 * 订单收货地址关联表(TbOrderAddress)实体类
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
public class TbOrderAddress implements Serializable {
    private static final long serialVersionUID = -78940221579020567L;

    private Long orderId;
    /**
     * 收货人姓名
     */
    private String userName;
    /**
     * 收货人手机号
     */
    private String userPhone;
    /**
     * 省
     */
    private String provinceName;
    /**
     * 城
     */
    private String cityName;
    /**
     * 区
     */
    private String regionName;
    /**
     * 收件详细地址(街道/楼宇/单元)
     */
    private String detailAddress;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

}

