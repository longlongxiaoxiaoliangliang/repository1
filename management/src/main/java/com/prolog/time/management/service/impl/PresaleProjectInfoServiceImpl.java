package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.model.dto.PresaleProjectInfoDto;
import com.prolog.time.management.mapper.PresaleProjectInfoMapper;
import com.prolog.time.management.model.entity.PresaleProjectInfo;
import com.prolog.time.management.Interface.PresaleProjectInfoService;
import com.prolog.time.management.util.ContexToken;
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
public class PresaleProjectInfoServiceImpl implements PresaleProjectInfoService {
    @Autowired
    private PresaleProjectInfoMapper presaleProjectInfoMapper;

    @Autowired
    private ContexToken contexToken;
    //作为后缀用来生成存档编号
    public static int num;

    @Override
    @Transactional
    public int add(PresaleProjectInfoDto presaleProjectInfoDto) {
        //dto数据赋值到javabean
        PresaleProjectInfo presaleProjectInfo = new PresaleProjectInfo();
        presaleProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
        presaleProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
        presaleProjectInfo.setIns_dt(new Date());
        BeanUtils.copyProperties(presaleProjectInfoDto, presaleProjectInfo);
        SimpleDateFormat sdf1 = new SimpleDateFormat(
                "yyyyMMddHH");
        num++;
        if (num == 1000) {
            num = 0;
        }
        String nowStr = sdf1.format(new Date());
        nowStr = nowStr + num;
        presaleProjectInfo.setArc_no(nowStr);
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = presaleProjectInfoMapper.insert(presaleProjectInfo);
        if (flagNum2 == 0) {
           throw  new RuntimeException("保存失败，请重新添加！");
        }
        return 1;
    }

    @Override
    @Transactional
    public int addList(List<PresaleProjectInfoDto> presaleProjectInfoDtos) {
        List<PresaleProjectInfo> presaleProjectInfos = new ArrayList<>();
        for (PresaleProjectInfoDto presaleProjectInfoDto : presaleProjectInfoDtos) {
            //dto数据赋值到javabean
            PresaleProjectInfo presaleProjectInfo = new PresaleProjectInfo();
            presaleProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
            presaleProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
            presaleProjectInfo.setIns_dt(new Date());
            BeanUtils.copyProperties(presaleProjectInfoDto, presaleProjectInfo);
            SimpleDateFormat sdf1 = new SimpleDateFormat(
                    "yyyyMMddHH");
            num++;
            if (num == 1000) {
                num = 0;
            }
            String nowStr = sdf1.format(new Date());
            nowStr = nowStr + num;
            presaleProjectInfo.setArc_no(nowStr);
            presaleProjectInfos.add(presaleProjectInfo);
        }
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = presaleProjectInfoMapper.insertByList(presaleProjectInfos);
        if (flagNum2 == 0) {
            throw  new RuntimeException("保存失败，请重新添加！");
        }
        return 1;
    }

    //删除记录
    @Override
    @Transactional
    public boolean deleteByIds(List<String> ids) throws Exception {
        int flg = presaleProjectInfoMapper.deleteByIds(ids);
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
        List<PresaleProjectInfo> presaleProjectInfoList = new ArrayList();
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
                PresaleProjectInfo presaleProjectInfo = new PresaleProjectInfo();
                //验证非空列
                //所有列变量,字符串类型
                String str1 = getcellvalue(row.getCell(0));
                String str2 = getcellvalue(row.getCell(1));
                String str3 = getcellvalue(row.getCell(2));
                String str4 = getcellvalue(row.getCell(3));
                String str5 = getcellvalue(row.getCell(4));
                String str6 = getcellvalue(row.getCell(5));
                String str7 = getcellvalue(row.getCell(6));
                String str8 = getcellvalue(row.getCell(7));
                String str9 = getcellvalue(row.getCell(11));
                String str10 = getcellvalue(row.getCell(14));
                if ((str1 == null) || (str1.equals(""))) {
                    throw new RuntimeException("第" + i + "行区总不能为空！");
                }
                if (str2 == null || str2.equals("")) {
                    throw new RuntimeException("第" + i + "行销售经理不能为空！");
                }
                if (str3 == null || str3.equals("")) {
                    throw new RuntimeException("第" + i + "行区域名称不能为空！");
                }
                if (str4 == null || str4.equals("")) {
                    throw new RuntimeException("第" + i + "行省份不能为空！");
                }
                if (str5 == null || str5.equals("")) {
                    throw new RuntimeException("第" + i + "行省份代码不能为空！");
                }
                if (str6 == null || str6.equals("")) {
                    throw new RuntimeException("第" + i + "行客户名称不能为空！");
                }
                if (str7 == null || str7.equals("")) {
                    throw new RuntimeException("第" + i + "行客户代码不能为空！");
                }
                if (str8 == null || str8.equals("")) {
                    throw new RuntimeException("第" + i + "行项目名称不能为空！");
                }
                if (str9 == null || str9.equals("")) {
                    throw new RuntimeException("第" + i + "行立项次数不能为空！");
                }
                if (str10 == null || str10.equals("")) {
                    throw new RuntimeException("第" + i + "行项目编号不能为空！");
                }
                //获取每一行数据并存入到workinghours对象中
                presaleProjectInfo.setRegion_manager(str1);
                presaleProjectInfo.setSale_manager(str2);
                presaleProjectInfo.setRegion_name(str3);
                presaleProjectInfo.setProvince_name(str4);
                presaleProjectInfo.setProvince_no(str5);
                presaleProjectInfo.setCustomer_name(str6);
                presaleProjectInfo.setCustomer_no(str7);
                presaleProjectInfo.setProject_name(str8);
                presaleProjectInfo.setConsult_user(getcellvalue(row.getCell(8)));
                presaleProjectInfo.setConsultation_dep(getcellvalue(row.getCell(9)));
                presaleProjectInfo.setProject_status(getcellvalue(row.getCell(10)));
                presaleProjectInfo.setProject_cnt(str9);
                presaleProjectInfo.setCooperation_object(getcellvalue(row.getCell(12)));
                presaleProjectInfo.setInfo_source(getcellvalue(row.getCell(13)));
                presaleProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
                presaleProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
                presaleProjectInfo.setIns_dt(new Date());
                presaleProjectInfo.setProject_no(str10);
                SimpleDateFormat sdf1 = new SimpleDateFormat(
                        "yyyyMMddHH");
                num++;
                if (num == 1000) {
                    num = 0;
                }
                String nowStr = sdf1.format(new Date());
                nowStr = nowStr + num;
                presaleProjectInfo.setArc_no(nowStr);
                presaleProjectInfoList.add(presaleProjectInfo);
            }
        }
        is.close();
        int insertRt = presaleProjectInfoMapper.insertByList(presaleProjectInfoList);
        if (insertRt == presaleProjectInfoList.size()) {
            return insertRt + "";
        }
        return null;
    }

    @Override
    public Page<Map<String, Object>> queryPresaleProjectInfoData(Map<String, Object> param) {
        int pageNum = (int) param.get("pageNum");
        int pageSize = (int) param.get("pageSize");
        param.remove("pageNum");
        param.remove("pageSize");
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = presaleProjectInfoMapper.queryPresaleProjectInfoData(param);
        Page<Map<String, Object>> page = PageUtils.getPage(list);
        return page;
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
