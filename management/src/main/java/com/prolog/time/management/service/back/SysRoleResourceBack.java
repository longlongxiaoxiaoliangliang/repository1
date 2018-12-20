package com.prolog.time.management.service.back;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.resources.ISysRoleResource;
import com.prolog.user.model.PlgFxRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleResourceBack implements ISysRoleResource {
    @Override
    public RestMessage<Page<Map<String, Object>>> allotRoleMenuAuth(Map<String, Object> params) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<Page<Map<String, Object>>> allotRoleMenu(Map<String, Object> params) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<Page<Map<String, Object>>> allotUserRoleGroup(Map<String, Object> params) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<Page<Map<String, Object>>> allotUserRole(Map<String, Object> params) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<String> deletePlgFxRoleById(String id) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<Page<PlgFxRole>> getDataRolePage(String name, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<PlgFxRole> getPlgFxRoleById(String id) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<Page<PlgFxRole>> getPlgFxRolePage(String name, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<Page<Map<String, Object>>> getRoleMenuAuth(String roleid, String menuid, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<List<Map<String, Object>>> getRoleMenu(String id) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<Page<Map<String, Object>>> getUserRoleGroup(String search, String type, String roleid, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<Page<Map<String, Object>>> getUserRole(String search, String type, String roleid, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<String> insertPlgFxRole(Map<String, Object> plgFxRole) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }

    @Override
    public RestMessage<String> updatePlgFxRole(Map<String, Object> plgFxRole) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }


    @Override
    public RestMessage<Page<Map<String, Object>>> getAllocatedUser(String search, String type, String usergroupid, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！", null);
    }
}
