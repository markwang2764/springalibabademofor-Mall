package goods.service.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (GoodsCategory)实体类
 *
 * @author makejava
 * @since 2023-09-10 15:31:40
 */
@Data
public class GoodsCategory implements Serializable {
    private static final long serialVersionUID = -66627131733776916L;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 分类级别(1-一级分类 2-二级分类 3-三级分类)
     */
    private Byte categoryLevel;
    /**
     * 父分类id
     */
    private Long parentId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 排序值(字段越大越靠前)
     */
    private Integer categoryRank;
    /**
     * 删除标识字段(0-未删除 1-已删除)
     */
    private Byte isDeleted;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者id
     */
    private Integer createUser;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd: HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 修改者id
     */
    private Integer updateUser;
}

