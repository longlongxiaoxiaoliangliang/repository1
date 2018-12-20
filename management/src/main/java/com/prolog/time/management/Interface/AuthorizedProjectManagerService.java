package com.prolog.time.management.Interface;

import com.prolog.framework.core.pojo.Page;
import com.prolog.user.model.PlgFxUser;

import java.util.List;
import java.util.Map;

public interface AuthorizedProjectManagerService {

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询项目之外的所有人账号，姓名，部门
     */
    Page<PlgFxUser> queryAllUsersData(String search, String projectId, int pageNum, int pageSize,String flg);


    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询项目
     */
    Page<Map<String, Object>> queryProjectDefineData(String strparam,String flg,int pageNum,int pageSize);



    /**
     * 新增多条数据
     * @param List<map>
     * @return 添加成功条数
     */
    int addList(List<Map<String,Object>> list,String projectno) throws Exception;
}
