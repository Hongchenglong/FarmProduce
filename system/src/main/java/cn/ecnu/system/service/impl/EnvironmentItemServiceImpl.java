package cn.ecnu.system.service.impl;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.mapper.EnvironmentItemMapper;
import cn.ecnu.system.pojo.EnvironmentItem;
import cn.ecnu.system.service.EnvironmentItemService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/****
 * @Author: Ecnu
 * @Description: EnvironmentItemServiceImpl
 * @Date 2022-05-07 13:07:16
 *****/
@Service
@Slf4j
public class EnvironmentItemServiceImpl extends ServiceImpl<EnvironmentItemMapper, EnvironmentItem> implements EnvironmentItemService{

    /**
     * EnvironmentItem条件+分页查询
     * @param environmentItem 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult findPage(EnvironmentItem environmentItem, int pageNo, int size){
        Page<EnvironmentItem> page = new Page<>();
        page.setCurrent(pageNo).setSize(size);
        LambdaQueryWrapper<EnvironmentItem> wrapper = Wrappers.lambdaQuery(environmentItem).orderByDesc(EnvironmentItem::getCreateTime);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNo, int size){
        Page<EnvironmentItem> page = new Page<>();
        LambdaQueryWrapper<EnvironmentItem> wrapper = Wrappers.<EnvironmentItem>lambdaQuery().orderByDesc(EnvironmentItem::getCreateTime);//根据某个字段排序，自己根据实际情况小改一下
        page.setCurrent(pageNo).setSize(size);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }
}
