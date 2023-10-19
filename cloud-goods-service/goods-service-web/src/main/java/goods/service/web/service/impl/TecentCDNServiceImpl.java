//package goods.service.web.service.impl;
//
//import cloud.common.springcloud.dto.ResultGenerator;
//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.ClientConfig;
//import com.qcloud.cos.auth.BasicCOSCredentials;
//import com.qcloud.cos.auth.COSCredentials;
//import com.qcloud.cos.model.PutObjectRequest;
//import com.qcloud.cos.model.PutObjectResult;
//import com.qcloud.cos.region.Region;
//import goods.service.web.service.TencentCDNService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpSession;
//import java.io.File;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.UUID;
//
///**
// * @program: SpringCloudAlibabaDemo
// * @description:
// * @author: Mr.markWang 2764
// * @create: 2023-10-17 17:55
// **/
//@Service
//public class TecentCDNServiceImpl implements TencentCDNService {
//    @Value("${cos.SecretID}")
//    private String accessKey;
//    @Value("${cos.SecretKey}")
//    private String secretKey;
//    @Value("${cos.regionName}")
//    private String regionName;
//    @Value("${cos.bucketName}")
//    private String bucketName;
//    @Value("${cos.path}")
//    private String path;
//    @Override
//    public String uploadImages(MultipartFile file) {
//        String oldFileName = file.getOriginalFilename();
//        assert oldFileName != null;
//        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
//        String newFileName = UUID.randomUUID()+eName;
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DATE);
//        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
//        ClientConfig clientConfig = new ClientConfig(new Region(regionName));
//        COSClient cosClient = new COSClient(cred, clientConfig);
//        String bucketName = this.bucketName;
//        File localFile = null;
//
//        try {
//            localFile = File.createTempFile("temp", null);
//            file.transferTo(localFile);
//            String key = "/" + year + "/" + month + "/" + day + "/" + newFileName;
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
//            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
//            return this.path + putObjectRequest.getKey();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            cosClient.shutdown();
//        }
//    }
//}
