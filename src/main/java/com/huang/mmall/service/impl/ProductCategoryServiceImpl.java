package com.huang.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.mmall.entity.Product;
import com.huang.mmall.entity.ProductCategory;
import com.huang.mmall.mapper.ProductCategoryMapper;
import com.huang.mmall.mapper.ProductMapper;
import com.huang.mmall.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.mmall.vo.ProductCategoryVO;
import com.huang.mmall.vo.ProductVO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
@Service
@MapperScan("com.huang.mmall.mapper")
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductMapper productMapper;

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


        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type", 1);
        List<ProductCategory> levelOne = productCategoryMapper.selectList(wrapper);
        List<ProductCategoryVO> levelOneVO = levelOne.stream().map(e -> new ProductCategoryVO(e.getId(), e.getName())).collect(Collectors.toList());
        // 图片赋值
        // 商品信息赋值
        for (int i = 0; i < levelOneVO.size(); i++) {
            levelOneVO.get(i).setBannerImg("/images/banner" + i + ".png");
            levelOneVO.get(i).setTopImg("/images/top" + i + ".png");
            wrapper = new QueryWrapper();
            wrapper.eq("categorylevelone_id", levelOneVO.get(i).getId());
            List<Product> productList = productMapper.selectList(wrapper);
            List<ProductVO> productVOList = productList.stream()
                    .map(e -> new ProductVO(e.getId(),e.getName(),e.getPrice(),e.getFileName())).collect(Collectors.toList());
            levelOneVO.get(i).setProductVOList(productVOList);
        }

        for (ProductCategoryVO productCategoryOneVO : levelOneVO) {
            wrapper = new QueryWrapper();
            wrapper.eq("type", 2);
            wrapper.eq("parent_id", productCategoryOneVO.getId());
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(wrapper);
            List<ProductCategoryVO> levelTwoVO = levelTwo.stream().map(e -> new ProductCategoryVO(e.getId(), e.getName())).collect(Collectors.toList());
            productCategoryOneVO.setChildren(levelTwoVO);
            for (ProductCategoryVO productCategoryTwoVO : levelTwoVO) {
                wrapper = new QueryWrapper();
                wrapper.eq("type", 3);
                wrapper.eq("parent_id", productCategoryTwoVO.getId());
                List<ProductCategory> levelThree = productCategoryMapper.selectList(wrapper);
                List<ProductCategoryVO> levelThreeVO = levelThree.stream().map(e -> new ProductCategoryVO(e.getId(), e.getName())).collect(Collectors.toList());
                productCategoryTwoVO.setChildren(levelThreeVO);
            }
        }
        return levelOneVO;
    }
}
