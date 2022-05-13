package cn.ecnu.system.service.impl;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.mapper.EnvironmentAlertMapper;
import cn.ecnu.system.pojo.EnvironmentAlert;
import cn.ecnu.system.service.EnvironmentAlertService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/****
 * @Author: Ecnu
 * @Description: EnvironmentAlertServiceImpl
 * @Date 2022-05-10 15:06:54
 *****/
@Service
@Slf4j
public class EnvironmentAlertServiceImpl extends ServiceImpl<EnvironmentAlertMapper, EnvironmentAlert> implements EnvironmentAlertService{

    /**
     * EnvironmentAlert条件+分页查询
     * @param environmentAlert 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult findPage(EnvironmentAlert environmentAlert, int pageNo, int size){
        Page<EnvironmentAlert> page = new Page<>();
        page.setCurrent(pageNo).setSize(size);
        LambdaQueryWrapper<EnvironmentAlert> wrapper = Wrappers.lambdaQuery(environmentAlert).orderByDesc(EnvironmentAlert::getCreateTime);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNo, int size){
        Page<EnvironmentAlert> page = new Page<>();
        LambdaQueryWrapper<EnvironmentAlert> wrapper = Wrappers.<EnvironmentAlert>lambdaQuery().eq(EnvironmentAlert::getHandled, 0).orderByDesc(EnvironmentAlert::getCreateTime);//根据某个字段排序，自己根据实际情况小改一下
        page.setCurrent(pageNo).setSize(size);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }
}
