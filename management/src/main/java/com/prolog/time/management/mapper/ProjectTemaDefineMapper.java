package com.prolog.time.management.mapper;

import com.prolog.time.management.model.entity.ImpProjectInfo;

import java.util.List;
import java.util.Map;

public interface ProjectTemaDefineMapper {




    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询非管理员和配置管理员能看到的项目
     */
    List<Map<String, Object>> queryLoginUserProjectDefineData(String userid,String strparam);

    /**
     * 根据项目编号和项目经理查询项目成员
     * @param Map<String, Object>
     * @return 项目成员集合
     */
    List<Map<String,Object>> queryUsersOfProjectData(String projectId,String projectmanager);



    /**
     * 批量添加到项目组定义表或者更新表的角色字段
     * @param List<map>
     * @return 插入时返回的行数
     */
    int insert(List<Map<String,Object>> list);



    /**
     * 删除
     * @param
     * @return
     */
    int deleteByIds(List<String> ids,String projectno,String projectmanager);

    /**
     * 删除
     * @param
     * @return
     */
    int deleteAllOfThis(String projectno,String usrid);
}

