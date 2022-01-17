package top.baozoulolw.exam.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public interface AliyunOssService {
    Map<String, Object> upload(MultipartFile file, String fileName);
}
