package com.lhb.singledata.service;

import com.lhb.singledata.pojo.UserModel;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author lhb
 * @date 2022/2/14 10:46
 */
@Component
public class DataSyn {
    /**
     * 取两个 list 的差集,将his的数据与本地进行比较
     * @param his
     * @param local
     * @return
     */
    public List<UserModel> distinct(List<UserModel> his, List<UserModel> local){

        HashSet<UserModel> hisSet = new HashSet<>(his); //添加到set集合中,用于快速取差集
        HashSet<UserModel> localSet = new HashSet<>(local);
        ArrayList<UserModel> userModels = new ArrayList<>(); //定义一个集合 用来存放需要更新或插入的数据
        for (UserModel userModel : hisSet) {
            if (!localSet.contains(userModel)){ //当本地数据库中不存在此条记录时,则添加到集合中用于后续更新或者插入
                userModels.add(userModel);
            }
        }

        return userModels;
    }
}
