package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.resources.ISysMenuConfiguration;
import com.prolog.time.management.util.ContexToken;
import com.prolog.time.management.util.MapUtil;
import com.prolog.user.model.PlgFxMenu;
//import com.prolog.user.model.PlgFxMenuAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "菜单配置")
@RestController
@RequestMapping("/menu")
public class MenuConfigurationController {
    @Autowired
    private ContexToken contexToken;

    @Autowired
    private ISysMenuConfiguration iSysMenuConfiguration;

    @ApiOperation(value = "删除资源", notes = "删除资源")
    @ApiImplicitParam(name = "id", value = "资源id")
    @PostMapping("/deletePlgFxMenuAuthById")
    public RestMessage<String> deletePlgFxMenuAuthById(@RequestParam(value = "id") String id) {
        String data = iSysMenuConfiguration.deletePlgFxMenuAuthById(id).getData();
        return RestMessage.newInstance(true, "删除成功！", data);
    }

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @ApiImplicitParam(name = "id", value = "菜单id")
    @PostMapping("/deletePlgFxMenuById")
    public RestMessage<String> deletePlgFxMenuById(@RequestParam(value = "id") String id) {
        String data = iSysMenuConfiguration.deletePlgFxMenuById(id).getData();
        return RestMessage.newInstance(true, "删除成功！", data);
    }

    @ApiOperation(value = "登陆菜单", notes = "登陆菜单")
    @GetMapping("/getMainMenu")
    public RestMessage<List<Map<String, Object>>> getMainMenu() {
        List<Map<String, Object>> data = iSysMenuConfiguration.getMainMenu(contexToken.getUser().getP_username()).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

/*    @ApiOperation(value = "获取资源信息", notes = "获取资源信息")
    @ApiImplicitParam(name = "menuid", value = "可按系统编号，资源编号查询")
    @PostMapping("/getMenuAuth")
    public RestMessage<Page<PlgFxMenuAuth>> getMenuAuth(@RequestParam(value = "menuid", required = false) String menuid,
                                                        @RequestParam(value = "pageNum") int pageNum,
                                                        @RequestParam(value = "pageSize") int pageSize) {
        Page<PlgFxMenuAuth> data = iSysMenuConfiguration.getMenuAuth(menuid, pageNum, pageSize).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }*/

    @ApiOperation(value = "获取菜单信息", notes = "获取菜单信息")
    @PostMapping("/getMenu")
    public RestMessage<List<Map<String, Object>>> getMenu() {
        List<Map<String, Object>> data = iSysMenuConfiguration.getMenu().getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "通过ID获取菜单信息", notes = "通过ID获取菜单信息")
    @ApiImplicitParam(name = "id", value = "菜单id")
    @GetMapping("/getPlgFxMenuById")
    public RestMessage<PlgFxMenu> getPlgFxMenuById(@RequestParam(value = "id") String id) {
        PlgFxMenu data = iSysMenuConfiguration.getPlgFxMenuById(id).getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }

/*    @ApiOperation(value = "添加资源", notes = "添加资源")
    @PostMapping("/insertPlgFxMenuAuth")
    public RestMessage<String> insertPlgFxMenuAuth(PlgFxMenuAuth plgFxMenuAuth) {
        Map<String, Object> plgFxMenuAuth1 = MapUtil.beanToMap(plgFxMenuAuth);
        RestMessage<String> allData = iSysMenuConfiguration.insertPlgFxMenuAuth(plgFxMenuAuth1);
        String data = allData.getData();
        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "添加成功！", data);
    }*/

    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @PostMapping("/insertPlgFxMenu")
    public RestMessage<String> insertPlgFxMenu(PlgFxMenu plgFxMenu) {
        Map<String, Object> plgFxMenu1 = MapUtil.beanToMap(plgFxMenu);
        RestMessage<String> allData = iSysMenuConfiguration.insertPlgFxMenu(plgFxMenu1);
        String data = allData.getData();

        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "添加成功！", data);
    }

/*    @ApiOperation(value = "修改资源配置", notes = "修改资源配置")
    @PostMapping("/updatePlgFxMenuAuth")
    public RestMessage<String> updatePlgFxMenuAuth(PlgFxMenuAuth plgFxMenuAuth) {
        Map<String, Object> plgFxMenuAuth1 = MapUtil.beanToMap(plgFxMenuAuth);
        RestMessage<String> allData = iSysMenuConfiguration.updatePlgFxMenuAuth(plgFxMenuAuth1);
        String data = allData.getData();
        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "修改成功！", data);
    }*/

    @ApiOperation(value = "修改菜单配置", notes = "修改菜单配置")
    @PostMapping("/updatePlgFxMenu")
    public RestMessage<String> updatePlgFxMenu(PlgFxMenu plgFxMenu) {
        Map<String, Object> plgFxMenu1 = MapUtil.beanToMap(plgFxMenu);
        RestMessage<String> allData = iSysMenuConfiguration.updatePlgFxMenu(plgFxMenu1);
        String data = allData.getData();

        if (!allData.isSuccess()) {
            return RestMessage.newInstance(false, allData.getMessage(), data);
        }
        return RestMessage.newInstance(true, "修改成功！", data);
    }

    @ApiOperation(value = "获取系统配置（系统配置）", notes = "获取系统配置（系统配置）")
    @PostMapping("/getSelectSystem")
    public RestMessage<List<Map<String, Object>>> getSelectSystem() {
        List<Map<String, Object>> data = iSysMenuConfiguration.getSelectSystem().getData();
        return RestMessage.newInstance(true, "获取成功！", data);
    }
}
