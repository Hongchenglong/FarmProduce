package cn.ecnu.system.service.impl;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.mapper.GreenhouseMapper;
import cn.ecnu.system.pojo.Greenhouse;
import cn.ecnu.system.service.GreenhouseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/****
 * @Author: Kili
 * @Description: GreenhouseServiceImpl
 * @Date 2022-04-18 21:25:40
 *****/
@Service
@Slf4j
public class GreenhouseServiceImpl extends ServiceImpl<GreenhouseMapper, Greenhouse> implements GreenhouseService{

    /**
     * Greenhouse条件+分页查询
     * @param greenhouse 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult findPage(Greenhouse greenhouse, int pageNo, int size){
        Page<Greenhouse> page = new Page<>();
        page.setCurrent(pageNo).setSize(size);
        LambdaQueryWrapper<Greenhouse> wrapper = Wrappers.lambdaQuery(greenhouse).orderByDesc(Greenhouse::getCreateTime);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNo, int size){
        Page<Greenhouse> page = new Page<>();
        LambdaQueryWrapper<Greenhouse> wrapper = Wrappers.<Greenhouse>lambdaQuery().orderByDesc(Greenhouse::getCreateTime);//根据某个字段排序，自己根据实际情况小改一下
        page.setCurrent(pageNo).setSize(size);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    /**
     * 根据id查询已经逻辑删除的数据
     * 需在配置文件设置好逻辑删除字段
     * 并在该字段上加上 @TableLogic注解
     */
    @Override
    public Greenhouse findLogicDelById(Long id) {
        return baseMapper.findLogicDelById(id);
    }

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Override
    public List<Greenhouse> findAllLogicDel() {
        return baseMapper.findAllLogicDel();
    }


    /**
     * 根据id物理删除数据
     */
    public Boolean deleteTablelogic(Long id){
        return baseMapper.deleteTablelogic(id);
    };

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param greenhouse
     */
    public Boolean updateLogicDelById(Greenhouse greenhouse){
        return baseMapper.updateLogicDelById(greenhouse);
    };
}
