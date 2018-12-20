package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.resources.ISysRoleResource;
import com.prolog.time.management.util.MapUtil;
import com.prolog.user.model.PlgFxRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleManagementController {
    @Autowired
    private ISysRoleResource iSysRoleResource;

    @ApiOperation(value = "分配角色菜单资源", notes = "分配角色菜单资源")
    @ApiImplicitParam(name = "params", value = "{state:'0取消/1授权',roleid:'角色id',authid:'资源id'}")
    @PostMapping("/allotRoleMenuAuth")
    public RestMessage<Page<Map<String, Object>>> allotRoleMenuAuth(@RequestBody Map<String, Object> params) {
        Page<Map<String, Object>> page = iSysRoleResource.allotRoleMenuAuth(params).getData();
        return RestMessage.newInstance(true, "分配成功！", page);
    }

    @ApiOperation(value = "分配角色菜单", notes = "分配角色菜单")
    @ApiImplicitParam(name = "params", value = "{state:'0取消/1授权',roleid:'角色id',menuids:'菜单id1,菜单id2'}")
    @PostMapping("/allotRoleMenu")
    public RestMessage<Page<Map<String, Object>>> allotRoleMenu(@RequestBody Map<String, Object> params) {
        Page<Map<String, Object>> page = iSysRoleResource.allotRoleMenu(params).getData();
        return RestMessage.newInstance(true, "分配成功！", page);
    }

    @ApiOperation(value = "用户组分配角色", notes = "用户组分配角色")
    @ApiImplicitParam(name = "params", value = "{type:'0取消/1分配',roleid:'角色id',usergroupids:'用户组id1,用户组id2'}")
    @PostMapping("/allotUserRoleGroup")
    public RestMessage<Page<Map<String, Object>>> allotUserRoleGroup(@RequestBody Map<String, Object> params) {
        Page<Map<String, Object>> page = iSysRoleResource.allotUserRoleGroup(params).getData();
        return RestMessage.newInstance(true, "分配成功！", page);
    }

    @ApiOperation(value = "用户分配角色", notes = "用户分配角色")
    @ApiImplicitParam(name = "params", value = "{type:'0取消/1分配',roleid:'角色id',userids:'用户id1,用户id2'}")
    @PostMapping("/allotUserRole")
    public RestMessage<Page<Map<String, Object>>> allotUserRole(@RequestBody Map<String, Object> params) {
        Page<Map<String, Object>> page = iSysRoleResource.allotUserRole(params).getData();
        return RestMessage.newInstance(true, "分配成功！", page);
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParam(name = "id", value = "角色ID")
    @PostMapping("/deletePlgFxRoleById")
    public RestMessage<String> deletePlgFxRoleById(@RequestParam(value = "id") String id) {
        String data = iSysRoleResource.deletePlgFxRoleById(id).getData();
        return RestMessage.newInstance(true, "删除成功！", data);
    }

    @ApiOperation(value = "数据角色信息分页查询", notes = "数据角色信息分页查询")
    @ApiImplicitParam(name = "name", value = "角色名称")
    @PostMapping("/getMenuAuth")
    public RestMessage<Page<PlgFxRole>> getDataRolePage(@RequestParam(value = "name", required = false) String name,
                                                        @RequestParam(value = "pageNum") int pageNum,
                                                        @RequestParam(value = "pageSize") int pageSize) {
        Page<PlgFxRole> data = iSysRoleResource.getDataRolePage(name, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "通过ID获取角色信息", notes = "通过ID获取角色信息")
    @ApiImplicitParam(name = "id", value = "角色id")
    @GetMapping("/getPlgFxRoleById")
    public RestMessage<PlgFxRole> getPlgFxRoleById(@RequestParam(value = "id") String id) {
        PlgFxRole data = iSysRoleResource.getPlgFxRoleById(id).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }


    @ApiOperation(value = "客户端角色信息分页查询", notes = "客户端角色信息分页查询")
    @ApiImplicitParam(name = "name", value = "角色名称")
    @PostMapping("/getPlgFxRolePage")
    public RestMessage<Page<PlgFxRole>> getPlgFxRolePage(@RequestParam(value = "name", required = false) String name,
                                                         @RequestParam(value = "pageNum") int pageNum,
                                                         @RequestParam(value = "pageSize") int pageSize) {
        Page<PlgFxRole> data = iSysRoleResource.getPlgFxRolePage(name, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "获取角色菜单资源", notes = "获取角色菜单资源")
    @ApiImplicitParams({@ApiImplicitParam(name = "roleid", value = "角色ID", dataType = "string", required = true),
            @ApiImplicitParam(name = "menuid", value = "菜单ID", dataType = "string", required = true)})
    @GetMapping("/getRoleMenuAuth")
    public RestMessage<Page<Map<String, Object>>> getRoleMenuAuth(@RequestParam(value = "roleid") String roleid,
                                                                  @RequestParam(value = "menuid") String menuid,
                                                                  @RequestParam(value = "pageNum") int pageNum,
                                                                  @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = iSysRoleResource.getRoleMenuAuth(roleid, menuid, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "获取角色菜单", notes = "获取角色菜单")
    @ApiImplicitParam(name = "id", value = "角色id")
    @GetMapping("/getRoleMenu")
    public RestMessage<List<Map<String, Object>>> getRoleMenu(@RequestParam(value = "id") String id) {
        List<Map<String, Object>> data = iSysRoleResource.getRoleMenu(id).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "用户组角色分配信息", notes = "用户组角色分配信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "可按：工号，用户名称查询", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "是否分配(0未分配/1已分配)", dataType = "string", required = true),
            @ApiImplicitParam(name = "roleid", value = "角色ID", dataType = "string", required = true)})
    @PostMapping("/getUserRoleGroup")
    public RestMessage<Page<Map<String, Object>>> getUserRoleGroup(@RequestParam(value = "search", required = false) String search,
                                                                   @RequestParam(value = "type") String type,
                                                                   @RequestParam(value = "roleid") String roleid,
                                                                   @RequestParam(value = "pageNum") int pageNum,
                                                                   @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = iSysRoleResource.getUserRoleGroup(search, type, roleid, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "用户角色分配信息", notes = "用户角色分配信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "可按：工号，用户名称查询", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "是否分配(0未分配/1已分配)", dataType = "string", required = true),
            @ApiImplicitParam(name = "roleid", value = "角色ID", dataType = "string", required = true)})
    @PostMapping("/getUserRole")
    public RestMessage<Page<Map<String, Object>>> getUserRole(@RequestParam(value = "search", required = false) String search,
                                                              @RequestParam(value = "type") String type,
                                                              @RequestParam(value = "roleid") String roleid,
                                                              @RequestParam(value = "pageNum") int pageNum,
                                                              @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = iSysRoleResource.getUserRole(search, type, roleid, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "添加角色", notes = "添加角色")
    @PostMapping("/insertPlgFxRole")
    public RestMessage<String> insertPlgFxRole(PlgFxRole plgFxRole) {
        Map<String, Object> plgFxRole1 = MapUtil.beanToMap(plgFxRole);
        RestMessage<String> allData = iSysRoleResource.insertPlgFxRole(plgFxRole1);
        String data = allData.getData();
        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "添加成功！", data);
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PutMapping("/updatePlgFxRole")
    public RestMessage<String> updatePlgFxRole(PlgFxRole plgFxRole) {
        Map<String, Object> plgFxRole1 = MapUtil.beanToMap(plgFxRole);
        RestMessage<String> allData = iSysRoleResource.updatePlgFxRole(plgFxRole1);
        String data = allData.getData();
        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "修改成功！", data);
    }

    @ApiOperation(value = "获取用户分配组信息", notes = "获取用户分配组信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "可按工号，用户名称查询", dataType = "string"),
            @ApiImplicitParam(name = "type", value = "是否分配(0未分配/1已分配)", dataType = "string", required = true),
            @ApiImplicitParam(name = "usergroupid", value = "用户组ID", dataType = "string", required = true)})
    @PostMapping("/getAllocatedUser")
    public RestMessage<Page<Map<String, Object>>> getAllocatedUser(@RequestParam(value = "search", required = false) String search,
                                                                   @RequestParam(value = "type") String type,
                                                                   @RequestParam(value = "usergroupid") String usergroupid,
                                                                   @RequestParam(value = "pageNum") int pageNum,
                                                                   @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = iSysRoleResource.getUserRole(search, type, usergroupid, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }
}
