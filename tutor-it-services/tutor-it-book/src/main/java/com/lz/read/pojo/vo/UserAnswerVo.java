package com.lz.read.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/16 19:10
 * description:  构造用户回答的bean
 **/
@Data
public class UserAnswerVo {
    private List<AnswerVo> data;
}
