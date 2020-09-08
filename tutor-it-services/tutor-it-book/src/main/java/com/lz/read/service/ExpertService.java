package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Expert;

/**
    * @author :     lz
    * @date :       2020/4/1 23:55
    * description:  
 **/
  
public interface ExpertService{

    public Expert selectExpertByNameAndPwd(String username, String password);

    RestResult getExpertById(Integer id);

    public RestResult delExpert(Integer id);

    RestResult updateAdminPwd(String adminName,String originPwd,String newPwd);

    public RestResult updateExpert(Expert expert);

    public RestResult listExpert(Integer type,int pageNum,int pageSize);

    public RestResult registerExpert(Expert expert);





}
