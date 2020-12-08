package com.huang.mmall.service;

import com.huang.mmall.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.mmall.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVO> getAllProductCategoryVO();
}
