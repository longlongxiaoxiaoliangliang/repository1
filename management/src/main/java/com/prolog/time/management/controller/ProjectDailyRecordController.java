package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.Interface.ProjectDailyRecordService;
import com.prolog.time.management.mapper.NotfilledPublicMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(tags = "未填写日报统计")
@RestController
@RequestMapping("/notwrited")
public class ProjectDailyRecordController {
    @Autowired
    private ProjectDailyRecordService projectDailyRecordService;


    @ApiOperation(value = "分页查询", notes = "未填写日报统计分页查询")
    @ApiImplicitParam(name = "param", value = "{bgtime:'开始时间',edtime:'结束时间',strparam:'查询条件'}")
    @PostMapping("/query")
    public RestMessage<Page<Map<String, Object>>> queryProjectDailyRecordData(@RequestBody Map<String, Object> param) {
        Page<Map<String, Object>> page = projectDailyRecordService.queryProjectDailyRecordData(param);
        return RestMessage.newInstance(true, "查询成功！", page);
    }

    @ApiOperation(value = "导出", notes = "未填写日报导出")
    @ApiImplicitParam(name = "param", value = "{bgtime:'开始时间',edtime:'结束时间',strparam:'查询条件'}")
    @PostMapping("/downLoad")
    public RestMessage<String> downLoadExcel(HttpServletResponse response, @RequestBody Map<String, Object> param) throws Exception {
        projectDailyRecordService.downLoadExcel(response, param);
        return RestMessage.newInstance(true, "导出成功！", "");
    }
}
