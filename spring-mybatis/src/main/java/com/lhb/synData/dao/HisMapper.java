package com.lhb.synData.dao;

import com.lhb.synData.pojo.UserModel;

import java.util.List;

/**
 * @author lhb
 * @date 2022/2/12 16:12
 */
public interface HisMapper {

    /**
     * 获取his的数据
     * @return
     */
    List<UserModel> getAll();

}
