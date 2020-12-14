package com.huang.mmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.huang.mmall.enums.GenderEnum;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 登录名
     */
      private String loginName;

      /**
     * 用户名
     */
      private String userName;

      /**
     * 密码
     */
      private String password;

      /**
     * 性别(1:男 0：女)
     */
      private Integer gender;

      /**
     * 身份证号
     */
      private String identityCode;

      /**
     * 邮箱
     */
      private String email;

      /**
     * 手机
     */
      private String mobile;

    private String fileName;

      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getLoginName() {
        return loginName;
    }

      public void setLoginName(String loginName) {
          this.loginName = loginName;
      }
    
    public String getUserName() {
        return userName;
    }

      public void setUserName(String userName) {
          this.userName = userName;
      }
    
    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
          this.password = password;
      }
    
    public Integer getGender() {
        return gender;
    }

      public void setGender(Integer gender) {
          this.gender = gender;
      }
    
    public String getIdentityCode() {
        return identityCode;
    }

      public void setIdentityCode(String identityCode) {
          this.identityCode = identityCode;
      }
    
    public String getEmail() {
        return email;
    }

      public void setEmail(String email) {
          this.email = email;
      }
    
    public String getMobile() {
        return mobile;
    }

      public void setMobile(String mobile) {
          this.mobile = mobile;
      }
    
    public String getFileName() {
        return fileName;
    }

      public void setFileName(String fileName) {
          this.fileName = fileName;
      }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }

      public void setCreateTime(LocalDateTime createTime) {
          this.createTime = createTime;
      }
    
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

      public void setUpdateTime(LocalDateTime updateTime) {
          this.updateTime = updateTime;
      }

    @Override
    public String toString() {
        return "User{" +
              "id=" + id +
                  ", loginName=" + loginName +
                  ", userName=" + userName +
                  ", password=" + password +
                  ", gender=" + gender +
                  ", identityCode=" + identityCode +
                  ", email=" + email +
                  ", mobile=" + mobile +
                  ", fileName=" + fileName +
                  ", createTime=" + createTime +
                  ", updateTime=" + updateTime +
              "}";
    }
}
