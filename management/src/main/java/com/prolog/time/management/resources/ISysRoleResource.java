package com.prolog.time.management.resources;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.service.back.SysRoleResourceBack;
import com.prolog.user.model.PlgFxRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Service
@FeignClient(value = "service-users", fallback = SysRoleResourceBack.class)
public interface ISysRoleResource {

    //分配角色菜单资源
    @PostMapping("/role/allotRoleMenuAuth/v1.0")
    public RestMessage<Page<Map<String, Object>>> allotRoleMenuAuth(@RequestBody Map<String, Object> params);

    //分配角色菜单
    @PostMapping("/role/allotRoleMenu/v1.0")
    public RestMessage<Page<Map<String, Object>>> allotRoleMenu(@RequestBody Map<String, Object> params);

    //用户组分配角色
    @PostMapping("/role/allotUserRoleGroup/v1.0")
    public RestMessage<Page<Map<String, Object>>> allotUserRoleGroup(@RequestBody Map<String, Object> params);

    //用户分配角色
    @PostMapping("/role/allotUserRole/v1.0")
    public RestMessage<Page<Map<String, Object>>> allotUserRole(@RequestBody Map<String, Object> params);

    //删除角色
    @PostMapping("/role/deletePlgFxRoleById/v1.0")
    public RestMessage<String> deletePlgFxRoleById(@RequestParam(value = "id") String id);

    //数据角色信息分页查询
    @PostMapping("/role/getDataRolePage/v1.0")
    public RestMessage<Page<PlgFxRole>> getDataRolePage(@RequestParam(value = "name") String name,
                                                        @RequestParam(value = "pageNum") int pageNum,
                                                        @RequestParam(value = "pageSize") int pageSize);

    //通过ID获取角色信息
    @GetMapping("/role/getPlgFxRoleById/v1.0")
    public RestMessage<PlgFxRole> getPlgFxRoleById(@RequestParam(value = "id") String id);

    //客户端角色信息分页查询
    @PostMapping("/role/getPlgFxRolePage/v1.0")
    public RestMessage<Page<PlgFxRole>> getPlgFxRolePage(@RequestParam(value = "name") String name,
                                                         @RequestParam(value = "pageNum") int pageNum,
                                                         @RequestParam(value = "pageSize") int pageSize);

    //获取角色菜单资源
    @GetMapping("/role/getRoleMenuAuth/v1.0")
    public RestMessage<Page<Map<String, Object>>> getRoleMenuAuth(@RequestParam(value = "roleid") String roleid,
                                                                  @RequestParam(value = "menuid") String menuid,
                                                                  @RequestParam(value = "pageNum") int pageNum,
                                                                  @RequestParam(value = "pageSize") int pageSize);

    //获取角色菜单
    @GetMapping("/role/getRoleMenu/v1.0")
    public RestMessage<List<Map<String, Object>>> getRoleMenu(@RequestParam(value = "id") String id);

    //用户组角色分配信息
    @PostMapping("/role/getUserRoleGroup/v1.0")
    public RestMessage<Page<Map<String, Object>>> getUserRoleGroup(@RequestParam(value = "search") String search,
                                                                   @RequestParam(value = "type") String type,
                                                                   @RequestParam(value = "roleid") String roleid,
                                                                   @RequestParam(value = "pageNum") int pageNum,
                                                                   @RequestParam(value = "pageSize") int pageSize);

    //用户角色分配信息
    @PostMapping("/role/getUserRole/v1.0")
    public RestMessage<Page<Map<String, Object>>> getUserRole(@RequestParam(value = "search") String search,
                                                              @RequestParam(value = "type") String type,
                                                              @RequestParam(value = "roleid") String roleid,
                                                              @RequestParam(value = "pageNum") int pageNum,
                                                              @RequestParam(value = "pageSize") int pageSize);

    //添加角色
    @PostMapping("/role/insertPlgFxRole/v1.0")
    public RestMessage<String> insertPlgFxRole(@RequestParam(value = "plgFxRole") Map<String, Object> plgFxRole);


    //添加角色
    @PutMapping("/role/updatePlgFxRole/v1.0")
    public RestMessage<String> updatePlgFxRole(@RequestParam(value = "plgFxRole") Map<String, Object> plgFxRole);

    //这里需要调用：获取用户分配组信息（用户组配置）
    @PostMapping("/usergroup/getAllocatedUser/v1.0")
    public RestMessage<Page<Map<String, Object>>> getAllocatedUser(@RequestParam(value = "search") String search,
                                                                   @RequestParam(value = "type") String type,
                                                                   @RequestParam(value = "usergroupid") String usergroupid,
                                                                   @RequestParam(value = "pageNum") int pageNum,
                                                                   @RequestParam(value = "pageSize") int pageSize);

}
