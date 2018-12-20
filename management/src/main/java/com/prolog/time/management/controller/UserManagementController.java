package com.prolog.time.management.controller;

import com.prolog.framework.authority.core.vo.AuthorityVO;
import com.prolog.framework.authority.core.vo.UserVO;
import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.resources.ISysUserResource;
import com.prolog.time.management.util.MapUtil;
import com.prolog.user.model.PlgFxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserManagementController {

    @Autowired
    private ISysUserResource iSysUserResource;

    @ApiOperation(value = "用户分配系统", notes = "用户分配系统")
    @ApiImplicitParam(name = "params", value = "{type:'0取消/1分配',userid:'用户id',systemids:'系统id1,系统id2'}")
    @PostMapping("/allotSystem")
    public RestMessage<String> allotSystem(@RequestBody Map<String, Object> params) {
        String data = iSysUserResource.allotSystem(params).getData();
        return RestMessage.newInstance(true, "分配成功！", data);

    }

    @ApiOperation(value = "批量添加用户", notes = "批量添加用户")
    @PostMapping("/batchInsertPlgFxUser")
    public RestMessage<List<PlgFxUser>> batchInsertPlgFxUser(@RequestBody List<PlgFxUser> user) {
        List<PlgFxUser> data = iSysUserResource.batchInsertPlgFxUser(user).getData();
        return RestMessage.newInstance(true, "添加成功！", data);
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @PostMapping("/deletePlgFxUserById")
    public RestMessage<String> deletePlgFxUserById(@RequestParam(value = "id") String id) {
        String data = iSysUserResource.deletePlgFxUserById(id).getData();
        return RestMessage.newInstance(true, "删除成功！", data);
    }

    @ApiOperation(value = "获取系统分配信息", notes = "获取系统分配信息")
    @PostMapping("/getAllocatedSystem")
    public RestMessage<Page<Map<String, Object>>> getAllocatedSystem(@RequestParam(value = "search", required = false) String search,
                                                                     @RequestParam(value = "type") String type,
                                                                     @RequestParam(value = "userid") String userid,
                                                                     @RequestParam(value = "pageNum") String pageNum,
                                                                     @RequestParam(value = "pageSize") String pageSize) {
        Page<Map<String, Object>> data = iSysUserResource.getAllocatedSystem(search, type, userid, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "删除成功！", data);
    }

    @ApiOperation(value = "通过ID获取用户信息", notes = "通过ID获取用户信息")
    @GetMapping("/getPlgFxUserById")
    public RestMessage<PlgFxUser> getPlgFxUserById(@RequestParam(value = "id") String id) {
        PlgFxUser data = iSysUserResource.getPlgFxUserById(id).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "用户信息分页查询", notes = "用户信息分页查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "search", value = "可按用户账号，用户名称，手机号查询", dataType = "string"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", required = true),
            @ApiImplicitParam(name = "pageSize", value = "总条数", dataType = "int", required = true)})
    @PostMapping("/getPlgFxUserPage")
    public RestMessage<Page<PlgFxUser>> getPlgFxUserPage(@RequestParam(value = "search", required = false) String search,
                                                         @RequestParam(value = "pageNum") int pageNum,
                                                         @RequestParam(value = "pageSize") int pageSize) {
        Page<PlgFxUser> data = iSysUserResource.getPlgFxUserPage(search, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "获取系统资源", notes = "获取系统资源")
    @ApiImplicitParam(name = "systemid", value = "系统id")
    @GetMapping("/getSystemServiceAuthority")
    public RestMessage<List<AuthorityVO>> getSystemServiceAuthority(@RequestParam(value = "systemid") String systemid) {
        List<AuthorityVO> data = iSysUserResource.getSystemServiceAuthority(systemid).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "通过用户名查询", notes = "通过用户名查询")
    @ApiImplicitParam(name = "username", value = "用户名")
    @GetMapping("/getUserByUsername")
    public RestMessage<PlgFxUser> getUserByUsername(@RequestParam(value = "username") String username) {
        PlgFxUser data = iSysUserResource.getUserByUsername(username).getData();
        return RestMessage.newInstance(true, "查询成功！", data);
    }

    @ApiOperation(value = "获取用户资源权限", notes = "获取用户资源权限")
    @ApiImplicitParam(name = "username", value = "用户名")
    @GetMapping("/getUserServiceAuthority")
    public RestMessage<UserVO> getUserServiceAuthority(@RequestParam(value = "username") String username) {
        UserVO data = iSysUserResource.getUserServiceAuthority(username).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @PostMapping("/insertPlgFxUser")
    public RestMessage<PlgFxUser> insertPlgFxUser(PlgFxUser plgFxUser) throws Exception {
        Map<String, Object> plgFxUser1 = MapUtil.beanToMap(plgFxUser);
        RestMessage<PlgFxUser> allData = iSysUserResource.insertPlgFxUser(plgFxUser1);
        PlgFxUser data = allData.getData();
        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "添加成功！", data);
    }

    @ApiOperation(value = "重置密码", notes = "重置密码")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", dataType = "string", required = true),
            @ApiImplicitParam(name = "username", value = "用户账号", dataType = "string", required = true)})
    @PostMapping("/resetPassword")
    public RestMessage<String> resetPassword(@RequestParam(value = "id") String id,
                                             @RequestParam(value = "username") String username) {
        String data = iSysUserResource.resetPassword(id, username).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PutMapping("/updatePlgFxUser")
    public RestMessage<String> updatePlgFxUser(PlgFxUser plgFxUser) {
        Map<String, Object> plgFxUser1 = MapUtil.beanToMap(plgFxUser);
        RestMessage<String> allData = iSysUserResource.updatePlgFxUser(plgFxUser1);
        String data = allData.getData();
        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "修改成功！", data);
    }

    @ApiOperation(value = "查询企业", notes = "查询企业")
    @PostMapping("/getOrganization")
    public RestMessage<List<Map<String, Object>>> getOrganization() {
        List<Map<String, Object>> data = iSysUserResource.getOrganization().getData();
        return RestMessage.newInstance(true, "查询成功！", data);
    }
}
