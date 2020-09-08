package xyz.ring2.tutormedia.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.ring2.tutormedia.pojo.FileInfo;

/**
 * @author :     weiquanquan
 * @date :       2020/3/11 10:55
 * description:
 **/
public interface FileInfoRepository extends MongoRepository<FileInfo,String> {
}
