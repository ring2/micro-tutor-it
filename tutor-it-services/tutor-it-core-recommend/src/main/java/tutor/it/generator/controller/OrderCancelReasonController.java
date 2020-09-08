package tutor.it.generator.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import tutor.it.common.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import tutor.it.generator.entity.dto.OrderCancelReasonDTO;
import tutor.it.common.QueryParam;
import tutor.it.generator.service.IOrderCancelReasonService;
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
@RequestMapping("/generator/orderCancelReason")
public class OrderCancelReasonController {

    @Autowired
    IOrderCancelReasonService baseOrderCancelReasonService;

   /**
    *  新增
    */
    @PostMapping
    public RestResult create(@RequestBody @Valid OrderCancelReasonDTO object) {
        return baseOrderCancelReasonService.create(object);
    }

   /**
    *  删除
    */
    @DeleteMapping("/{id}")
    public RestResult delete(@PathVariable Integer id) {
        return baseOrderCancelReasonService.delete(id);
    }

   /**
    *  修改
    */
    @PutMapping
    public RestResult update(@RequestBody @Valid OrderCancelReasonDTO object) {
        return baseOrderCancelReasonService.update(object);
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
        return baseOrderCancelReasonService.listByPage(queryParam);
    }
}
