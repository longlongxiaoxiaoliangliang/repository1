package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.Interface.AuthorizedProjectManagerService;
import com.prolog.user.model.PlgFxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "项目经理授权")
@RestController
@RequestMapping("/authorizedProjectManager")
public class AuthorizedProjectManagerController {

    @Autowired
    private AuthorizedProjectManagerService authorizedProjectManagerService;

    @ApiOperation(value = "查询用户或已授权项目经理", notes = "查询用户或已授权项目经理")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "按用户ID或者姓名模糊查询",required = false),
            @ApiImplicitParam(name = "projectId", value = "项目编号", dataType = "string",required = true),
            @ApiImplicitParam(name = "flg", value = "未授权（0）/已授权（1）", dataType = "string",required = true),
            @ApiImplicitParam(name = "pageNum", value = "起始页", dataType = "int",required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int",required = true)})
    @PostMapping("/queryAllUsersData")
    public RestMessage<Page<PlgFxUser>> queryAllUsersData(@RequestParam(value = "search", required = false) String search,
                                                            @RequestParam(value = "projectId") String projectId,
                                                            @RequestParam(value = "flg") String flg,
                                                            @RequestParam(value = "pageNum") int pageNum,
                                                            @RequestParam(value = "pageSize") int pageSize) {
        Page<PlgFxUser> data = authorizedProjectManagerService.queryAllUsersData(search, projectId, pageNum, pageSize, flg);
        return RestMessage.newInstance(true, "获取成功！", data);
    }


    @ApiOperation(value = "批量保存项目授权项目经理信息", notes = "批量保存项目授权项目经理信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "list", value = "[{project_no:项目编号,project_name:项目名称,project_manager：项目经理ID,project_manager_name：项目经理姓名,project_manager_dept：所在部门}]"),
                       @ApiImplicitParam(name = "projectId", value = "项目编号", dataType = "string",required = true)})
    @PostMapping("/addList")
    public RestMessage<String> addByList(@RequestBody List<Map<String,Object>> list,@RequestParam(value = "project_no") String project_no) throws Exception {
        int addFlag = authorizedProjectManagerService.addList(list,project_no);
        return RestMessage.newInstance(true, "保存成功！", String.valueOf(addFlag));
    }

    @ApiOperation(value = "所有项目查询", notes = "所有项目查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "按名称查询", dataType = "string"),
                        @ApiImplicitParam(name = "flg", value = "项目类型（1是售前项目/2是实施项目/3是内部项目）", dataType = "string",required = true),
                        @ApiImplicitParam(name = "pageNum", value = "起始页", dataType = "int",required = true),
                        @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int",required = true)})
    @PostMapping("/queryAllProjects")
    public RestMessage<Page<Map<String, Object>>> queryAllProjects(@RequestParam(value = "search", required = false) String search,
                                                                   @RequestParam(value = "flg", required = true) String flg,
                                                                   @RequestParam(value = "pageNum") int pageNum,
                                                                   @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> list = authorizedProjectManagerService.queryProjectDefineData(search, flg,pageNum,pageSize);
        return RestMessage.newInstance(true, "查询成功！", list);
    }
}
