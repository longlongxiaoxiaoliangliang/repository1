package com.prolog.time.management.Interface;

import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.model.dto.WorkinghoursEntryDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface WorkinghoursentryService {

    /**
     * 新增
     * @param workinghours
     * @return 影响行数：如果提交人当天已经提交过，影响行数为0，新增成功，影响行数为1
     */
    int add(WorkinghoursEntryDto workinghoursEntryDto);

    /**
     * 新增多条数据
     * @param workinghours
     * @return 影响行数：如果提交人当天已经提交过，影响行数为0，新增成功，影响行数为1
     */
    int addList(List<WorkinghoursEntryDto> workinghoursEntryDtos);

    /**
     * 删除
     * @param workingId
     * @return 根据workingId删除，支持批量删除
     */
    boolean deleteByIds(List<String> ids) throws Exception;

    /**
     * 导出模板
     * @param res req  name
     * @return
     */
    void downloadExcel(HttpServletResponse res, HttpServletRequest req, String name) throws Exception;

    /**
     * 导入数据
     * @param file
     * @return
     */
    String  importExcel(MultipartFile file) throws Exception;

    /**
     * 查询工时数据
     * @param map
     * @return List<map>
     */
    Page<Map<String, Object>> queryWorkinghoursData(Map<String, Object> param);

    /**
     * 查询所参与项目数据
     * @param map
     * @return List<map>
     */
    Page<Map<String, Object>> queryProjectData(String strparam,int pageSize,int pageNum);

    /**
     * 修改此条工时记录
     * @param workinghours
     * @return 影响行数：int
     */
    int updateEntryData(Map<String, Object> param);
}
