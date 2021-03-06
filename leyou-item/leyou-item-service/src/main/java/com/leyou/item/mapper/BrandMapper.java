package com.leyou.item.mapper;

import com.leyou.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface BrandMapper extends Mapper<Brand> {

    /**
     * 新增商品分类和品牌中间表
     * @param cid 商品分类id
     * @param bid 品牌分类id
     * @return
     */
    @Insert("INSERT INTO tb_category_brand(category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);
}