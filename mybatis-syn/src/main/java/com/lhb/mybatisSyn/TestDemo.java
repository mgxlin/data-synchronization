package com.lhb.mybatisSyn;

import com.lhb.mybatisSyn.dao.HisMapper;
import com.lhb.mybatisSyn.dao.LocalMapper;
import com.lhb.mybatisSyn.pojo.UserModel;
import com.lhb.mybatisSyn.util.DataUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author lhb
 * @date 2022/2/12 15:58
 */
public class TestDemo {
    public static void main(String[] args) throws IOException {
        String path = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(path);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = factory.openSession();
        HisMapper hisMapper = sqlSession.getMapper(HisMapper.class);
        LocalMapper localMapper = sqlSession.getMapper(LocalMapper.class);

        List<UserModel> his = hisMapper.getAll();
        List<UserModel> local = localMapper.getAll();
        System.out.println("对比中");

        List<UserModel> distinct = DataUtil.distinct(his, local);
        if (distinct.size()>0){
            localMapper.updateAll(distinct);
        }

        sqlSession.commit();

        System.out.println("对比结束");

    }

}
