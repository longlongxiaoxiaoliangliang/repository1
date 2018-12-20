package com.prolog.time.management.mapper;

import java.util.List;
import java.util.Map;

public interface PublicQueryMapper {

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 售前立项存档编号
     */
    List<Map<String, Object>> queryPresaleProjectNoData(String strparam);

    /**
     * 按条件查询
     * @param String
     * @return 查询合同编码
     */
    List<Map<String, Object>> queryContractCoding(String strparam);

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询销售合同编码
     */
    List<Map<String, Object>> querySaleContractCoding(String strparam);


}

