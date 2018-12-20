package com.prolog.time.management.service.impl;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.Interface.AuthorizedProjectManagerService;
import com.prolog.time.management.mapper.AuthorizedProjectManagerMapper;
import com.prolog.time.management.resources.ISysUserResource;
import com.prolog.time.management.util.ContexToken;
import com.prolog.user.model.PlgFxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class AuthorizedProjectManagerServiceImpl implements AuthorizedProjectManagerService {

    @Autowired
    private AuthorizedProjectManagerMapper authorizedProjectManagerMapper;

    @Autowired
    private ISysUserResource iSysUserResource;

    @Autowired
    private ContexToken contexToken;

    @Override
    public Page<PlgFxUser> queryAllUsersData(String search, String projectId, int pageNum, int pageSize,String flg) {
        Page<PlgFxUser> page = new Page<>();
        if(flg.equals("0")) {
            RestMessage<Page<PlgFxUser>> restMessage = iSysUserResource.getPlgFxUserPage(search, pageNum, pageSize);
            page = restMessage.getData();
        }else {
            List<Map<String,Object>> list = authorizedProjectManagerMapper.queryManagersOfProject(projectId);
            List<PlgFxUser> userList = new ArrayList<PlgFxUser>();
            page.setPageNum(1);
            page.setPageSize(10);
            page.setTotalCount(list.size());
            for(Map<String,Object> map : list){
                PlgFxUser plgFxUser = new PlgFxUser();
                plgFxUser.setP_username(map.get("PROJECT_MANAGER").toString());
                plgFxUser.setP_nickname(map.get("PROJECT_MANAGER_NAME").toString());
                plgFxUser.setP_orgname(map.get("PROJECT_MANAGER_DEPT").toString());
                userList.add(plgFxUser);
            }
            page.setList(userList);
        }
        return page;
    }

    @Override
    public Page<Map<String, Object>> queryProjectDefineData(String strparam,String flg,int pageNum,int pageSize) {
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = null;
        if(flg.equals("1")){
            list = authorizedProjectManagerMapper.queryPresaleProjectData(strparam);
        }else if(flg.equals("2")){
            list = authorizedProjectManagerMapper.queryImpProjectData(strparam);
        }else {
            list = authorizedProjectManagerMapper.queryInProjectData(strparam);
        }
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }

    @Override
    @Transactional
    public int addList(List<Map<String, Object>> list,String projectno) throws Exception {
        int num = 0;
        List<Map<String, Object>> list1 = new ArrayList<>();
        for(Map<String,Object> map : list){
            map.put("ins_usr_id",contexToken.getUser().getP_username());
            map.put("lst_upd_usr_id",contexToken.getUser().getP_username());
            map.put("ins_dt",new Date());
            map.put("lst_upd_dt",new Date());
            list1.add(map);
        }
        authorizedProjectManagerMapper.deleteById(projectno);
        if(list.size()>0) {
            num = authorizedProjectManagerMapper.insert(list1);
        }
        return num;
    }
}
