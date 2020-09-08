package com.lz.read.pojo.dto;

import com.lz.read.pojo.Administrator;
import lombok.Data;

/**
 * @author :     lz
 * @date :       2020/4/3 19:58
 * description:
 **/
@Data
public class AdministratorDTO extends Administrator {
    private String newPwd;
}
