package com.prolog.time.management.Interface;

import com.prolog.framework.core.pojo.Page;

import java.util.List;
import java.util.Map;

public interface WorkinghoursauditService {


    /**
     * 查询工时数据
     * @param map
     * @return List<map>
     */
    Page<Map<String, Object>> queryWorkinghoursData(Map<String, Object> param);

    /**
     * 修改
     * @param ids
     * @return 根据ids修改，返回修改成功条数，其中有未成功的返回0
     */
    int updateByIdsPass(List<String> ids);

    /**
     * 修改
     * @param ids
     * @return 根据ids修改，返回修改成功条数，其中有未成功的返回0
     */
    int updateByIdsUnPass(List<String> ids);
}
