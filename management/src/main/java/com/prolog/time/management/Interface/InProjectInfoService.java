package com.prolog.time.management.Interface;

import com.prolog.framework.core.pojo.Page;
import com.prolog.time.management.model.dto.InProjectInfoDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface InProjectInfoService {

    /**
     * 新增
     * @param inProjectInfoDto
     * @return 影响行数：如果提交人当天已经提交过，影响行数为0，新增成功，影响行数为1
     */
    int add(InProjectInfoDto inProjectInfoDto);

    /**
     * 新增多条数据
     * @param impProjectInfoDtos
     * @return 影响行数：如果提交人当天已经提交过，影响行数为0，新增成功，影响行数为1
     */
    int addList(List<InProjectInfoDto> inProjectInfoDtos);

    /**
     * 删除
     * @param ids
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
     * 查询实施项目立项信息
     * @param map
     * @return List<map>
     */
    Page<Map<String, Object>> queryInProjectInfoData(Map<String, Object> param);
}
