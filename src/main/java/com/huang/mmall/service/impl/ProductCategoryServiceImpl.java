package com.huang.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.mmall.entity.ProductCategory;
import com.huang.mmall.mapper.ProductCategoryMapper;
import com.huang.mmall.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.mmall.vo.ProductCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategoryVO> getAllProductCategoryVO() {
//        // 实体类转VO
//        List<ProductCategory> productCategoryList = productCategoryMapper.selectList(null);
//        // VO
//        List<ProductCategoryVO> productCategoryOVList = new ArrayList<>();
//        for (ProductCategory productCategory : productCategoryList) {
//            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
//            // 相当于复制属性。
//            BeanUtils.copyProperties(productCategory, productCategoryVO);
////            productCategoryVO.setId(productCategory.getId());
////            productCategoryVO.setName(productCategory.getName());
//            productCategoryOVList.add(productCategoryVO);
//        }
        //一级分类
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type", 1);
        List<ProductCategory> levelOne = productCategoryMapper.selectList(wrapper);

        //二级分类
        wrapper = new QueryWrapper();
        wrapper.eq("type", 2);
        List<ProductCategory> levelTwo = productCategoryMapper.selectList(wrapper);
        //三级分类
        wrapper = new QueryWrapper();
        wrapper.eq("type", 3);
        List<ProductCategory> levelThree = productCategoryMapper.selectList(wrapper);
        return null;
    }
}
