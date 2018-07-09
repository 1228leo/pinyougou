package com.pinyougou.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;

public interface BrandService {
    public List<TbBrand> findAll();
    /**
     * 分页查询品牌列表
     * @return
     */

    public PageResult<TbBrand> findPage(int pageNum, int pageSize,TbBrand brand);

    void saveBrand(TbBrand brand);

    TbBrand findById(Long id);

    void updateBrand(TbBrand brand);

    void deleteBrand(Long[] ids);

}
