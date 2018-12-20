package com.prolog.time.management.mapper;

import java.util.List;
import java.util.Map;

public interface AuthorizedProjectManagerMapper {
    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询所有售前项目
     */
    List<Map<String, Object>> queryPresaleProjectData(String strparam);

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询所有实施项目
     */
    List<Map<String, Object>> queryImpProjectData(String strparam);


    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询所有汇总项目
     */
    List<Map<String, Object>> queryInProjectData(String strparam);


    /**
     * 批量添加项目授权的项目经理
     * @param List<map>
     * @return 插入时返回的行数
     */
    int insert(List<Map<String,Object>> list);

    /**
     * 根据项目编号查询已授权的项目经理
     * @param Map<String, Object>
     * @return 项目成员集合
     */
    List<Map<String,Object>> queryManagersOfProject(String projectId);

    /**
     * 删除
     * @param
     * @return
     */
    int deleteById(String projectno);


}
