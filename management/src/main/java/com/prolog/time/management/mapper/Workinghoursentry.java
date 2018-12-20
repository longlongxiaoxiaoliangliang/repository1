package com.prolog.time.management.mapper;

import com.prolog.time.management.model.entity.Workinghours;

import java.util.List;
import java.util.Map;

public interface Workinghoursentry {
    /**
     * 新增
     * @param workinghours
     * @return 影响行数：如果提交人当天已经提交过，影响行数为0，新增成功，影响行数为1
     */
    int insert(Workinghours workinghours);

    /**
     * 通过开始或结束时间和申请人查询
     * @param begDate 日期时间字符串，creatBy 申请人
     * @return 通过时间和申请人查询，如果存在信息返回查到行数大于0，则不能保存
     */
    int countRecord(String begDateStr,String  creatBy,String projectid);

    /**
     * 删除
     * @param begDate 工作编号（主键）
     * @return 通过时间和申请人查询，如果存在信息返回查到行数大于0，则不能保存
     */
    int deleteByIds(List<String> ids);

    /**
     * 批量添加
     * @param List<workinghours>
     * @return 插入时返回的行数
     */
    int insertByList(List<Workinghours> list);
    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询信息的数据
     */
    List<Map<String, Object>> queryWorkinghoursData(Map<String, Object> param);

    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询所参加的所有项目
     */
    List<Map<String, Object>> queryProjectData(String userid,String strparam);

    /**
     * 修改审批状态为审批未通过
     * @param List<String>
     * @return 处理成功条数
     */
    int updateEntryData(Map<String, Object> param);
}
