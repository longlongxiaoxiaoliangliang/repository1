package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.Interface.HomePageService;
import com.prolog.time.management.mapper.HomePageMapper;
import com.prolog.time.management.util.ContexToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private HomePageMapper homePageMapper;

    @Autowired
    private ContexToken contexToken;
    @Override
    public Page<Map<String, Object>> querySelfNotfilledData(int pageNum, int pageSize) {
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = homePageMapper.querySelfNotfilledData(contexToken.getUser().getP_username());
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }

    @Override
    public Page<Map<String, Object>> querySelfNotauditedData(int pageNum, int pageSize) {
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = homePageMapper.querySelfNotauditedData(contexToken.getUser().getP_username());
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }
}
