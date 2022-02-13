package com.lhb.singledata.task;

import com.lhb.singledata.dao.HisMapper;
import com.lhb.singledata.dao.LocalMapper;
import com.lhb.singledata.pojo.UserModel;
import com.lhb.singledata.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
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

    /**
     * 2秒执行一次
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void task() {
        List<UserModel> local = localMapper.getAll();
        List<UserModel> his = hisMapper.getAll();
        System.out.println("开始同步");

        List<UserModel> distinct = DataUtil.distinct(his,local);
        if (!distinct.isEmpty()){
            localMapper.updateAll(distinct);
        }

        System.out.println("同步结束");
    }
}
