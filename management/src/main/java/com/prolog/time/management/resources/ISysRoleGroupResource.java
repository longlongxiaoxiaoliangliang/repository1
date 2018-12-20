package com.prolog.time.management.resources;


import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.service.back.SysRoleGroupResourceBack;
import com.prolog.user.model.PlgFxRole;
//import com.prolog.user.model.PlgFxRolegroup;
import com.prolog.user.model.PlgFxUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Service
@FeignClient(value = "service-users",fallback = SysRoleGroupResourceBack.class)
public interface ISysRoleGroupResource {

    //分配角色
    @PostMapping("/rolegroup/allotRoleGroup/v1.0")
    public RestMessage<String> allotRoleGroup(@RequestBody Map<String,Object> params);

    //删除角色组
    @PostMapping("/rolegroup/deletePlgFxRolegroup/v1.0")
    public RestMessage<String> deletePlgFxRolegroup(@RequestParam(value="id") String id);

    //获取角色分配组信息
    @PostMapping("/rolegroup/getAllocatedRole/v1.0")
    public RestMessage<Page<Map<String,Object>>> getAllocatedRole(@RequestParam(value="name") String name,
                                                                  @RequestParam(value="type") String type,
                                                                  @RequestParam(value="rolegroupid") String rolegroupid,
                                                                  @RequestParam(value="pageNum") int pageNum,
                                                                  @RequestParam(value="pageSize") int pageSize);
/*    //通过ID获取角色组信息
    @GetMapping("/rolegroup/getPlgFxRolegroupById/v1.0")
    public RestMessage<PlgFxRolegroup> getPlgFxRolegroupById(@RequestParam(value="id") String id);

    //角色组信息分页查询
    @PostMapping("/rolegroup/getPlgFxRolegroupPage/v1.0")
    public RestMessage<Page<PlgFxRolegroup>> getPlgFxRolegroupPage(@RequestParam(value="name") String name,
                                                         @RequestParam(value="pageNum") int pageNum,
                                                         @RequestParam(value="pageSize") int pageSize);*/

    //添加角色组
    @PostMapping("/rolegroup/insertPlgFxRolegroup/v1.0")
    public RestMessage<String> insertPlgFxRolegroup(@RequestParam(value="plgFxRolegroup") Map<String,Object> plgFxRolegroup);

    //修改角色信息组
    @PutMapping("/rolegroup/updatePlgFxRolegroup/v1.0")
    public RestMessage<String> updatePlgFxRolegroup(@RequestParam(value="plgFxRolegroup") Map<String,Object> plgFxRolegroup);
}
