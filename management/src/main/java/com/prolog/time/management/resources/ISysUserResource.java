package com.prolog.time.management.resources;

import com.prolog.framework.authority.core.vo.AuthorityVO;
import com.prolog.framework.authority.core.vo.UserVO;
import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.service.back.SysUserResourceBack;
import com.prolog.user.model.PlgFxUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Service
@FeignClient(value = "service-users",fallback = SysUserResourceBack.class)
public interface ISysUserResource {
    //通过用户名查询
    @GetMapping("/user/getUserByUsername/v1.0")
    public RestMessage<PlgFxUser> getUserByUsername(@RequestParam(value="username") String username);
    //获取用户资源权限
    @GetMapping("/user/getUserServiceAuthority/v1.0")
    public RestMessage<UserVO> getUserServiceAuthority(@RequestParam(value="username") String username);

    //获取系统资源
    @GetMapping("/user/getSystemServiceAuthority/v1.0")
    public RestMessage<List<AuthorityVO>> getSystemServiceAuthority(@RequestParam(value="systemid") String systemid);

    //用户分配系统
    @PostMapping("/user/allotSystem/v1.0")
    public RestMessage<String> allotSystem(@RequestBody Map<String,Object> params);
    //批量添加用户
    @PostMapping("/user/batchInsertPlgFxUser/v1.0")
    public RestMessage<List<PlgFxUser>> batchInsertPlgFxUser(@RequestBody List<PlgFxUser> user);
    //删除用户
    @PostMapping("/user/deletePlgFxUserById/v1.0")
    public RestMessage<String> deletePlgFxUserById(@RequestParam(value="id") String id);
    //获取系统分配信息
    @PostMapping("/user/getAllocatedSystem/v1.0")
    public RestMessage<Page<Map<String,Object>>> getAllocatedSystem(@RequestParam(value="search") String search,
                                                                    @RequestParam(value="type") String type,
                                                                    @RequestParam(value="userid") String userid,
                                                                    @RequestParam(value="pageNum") String pageNum,
                                                                    @RequestParam(value="pageSize") String pageSize);
    //通过ID获取用户信息
    @GetMapping("/user/getPlgFxUserById/v1.0")
    public RestMessage<PlgFxUser> getPlgFxUserById(@RequestParam(value="id") String id);
    //用户信息分页查询
    @PostMapping("/user/getPlgFxUserPage/v1.0")
    public RestMessage<Page<PlgFxUser>> getPlgFxUserPage(@RequestParam(value="search") String search,
                                                         @RequestParam(value="pageNum") int pageNum,
                                                         @RequestParam(value="pageSize") int pageSize);



    //添加用户
    @PostMapping("/user/insertPlgFxUser/v1.0")
    public RestMessage<PlgFxUser> insertPlgFxUser(@RequestParam(value="plgFxUser") Map<String,Object> plgFxUser) throws Exception;

    //重置密码
    @PostMapping("/user/resetPassword/v1.0")
    public RestMessage<String> resetPassword(@RequestParam(value="id") String id,
                                             @RequestParam(value="username") String username);

    //修改用户信息
    @PutMapping("/user/updatePlgFxUser/v1.0")
    public RestMessage<String> updatePlgFxUser(@RequestParam(value="plgFxUser") Map<String,Object> plgFxUser);

    //这里要用到组织管理的查询企业接口，加在这里
    @PostMapping("/organization/getOrganization/v1.0")
    public RestMessage<List<Map<String,Object>>> getOrganization();
}
