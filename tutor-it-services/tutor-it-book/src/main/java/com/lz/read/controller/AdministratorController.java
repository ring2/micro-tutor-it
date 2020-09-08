package com.lz.read.controller;

import cn.hutool.core.util.ObjectUtil;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.Administrator;
import com.lz.read.pojo.dto.AdministratorDTO;
import com.lz.read.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :     lz
 * @date :       2020/4/2 08:28
 * description:  管理员相关接口
 **/
@RestController
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    AdministratorService administratorService;

    @GetMapping()
    public RestResult get() {
        return new RestResult();
    }

    @PutMapping("update_pwd")
    public RestResult updatePwd(@RequestBody AdministratorDTO administrator) {
        if (ObjectUtil.isNotEmpty(administrator)) {
            String adminName = administrator.getAdminName();
            String adminPwd = administrator.getAdminPwd();
            String newPwd = administrator.getNewPwd();
            return administratorService.updateAdminPwd(adminName, adminPwd, newPwd);
        }
        return RestResult.failureOfParam();
    }

    @PutMapping
    public RestResult updateAdmin(Administrator administrator) {
        return administratorService.updateAdmin(administrator);
    }

    @GetMapping("/{adminName}/{adminPwd}")
    public RestResult selAdmin(@PathVariable String adminName, @PathVariable String adminPwd) {
        Administrator administrator = administratorService.selectAdminByNameAndPwd(adminName, adminPwd);
        return RestResult.success(administrator);
    }
}
