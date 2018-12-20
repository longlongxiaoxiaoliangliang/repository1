package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.Interface.PublicQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "公用数据查询接口")
@RestController
@RequestMapping("/publicquery")
public class PublicQueryController {

    @Autowired
    private PublicQueryService publicQueryService;

    @ApiOperation(value = "查询售前立项存档编号", notes = "查询售前立项存档编号")
    @ApiImplicitParams({@ApiImplicitParam(name = "strparam", value = "查询条件",required = false),
            @ApiImplicitParam(name = "pageNum", value = "起始页码", dataType = "int",required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页长度", dataType = "int",required = true)})
    @PostMapping("/queryPresaleProjectNoData")
    public RestMessage<Page<Map<String, Object>>> queryPresaleProjectNoData(@RequestParam(value = "strparam",required = false) String strparam,
                                                                      @RequestParam(value = "pageNum") int pageNum,
                                                                      @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = publicQueryService.queryPresaleProjectNoData(strparam, pageNum, pageSize);
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "查询合同编码", notes = "查询合同编码")
    @ApiImplicitParams({@ApiImplicitParam(name = "strparam", value = "查询条件",required = false),
            @ApiImplicitParam(name = "pageNum", value = "起始页码", dataType = "int",required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页长度", dataType = "int",required = true)})
    @PostMapping("/queryContractCoding")
    public RestMessage<Page<Map<String, Object>>> queryContractCoding(@RequestParam(value = "strparam",required = false) String strparam,
                                                                            @RequestParam(value = "pageNum") int pageNum,
                                                                            @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = publicQueryService.queryContractCoding(strparam, pageNum, pageSize);
        return RestMessage.newInstance(true, "获取成功！", data);
    }

    @ApiOperation(value = "查询销售合同编码", notes = "查询销售合同编码")
    @ApiImplicitParams({@ApiImplicitParam(name = "strparam", value = "查询条件",required = false),
            @ApiImplicitParam(name = "pageNum", value = "起始页码", dataType = "int",required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页长度", dataType = "int",required = true)})
    @PostMapping("/querySaleContractCoding")
    public RestMessage<Page<Map<String, Object>>> querySaleContractCoding(@RequestParam(value = "strparam",required = false) String strparam,
                                                                      @RequestParam(value = "pageNum") int pageNum,
                                                                      @RequestParam(value = "pageSize") int pageSize) {
        Page<Map<String, Object>> data = publicQueryService.querySaleContractCoding(strparam, pageNum, pageSize);
        return RestMessage.newInstance(true, "获取成功！", data);
    }
}
