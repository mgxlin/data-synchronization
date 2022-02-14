package com.lhb.synData.task;

import com.lhb.synData.dao.HisMapper;
import com.lhb.synData.dao.LocalMapper;
import com.lhb.synData.pojo.UserModel;
import com.lhb.synData.service.DataSyn;
import com.lhb.synData.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 开启定时任务
 *
 * @author lhb
 * @date 2022/2/13 13:35
 */
@Component
public class SpringTask {
    @Autowired
    private LocalMapper localMapper;
    @Autowired
    private HisMapper hisMapper;
    @Resource
    private DataSyn dataSyn;

    /**
     * 2秒执行一次
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void task() {
        List<UserModel> local = localMapper.getAll();
        List<UserModel> his = hisMapper.getAll();

        List<UserModel> distinct = dataSyn.distinct(his,local);
        if (!distinct.isEmpty()){
            localMapper.updateAll(distinct);
        }
    }

}
