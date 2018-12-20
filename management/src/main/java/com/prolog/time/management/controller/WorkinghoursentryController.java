package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.model.dto.WorkinghoursEntryDto;
import com.prolog.time.management.Interface.WorkinghoursentryService;
import com.prolog.user.model.PlgFxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Api(tags = "个人工时录入")
@RestController
@RequestMapping("/entry")
public class WorkinghoursentryController {
    @Autowired
    private WorkinghoursentryService workinghoursentryService;

    @ApiOperation(value = "保存工时信息", notes = "根据页面传过来的参数保存工时信息")
    @PostMapping("/add")
    public RestMessage<String> add(@RequestBody WorkinghoursEntryDto workinghoursEntryDto) throws Exception {
               workinghoursentryService.add(workinghoursEntryDto);
            return RestMessage.newInstance(true, "保存成功！", workinghoursEntryDto.getProjectId());
    }

    @ApiOperation(value = "批量保存工时信息", notes = "根据页面传过来的参数保存工时信息")
    @PostMapping("/addList")
    public RestMessage<String> addByList(@RequestBody List<WorkinghoursEntryDto> workinghoursEntryDtos) throws Exception {
                workinghoursentryService.addList(workinghoursEntryDtos);
            return RestMessage.newInstance(true, "保存成功！", "");
    }

    @ApiOperation(value = "删除", notes = "可删除一条或多条记录")
    @PostMapping("/delete")
    public RestMessage<String> deleteByIds(String[] ids) throws Exception {
        List<String> idlists = new ArrayList<String>(Arrays.asList(ids));
        boolean deflag = workinghoursentryService.deleteByIds(idlists);
        return RestMessage.newInstance(true, "删除成功！", String.valueOf(idlists.size()));
    }

    @ApiOperation(value = "导出模板", notes = "固定模板")
    @PostMapping("/upload")
    public RestMessage<String> downloadExcel(HttpServletResponse res, HttpServletRequest req, String name) throws Exception {
        name = "个人工时录入模板";
        workinghoursentryService.downloadExcel(res, req, name);
        return RestMessage.newInstance(true, "导出模板成功！", "");

    }

    @ApiOperation(value = "导入模板", notes = "上传填好的excel模板")
    @PostMapping("/import")
    public RestMessage<String> importExcel(@RequestParam MultipartFile file) throws Exception {

        String data = workinghoursentryService.importExcel(file);
        return RestMessage.newInstance(true, "导入数据成功！", data);
    }

    @ApiOperation(value = "分页查询", notes = "工时信息分页查询")
    @ApiImplicitParam(name = "param", value = "{bgtime:'开始时间',edtime:'结束时间',strparam:'查询条件'}")
    @PostMapping("/query")
    public RestMessage<Page<Map<String, Object>>> queryWorkinghoursData(@RequestBody Map<String, Object> param) {
        Page<Map<String, Object>> page = workinghoursentryService.queryWorkinghoursData(param);
        return RestMessage.newInstance(true, "查询成功！", page);
    }

    @ApiOperation(value = "项目信息选择框分页查询", notes = "项目信息选择框分页查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "可按项目编号/项目名称查询", dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", required = true),
            @ApiImplicitParam(name = "pageSize", value = "总条数", dataType = "int", required = true)})
    @PostMapping("/queryProjectData")
    public RestMessage<Page<Map<String, Object>>> queryProjectData(@RequestParam(value = "search", required = false) String search,
                                                         @RequestParam(value = "pageNum") int pageNum,
                                                         @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = workinghoursentryService.queryProjectData(search, pageSize, pageNum);
        return RestMessage.newInstance(true, "查询成功！", data);
    }

    @ApiOperation(value = "修改工时录入信息", notes = "修改工时录入信息")
    @ApiImplicitParam(name = "param", value = "{all_project_id：项目编号 ，all_project_name：项目名称," +
            "project_type：项目类型,remark1：是否休息（是/否）,work_site：工作地点,beg_date：开始时间,end_date：结束时间,working_id：工时ID}")
    @PostMapping("/updateEntryData")
    public RestMessage<String> updateEntryData(@RequestBody Map<String, Object> param) {
        workinghoursentryService.updateEntryData(param);
        return RestMessage.newInstance(true, "修改成功！", "");
    }
}
