package com.prolog.time.management.service.back;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.resources.ISysMenuConfiguration;
import com.prolog.user.model.PlgFxMenu;
//import com.prolog.user.model.PlgFxMenuAuth;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SysMenuConfigurationBack implements ISysMenuConfiguration {

    @Override
    public RestMessage<String> deletePlgFxMenuAuthById(String id) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> deletePlgFxMenuById(String id) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<List<Map<String, Object>>> getMainMenu(String userid) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

/*    @Override
    public RestMessage<Page<PlgFxMenuAuth>> getMenuAuth(String menuid, int pageNum, int pageSize) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }*/

    @Override
    public RestMessage<List<Map<String, Object>>> getMenu() {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<PlgFxMenu> getPlgFxMenuById(String id) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> insertPlgFxMenuAuth(Map<String, Object> plgFxMenuAuth) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> insertPlgFxMenu(Map<String, Object> plgFxMenu) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> updatePlgFxMenuAuth(Map<String, Object> plgFxMenuAuth) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }

    @Override
    public RestMessage<String> updatePlgFxMenu(Map<String, Object> plgFxMenu) {
        return RestMessage.newInstance(false, "请求失败！",null);
    }


    @Override
    public RestMessage<List<Map<String, Object>>> getSelectSystem() {
        return RestMessage.newInstance(false, "请求失败！",null);
    }
}
