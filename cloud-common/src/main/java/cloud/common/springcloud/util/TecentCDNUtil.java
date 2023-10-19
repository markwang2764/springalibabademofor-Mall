package cloud.common.springcloud.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

/**
 * @program: SpringCloudAlibabaDemo
 * @description:
 * @author: Mr.markWang 2764
 * @create: 2023-10-17 17:55
 **/
public class TecentCDNUtil {

    private static final String accessKey = "AKIDxKWUCesra3GDuKTLwHL9gz2pNgjX6QUI";
    private static final String secretKey = "bw1qQkocCZhaDcleHgohKhphJfmdEahY";
    private static final String bucketName = "springcloud-demo-1258844902";
    private static final String regionName = "ap-nanjing";
    private static final String path = "https://springcloud-demo-1258844902.cos.ap-nanjing.myqcloud.com";

    static public String uploadImages(MultipartFile file) {
        String oldFileName = file.getOriginalFilename();
        assert oldFileName != null;
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID()+eName;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
        COSClient cosClient = new COSClient(cred, clientConfig);
        File localFile = null;

        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            String key = "/" + year + "/" + month + "/" + day + "/" + newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return path + putObjectRequest.getKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            cosClient.shutdown();
        }
    }
}
