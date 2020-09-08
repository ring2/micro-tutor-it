package tutor.it.generator.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import tutor.it.common.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import tutor.it.generator.entity.dto.OrderDTO;
import tutor.it.common.QueryParam;
import tutor.it.generator.service.IOrderService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ring2
 * @since 2020-05-25
 */
@RestController
@Slf4j
@RequestMapping("/generator/order")
public class OrderController {

    @Autowired
    IOrderService baseOrderService;

   /**
    *  新增
    */
    @PostMapping
    public RestResult create(@RequestBody @Valid OrderDTO object) {
        return baseOrderService.create(object);
    }

   /**
    *  删除
    */
    @DeleteMapping("/{id}")
    public RestResult delete(@PathVariable Integer id) {
        return baseOrderService.delete(id);
    }

   /**
    *  修改
    */
    @PutMapping
    public RestResult update(@RequestBody @Valid OrderDTO object) {
        return baseOrderService.update(object);
    }

   /**
    *  分页查询
    */
    @GetMapping("/list")
    public RestResult listByPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String keyword) {
        QueryParam queryParam = new QueryParam();
        queryParam.setPageNum(pageNum);
        queryParam.setPageSize(pageSize);
        queryParam.setKeyword(keyword);
        return baseOrderService.listByPage(queryParam);
    }

    @GetMapping("/apriori_analysis")
    public RestResult apriori_analysis() {
        return baseOrderService.getApriori_analysis();
    }
}
