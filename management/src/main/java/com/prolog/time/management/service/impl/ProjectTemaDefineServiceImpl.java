package com.prolog.time.management.service.impl;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.Interface.ProjectTemaDefineService;
import com.prolog.time.management.mapper.ProjectTemaDefineMapper;
import com.prolog.time.management.resources.ISysUserResource;
import com.prolog.time.management.util.ContexToken;
import com.prolog.user.model.PlgFxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Service
public class ProjectTemaDefineServiceImpl implements ProjectTemaDefineService {

    @Autowired
    private ProjectTemaDefineMapper projectTemaDefineMapper;

    @Autowired
    private ContexToken contexToken;

    @Autowired
    private ISysUserResource iSysUserResource;

    @Override
    public List<Map<String, Object>> queryProjectDefineData(String strparam) {
        String userid = contexToken.getUser().getP_username();

        List<Map<String, Object>> list = projectTemaDefineMapper.queryLoginUserProjectDefineData(userid,strparam);

        return list;
    }

    @Override
    public Page<PlgFxUser> queryLeftUsersData(String search,String projectId, int pageNum, int pageSize) {
        RestMessage<Page<PlgFxUser>> restMessage = iSysUserResource.getPlgFxUserPage(search, pageNum, pageSize);
        Page<PlgFxUser> page = restMessage.getData();
        return page;
    }

    @Override
    public Page<Map<String, Object>> queryRightUsersData(String projectId,String projectmanager, int pageNum, int pageSize) {
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String,Object>> listProjectUsers = projectTemaDefineMapper.queryUsersOfProjectData(projectId,projectmanager);
        Page<Map<String, Object>> page = PageUtils.getPage(listProjectUsers);
        return page;
    }
    @Transactional
    @Override
    public int addList(List<Map<String, Object>> list,String projectno) throws Exception {
        int flgnum = 0;
        if(list.size()>0) {
            //在点保存时，右菜单没有，该项目经理下有的人员，则要删除；右菜单有的，项目经理下面也有，不处理；右菜单有，项目经理下面没有，添加。
            List<Map<String, Object>> list1 = new ArrayList<>();
            //取出要添加数据的项目编号和项目经理ID
            String projectmanager = list.get(0).get("project_manager").toString();
            String projectId = list.get(0).get("project_no").toString();
            //查询此项目和项目经理下的人员数据
            List<Map<String, Object>> listProjectUsers = projectTemaDefineMapper.queryUsersOfProjectData(projectId, projectmanager);
            //存放接口添加人员ID
            List<String> userIds1 = new ArrayList<>();
            for (Map<String, Object> map : list) {
                String projectuser = map.get("project_user").toString();
                userIds1.add(projectuser);
                map.put("id", "1");
                map.put("ins_usr_id", contexToken.getUser().getP_username());
                map.put("lst_upd_usr_id", contexToken.getUser().getP_username());
                map.put("ins_dt", new Date());
                map.put("lst_upd_dt", new Date());
                list1.add(map);
            }
            //存放数据库中查询的人员ID
            List<String> userIds2 = new ArrayList<>();
            for (Map<String, Object> map1 : listProjectUsers) {
                String pruser = map1.get("PROJECT_USER").toString();
                userIds2.add(pruser);
            }
            //取出在listProjectUsers中却不在list中的人员，即取出在userIds2中不在userIds1中的人员
            if (userIds2.size() > 0) {
                userIds2.removeAll(userIds1);
                if (userIds2.size() > 0) {
                    //删除listProjectUsers中却不在list中的人员
                    projectTemaDefineMapper.deleteByIds(userIds2, projectId, projectmanager);
                }
            }
            flgnum = projectTemaDefineMapper.insert(list1);
        }else{
            projectTemaDefineMapper.deleteAllOfThis(projectno, contexToken.getUser().getP_username());

        }
        return flgnum;
    }

}
