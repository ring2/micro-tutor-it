package xyz.ring2.admin.feign.fallback;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.ring2.admin.common.RestResult;
import xyz.ring2.admin.feign.MinioService;

/**
 * @author :     ring2
 * @date :       2020/5/25 09:46
 * description:
 **/
@Service
public class MinioServiceFallBack implements MinioService {
    @Override
    public RestResult uploadImage(MultipartFile file) {
        return RestResult.failure();
    }
}
