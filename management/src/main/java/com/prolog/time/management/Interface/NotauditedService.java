package com.prolog.time.management.Interface;

import com.prolog.framework.core.pojo.Page;


import java.util.Map;

public interface NotauditedService {



    /**
     * 查询未审批日报统计
     * @param map
     * @return List<map>
     */
    Page<Map<String, Object>> queryNotauditedData(Map<String, Object> param);

}
