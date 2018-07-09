package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.service.BrandService;
import entity.PageResult;
import entity.Result;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {



    @Reference
    private BrandService brandService;
    /**
     * 返回全部列表
     * @return
     */
    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }
    /**
     * 分页查询品牌
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult<TbBrand> findAll(Integer page, Integer size,@RequestBody TbBrand brand){


        return brandService.findPage(page,size,brand);
    }
    @RequestMapping("add")
    @ResponseBody
    public Result saveBrand(@RequestBody TbBrand brand){
        try {
            brandService.saveBrand(brand);
            return new Result(true,"保存品牌信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"保存品牌信息失败！");
        }
    }

    @RequestMapping("/findById")
    @ResponseBody
    public TbBrand findById(Long id){
        return brandService.findById(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result updateBrand(@RequestBody TbBrand brand){
        try {
            brandService.updateBrand(brand);
            return new Result(true,"更新品牌信息成功！");
        } catch (Exception e) {
            return new Result(false,"更新品牌信息失败！");
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteBrand(Long ids[]){
        try {
            brandService.deleteBrand(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
}
