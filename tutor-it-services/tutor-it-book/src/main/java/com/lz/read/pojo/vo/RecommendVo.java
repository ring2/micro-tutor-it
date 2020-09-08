package com.lz.read.pojo.vo;

import com.lz.read.pojo.Recommend;
import lombok.Data;

/**
 * @author :     lz
 * @date :       2020/4/15 11:00
 * description:
 **/
@Data
public class RecommendVo extends Recommend {
    private Integer rank;
    private String bookname;
}
