package com.prolog.time.management.Interface;

import com.prolog.framework.core.pojo.Page;

import java.util.Map;

public interface PublicQueryService {
    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 售前立项存档编号
     */
    Page<Map<String, Object>> queryPresaleProjectNoData(String strparam,int pageNum,int pageSize);

    /**
     * 按条件查询
     * @param String
     * @return 查询合同编码
     */
    Page<Map<String, Object>> queryContractCoding(String strparam,int pageNum,int pageSize);

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询销售合同编码
     */
    Page<Map<String, Object>> querySaleContractCoding(String strparam,int pageNum,int pageSize);
}
