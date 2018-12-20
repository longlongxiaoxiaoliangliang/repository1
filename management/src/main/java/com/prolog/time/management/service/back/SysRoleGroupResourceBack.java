package com.prolog.time.management.service.back;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.resources.ISysRoleGroupResource;
//import com.prolog.user.model.PlgFxRolegroup;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysRoleGroupResourceBack implements ISysRoleGroupResource {
    @Override
    public RestMessage<String> allotRoleGroup(Map<String, Object> params) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> deletePlgFxRolegroup(String id) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<Page<Map<String, Object>>> getAllocatedRole(String name, String type, String rolegroupid, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

/*    @Override
    public RestMessage<PlgFxRolegroup> getPlgFxRolegroupById(String id) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<Page<PlgFxRolegroup>> getPlgFxRolegroupPage(String name, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }*/

    @Override
    public RestMessage<String> insertPlgFxRolegroup(Map<String, Object> plgFxRolegroup) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> updatePlgFxRolegroup(Map<String, Object> plgFxRolegroup) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }


}
