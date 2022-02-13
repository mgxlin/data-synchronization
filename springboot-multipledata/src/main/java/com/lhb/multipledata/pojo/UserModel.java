package com.lhb.multipledata.pojo;

import java.util.Objects;

/**
 *
 * 传输信息实体类
 * 设置两个字段模拟
 *
 * 需要重写对象 equals hashcode方法,用来判断对象相等
 *
 * @author lhb
 * @date 2022/2/12 19:33
 */
public class UserModel {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(username, userModel.username) &&
                Objects.equals(password, userModel.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
