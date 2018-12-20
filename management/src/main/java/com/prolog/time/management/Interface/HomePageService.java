package com.prolog.time.management.Interface;

import com.prolog.framework.core.pojo.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface HomePageService {



    /**
     * 查询当前用户未填写日报统计
     * @param map
     * @return Page<Map<String, Object>>
     */
    Page<Map<String, Object>> querySelfNotfilledData(int pageNum,int pageSize);

    /**
     * 查询当前用户未审核日报统计
     * @param map
     * @return Page<Map<String, Object>>
     */
    Page<Map<String, Object>> querySelfNotauditedData(int pageNum,int pageSize);

}
