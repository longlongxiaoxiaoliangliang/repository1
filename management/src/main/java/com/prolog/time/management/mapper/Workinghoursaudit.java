package com.prolog.time.management.mapper;


import java.util.List;
import java.util.Map;

public interface Workinghoursaudit {




    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询带分页信息的数据
     */
    List<Map<String, Object>> queryWorkinghoursData(Map<String, Object> param);

    /**
     * 修改审批状态为审批通过
     * @param List<String>
     * @return 处理成功条数
     */
    int updateByIdsPass(List<String> ids,String auditid,String auditname);


    /**
     * 修改审批状态为审批未通过
     * @param List<String>
     * @return 处理成功条数
     */
    int updateByIdsUnPass(List<String> ids,String auditid,String auditname);

}
