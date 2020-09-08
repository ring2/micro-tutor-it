package com.lz.read.service;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.Announcement;

/**
    * @author :     lz
    * @date :       2020/4/1 23:55
    * description:  
 **/
  
public interface AnnouncementService{

    /**
     *
     * @param announcement  要添加的公告
     * @return 添加公告
     */
    public RestResult addAnnouncement(Announcement announcement);

    // 删除公告
    public RestResult delAnnouncement(Integer id);

    // 查询公告
    public RestResult listAnnouncement(String title,int pageNum,int pageSize);

    //修改公告
    public RestResult updateAnnouncement(Announcement announcement);
}
