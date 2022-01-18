package top.baozoulolw.exam.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;


@Component
public class FileUploadUtil {

    @Value("${aliyun.oss.end-point}")
    private String endpoint;

    @Value("${aliyun.oss.pre-point}")
    private String prePoint;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    @Resource
    private OSS ossClient;

    public String upload(MultipartFile file, String fileName) {
        // 写入文件
        try {
            //获取上传的输入流
            InputStream inputStream = file.getInputStream();

            //设置访问地址是默认是预览图片
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType("image/jpg");


                /*
                调用oss上传到阿里云对象存储oss----Bucket存储空间
                第一个参数 bucket名称
                第二个参数 文件名称
                第三个参数 输入流
                第四个参数 文件类型
                 */
            ossClient.putObject(bucketName, fileName, inputStream, meta);

            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            //ossClient.deleteObject(bucketName, "a/");


            //上传成功后获取文件路径
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delFile(String fileName) {
        ossClient.deleteObject(bucketName,fileName);
    }

    public String getPreviewUrl(String fileName){
        return "https://" + bucketName + "." + prePoint + "/" + fileName;
    }

    public String genFilename(){
        int first = new Random(10).nextInt(8) + 1;
        System.out.println(first);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        // 有可能是负数
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }

    /**
     * 创建桶
     *
     * @param ossClient  OSS连接
     * @param bucketName 桶名称
     * @return
     */
    public String createBucketName(OSS ossClient, String bucketName) {
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            return bucket.getName();
        }
        return bucketName;
    }

    /**
     * 删除桶
     *
     * @param ossClient  oss对象
     * @param bucketName 桶名称
     */
    public static void deleteBucket(OSS ossClient, String bucketName) {
        ossClient.deleteBucket(bucketName);
    }

    /**
     * 获得url链接
     *
     * @param bucketName 桶名称
     * @param key        Bucket下的文件的路径名+文件名 如："appversion/20200723/a3662009-897c-43ea-a6d8-466ab8310c6b.apk"
     * @return 图片链接：http://xxxxx.oss-cn-beijing.aliyuncs.com/test/headImage/1546404670068899.jpg?Expires=1861774699&OSSAccessKeyId=****=p%2BuzEEp%2F3JzcHzm%2FtAYA9U5JM4I%3D
     * （Expires=1861774699&OSSAccessKeyId=LTAISWCu15mkrjRw&Signature=p%2BuzEEp%2F3JzcHzm%2FtAYA9U5JM4I%3D 分别为：有前期、keyID、签名）
     */
    public String getUrl(String bucketName, String key) {
        // 设置URL过期时间为10年  3600L*1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        return url.toString().substring(0, url.toString().lastIndexOf("?"));
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileExtension 文件名扩展名
     * @return 文件的contentType
     */
    public String getContentType(String fileExtension) {
        // 文件的后缀名
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)
                || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        if (".mp4".equalsIgnoreCase(fileExtension)) {
            return "video/mp4";
        }
        // android
        if (".apk".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.android.package-archive";
        }
        // ios
        if (".ipa".equals(fileExtension)) {
            return "application/vnd.iphone";
        }
        // 默认返回类型
        return "application/octet-stream";
    }
}

