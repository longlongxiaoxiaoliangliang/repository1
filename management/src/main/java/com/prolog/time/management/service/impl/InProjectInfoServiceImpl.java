package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.model.dto.InProjectInfoDto;
import com.prolog.time.management.mapper.InProjectInfoMapper;
import com.prolog.time.management.model.entity.InProjectInfo;
import com.prolog.time.management.Interface.InProjectInfoService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class InProjectInfoServiceImpl implements InProjectInfoService {
    @Autowired
    private InProjectInfoMapper inProjectInfoMapper;

    @Autowired
    private ContexToken contexToken;

    @Override
    @Transactional
    public int add(InProjectInfoDto inProjectInfoDto) {
        //dto数据赋值到javabean
        InProjectInfo inProjectInfo = new InProjectInfo();
        inProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
        inProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
        inProjectInfo.setIns_dt(new Date());
        BeanUtils.copyProperties(inProjectInfoDto, inProjectInfo);
        inProjectInfo.setProject_states("10");
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = inProjectInfoMapper.insert(inProjectInfo);
        if (flagNum2 == 0) {
            throw  new RuntimeException("保存失败，请重新添加！");
        }
        return 1;
    }

    @Override
    @Transactional
    public int addList(List<InProjectInfoDto> inProjectInfoDtos) {
        List<InProjectInfo> inProjectInfos = new ArrayList<>();
        for (InProjectInfoDto inProjectInfoDto : inProjectInfoDtos) {
            //dto数据赋值到javabean
            InProjectInfo inProjectInfo = new InProjectInfo();
            inProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
            inProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
            inProjectInfo.setIns_dt(new Date());
            BeanUtils.copyProperties(inProjectInfoDto, inProjectInfo);
            inProjectInfo.setProject_states("10");
            inProjectInfos.add(inProjectInfo);
        }
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = inProjectInfoMapper.insertByList(inProjectInfos);
        if (flagNum2 == 0) {
            throw  new RuntimeException("保存失败，请重新添加！");
        }
        return 1;
    }

    //删除记录
    @Override
    @Transactional
    public boolean deleteByIds(List<String> ids) throws Exception {
        int flg = inProjectInfoMapper.deleteByIds(ids);
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
        List<InProjectInfo> inProjectInfoList = new ArrayList();
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
        for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
            if (getcellvalue(row.getCell(0)) == null || getcellvalue(row.getCell(0)).equals("")) {
                continue;
            }
            if (i > 0) {
                InProjectInfo inProjectInfo = new InProjectInfo();
                //验证非空列
                //所有列变量,字符串类型
                String str1 = getcellvalue(row.getCell(0));
                String str2 = getcellvalue(row.getCell(2));
                String str3 = getcellvalue(row.getCell(3));
                String str4 = getcellvalue(row.getCell(4));
                String str5 = getcellvalue(row.getCell(6));
                String str6 = getcellvalue(row.getCell(7));
                String str7 = getcellvalue(row.getCell(8));
                String str8 = getcellvalue(row.getCell(9));
                String str9 = getcellvalue(row.getCell(13));
                String str10 = getcellvalue(row.getCell(14));
                String str11 = getcellvalue(row.getCell(15));
                if ((str1 == null) || (str1.equals(""))) {
                    throw new RuntimeException("第" + i + "行合同编码不能为空！");
                }
                if (str2 == null || str2.equals("")) {
                    throw new RuntimeException("第" + i + "行区总不能为空！");
                }
                if (str3 == null || str3.equals("")) {
                    throw new RuntimeException("第" + i + "行销售经理不能为空！");
                }
                if (str4 == null || str4.equals("")) {
                    throw new RuntimeException("第" + i + "行区域名称不能为空！");
                }
                if (str5 == null || str5.equals("")) {
                    throw new RuntimeException("第" + i + "行公司代码不能为空！");
                }
                if (str6 == null || str6.equals("")) {
                    throw new RuntimeException("第" + i + "行签订日期不能为空！");
                }
                if (str7 == null || str7.equals("")) {
                    throw new RuntimeException("第" + i + "行省份不能为空！");
                }
                if (str8 == null || str8.equals("")) {
                    throw new RuntimeException("第" + i + "行省份代码不能为空！");
                }
                if (str9 == null || str9.equals("")) {
                    throw new RuntimeException("第" + i + "行合同类别不能为空！");
                }
                if (str10 == null || str10.equals("")) {
                    throw new RuntimeException("第" + i + "行类别不能为空！");
                }
                if (str11 == null || str11.equals("")) {
                    throw new RuntimeException("第" + i + "行合作/自主不能为空！");
                }
                //获取每一行数据并存入到impProjectInfo对象中
                inProjectInfo.setContract_no(str1);
                inProjectInfo.setRegion_manager(str2);
                inProjectInfo.setSale_manager(str3);
                inProjectInfo.setRegion_name(str4);
                inProjectInfo.setCompany_code(str5);
                inProjectInfo.setSign_date(row.getCell(7).getDateCellValue());
                inProjectInfo.setProvince_name(str7);
                inProjectInfo.setProvince_no(str8);
                inProjectInfo.setContract_type(str9);
                inProjectInfo.setProject_type(str10);
                inProjectInfo.setCooperation_type(str11);
                inProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
                inProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
                inProjectInfo.setIns_dt(new Date());
                inProjectInfo.setProject_types(getcellvalue(row.getCell(1)));
                inProjectInfo.setContract_name(getcellvalue(row.getCell(5)));
                inProjectInfo.setChinese_project(getcellvalue(row.getCell(10)));
                inProjectInfo.setProject_code(getcellvalue(row.getCell(11)));
                inProjectInfo.setCnt((int) (row.getCell(12).getNumericCellValue()));
                inProjectInfo.setProject_states("10");
                inProjectInfoList.add(inProjectInfo);
            }
        }
        is.close();
        int insertRt = inProjectInfoMapper.insertByList(inProjectInfoList);
        if (insertRt == inProjectInfoList.size()) {
            return insertRt + "";
        }
        return null;
    }

    @Override
    public Page<Map<String, Object>> queryInProjectInfoData(Map<String, Object> param) {
        int pageNum = (int) param.get("pageNum");
        int pageSize = (int) param.get("pageSize");
        param.remove("pageNum");
        param.remove("pageSize");
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = inProjectInfoMapper.queryInProjectInfoData(param);
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
