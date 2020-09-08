package tutor.it.generator.service;

import tutor.it.generator.entity.ShopCar;
import com.baomidou.mybatisplus.extension.service.IService;
import tutor.it.common.RestResult;
import tutor.it.generator.entity.dto.ShopCarDTO;
import tutor.it.common.QueryParam;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ring2
 * @since 2020-05-25
 */
public interface IShopCarService extends IService<ShopCar> {

 /**
  * 创建实体
  *
  * @param object 前端接收的实体DTO
  * @return
  */
  RestResult create(ShopCarDTO object);

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
  RestResult update(ShopCarDTO object);

 /**
   * 分页查询某实体
  * @param queryParam 分页条件及相关参数
  * @return
  */
  RestResult listByPage(QueryParam queryParam);
}
