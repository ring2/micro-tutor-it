package com.lz.read.pojo.dto;

import com.lz.read.pojo.Expert;
import lombok.Data;

/**
 * @author :     lz
 * @date :       2020/4/5 06:53
 * description:
 **/
@Data
public class ExpertDTO extends Expert {
    private String newPwd;
}
