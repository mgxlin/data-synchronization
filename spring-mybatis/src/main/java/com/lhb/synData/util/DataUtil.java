package com.lhb.synData.util;

import com.lhb.synData.pojo.UserModel;

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
     * ????????? list ?????????,???his??????????????????????????????
     * @param his
     * @param local
     * @return
     */
    public static List<UserModel> distinct(List<UserModel> his, List<UserModel> local){
        long start = System.currentTimeMillis();
        HashSet<UserModel> hisSet = new HashSet<>(his); //?????????set?????????,?????????????????????
        HashSet<UserModel> localSet = new HashSet<>(local);
        ArrayList<UserModel> userModels = new ArrayList<>(); //?????????????????? ??????????????????????????????????????????
        for (UserModel userModel : hisSet) {
            if (!localSet.contains(userModel)){ //?????????????????????????????????????????????,???????????????????????????????????????????????????
                userModels.add(userModel);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return userModels;
    }
}
