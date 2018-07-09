package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BrandService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;
    @Override
    public List<TbBrand> findAll() {
        return brandMapper.select(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize,TbBrand brand) {
        PageResult<TbBrand> result = new PageResult<TbBrand>();
        //设置分页条件
        PageHelper.startPage(pageNum, pageSize);

        //构建查询条件
        Example example = new Example(TbBrand.class);
        Example.Criteria criteria = example.createCriteria();
        //如果名称不为空
        if (StringUtils.isNotEmpty(brand.getName())) {
            criteria.andLike("name", "%" + brand.getName() + "%");
        }
        //如果首字母不为空
        if (StringUtils.isNotEmpty(brand.getFirstChar())) {
            criteria.andEqualTo("firstChar", brand.getFirstChar());
        }

        //查询数据
        List<TbBrand> brands = brandMapper.selectByExample(example);
        //保存数据列表
        result.setRows(brands);

        //获取总记录数
        PageInfo<TbBrand> info = new PageInfo<TbBrand>(brands);
        result.setTotal(info.getTotal());

        return result;
    }


    @Override
    public void saveBrand(TbBrand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public TbBrand findById(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateBrand(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void deleteBrand(Long[] ids) {
        List longs = Arrays.asList(ids);
        //构建查询条件
        Example example = new Example(TbBrand.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", longs);

        //跟据查询条件删除数据
        brandMapper.deleteByExample(example);

    }

}
