package goods.service.web.controller.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-17 18:51
 **/
@Data
public class UploadImageParam {
    @ApiModelProperty("图片")
    @NotNull(message = "图片不能为空")
    private MultipartFile image;
}
