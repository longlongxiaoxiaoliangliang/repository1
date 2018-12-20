package com.prolog.time.management.Interface;

import com.prolog.framework.core.pojo.Page;
import com.prolog.user.model.PlgFxUser;

import java.util.List;
import java.util.Map;

public interface ProjectTemaDefineService {

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询项目
     */
    List<Map<String, Object>> queryProjectDefineData(String strparam);

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询项目之外的所有人账号，姓名，部门
     */
    Page<PlgFxUser> queryLeftUsersData(String search,String projectId, int pageNum, int pageSize);

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询项目之中的所有人账号，姓名，部门
     */
    Page<Map<String,Object>> queryRightUsersData(String projectId,String projectmanager, int pageNum, int pageSize);

    /**
     * 新增多条数据
     * @param List<map>
     * @return 影响行数：如果提交人当天已经提交过，影响行数为0，新增成功，影响行数为1
     */
    int addList(List<Map<String,Object>> list,String projectno) throws Exception;



}

