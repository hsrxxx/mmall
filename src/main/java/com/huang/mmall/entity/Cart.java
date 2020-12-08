package com.huang.mmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
public class Cart implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer productId;

    private Integer quantity;

    private Integer cost;

    private Integer userId;

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
    
    public Integer getProductId() {
        return productId;
    }

      public void setProductId(Integer productId) {
          this.productId = productId;
      }
    
    public Integer getQuantity() {
        return quantity;
    }

      public void setQuantity(Integer quantity) {
          this.quantity = quantity;
      }
    
    public Integer getCost() {
        return cost;
    }

      public void setCost(Integer cost) {
          this.cost = cost;
      }
    
    public Integer getUserId() {
        return userId;
    }

      public void setUserId(Integer userId) {
          this.userId = userId;
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
        return "Cart{" +
              "id=" + id +
                  ", productId=" + productId +
                  ", quantity=" + quantity +
                  ", cost=" + cost +
                  ", userId=" + userId +
                  ", createTime=" + createTime +
                  ", updateTime=" + updateTime +
              "}";
    }
}
