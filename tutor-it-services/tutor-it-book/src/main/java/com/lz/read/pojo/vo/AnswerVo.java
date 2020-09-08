package com.lz.read.pojo.vo;

import com.lz.read.pojo.Answer;
import lombok.Data;

import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/16 19:11
 * description:
 **/
@Data
public class AnswerVo {
    private String title;
    private List<ContentVo>  answer;
}
