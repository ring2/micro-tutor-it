package com.lz.read.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.read.common.RestResult;
import com.lz.read.pojo.Announcement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.lz.read.dao.AnnouncementMapper;
import com.lz.read.service.AnnouncementService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author :     lz
 * @date :       2020/4/1 23:55
 * description:
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class AnnouncementServiceImpl implements AnnouncementService {

    @Resource
    private AnnouncementMapper announcementMapper;

    @Override
    public RestResult addAnnouncement(Announcement announcement) {
        if (ObjectUtil.isNotEmpty(announcement)) {
            announcement.setAnnoPublishtime(new Date());
            int i = announcementMapper.insertSelective(announcement);
            if (i > 0) {
                return RestResult.success();
            }
            return RestResult.failure("公告添加失败");
        }
        return RestResult.failureOfParam();
    }

    // 假删
    @Override
    public RestResult delAnnouncement(Integer id) {
        if (id != null) {
            Announcement announcement = announcementMapper.selectByPrimaryKey(id);
            Byte del = 1;
            announcement.setAnnoDelete(del);
            int i = announcementMapper.updateByPrimaryKeySelective(announcement);
            if (i > 0)
                return RestResult.success();
            else
                return RestResult.failure("删除公告失败");
        }
        return RestResult.failureOfParam();
    }

    @Override
    public RestResult listAnnouncement(String title, int pageNum, int pageSize) {
        if (title.equals(" ")) {
            title = null;
        }
        Example example = new Example(Announcement.class);
        PageHelper.startPage(pageNum, pageSize);
        if (StrUtil.isNotEmpty(title)) {
            example.createCriteria().andEqualTo("annoDelete", 0).andLike("annoTitle", "%" + title + "%");
        } else {
            example.createCriteria().andEqualTo("annoDelete", 0);
        }
        List<Announcement> announcements = announcementMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(announcements);
        return RestResult.success(pageInfo);
    }

    @Override
    public RestResult updateAnnouncement(Announcement announcement) {
        if (ObjectUtil.isNotEmpty(announcement)) {
            int i = announcementMapper.updateByPrimaryKeySelective(announcement);
            if (i > 0) {
                return RestResult.success();
            }
            return RestResult.failure("公告修改失败");
        }
        return RestResult.failureOfParam();
    }
}
