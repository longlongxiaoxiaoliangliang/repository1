package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.mapper.ProjectDailyRecordMapper;
import com.prolog.time.management.Interface.ProjectDailyRecordService;
import com.prolog.time.management.model.entity.ExcelData;
import com.prolog.time.management.util.ContexToken;
import com.prolog.time.management.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProjectDailyRecordServiceImpl implements ProjectDailyRecordService {
    @Autowired
    private ProjectDailyRecordMapper projectDailyRecordMapper;

    @Autowired
    private ContexToken contexToken;

    //定时任务，定时更新project_daily_record表中的数据
    @Override
    @Transactional
    //@Scheduled(cron = "0/2 * * * * *")
    public void updateData() throws Exception {
        projectDailyRecordMapper.delete();
        projectDailyRecordMapper.insert();
    }

    @Override
    public Page<Map<String, Object>> queryProjectDailyRecordData(Map<String, Object> param) {
        int pageNum = (int) param.get("pageNum");
        int pageSize = (int) param.get("pageSize");
        param.remove("pageNum");
        param.remove("pageSize");
        param.put("userid", contexToken.getUser().getP_username());
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = projectDailyRecordMapper.queryProjectDailyRecordData(param);
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }

    @Override
    @Transactional
    public void downLoadExcel(HttpServletResponse response, Map<String, Object> param) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("未填写日报统计");
        //添加表头
        List<String> titles = new ArrayList();
        titles.add("项目成员");
        titles.add("当日工作时长");
        titles.add("未提交日期");
        titles.add("项目经理");
        titles.add("部门");
        data.setTitles(titles);
        param.put("userid", contexToken.getUser().getP_username());
        List<Map<String, Object>> list = projectDailyRecordMapper.queryProjectDailyRecordData(param);
        //添加列
        List<List<Object>> rows = new ArrayList();
        List<Object> row = null;
        for (int i = 0; i < list.size(); i++) {
            row = new ArrayList();
            row.add(list.get(i).get("PROJECT_USER_NAME"));
            row.add(list.get(i).get("USER_HOUR"));
            row.add(list.get(i).get("NOCOMMIT_DATE"));
            row.add(list.get(i).get("PROJECT_MANAGER_NAME"));
            row.add(list.get(i).get("PROJECT_USER_DEPT"));
            rows.add(row);
        }
        data.setRows(rows);
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String fileName = fdate.format(new Date()) + ".xlsx";
        ExcelUtil.exportExcel(response, fileName, data);


    }
}
