package com.prolog.time.management.service.impl;

import com.prolog.framework.core.pojo.Page;
import com.prolog.framework.dao.util.PageUtils;
import com.prolog.time.management.model.dto.ImpProjectInfoDto;
import com.prolog.time.management.mapper.ImpProjectInfoMapper;
import com.prolog.time.management.model.entity.ImpProjectInfo;
import com.prolog.time.management.Interface.ImpProjectInfoService;
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
public class ImpProjectInfoServiceImpl implements ImpProjectInfoService {
    @Autowired
    private ImpProjectInfoMapper impProjectInfoMapper;

    @Autowired
    private ContexToken contexToken;

    @Override
    @Transactional
    public int add(ImpProjectInfoDto impProjectInfoDto) {
        //dto数据赋值到javabean
        ImpProjectInfo impProjectInfo = new ImpProjectInfo();
        impProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
        impProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
        impProjectInfo.setIns_dt(new Date());
        BeanUtils.copyProperties(impProjectInfoDto, impProjectInfo);
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = impProjectInfoMapper.insert(impProjectInfo);
        if (flagNum2 == 0) {
            throw  new RuntimeException("保存失败，请重新添加！");
        }
        return 1;
    }

    @Override
    @Transactional
    public int addList(List<ImpProjectInfoDto> impProjectInfoDtos) {
        List<ImpProjectInfo> impProjectInfos = new ArrayList<>();
        for (ImpProjectInfoDto impProjectInfoDto : impProjectInfoDtos) {
            //dto数据赋值到javabean
            ImpProjectInfo impProjectInfo = new ImpProjectInfo();
            impProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
            impProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
            impProjectInfo.setIns_dt(new Date());
            BeanUtils.copyProperties(impProjectInfoDto, impProjectInfo);
            impProjectInfos.add(impProjectInfo);
        }
        //看数据插入是否成功插入，如果flagNum2=0，便未成功
        int flagNum2 = impProjectInfoMapper.insertByList(impProjectInfos);
        if (flagNum2 == 0) {
            throw  new RuntimeException("保存失败，请重新添加！");
        }
        return 1;
    }

    //删除记录
    @Override
    @Transactional
    public boolean deleteByIds(List<String> ids) throws Exception {
        int flg = impProjectInfoMapper.deleteByIds(ids);
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
        List<ImpProjectInfo> impProjectInfoList = new ArrayList();
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
                ImpProjectInfo impProjectInfo = new ImpProjectInfo();
                //验证非空列
                //所有列变量,字符串类型
                String str1 = getcellvalue(row.getCell(0));
                String str2 = getcellvalue(row.getCell(1));
                String str3 = getcellvalue(row.getCell(2));
                String str4 = getcellvalue(row.getCell(4));
                String str5 = getcellvalue(row.getCell(5));
                String str6 = getcellvalue(row.getCell(7));
                String str7 = getcellvalue(row.getCell(8));
                String str8 = getcellvalue(row.getCell(12));
                String str9 = getcellvalue(row.getCell(13));
                String str10 = getcellvalue(row.getCell(14));
                String str11 = getcellvalue(row.getCell(15));
                String str12 = getcellvalue(row.getCell(16));
                if ((str1 == null) || (str1.equals(""))) {
                    throw new RuntimeException("第" + i + "行售前立项存档编号不能为空！");
                }
                if (str2 == null || str2.equals("")) {
                    throw new RuntimeException("第" + i + "行区总不能为空！");
                }
                if (str3 == null || str3.equals("")) {
                    throw new RuntimeException("第" + i + "行销售经理不能为空！");
                }
                if (str4 == null || str4.equals("")) {
                    throw new RuntimeException("第" + i + "行合同名称不能为空！");
                }
                if (str5 == null || str5.equals("")) {
                    throw new RuntimeException("第" + i + "行公司代码不能为空！");
                }
                if (str6 == null || str6.equals("")) {
                    throw new RuntimeException("第" + i + "行省份不能为空！");
                }
                if (str7 == null || str7.equals("")) {
                    throw new RuntimeException("第" + i + "行省份代码不能为空！");
                }
                if (str8 == null || str8.equals("")) {
                    throw new RuntimeException("第" + i + "行合同类别不能为空！");
                }
                if (str9 == null || str9.equals("")) {
                    throw new RuntimeException("第" + i + "行类别不能为空！");
                }
                if (str10 == null || str10.equals("")) {
                    throw new RuntimeException("第" + i + "行合作/自主不能为空！");
                }
                if (str11 == null || str11.equals("")) {
                    throw new RuntimeException("第" + i + "行合同金额不能为空！");
                }
                if (str12 == null || str12.equals("")) {
                    throw new RuntimeException("第" + i + "行合同编号不能为空！");
                }
                //获取每一行数据并存入到impProjectInfo对象中
                impProjectInfo.setArc_no(str1);
                impProjectInfo.setRegion_manager(str2);
                impProjectInfo.setSale_manager(str3);
                impProjectInfo.setContract_name(str4);
                impProjectInfo.setCompany_code(str5);
                impProjectInfo.setProvince_name(str6);
                impProjectInfo.setProvince_no(str7);
                impProjectInfo.setContract_type(str8);
                impProjectInfo.setProject_type(str9);
                impProjectInfo.setCooperation_type(str10);
                impProjectInfo.setContract_money(Float.parseFloat(str11));
                impProjectInfo.setRegion_name(getcellvalue(row.getCell(3)));
                impProjectInfo.setSign_date(row.getCell(6).getDateCellValue());
                impProjectInfo.setChinese_project(getcellvalue(row.getCell(9)));
                impProjectInfo.setProject_code(getcellvalue(row.getCell(10)));
                impProjectInfo.setCnt(getcellvalue(row.getCell(11)));
                impProjectInfo.setContract_no(str12);
                impProjectInfo.setIns_usr_id(contexToken.getUser().getP_username());
                impProjectInfo.setIns_usr_name(contexToken.getUser().getP_nickname());
                impProjectInfo.setIns_dt(new Date());
                impProjectInfoList.add(impProjectInfo);
            }
        }
        is.close();
        int insertRt = impProjectInfoMapper.insertByList(impProjectInfoList);
        if (insertRt == impProjectInfoList.size()) {
            return insertRt + "";
        }
        return null;
    }

    @Override
    public Page<Map<String, Object>> queryImpProjectInfoData(Map<String, Object> param) {
        int pageNum = (int) param.get("pageNum");
        int pageSize = (int) param.get("pageSize");
        param.remove("pageNum");
        param.remove("pageSize");
        PageUtils.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = impProjectInfoMapper.queryImpProjectInfoData(param);
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
