package cloud.order.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 收货地址表(TbOrderUserAddress)实体类
 *
 * @author makejava
 * @since 2023-10-21 13:09:23
 */
public class TbOrderUserAddress implements Serializable {
    private static final long serialVersionUID = 623423917140045865L;

    private Long addressId;
    /**
     * 用户主键id
     */
    private Long userId;
    /**
     * 收货人姓名
     */
    private String userName;
    /**
     * 收货人手机号
     */
    private String userPhone;
    /**
     * 是否为默认 0-非默认 1-是默认
     */
    private Integer defaultFlag;
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
    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    private Integer isDeleted;
    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;


    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}

