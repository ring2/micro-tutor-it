package xyz.ring2.admin.core.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import xyz.ring2.admin.common.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import xyz.ring2.admin.core.entity.dto.CustomerDTO;
import xyz.ring2.admin.common.QueryParam;
import xyz.ring2.admin.core.service.ICustomerService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ring2
 * @since 2020-05-21
 */
@RestController
@Slf4j
@RequestMapping("/core/customer")
public class CustomerController {

    @Autowired
    ICustomerService baseCustomerService;

   /**
    *  新增
    */
    @PostMapping
    public RestResult create(@RequestBody @Valid CustomerDTO object) {
        return baseCustomerService.create(object);
    }

   /**
    *  删除
    */
    @DeleteMapping("/{id}")
    public RestResult delete(@PathVariable Integer id) {
        return baseCustomerService.delete(id);
    }

   /**
    *  修改
    */
    @PutMapping
    public RestResult update(@RequestBody @Valid CustomerDTO object) {
        return baseCustomerService.update(object);
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
        return baseCustomerService.listByPage(queryParam);
    }

    @PostMapping("/login")
    public RestResult login(@RequestBody CustomerDTO customerDTO) {
        String username = customerDTO.getUsername();
        String password = customerDTO.getPassword();
        return baseCustomerService.validUser(username,password);
    }

    @PostMapping("/register")
    public RestResult register(@RequestBody CustomerDTO customerDTO) {
        String username = customerDTO.getUsername();
        String password = customerDTO.getPassword();
        return baseCustomerService.register(username,password);
    }
}
