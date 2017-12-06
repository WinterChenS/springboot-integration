package com.winterchen.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * Created by winterchen on 2017/11/21.
 */
@Entity
public class UserEntity {

    @Id
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(nullable = true)
    private String userName;

    @Column(nullable = true)
    private Integer age;

    @Column(nullable = true)
    private Integer sex;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String roles;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String QQ;

    @Column(nullable = true)
    private String avatar;

    @Column(nullable = true)
    private Date createTime;

    @Column(nullable = true)
    private Date lastChangeTime;

    @Column(nullable = true)
    private String nikeName;

    @Column(nullable = true)
    private String QQOpenId;


    /**
     * @OneToOne：一对一关联
     * cascade：级联配置
     * CascadeType.PERSIST: 级联新建
     * CascadeType.REMOVE : 级联删除
     * CascadeType.REFRESH: 级联刷新
     * CascadeType.MERGE  : 级联更新
     * CascadeType.ALL    : 以上全部四项
     * @JoinColumn:主表外键字段
     * sid：Source所映射的表中的一个字段(会在User表创建一个cid字段,与Care外键关系)
     */
    @OneToOne(cascade = CascadeType.REFRESH)//使用CascadeType.ALL无法保存成功
    @JoinColumn(name = "sid", unique=true)
    private SourceEntity sourceEntity;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public SourceEntity getSourceEntity() {
        return sourceEntity;
    }

    public void setSourceEntity(SourceEntity sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(Date lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getQQOpenId() {
        return QQOpenId;
    }

    public void setQQOpenId(String QQOpenId) {
        this.QQOpenId = QQOpenId;
    }
}
