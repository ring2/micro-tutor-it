package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Administrator;

/**
    * @author :     lz
    * @date :       2020/4/1 23:55
    * description:  
 **/
  
public interface AdministratorService{

    public Administrator selectAdminByNameAndPwd(String username,String password);

    public RestResult updateAdminPwd(String adminName,String originPwd,String newPwd);

    public RestResult updateAdmin(Administrator administrator);


}
