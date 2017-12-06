package com.winterchen.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户JPA
 * Created by winterchen on 2017/11/21.
 */
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<UserEntity, String> {

    /*@Cacheable(key = "#p0")
    UserEntity findByName(String name);*/

    //UserEntity findByNameAndAge(String name, Integer age);

    /*@Query("from User u where u.name=:name")
    UserEntity findUser(@Param("name") String name);*/

    /*@CachePut(key = "#p0.name")
    UserEntity save(UserEntity userEntity);*/

    /*@Cacheable(key = "#p0")
    UserEntity findByPhone(String phone);*/

    /**
     * 通过账号查找用户
     * @param userName
     * @return
     */
    @Query("FROM UserEntity u where u.userName=:userName")
    UserEntity findByUserName(@Param("userName") String userName);

    /**
     * 通过phone查找用户
     * @param phone
     * @return
     */
    @Query("FROM UserEntity u where u.phone=:phone")
    UserEntity findByPhone(@Param("phone") String phone);

    /**
     * 通过Email查找用户
     * @param email
     * @return
     */
    @Query("FROM UserEntity u where u.email=:email")
    UserEntity findByEmail(@Param("email") String email);


    /**
     * 通过QQ的唯一标示OpenId查找用户
     * @param openId
     * @return
     */
    @Query("FROM UserEntity u where u.QQOpenId=:openId")
    UserEntity findByQQOPenId(@Param("openId") String openId);

    /**
     * 更新QQ用户的资料
     * @param avatar
     * @param nikeName
     * @param openId
     * @param changeTime
     * @return
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.avatar=:avatar,u.nikeName=:nikename,u.lastChangeTime=:changeTime WHERE u.QQOpenId=:openId")
    UserEntity updateByQQOpenId(@Param("avatar") String avatar, @Param("nikename") String nikeName
            , @Param("openId") String openId, @Param("changeTime")Date changeTime);



}
