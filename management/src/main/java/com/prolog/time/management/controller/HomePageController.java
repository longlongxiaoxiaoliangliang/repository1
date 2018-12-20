package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.Interface.HomePageService;
import com.prolog.time.management.Interface.ProjectDailyRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(tags = "首页")
@RestController
@RequestMapping("/homepage")
public class HomePageController {
    @Autowired
    private HomePageService homePageService;


    @ApiOperation(value = "分页查询未填写日报统计", notes = "未填写日报统计分页查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", required = true),
            @ApiImplicitParam(name = "pageSize", value = "总条数", dataType = "int", required = true)})
    @PostMapping("/querySelfNotfilledData")
    public RestMessage<Page<Map<String, Object>>> querySelfNotfilledData(@RequestParam(value = "pageNum") int pageNum,
                                                                         @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> page = homePageService.querySelfNotfilledData(pageNum, pageSize);
        return RestMessage.newInstance(true, "查询成功！", page);
    }

    @ApiOperation(value = "分页查询未审核日报统计", notes = "未审核日报统计分页查询")
    @ApiImplicitParams({@ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", required = true),
            @ApiImplicitParam(name = "pageSize", value = "总条数", dataType = "int", required = true)})
    @PostMapping("/querySelfNotauditedData")
    public RestMessage<Page<Map<String, Object>>> querySelfNotauditedData(@RequestParam(value = "pageNum") int pageNum,
                                                                         @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> page = homePageService.querySelfNotauditedData(pageNum, pageSize);
        return RestMessage.newInstance(true, "查询成功！", page);
    }

}
