package com.prolog.time.management.mapper;

import java.util.List;
import java.util.Map;

public interface ProjectDailyRecordMapper {
    /**
     * 新增 将符合条件的未填写日报统计插入到project_daily_record表
     * @param i
     * @return
     */
    int insert();


    /**
     * 删除 先删除存在未提交日报记录表，并且又提交过日报的记录
     * @param
     * @return
     */
    int delete();


    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询未填写日报统计
     */
    List<Map<String, Object>> queryProjectDailyRecordData(Map<String, Object> param);
}
