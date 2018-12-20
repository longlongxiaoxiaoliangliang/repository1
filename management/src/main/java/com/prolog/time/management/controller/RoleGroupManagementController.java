package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.resources.ISysRoleGroupResource;
import com.prolog.time.management.util.MapUtil;
//import com.prolog.user.model.PlgFxRolegroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "角色组管理")
@RestController
@RequestMapping("/rolegroup")
public class RoleGroupManagementController {

    @Autowired
    private ISysRoleGroupResource iSysRoleGroupResource;

    @ApiOperation(value = "分配角色", notes = "分配角色")
    @ApiImplicitParam(name = "params", value = "{type:'0取消/1分配',rolegroupid:'角色组id',roleids:'角色id1,角色id2'}")
    @PostMapping("/allotRoleGroup")
    public RestMessage<String> allotRoleGroup(@RequestBody Map<String, Object> params) {
        String data = iSysRoleGroupResource.allotRoleGroup(params).getData();
        return RestMessage.newInstance(true, "分配成功！", data);
    }

    @ApiOperation(value = "删除角色组", notes = "删除角色组")
    @ApiImplicitParam(name = "id", value = "角色组ID")
    @PostMapping("/deletePlgFxRolegroup")
    public RestMessage<String> deletePlgFxRolegroup(@RequestParam(value = "id") String id) {
        String data = iSysRoleGroupResource.deletePlgFxRolegroup(id).getData();
        return RestMessage.newInstance(true, "删除成功！", data);
    }

    @ApiOperation(value = "获取角色分配组信息", notes = "获取角色分配组信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "角色名称", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "是否分配(0未分配/1已分配)", dataType = "string", required = true),
            @ApiImplicitParam(name = "rolegroupid", value = "角色组ID", dataType = "string", required = true)})
    @PostMapping("/getAllocatedRole")
    public RestMessage<Page<Map<String, Object>>> getAllocatedRole(@RequestParam(value = "name", required = false) String name,
                                                                   @RequestParam(value = "type") String type,
                                                                   @RequestParam(value = "rolegroupid") String rolegroupid,
                                                                   @RequestParam(value = "pageNum") int pageNum,
                                                                   @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = iSysRoleGroupResource.getAllocatedRole(name, type, rolegroupid, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

/*    @ApiOperation(value = "通过ID获取角色组信息", notes = "通过ID获取角色组信息")
    @ApiImplicitParam(name = "id", value = "角色组ID")
    @GetMapping("/getPlgFxRolegroupById")
    public RestMessage<PlgFxRolegroup> getPlgFxRolegroupById(@RequestParam(value = "id") String id) {
        PlgFxRolegroup data = iSysRoleGroupResource.getPlgFxRolegroupById(id).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "角色组信息分页查询", notes = "角色组信息分页查询")
    @ApiImplicitParam(name = "name", value = "角色组名称")
    @PostMapping("/getPlgFxRolegroupPage")
    public RestMessage<Page<PlgFxRolegroup>> getPlgFxRolegroupPage(@RequestParam(value = "name", required = false) String name,
                                                                   @RequestParam(value = "pageNum") int pageNum,
                                                                   @RequestParam(value = "pageSize") int pageSize) {
        Page<PlgFxRolegroup> data = iSysRoleGroupResource.getPlgFxRolegroupPage(name, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "添加角色组", notes = "添加角色组")
    @PostMapping("/insertPlgFxRolegroup")
    public RestMessage<String> insertPlgFxRolegroup(PlgFxRolegroup plgFxRolegroup) {
        Map<String, Object> plgFxRolegroup1 = MapUtil.beanToMap(plgFxRolegroup);
        RestMessage<String> allData = iSysRoleGroupResource.insertPlgFxRolegroup(plgFxRolegroup1);
        String data = allData.getData();
        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "添加成功！", data);
    }

    @ApiOperation(value = "修改角色信息组", notes = "修改角色信息组")
    @PutMapping("/updatePlgFxRolegroup")
    public RestMessage<String> updatePlgFxRolegroup(PlgFxRolegroup plgFxRolegroup) {
        Map<String, Object> plgFxRolegroup1 = MapUtil.beanToMap(plgFxRolegroup);
        RestMessage<String> allData = iSysRoleGroupResource.insertPlgFxRolegroup(plgFxRolegroup1);
        String data = allData.getData();
        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "修改成功！", data);
    }*/
}
