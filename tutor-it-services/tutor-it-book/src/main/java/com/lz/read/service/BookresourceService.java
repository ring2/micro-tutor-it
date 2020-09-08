package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Bookresource;

/**
    * @author :     lz
    * @date :       2020/4/1 23:55
    * description:  
 **/
  
public interface BookresourceService{


    public RestResult addBookResource(Bookresource bookresource);

    public RestResult delBookResource(Integer id);

    public RestResult updateBookResource(Bookresource bookresource);

    public RestResult listBookResource(Byte isCharge);
}
