package cn.ecnu.system.service.impl;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.mapper.EnvironmentMapper;
import cn.ecnu.system.pojo.Environment;
import cn.ecnu.system.service.EnvironmentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/****
 * @Author: Ecnu
 * @Description: EnvironmentServiceImpl
 * @Date 2022-05-06 18:40:08
 *****/
@Service
@Slf4j
public class EnvironmentServiceImpl extends ServiceImpl<EnvironmentMapper, Environment> implements EnvironmentService{

    /**
     * Environment条件+分页查询
     * @param environment 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult findPage(Environment environment, int pageNo, int size){
        Page<Environment> page = new Page<>();
        page.setCurrent(pageNo).setSize(size);
        LambdaQueryWrapper<Environment> wrapper = Wrappers.lambdaQuery(environment).orderByDesc(Environment::getCreateTime);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNo, int size){
        Page<Environment> page = new Page<>();
        LambdaQueryWrapper<Environment> wrapper = Wrappers.<Environment>lambdaQuery().orderByDesc(Environment::getCreateTime);//根据某个字段排序，自己根据实际情况小改一下
        page.setCurrent(pageNo).setSize(size);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }
}
