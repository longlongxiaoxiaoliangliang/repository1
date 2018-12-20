package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.model.dto.PurProjectInfoDto;
import com.prolog.time.management.Interface.PurProjectInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Api(tags = "采购项目立项")
@RestController
@RequestMapping("/purchase")
public class PurProjectInfoController {
    @Autowired
    private PurProjectInfoService purProjectInfoService;

    @ApiOperation(value = "保存项目信息", notes = "根据页面传过来的参数保存项目信息")
    @PostMapping("/add")
    public RestMessage<String> add(@RequestBody PurProjectInfoDto purProjectInfoDto) throws Exception {
        int addFlag = purProjectInfoService.add(purProjectInfoDto);
        return RestMessage.newInstance(true, "保存成功！", purProjectInfoDto.getPur_contract_no());
    }

    @ApiOperation(value = "批量保存项目信息", notes = "根据页面传过来的参数保存项目信息")
    @PostMapping("/addList")
    public RestMessage<String> addByList(@RequestBody List<PurProjectInfoDto> purProjectInfoDtos) throws Exception {
        int addFlag = purProjectInfoService.addList(purProjectInfoDtos);
        return RestMessage.newInstance(true, "保存成功！", "");
    }

    @ApiOperation(value = "删除", notes = "可删除一条或多条记录")
    @PostMapping("/delete")
    public RestMessage<String> deleteByIds(String[] ids) throws Exception {
        List<String> idlists = new ArrayList<String>(Arrays.asList(ids));
        boolean deflag = purProjectInfoService.deleteByIds(idlists);
        return RestMessage.newInstance(true, "删除成功！", String.valueOf(idlists.size()));
    }

    @ApiOperation(value = "导出模板", notes = "固定模板")
    @PostMapping("/upload")
    public RestMessage<String> downloadExcel(HttpServletResponse res, HttpServletRequest req, String name) throws Exception {
        name = "采购项目立项模板";
        purProjectInfoService.downloadExcel(res, req, name);
        return RestMessage.newInstance(true, "导出模板成功！", "");

    }

    @ApiOperation(value = "导入模板", notes = "上传填好的excel模板")
    @PostMapping("/import")
    public RestMessage<String> importExcel(@RequestParam MultipartFile file) throws Exception {

        String data = purProjectInfoService.importExcel(file);
        return RestMessage.newInstance(true, "导入数据成功！", data);
    }

    @ApiOperation(value = "分页查询", notes = "立项信息分页查询")
    @ApiImplicitParam(name = "param", value = "{strparam:'查询条件'}")
    @PostMapping("/query")
    public RestMessage<Page<Map<String, Object>>> queryPurProjectInfoData(@RequestBody Map<String, Object> param) {
        Page<Map<String, Object>> page = purProjectInfoService.queryPurProjectInfoData(param);
        return RestMessage.newInstance(true, "查询成功！", page);
    }
}
