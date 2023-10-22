package recommend.service.web.controller.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-18 20:24
 **/
@Data
public class IndexConfigAddParam {
    @ApiModelProperty("配置项名称")
    @NotEmpty(message = "configName不能为空")
    private String configName;
    @ApiModelProperty("配置类别")
    @NotNull(message = "configType不能为空")
    @Min(value = 1, message = "configType最小值为1")
    @Max(value= 5, message = "configType最大值为5")
    private Integer configType;
    @ApiModelProperty("商品id")
    @NotNull(message = "商品id不能为空")
    @Min(value =1, message = "商品id不能为空")
    private Long goodsId;
    @ApiModelProperty("排序值")
    @Min(value = 1, message = "configRank最低为1")
    @Max(value = 200, message = "configRank最高为200")
    @NotNull(message = "configRank不能为空")
    private Integer configRank;
}
