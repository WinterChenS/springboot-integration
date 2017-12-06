package com.winterchen.utils;

/**
 * 错误代码描述
 * Created by winterchen on 2017/12/1.
 */
public interface ErrorCodeInfo {

    Integer SUCCESS = 0;

    /**
     * QQ用户的openid为空
     */
    Integer QQ_OPENID_IS_NULL = 100001;

    /**
     * 用户名为空
     */
    Integer USERNAME_IS_NULL = 100002;

    /**
     * QQ用户为空
     */
    Integer QQUSER_IS_NULL = 100003;

    /**
     * 用户名已经被注册
     */
    Integer USERNAME_EXIST = 100004;

}
