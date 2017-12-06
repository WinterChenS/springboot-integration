package com.winterchen.service.user.impl;

import com.winterchen.constant.RoleConstant;
import com.winterchen.constant.UserConstant;
import com.winterchen.domain.QQUser;
import com.winterchen.domain.UserEntity;
import com.winterchen.domain.UserRepository;
import com.winterchen.exception.ArgumentIsNullException;
import com.winterchen.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 用户服务层
 * Created by winterchen on 2017/11/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity findUserByName(String userName) {
        if(StringUtils.isEmpty(userName))
            throw new ArgumentIsNullException("账号为空");
        UserEntity uu = userRepository.findByUserName(userName);
        if(uu == null){
            uu = userRepository.findByEmail(userName);

            if(uu == null){
                uu = userRepository.findByPhone(userName);
            }
        }

        //如果该账号是被禁用了返回null
        if(uu != null){

            if(uu.getStatus() == UserConstant.USER_STATUS_DISABLE){
                return null;
            }
        }
        return uu;
    }

    @Override
    public UserEntity findUserByQQOpenId(String openId) {

        if(StringUtils.isEmpty(openId))
            throw new ArgumentIsNullException("用户的OpenId为空");

        UserEntity userEntity = userRepository.findByQQOPenId(openId);

        return userEntity;
    }

    @Override
    public UserEntity updateUserByQQUser(QQUser user) {
        if(user == null)
            throw new ArgumentIsNullException("QQ用户信息为空");



        return userRepository.updateByQQOpenId(user.getAvatar(), user.getNickname(), user.getOpenId(), new Date());
    }

    @Override
    public UserEntity saveUserByQQUser(QQUser user) {

        if(user == null)
            throw new ArgumentIsNullException("QQ用户资料为空");

        UserEntity userEntity = new UserEntity();
        userEntity.setRoles(RoleConstant.ROLE_USER);
        userEntity.setStatus(UserConstant.USER_STATUS_DEFAULT);
        userEntity.setAvatar(user.getAvatar());
        userEntity.setCreateTime(new Date());
        userEntity.setNikeName(user.getNickname());
        userEntity.setQQOpenId(user.getOpenId());

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity saveUserEntity(UserEntity userEntity) {

        if(userEntity == null)
            throw new ArgumentIsNullException("用户参数为空");

        userEntity.setRoles(RoleConstant.ROLE_USER);
        userEntity.setStatus(UserConstant.USER_STATUS_DEFAULT);
        userEntity.setCreateTime(new Date());

        return userRepository.save(userEntity);
    }
}
