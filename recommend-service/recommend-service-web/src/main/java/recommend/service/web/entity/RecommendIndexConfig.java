package recommend.service.web.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (RecommendIndexConfig)实体类
 *
 * @author makejava
 * @since 2023-09-17 23:41:50
 */
public class RecommendIndexConfig implements Serializable {
    private static final long serialVersionUID = -29687100518516009L;
    /**
     * 首页配置项主键id
     */
    private Long configId;
    /**
     * 显示字符(配置搜索时不可为空，其他可为空)
     */
    private String configName;
    /**
     * 1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐
     */
    private Integer configType;
    /**
     * 商品id 默认为0
     */
    private Long goodsId;
    /**
     * 点击后的跳转地址(默认不跳转)
     */
    private String redirectUrl;
    /**
     * 排序值(字段越大越靠前)
     */
    private Integer configRank;
    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    private Integer isDeleted;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者id
     */
    private Integer createUser;
    /**
     * 最新修改时间
     */
    private Date updateTime;
    /**
     * 修改者id
     */
    private Integer updateUser;


    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Integer getConfigType() {
        return configType;
    }

    public void setConfigType(Integer configType) {
        this.configType = configType;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Integer getConfigRank() {
        return configRank;
    }

    public void setConfigRank(Integer configRank) {
        this.configRank = configRank;
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

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

}

