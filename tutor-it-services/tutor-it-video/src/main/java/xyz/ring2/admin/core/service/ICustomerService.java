package xyz.ring2.admin.core.service;

import xyz.ring2.admin.core.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ring2.admin.common.RestResult;
import xyz.ring2.admin.core.entity.dto.CustomerDTO;
import xyz.ring2.admin.common.QueryParam;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ring2
 * @since 2020-05-21
 */
public interface ICustomerService extends IService<Customer> {

 /**
  * 创建实体
  *
  * @param object 前端接收的实体DTO
  * @return
  */
  RestResult create(CustomerDTO object);

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
  RestResult update(CustomerDTO object);

 /**
   * 分页查询某实体
  * @param queryParam 分页条件及相关参数
  * @return
  */
  RestResult listByPage(QueryParam queryParam);

 RestResult register(String username, String password);

 RestResult validUser(String username, String password);
}
