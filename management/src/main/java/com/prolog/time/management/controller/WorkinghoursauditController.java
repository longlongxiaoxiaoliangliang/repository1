package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.Interface.WorkinghoursauditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Api(tags = "个人工时审核")
@RestController
@RequestMapping("/audit")
public class WorkinghoursauditController {
    @Autowired
    private WorkinghoursauditService workinghoursauditService;


    @ApiOperation(value = "分页查询", notes = "工时信息分页查询")
    @ApiImplicitParam(name = "param", value = "{bgtime:'开始时间',edtime:'结束时间',strparam:'查询条件'}")
    @PostMapping("/query")
    public RestMessage<Page<Map<String, Object>>> queryWorkinghoursData(@RequestBody Map<String, Object> param) {
        Page<Map<String, Object>> page = workinghoursauditService.queryWorkinghoursData(param);
        return RestMessage.newInstance(true, "查询成功！", page);
    }

    @ApiOperation(value = "审核通过", notes = "可多条")
    @PostMapping("/updadePass")
    public RestMessage<String> auditPass(String[] ids) {
        List<String> idlists = new ArrayList<String>(Arrays.asList(ids));
        int num = workinghoursauditService.updateByIdsPass(idlists);
        return RestMessage.newInstance(true, "审核通过！", String.valueOf(num));
    }

    @ApiOperation(value = "审核不通过", notes = "可多条")
    @PostMapping("/updadeUnPass")
    public RestMessage<String> auditUnPass(String[] ids) {
        List<String> idlists = new ArrayList<String>(Arrays.asList(ids));
        int num = workinghoursauditService.updateByIdsUnPass(idlists);
        return RestMessage.newInstance(true, "审核不通过！", String.valueOf(num));
    }
}
