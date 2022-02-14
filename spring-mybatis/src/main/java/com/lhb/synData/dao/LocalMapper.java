package com.lhb.synData.dao;


import com.lhb.synData.pojo.UserModel;

import java.util.List;

/**
 * @author lhb
 * @date 2022/2/12 17:02
 */
public interface LocalMapper {
    /**
     * 获取本地的数据
     * @return
     */
    List<UserModel> getAll();

    /**
     * 导入所有数据
     * @param list
     * @return
     */
    int insertAllModel(List<UserModel> list);

    /**
     * 更新所有数据
     * @param insert
     * @return
     */
    int updateAllModel(List<UserModel> insert);

    /**
     * 一次性导入所有数据（存在的即更新，不存在的唯一索引值进行新增）
     * @param insert
     * @return
     */
    int updateAll(List<UserModel> insert);

}
