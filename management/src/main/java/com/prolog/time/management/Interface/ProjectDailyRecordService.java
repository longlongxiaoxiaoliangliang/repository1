package com.prolog.time.management.Interface;

import com.prolog.framework.core.pojo.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ProjectDailyRecordService {

    /**
     * 新增 定时更新符合条件的未统计报表信息
     * @param
     * @return
     */
    void updateData() throws Exception ;



    /**
     * 查询未填写日报统计
     * @param map
     * @return List<map>
     */
    Page<Map<String, Object>> queryProjectDailyRecordData(Map<String, Object> param);



    /**
     * 导出
     * @param response
     * @return
     */
    void downLoadExcel(HttpServletResponse response,Map<String, Object> param) throws Exception;
}
