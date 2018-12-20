package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.model.dto.WorkinghoursEntryDto;
import com.prolog.time.management.mapper.Workinghoursentry;
import com.prolog.time.management.model.entity.Workinghours;
import com.prolog.time.management.Interface.WorkinghoursentryService;
import com.prolog.time.management.util.ContexToken;
import com.prolog.user.model.PlgFxUser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WorkinghoursentryServiceImpl implements WorkinghoursentryService {
    @Autowired
    private Workinghoursentry workinghoursentry;

    @Autowired
    private ContexToken contexToken;

    @Override
    @Transactional
    public int add(WorkinghoursEntryDto workinghoursEntryDto) {
        //dto数据赋值到javabean
        Workinghours workinghours = new Workinghours();
        PlgFxUser plgFxUser = contexToken.getUser();
        String usrid = plgFxUser.getP_username();
        String usrname = plgFxUser.getP_nickname();
        workinghours.setCreatBy(usrid);
        workinghours.setInsusrname(usrname);
        workinghours.setCreatDate(new Date());
        BeanUtils.copyProperties(workinghoursEntryDto, workinghours);
        workinghours.setAuditStatus("0");
        String remark1Str = workinghoursEntryDto.getRemark1();
        if (remark1Str.equals("是")) {
            workinghoursEntryDto.setRemark1("1");
        } else {
            workinghoursEntryDto.setRemark1("0");
        }
        //获取开始和结束时间，转化为日期字符串比较
        Date bgTime = workinghours.getBegDate();
        Date edTime = workinghours.getEndDate();
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy/MM/dd");
        String bgStr = sdf.format(bgTime);
        String edStr = sdf.format(edTime);
        //如果开始时间和结束时间不在一天，便返回0
        if (!bgStr.equals(edStr)) {
            throw new RuntimeException("开始时间和结束时间必须在同一天！");
        }

        //查询在某天该用户是否有工时记录，如果有flagNum>0,则返回-1
        int flagNum = workinghoursentry.countRecord(bgStr, usrid,workinghours.getProjectId());
        if (flagNum > 0) {
            throw new RuntimeException("当天已经录入过工时！");
        }
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = workinghoursentry.insert(workinghours);
        if (flagNum2 == 0) {
            throw new RuntimeException("保存失败，请重试！");
        }
        return 0;
    }

    @Override
    @Transactional
    public int addList(List<WorkinghoursEntryDto> workinghoursEntryDtos) {
        List<Workinghours> workinghoursList = new ArrayList<>();
        for (WorkinghoursEntryDto workinghoursEntryDto : workinghoursEntryDtos) {
            //dto数据赋值到javabean
            Workinghours workinghours = new Workinghours();
            PlgFxUser plgFxUser = contexToken.getUser();
            String usrid = plgFxUser.getP_username();
            String usrname = plgFxUser.getP_nickname();
            workinghours.setCreatBy(usrid);
            workinghours.setInsusrname(usrname);
            workinghours.setCreatDate(new Date());
            BeanUtils.copyProperties(workinghoursEntryDto, workinghours);
            workinghours.setAuditStatus("0");
            String remark1Str = workinghoursEntryDto.getRemark1();
            if (remark1Str.equals("是")) {
                workinghoursEntryDto.setRemark1("1");
            } else {
                workinghoursEntryDto.setRemark1("0");
            }
            workinghoursList.add(workinghours);
            //获取开始和结束时间，转化为日期字符串比较
            Date bgTime = workinghours.getBegDate();
            Date edTime = workinghours.getEndDate();
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "yyyy/MM/dd");
            String bgStr = sdf.format(bgTime);
            String edStr = sdf.format(edTime);
            //如果开始时间和结束时间不在一天，便返回0
            if (!bgStr.equals(edStr)) {
                throw new RuntimeException("开始时间和结束时间必须在同一天！");
            }
            //查询在某天该用户是否有工时记录，如果有flagNum>0,则返回-1
            int flagNum = workinghoursentry.countRecord(bgStr, usrid,workinghours.getProjectId());
            if (flagNum > 0) {
                throw new RuntimeException("当天已经录入过工时！");
            }
        }
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = workinghoursentry.insertByList(workinghoursList);
        if (flagNum2 == 0) {
            throw new RuntimeException("保存失败，请重试！");
        }
        return 0;
    }

    //删除记录
    @Override
    @Transactional
    public boolean deleteByIds(List<String> ids) throws Exception {
        int flg = workinghoursentry.deleteByIds(ids);
        if (flg != ids.size()) {
            throw new RuntimeException("审核通过的记录不能删除！");
        }
        return true;
    }

    @Override
    public void downloadExcel(HttpServletResponse res, HttpServletRequest req, String name) throws Exception {
        String fileName = name + ".xlsx";
        ServletOutputStream out;
        res.setContentType("multipart/form-data");
        res.setCharacterEncoding("UTF-8");
        res.setContentType("text/html");
        String filePath = getClass().getResource("/template/" + fileName).getPath();
        String userAgent = req.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            fileName = new String((fileName).getBytes("UTF-8"), "ISO-8859-1");
        }
        filePath = URLDecoder.decode(filePath, "UTF-8");
        res.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        FileInputStream inputStream = new FileInputStream(filePath);
        out = res.getOutputStream();
        int b = 0;
        byte[] buffer = new byte[1024];
        while ((b = inputStream.read(buffer)) != -1) {
// 4.写到输出流(out)中
            out.write(buffer, 0, b);
        }
        inputStream.close();

        if (out != null) {
            out.flush();
            out.close();
        }
    }

    @Override
    @Transactional
    public String importExcel(MultipartFile file) throws Exception {
        Workbook wb = null;
        List<Workinghours> entryDtoListList = new ArrayList();
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        InputStream is = file.getInputStream();
        if (".xls".equals(fileType)) {
            wb = new HSSFWorkbook(is);
        } else if (".xlsx".equals(fileType)) {
            wb = WorkbookFactory.create(is);//new XSSFWorkbook(file.getInputStream());
        } else {
            throw new RuntimeException("请上传excel文件！");
        }
        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        //用来存项目开始日期字符串，如果上传数据中，有某天数据重复，便返回错误
        List listDateStr = new ArrayList();
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
            if (getcellvalue(row.getCell(0)) == null || getcellvalue(row.getCell(0)).equals("")) {
                continue;
            }
            if (i > 0) {
                Workinghours workinghours = new Workinghours();
                //验证非空列
                //所有列变量,字符串类型
                String str1 = getcellvalue(row.getCell(0));
                String str2 = getcellvalue(row.getCell(1));
                String str3 = getcellvalue(row.getCell(2));
                String str4 = getcellvalue(row.getCell(3));
                String str7 = getcellvalue(row.getCell(6));
                String str8 = getcellvalue(row.getCell(7));
                if ((str1 == null) || (str1.equals(""))) {
                    throw new RuntimeException("第" + i + "行售前编号/项目编号不能为空！");
                }
                if (str2 == null || str2.equals("")) {
                    throw new RuntimeException("第" + i + "行售前名称/项目名称不能为空！");
                }
                if (str3 == null || str3.equals("")) {
                    throw new RuntimeException("第" + i + "行项目类型不能为空！");
                }
                if (str4 == null || str4.equals("")) {
                    throw new RuntimeException("第" + i + "行是否休息不能为空！");
                }
                if (str7 == null || str7.equals("")) {
                    throw new RuntimeException("第" + i + "行工作地点不能为空！");
                }
                if (str8 == null || str8.equals("")) {
                    throw new RuntimeException("第" + i + "行工作内容不能为空！");
                }
                //获取每一行数据并存入到workinghours对象中
                workinghours.setProjectId(str1);
                workinghours.setProjectName(str2);
                workinghours.setRojectType(str3);
                String remark = "";
                if (str4.equals("是")) {
                    remark = "1";
                } else {
                    remark = "0";
                }
                workinghours.setRemark1(remark);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                if ((getcellvalue(row.getCell(4)) == null) || (getcellvalue(row.getCell(4)).equals(""))) {
                    throw new RuntimeException("第" + i + "行开始时间不能为空！");
                }
                if ((getcellvalue(row.getCell(5)) == null) || (getcellvalue(row.getCell(5)).equals(""))) {
                    throw new RuntimeException("第" + i + "行结束时间不能为空！");
                }
                Date parseTimeB = row.getCell(4).getDateCellValue();
                Date parseTimeE = row.getCell(5).getDateCellValue();
                SimpleDateFormat sdf1 = new SimpleDateFormat(
                        "yyyy/MM/dd");
                String bgStr = sdf1.format(parseTimeB);
                String edStr = sdf1.format(parseTimeE);
                String todayStr = sdf1.format(new Date());
                Date parsetoday = sdf1.parse(todayStr);
                Date parsebg = sdf1.parse(bgStr);
                if (parsetoday.before(parsebg)) {
                    throw new RuntimeException("第" + i + "行" + bgStr + "日期不能晚于当前日期！");
                }
                if (listDateStr.size() > 0 && listDateStr.contains(bgStr)) {
                    throw new RuntimeException("第" + i + "行" + bgStr + "日期重复！");
                }
                listDateStr.add(bgStr);
                //如果开始时间和结束时间不在一天，便返回0
                if (!bgStr.equals(edStr)) {
                    throw new RuntimeException("第" + i + "行" + "开始时间和结束时间必须在同一天");
                }
                workinghours.setBegDate(parseTimeB);
                workinghours.setEndDate(parseTimeE);
                workinghours.setWorkSite(str7);
                workinghours.setWorkingContext(str8);
                PlgFxUser plgFxUser = contexToken.getUser();
                String usrid = plgFxUser.getP_username();
                String usrname = plgFxUser.getP_nickname();
                workinghours.setCreatBy(usrid);
                workinghours.setInsusrname(usrname);
                workinghours.setCreatDate(new Date());
                workinghours.setAuditStatus("0");
                entryDtoListList.add(workinghours);
                //查询在某天该用户是否有工时记录，如果有flagNum>0,则返回-1
                int flagNum = workinghoursentry.countRecord(bgStr, usrid,str1);
                if (flagNum > 0) {
                    throw new RuntimeException("第" + i + "行" + bgStr + "当天已经录入过工时！");
                }
            }
        }
        is.close();
        int insertRt = workinghoursentry.insertByList(entryDtoListList);
        if (insertRt == entryDtoListList.size()) {
            return insertRt + "";
        }
        return null;
    }

    @Override
    public Page<Map<String, Object>> queryWorkinghoursData(Map<String, Object> param) {
        int pageNum = (int) param.get("pageNum");
        int pageSize = (int) param.get("pageSize");
        param.remove("pageNum");
        param.remove("pageSize");
        String usrid = contexToken.getUser().getP_username();
        param.put("usrid", usrid);
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = workinghoursentry.queryWorkinghoursData(param);
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }

    @Override
    public Page<Map<String, Object>> queryProjectData(String strparam, int pageSize, int pageNum) {
        String usrid = contexToken.getUser().getP_username();
        //String usrid = "lijw";
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = workinghoursentry.queryProjectData(usrid, strparam);
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
    }

    @Override
    @Transactional
    public int updateEntryData(Map<String, Object> param) {
        String userid = contexToken.getUser().getP_username();
        String remark1Str = param.get("remark1").toString();
        if(remark1Str.equals("是")){
            param.put("remark1","1");
        }else {
            param.put("remark1","0");
        }
        param.put("userid",userid);
        int flgnum = workinghoursentry.updateEntryData(param);
        if(flgnum == 0){
            throw new RuntimeException("修改失败，请重试！");
        }
        return flgnum;
    }


    private static String getcellvalue(Cell cell) {
        String cellValue = "";
        if (null != cell) {
            // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
            switch (cell.getCellType()) {
                case 0:
                    cellValue = String.valueOf((int) cell.getNumericCellValue());
                    break;
                case 1:
                    cellValue = cell.getStringCellValue();
                    break;
                case 2:
                    cellValue = cell.getNumericCellValue() + "";
                    break;
                case 3:
                    cellValue = "";
                    break;
                case 4:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case 5:
                    cellValue = String.valueOf(cell.getErrorCellValue());
                    break;
            }
        }
        return cellValue.trim();
    }
}
