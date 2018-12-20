package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.mapper.NotauditeMapper;
import com.prolog.time.management.Interface.NotauditedService;
import com.prolog.time.management.util.ContexToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotauditedServiceImpl implements NotauditedService {
    @Autowired
    private NotauditeMapper notauditeMapper;

    @Autowired
    private ContexToken contexToken;

    @Override
    public Page<Map<String, Object>> queryNotauditedData(Map<String, Object> param) {
        int pageNum = (int) param.get("pageNum");
        int pageSize = (int) param.get("pageSize");
        param.remove("pageNum");
        param.remove("pageSize");
        param.put("usrid", contexToken.getUser().getP_username());
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = notauditeMapper.queryNotauditedData(param);
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }


}
