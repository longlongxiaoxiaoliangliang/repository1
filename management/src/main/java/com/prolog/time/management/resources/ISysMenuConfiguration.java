package com.prolog.time.management.resources;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.service.back.SysMenuConfigurationBack;
import com.prolog.user.model.PlgFxMenu;
//import com.prolog.user.model.PlgFxMenuAuth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
@FeignClient(value = "service-users",fallback = SysMenuConfigurationBack.class)
public interface ISysMenuConfiguration {

    //删除资源
    @PostMapping("/menu/deletePlgFxMenuAuthById/v1.0")
    public RestMessage<String> deletePlgFxMenuAuthById(@RequestParam(value="id") String id);

    //删除菜单
    @PostMapping("/menu/deletePlgFxMenuById/v1.0")
    public RestMessage<String> deletePlgFxMenuById(@RequestParam(value="id") String id);

    //登陆菜单
    @PostMapping("/menu/getMainMenu/v1.0")
    public RestMessage<List<Map<String,Object>>> getMainMenu(@RequestParam(value="userid") String userid);

    //获取资源信息
/*    @PostMapping("/menu/getMenuAuth/v1.0")
    public RestMessage<Page<PlgFxMenuAuth>> getMenuAuth(@RequestParam(value="menuid") String menuid,
                                                        @RequestParam(value="pageNum") int pageNum,
                                                        @RequestParam(value="pageSize") int pageSize);*/

    //获取菜单信息
    @PostMapping("/menu/getMenu/v1.0")
    public RestMessage<List<Map<String,Object>>> getMenu();

    //通过ID获取菜单信息
    @GetMapping("/menu/getPlgFxMenuById/v1.0")
    public RestMessage<PlgFxMenu> getPlgFxMenuById(@RequestParam(value="id") String id);

    //添加资源
    @PostMapping("/menu/insertPlgFxMenuAuth/v1.0")
    public RestMessage<String> insertPlgFxMenuAuth(@RequestParam(value="plgFxMenuAuth") Map<String,Object> plgFxMenuAuth);

    //添加菜单
    @PostMapping("/menu/insertPlgFxMenu/v1.0")
    public RestMessage<String> insertPlgFxMenu(@RequestParam(value="plgFxMenu") Map<String,Object>  plgFxMenu);

    //修改资源配置
    @PutMapping("/menu/updatePlgFxMenuAuth/v1.0")
    public RestMessage<String> updatePlgFxMenuAuth(@RequestParam(value="plgFxMenuAuth") Map<String,Object>  plgFxMenuAuth);

    //修改菜单配置
    @PutMapping("/menu/updatePlgFxMenu/v1.0")
    public RestMessage<String> updatePlgFxMenu(@RequestParam(value="plgFxMenu") Map<String,Object> plgFxMenu);

    //获取系统配置（系统配置）
    @GetMapping("/sys/getSelectSystem/v1.0")
    public RestMessage<List<Map<String,Object>>> getSelectSystem();
}
