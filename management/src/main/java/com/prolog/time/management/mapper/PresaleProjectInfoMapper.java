package com.prolog.time.management.mapper;

import com.prolog.time.management.model.entity.PresaleProjectInfo;

import java.util.List;
import java.util.Map;

public interface PresaleProjectInfoMapper {
    /**
     * 新增
     * @param presaleProjectInfo
     * @return 影响行数：如果提交人当天已经提交过，影响行数为0，新增成功，影响行数为1
     */
    int insert(PresaleProjectInfo presaleProjectInfo);


    /**
     * 删除
     * @param begDate 工作编号（主键）
     * @return 通过时间和申请人查询，如果存在信息返回查到行数大于0，则不能保存
     */
    int deleteByIds(List<String> ids);

    /**
     * 批量添加
     * @param List<PresaleProjectInfo>
     * @return 插入时返回的行数
     */
    int insertByList(List<PresaleProjectInfo> list);
    /**
     * 按条件查询
     * @param Map<String, Object>
     * @return 查询带分页信息的数据
     */
    List<Map<String, Object>> queryPresaleProjectInfoData(Map<String, Object> param);
}
