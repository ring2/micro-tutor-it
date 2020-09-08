package xyz.ring2.admin.portal.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import xyz.ring2.admin.common.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import xyz.ring2.admin.portal.entity.dto.CourseChapterDTO;
import xyz.ring2.admin.common.QueryParam;
import xyz.ring2.admin.portal.service.ICourseChapterService;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程章节 前端控制器
 * </p>
 *
 * @author ring2
 * @since 2020-05-24
 */
@RestController
@Slf4j
@RequestMapping("/portal/courseChapter")
public class CourseChapterController {

    @Autowired
    ICourseChapterService baseCourseChapterService;

   /**
    *  新增
    */
    @PostMapping
    public RestResult create(@RequestBody @Valid CourseChapterDTO object) {
        return baseCourseChapterService.create(object);
    }

   /**
    *  删除
    */
    @DeleteMapping("/{id}")
    public RestResult delete(@PathVariable Integer id) {
        return baseCourseChapterService.delete(id);
    }

   /**
    *  修改
    */
    @PutMapping
    public RestResult update(@RequestBody @Valid CourseChapterDTO object) {
        return baseCourseChapterService.update(object);
    }

   /**
    *  分页查询
    */
    @GetMapping("/list")
    public RestResult listByPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String keyword) {
        QueryParam queryParam = new QueryParam();
        queryParam.setPageNum(pageNum);
        queryParam.setPageSize(pageSize);
        queryParam.setKeyword(keyword);
        return baseCourseChapterService.listByPage(queryParam);
    }
}
