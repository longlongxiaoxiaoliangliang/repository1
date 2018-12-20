package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.mapper.Workinghoursaudit;
import com.prolog.time.management.Interface.WorkinghoursauditService;
import com.prolog.time.management.util.ContexToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WorkinghoursauditServiceImpl implements WorkinghoursauditService {
    @Autowired
    private Workinghoursaudit workinghoursaudit;

    @Autowired
    private ContexToken contexToken;


    @Override
    public Page<Map<String, Object>> queryWorkinghoursData(Map<String, Object> param) {
        int pageNum = (int) param.get("pageNum");
        int pageSize = (int) param.get("pageSize");
        param.remove("pageNum");
        param.remove("pageSize");
        PageUtils.startPage(pageNum, pageSize);
        if (param.get("bgtime") != null && param.get("edtime") != null) {
            String strbg = param.get("bgtime").toString();
            String stred = param.get("edtime").toString();
            SimpleDateFormat sdf1 = new SimpleDateFormat(
                    "yyyy-MM-dd");
            Date datebg = null;
            Date dateed = null;
            try {
                datebg = sdf1.parse(strbg);
                dateed = sdf1.parse(stred);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String bgStr = sdf1.format(datebg);
            String edStr = sdf1.format(dateed);
            param.put("bgtime", bgStr);
            param.put("edtime", edStr);
            param.put("usrid", contexToken.getUser().getP_username());
            //param.put("usrid", "lijw");
        }
        List<Map<String, Object>> list1 = workinghoursaudit.queryWorkinghoursData(param);
        Page<Map<String, Object>> page = PageUtils.getPage(list1);
        return page;
    }

    @Override
    public int updateByIdsPass(List<String> ids) {
        String loginId = contexToken.getUser().getP_username();
        String loginName = contexToken.getUser().getP_nickname();
        return workinghoursaudit.updateByIdsPass(ids, loginId, loginName);
    }

    @Override
    public int updateByIdsUnPass(List<String> ids) {
        String loginId = contexToken.getUser().getP_username();
        String loginName = contexToken.getUser().getP_nickname();
        return workinghoursaudit.updateByIdsUnPass(ids, loginId, loginName);
    }

}
