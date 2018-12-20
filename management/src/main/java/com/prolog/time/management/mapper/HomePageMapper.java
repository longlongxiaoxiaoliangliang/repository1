package com.prolog.time.management.mapper;

import java.util.List;
import java.util.Map;

public interface HomePageMapper {

    /**
     * 按条件查询
     * @param String
     * @return 查询登陆人未填写日报统计
     */
    List<Map<String, Object>> querySelfNotfilledData(String usrid);


    /**
     * 按条件查询
     * @param String
     * @return 查询登陆人未审核日报统计
     */
    List<Map<String, Object>> querySelfNotauditedData(String usrid);
}
