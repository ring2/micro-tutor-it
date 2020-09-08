package xyz.ring2.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import xyz.ring2.admin.common.RestResult;
import xyz.ring2.admin.feign.fallback.MinioServiceFallBack;

/**
 * @author :     ring2
 * @date :       2020/5/25 09:44
 * description:
 **/
@FeignClient(value = "tutor-media",fallback = MinioServiceFallBack.class)
public interface MinioService {

    @PostMapping(value = "/upload/img", headers = {"content-type=multipart/form-data"})
    public RestResult uploadImage(MultipartFile file);
}
