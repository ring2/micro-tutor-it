package xyz.ring2.admin.portal.service;

import xyz.ring2.admin.portal.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ring2.admin.common.RestResult;
import xyz.ring2.admin.portal.entity.dto.CourseDTO;
import xyz.ring2.admin.common.QueryParam;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ring2
 * @since 2020-05-24
 */
public interface ICourseService extends IService<Course> {

 /**
  * 创建实体
  *
  * @param object 前端接收的实体DTO
  * @return
  */
  RestResult create(CourseDTO object);

 /**
  * 删除实体
  *
  * @param id 需要删除的主键id
  * @return
  */
  RestResult delete(Integer id);

 /**
  * 修改实体
  *
  * @param object 前端接收的实体DTO
  * @return
  */
  RestResult update(CourseDTO object);

 /**
   * 分页查询某实体
  * @param queryParam 分页条件及相关参数
  * @return
  */
  RestResult listByPage(QueryParam queryParam);
}
