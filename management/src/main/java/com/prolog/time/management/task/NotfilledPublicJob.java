package com.prolog.time.management.task;

import com.prolog.time.management.mapper.NotfilledPublicMapper;
import com.prolog.time.management.util.ContexToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotfilledPublicJob {

    @Autowired
    private ContexToken contexToken;

    @Autowired
    private NotfilledPublicMapper notfilledPublicMapper;

    //定时任务，定时更新project_daily_record表中的数据
    @Transactional
    //@Scheduled(cron = "0 0/30 * * * * ")
    public void updatePublicData() throws Exception {
        notfilledPublicMapper.delete();
        notfilledPublicMapper.insert();
    }
}
