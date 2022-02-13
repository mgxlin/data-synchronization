package com.lhb.mybatisSyn.util;

import com.lhb.mybatisSyn.pojo.UserModel;

import java.util.*;

/**
 * @author lhb
 * @date 2022/2/13 21:18
 */
public class DataUtil {
    public static List<UserModel> distinct3(List<UserModel> oldList, List<UserModel> newList){
        long start = System.currentTimeMillis();
        HashMap<UserModel, Integer> oldMap = new HashMap<>();
        for (UserModel user : oldList) {
            oldMap.put(user,1);
        }

        HashMap<UserModel, Integer> newMap = new HashMap<>();
        for (UserModel user : newList) {
            newMap.put(user,1);
        }

        ArrayList<UserModel> users = new ArrayList<>();
        Set<Map.Entry<UserModel, Integer>> entries = oldMap.entrySet();
        for (Map.Entry<UserModel, Integer> entry : entries) {
            if (!newMap.containsKey(entry.getKey())) {
                users.add(entry.getKey());
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return users;
    }

    public static HashMap<String, List<UserModel>> distinct2(List<UserModel> oldList, List<UserModel> newList){
        long start = System.currentTimeMillis();
        HashSet<UserModel> oldSet = new HashSet<>(oldList);
        HashSet<UserModel> newSet = new HashSet<>(newList);

        ArrayList<UserModel> updateUsers = new ArrayList<>();
        ArrayList<UserModel> insertUsers = new ArrayList<>();

        HashMap<String, List<UserModel>> map = new HashMap<>();

        for (UserModel userModel : oldSet){
            if (!newSet.contains(userModel)) {
                boolean flag = false;
                for (UserModel user : newSet){
                    if (user.getPassword().equals(userModel.getPassword())){
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    updateUsers.add(userModel);
                }else {
                    insertUsers.add(userModel);
                }
            }
        }
        map.put("update",updateUsers);
        map.put("insert",insertUsers);

        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return map;
    }

    /**
     * 取两个 list 的差集,将his的数据与本地进行比较
     * @param his
     * @param local
     * @return
     */
    public static List<UserModel> distinct(List<UserModel> his, List<UserModel> local){
        long start = System.currentTimeMillis();
        HashSet<UserModel> hisSet = new HashSet<>(his); //添加到set集合中,用于快速取差集
        HashSet<UserModel> localSet = new HashSet<>(local);
        ArrayList<UserModel> userModels = new ArrayList<>(); //定义一个集合 用来存放需要更新或插入的数据
        for (UserModel userModel : hisSet) {
            if (!localSet.contains(userModel)){ //当本地数据库中不存在此条记录时,则添加到集合中用于后续更新或者插入
                userModels.add(userModel);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return userModels;
    }
}
