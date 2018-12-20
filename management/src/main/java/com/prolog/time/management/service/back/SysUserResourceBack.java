package com.prolog.time.management.service.back;

import com.prolog.framework.authority.core.vo.AuthorityVO;
import com.prolog.framework.authority.core.vo.UserVO;
import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.resources.ISysUserResource;
import com.prolog.user.model.PlgFxUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserResourceBack implements ISysUserResource {
    @Override
    public RestMessage<PlgFxUser> getUserByUsername(String username) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<UserVO> getUserServiceAuthority(String username) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<List<AuthorityVO>> getSystemServiceAuthority(String systemid) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> allotSystem(Map<String, Object> params) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<List<PlgFxUser>> batchInsertPlgFxUser(List<PlgFxUser> user) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> deletePlgFxUserById(String id) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<Page<Map<String, Object>>> getAllocatedSystem(String search, String type, String userid, String pageNum, String pageSize) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<PlgFxUser> getPlgFxUserById(String id) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<Page<PlgFxUser>> getPlgFxUserPage(String search, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<PlgFxUser> insertPlgFxUser(Map<String, Object> plgFxUser) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }


    @Override
    public RestMessage<String> resetPassword(String id, String username) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> updatePlgFxUser(Map<String, Object> plgFxUser) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }


    @Override
    public RestMessage<List<Map<String, Object>>> getOrganization() {
        return RestMessage.newInstance(false, "请求失败！",null);
    }
}
