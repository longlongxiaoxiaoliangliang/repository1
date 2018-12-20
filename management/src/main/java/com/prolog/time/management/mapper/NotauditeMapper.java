package com.prolog.time.management.mapper;

import java.util.List;
import java.util.Map;

public interface NotauditeMapper {

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询未审核日报统计
     */
    List<Map<String, Object>> queryNotauditedData(Map<String, Object> param);

}

