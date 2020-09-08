package com.lz.read.controller;

import cn.hutool.core.util.ObjectUtil;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.Expert;
import com.lz.read.pojo.dto.AdministratorDTO;
import com.lz.read.pojo.dto.ExpertDTO;
import com.lz.read.service.ExpertService;
import com.lz.read.service.ExperttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :     lz
 * @date :       2020/4/2 08:27
 * description:   专家
 **/
@RestController
@RequestMapping("/expert")
public class ExpertController {

    @Autowired
    ExpertService expertService;

    @Autowired
    ExperttypeService experttypeService;

    @GetMapping("/list/{type}/{pageNum}/{pageSize}")
    public RestResult get(@PathVariable Integer type, @PathVariable int pageNum, @PathVariable int pageSize) {
        return expertService.listExpert(type, pageNum, pageSize);
    }

    @PutMapping("update_pwd")
    public RestResult updatePwd(@RequestBody ExpertDTO expert) {
        if (ObjectUtil.isNotEmpty(expert)) {
            String exUsername = expert.getExUsername();
            String exPwd = expert.getExPwd();
            String newPwd = expert.getNewPwd();
            return expertService.updateAdminPwd(exUsername, exPwd, newPwd);
        }
        return RestResult.failureOfParam();
    }

    @PutMapping
    public RestResult update(@RequestBody Expert expert) {
        return expertService.updateExpert(expert);
    }

    @DeleteMapping("/{id}")
    public RestResult del(@PathVariable Integer id) {
        return expertService.delExpert(id);
    }

    @GetMapping("/expert_type")
    public RestResult getExpertType() {
        return experttypeService.getExpertType();
    }

    @GetMapping("/{id}")
    public RestResult getExpert(@PathVariable Integer id) {
        return expertService.getExpertById(id);
    }
}
