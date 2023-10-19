package goods.service.web.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-17 17:44
 **/
public interface TencentCDNService {
    public String uploadImages(MultipartFile file);
}
