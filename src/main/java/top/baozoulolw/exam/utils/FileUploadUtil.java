package top.baozoulolw.exam.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;


@Component
public class FileUploadUtil {

    @Value("${aliyun.oss.end-point}")
    private String endpoint;

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
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delFile(String fileName) {
        ossClient.deleteObject(bucketName,fileName);
    }

    public String getPreviewUrl(String fileName){
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
}

