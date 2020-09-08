package com.lz.read.controller;

import com.lz.read.common.RestResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author :     lz
 * @date :       2020/4/2 08:51
 * description:  调查问卷
 **/
@RestController
@RequestMapping("/paper")
public class PaperController {
    @GetMapping()
    public RestResult get() {

        return new RestResult();
    }

    @PutMapping()
    public RestResult update() {

        return new RestResult();
    }

    @DeleteMapping()
    public RestResult del() {

        return new RestResult();
    }

    @PostMapping()
    public RestResult cre() {

        return new RestResult();
    }
}
