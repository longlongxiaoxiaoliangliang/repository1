package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.model.dto.PurProjectInfoDto;
import com.prolog.time.management.mapper.PurProjectInfoMapper;
import com.prolog.time.management.model.entity.PurProjectInfo;
import com.prolog.time.management.Interface.PurProjectInfoService;
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
public class PurProjectInfoServiceImpl implements PurProjectInfoService {
    @Autowired
    private PurProjectInfoMapper purProjectInfoMapper;

    @Autowired
    private ContexToken contexToken;

    @Override
    @Transactional
    public int add(PurProjectInfoDto purProjectInfoDto) {
        //dto数据赋值到javabean
        PurProjectInfo purProjectInfo = new PurProjectInfo();
        purProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
        purProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
        purProjectInfo.setIns_dt(new Date());
        BeanUtils.copyProperties(purProjectInfoDto, purProjectInfo);
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = purProjectInfoMapper.insert(purProjectInfo);
        if (flagNum2 == 0) {
            throw new RuntimeException("添加失败！请重新添加");
        }
        return  0;
    }

    @Override
    @Transactional
    public int addList(List<PurProjectInfoDto> purProjectInfoDtos) {
        List<PurProjectInfo> purProjectInfos = new ArrayList<>();
        for (PurProjectInfoDto purProjectInfoDto : purProjectInfoDtos) {
            //dto数据赋值到javabean
            PurProjectInfo purProjectInfo = new PurProjectInfo();
            purProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
            purProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
            purProjectInfo.setIns_dt(new Date());
            BeanUtils.copyProperties(purProjectInfoDto, purProjectInfo);
            purProjectInfos.add(purProjectInfo);
        }
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = purProjectInfoMapper.insertByList(purProjectInfos);
        if (flagNum2 == 0) {
            throw new RuntimeException("添加失败！请重新添加");
        }
        return 0;
    }

    //删除记录
    @Override
    @Transactional
    public boolean deleteByIds(List<String> ids) throws Exception {
        int flg = purProjectInfoMapper.deleteByIds(ids);
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
        List<PurProjectInfo> purProjectInfos = new ArrayList();
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
                PurProjectInfo purProjectInfo = new PurProjectInfo();
                //验证非空列
                //所有列变量,字符串类型
                String str1 = getcellvalue(row.getCell(0));
                String str2 = getcellvalue(row.getCell(1));
                String str3 = getcellvalue(row.getCell(2));
                String str4 = getcellvalue(row.getCell(7));
                if ((str1 == null) || (str1.equals(""))) {
                    throw new RuntimeException("第" + i + "行采购合同编号不能为空！");
                }
                if (str2 == null || str2.equals("")) {
                    throw new RuntimeException("第" + i + "行合同名称不能为空！");
                }
                if (str3 == null || str3.equals("")) {
                    throw new RuntimeException("第" + i + "行签订日期不能为空！");
                }
                if (str4 == null || str4.equals("")) {
                    throw new RuntimeException("第" + i + "行合同金额不能为空！");
                }
                //获取每一行数据并存入到impProjectInfo对象中
                purProjectInfo.setPur_contract_no(str1);
                purProjectInfo.setContract_name(str2);
                purProjectInfo.setSign_date(row.getCell(2).getDateCellValue());
                purProjectInfo.setContract_money(row.getCell(7).getNumericCellValue());
                purProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
                purProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
                purProjectInfo.setIns_dt(new Date());
                purProjectInfo.setSale_contract_no(getcellvalue(row.getCell(3)));
                purProjectInfo.setSale_contract_name(getcellvalue(row.getCell(4)));
                purProjectInfo.setFirst_party(getcellvalue(row.getCell(5)));
                purProjectInfo.setSecond_party(getcellvalue(row.getCell(6)));
                purProjectInfo.setPay_condition(getcellvalue(row.getCell(8)));
                purProjectInfos.add(purProjectInfo);
            }
        }
        is.close();
        int insertRt = purProjectInfoMapper.insertByList(purProjectInfos);
        if (insertRt == purProjectInfos.size()) {
            return insertRt + "";
        }
        return null;
    }

    @Override
    public Page<Map<String, Object>> queryPurProjectInfoData(Map<String, Object> param) {
        int pageNum = (int) param.get("pageNum");
        int pageSize = (int) param.get("pageSize");
        param.remove("pageNum");
        param.remove("pageSize");
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = purProjectInfoMapper.queryPurProjectInfoData(param);
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
