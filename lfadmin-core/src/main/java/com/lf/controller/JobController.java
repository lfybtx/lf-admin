package com.lf.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lf.entity.Job;
import com.lf.entity.vo.JobQueryVo;
import com.lf.service.JobService;
import com.lf.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lfybtx
 * @since 2024-08-03
 */
@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 分页查询岗位列表
     *
     * @param jobQueryVo
     * @return
     */
    @GetMapping("/list")
    public Result list(JobQueryVo jobQueryVo) {
        //创建分页对象
        IPage<Job> page = new Page<Job>
                (jobQueryVo.getPageNo(), jobQueryVo.getPageSize());
        //调用分页查询方法
        jobService.findListJob(page, jobQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * 添加岗位
     *
     * @param job
     * @return
     **/

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:job:add')")
    public Result add(@RequestBody Job job) {
        if (jobService.save(job)) {
            return Result.ok().message("岗位添加成功");
        }
        return Result.error().message("岗位添加失败");
    }

    /**
     * 修改角色
     *
     * @param job
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('sys:job:edit')")
    public Result update(@RequestBody Job job) {
        if (jobService.updateById(job)) {
            return Result.ok().message("岗位修改成功");
        }
        return Result.error().message("岗位修改失败");
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    public Result delete(@PathVariable Long id) {
        List<Long> userIdByJobId = jobService.findUserIdByJobId(id);
        if (userIdByJobId.size()>0){
            return Result.error().message("当前岗位存在用户，无法删除");
        }

        if (jobService.removeById(id)) {
            return Result.ok().message("岗位删除成功");
        }
        return Result.error().message("岗位删除失败");
    }

}

