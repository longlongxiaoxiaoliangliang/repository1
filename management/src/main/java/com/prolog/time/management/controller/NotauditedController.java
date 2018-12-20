package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.Interface.NotauditedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "未审核日报统计")
@RestController
@RequestMapping("/notaudited")
public class NotauditedController {
    @Autowired
    private NotauditedService notauditedService;


    @ApiOperation(value = "分页查询", notes = "未审核日报统计分页查询")
    @ApiImplicitParam(name = "param", value = "{bgtime:'开始时间',edtime:'结束时间',strparam:'查询条件'}")
    @PostMapping("/query")
    public RestMessage<Page<Map<String, Object>>> queryNotauditedData(@RequestBody Map<String, Object> param) {
        Page<Map<String, Object>> page = notauditedService.queryNotauditedData(param);
        return RestMessage.newInstance(true, "查询成功！", page);
    }

}
