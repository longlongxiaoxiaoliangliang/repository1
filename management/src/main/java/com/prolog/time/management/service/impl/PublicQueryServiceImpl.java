package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.Interface.PublicQueryService;
import com.prolog.time.management.mapper.PublicQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PublicQueryServiceImpl implements PublicQueryService {

    @Autowired
    private PublicQueryMapper publicQueryMapper;
    @Override
    public Page<Map<String, Object>> queryPresaleProjectNoData(String strparam,int pageNum,int pageSize) {
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String,Object>> list = publicQueryMapper.queryPresaleProjectNoData(strparam);
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }

    @Override
    public Page<Map<String, Object>> queryContractCoding(String strparam,int pageNum,int pageSize) {
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String,Object>> list = publicQueryMapper.queryContractCoding(strparam);
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }

    @Override
    public Page<Map<String, Object>> querySaleContractCoding(String strparam,int pageNum,int pageSize) {
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String,Object>> list = publicQueryMapper.querySaleContractCoding(strparam);
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }
}
