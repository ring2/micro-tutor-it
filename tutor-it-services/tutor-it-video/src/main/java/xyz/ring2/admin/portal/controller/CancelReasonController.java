package xyz.ring2.admin.portal.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import xyz.ring2.admin.common.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import xyz.ring2.admin.portal.entity.dto.CancelReasonDTO;
import xyz.ring2.admin.common.QueryParam;
import xyz.ring2.admin.portal.service.ICancelReasonService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ring2
 * @since 2020-05-24
 */
@RestController
@Slf4j
@RequestMapping("/portal/cancelReason")
public class CancelReasonController {

    @Autowired
    ICancelReasonService baseCancelReasonService;

   /**
    *  新增
    */
    @PostMapping
    public RestResult create(@RequestBody @Valid CancelReasonDTO object) {
        return baseCancelReasonService.create(object);
    }

   /**
    *  删除
    */
    @DeleteMapping("/{id}")
    public RestResult delete(@PathVariable Integer id) {
        return baseCancelReasonService.delete(id);
    }

   /**
    *  修改
    */
    @PutMapping
    public RestResult update(@RequestBody @Valid CancelReasonDTO object) {
        return baseCancelReasonService.update(object);
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
        return baseCancelReasonService.listByPage(queryParam);
    }
}
