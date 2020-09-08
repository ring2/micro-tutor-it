package com.lz.read.controller;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Expert;
import com.lz.read.service.AdministratorService;
import com.lz.read.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :     lz
 * @date :       2020/4/2 08:45
 * description:  专家注册接口
 **/
@RestController
@RequestMapping("/register")
public class RegisterController {


    @Autowired
    ExpertService expertService;

    @PostMapping
    public RestResult cre(@RequestBody Expert expert) {
        return expertService.registerExpert(expert);
    }
}
