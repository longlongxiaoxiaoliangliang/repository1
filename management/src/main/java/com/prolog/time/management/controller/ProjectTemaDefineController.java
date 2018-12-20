package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.Interface.NotauditedService;
import com.prolog.time.management.Interface.ProjectTemaDefineService;
import com.prolog.time.management.mapper.ProjectTemaDefineMapper;
import com.prolog.time.management.model.dto.ImpProjectInfoDto;
import com.prolog.user.model.PlgFxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "项目组定义")
@RestController
@RequestMapping("/projecttema")
public class ProjectTemaDefineController {
    @Autowired
    private ProjectTemaDefineService projectTemaDefineService;


    @ApiOperation(value = "项目查询", notes = "项目查询")
    @ApiImplicitParam(name = "search", value = "按名称查询", dataType = "string")
    @PostMapping("/queryproject")
    public RestMessage<List<Map<String, Object>>> queryProjectDefineData(@RequestParam(value = "search", required = false) String search) {
        List<Map<String, Object>> list = projectTemaDefineService.queryProjectDefineData(search);
        return RestMessage.newInstance(true, "查询成功！", list);
    }

    @ApiOperation(value = "选择项目后的左人员列表", notes = "选择项目后的左人员列表")
    @ApiImplicitParam(name = "projectno", value = "项目编号")
    @PostMapping("/queryLeftUsersData")
    public RestMessage<Page<PlgFxUser>> queryLeftUsersData(@RequestParam(value = "search", required = false) String search,
                                                           @RequestParam(value = "projectno") String projectno,
                                                           @RequestParam(value = "pageNum") int pageNum,
                                                           @RequestParam(value = "pageSize") int pageSize) {
        Page<PlgFxUser> data = projectTemaDefineService.queryLeftUsersData(search, projectno, pageNum, pageSize);
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "选择项目后当前项目人员查询", notes = "选择项目后当前项目人员查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "projectno", value = "项目编号",required = true),
       @ApiImplicitParam(name = "projectmanager", value = "项目经理ID", dataType = "string",required = true)})
    @PostMapping("/queryRightUsersData")
    public RestMessage<Page<Map<String, Object>>> queryRightUsersData(@RequestParam(value = "projectno") String projectno,
                                                                      @RequestParam(value = "projectmanager") String projectmanager,
                                                                     @RequestParam(value = "pageNum") int pageNum,
                                                                     @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = projectTemaDefineService.queryRightUsersData(projectno,projectmanager, pageNum, pageSize);
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "批量保存项目组定义信息", notes = "根据页面传过来的参数保存项目组定义信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "list", value = "[{project_no:项目编号,project_name:项目名称,project_manager：项目经理ID,project_user：项目人员ID,team_role：项目角色,project_manager_name：项目经理姓名,project_user_name：项目人员姓名,project_user_dept：所在部门}]"),
                        @ApiImplicitParam(name = "projectno", value = "项目编号",required = true)})
    @PostMapping("/addList")
    public RestMessage<String> addByList(@RequestBody List<Map<String,Object>> list,
                                         @RequestParam(value = "projectno") String projectno) throws Exception {
        int addFlag = projectTemaDefineService.addList(list,projectno);
        return RestMessage.newInstance(true, "保存成功！", String.valueOf(addFlag));
    }

}
