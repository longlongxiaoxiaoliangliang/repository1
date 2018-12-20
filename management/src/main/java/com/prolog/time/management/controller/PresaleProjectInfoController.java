package com.prolog.time.management.controller;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.model.dto.PresaleProjectInfoDto;
import com.prolog.time.management.Interface.PresaleProjectInfoService;
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

@Api(tags = "售前项目立项")
@RestController
@RequestMapping("/sales")
public class PresaleProjectInfoController {
    @Autowired
    private PresaleProjectInfoService presaleProjectInfoService;

    @ApiOperation(value = "保存项目信息", notes = "根据页面传过来的参数保存项目信息")
    @PostMapping("/add")
    public RestMessage<String> add(@RequestBody PresaleProjectInfoDto presaleProjectInfoDto) throws Exception {
        int addFlag = presaleProjectInfoService.add(presaleProjectInfoDto);
        return RestMessage.newInstance(true, "保存成功！", presaleProjectInfoDto.getArc_no());
    }

    @ApiOperation(value = "批量保存项目信息", notes = "根据页面传过来的参数保存项目信息")
    @PostMapping("/addList")
    public RestMessage<String> addByList(@RequestBody List<PresaleProjectInfoDto> PresaleProjectInfoDtos) throws Exception {
        int addFlag = presaleProjectInfoService.addList(PresaleProjectInfoDtos);
        return RestMessage.newInstance(true, "保存成功！", "");
    }

    @ApiOperation(value = "删除", notes = "可删除一条或多条记录")
    @PostMapping("/delete")
    public RestMessage<String> deleteByIds(String[] ids) throws Exception {
        List<String> idlists = new ArrayList<String>(Arrays.asList(ids));
        boolean deflag = presaleProjectInfoService.deleteByIds(idlists);
        return RestMessage.newInstance(true, "删除成功！", String.valueOf(idlists.size()));
    }

    @ApiOperation(value = "导出模板", notes = "固定模板")
    @PostMapping("/upload")
    public RestMessage<String> downloadExcel(HttpServletResponse res, HttpServletRequest req, String name) throws Exception {
        name = "售前项目立项模板";
        presaleProjectInfoService.downloadExcel(res, req, name);
        return RestMessage.newInstance(true, "导出模板成功！", "");

    }

    @ApiOperation(value = "导入模板", notes = "上传填好的excel模板")
    @PostMapping("/import")
    public RestMessage<String> importExcel(@RequestParam MultipartFile file) throws Exception {

        String data = presaleProjectInfoService.importExcel(file);
        return RestMessage.newInstance(true, "导入数据成功！", data);
    }

    @ApiOperation(value = "分页查询", notes = "立项信息分页查询")
    @ApiImplicitParam(name = "param", value = "{bgtime:'开始时间',edtime:'结束时间',strparam:'查询条件'}")
    @PostMapping("/query")
    public RestMessage<Page<Map<String, Object>>> queryPresaleProjectInfoData(@RequestBody Map<String, Object> param) {
        Page<Map<String, Object>> page = presaleProjectInfoService.queryPresaleProjectInfoData(param);
        return RestMessage.newInstance(true, "查询成功！", page);
    }
}
