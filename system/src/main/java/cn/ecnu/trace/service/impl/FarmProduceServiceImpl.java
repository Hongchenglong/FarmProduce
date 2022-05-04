package cn.ecnu.trace.service.impl;

import cn.ecnu.trace.common.utils.PageResult;
import cn.ecnu.trace.mapper.FarmProduceMapper;
import cn.ecnu.trace.pojo.FarmProduce;
import cn.ecnu.trace.service.FarmProduceService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/****
 * @Author: Ecnu
 * @Description: FarmProduceServiceImpl
 * @Date 2022-04-22 23:40:46
 *****/
@Service
@Slf4j
public class FarmProduceServiceImpl extends ServiceImpl<FarmProduceMapper, FarmProduce> implements FarmProduceService{

    /**
     * FarmProduce条件+分页查询
     * @param farmProduce 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult findPage(FarmProduce farmProduce, int pageNo, int size){
        Page<FarmProduce> page = new Page<>();
        page.setCurrent(pageNo).setSize(size);
        LambdaQueryWrapper<FarmProduce> wrapper = Wrappers.lambdaQuery(farmProduce).orderByDesc(FarmProduce::getCreateTime);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNo, int size){
        Page<FarmProduce> page = new Page<>();
        LambdaQueryWrapper<FarmProduce> wrapper = Wrappers.<FarmProduce>lambdaQuery().orderByDesc(FarmProduce::getCreateTime);//根据某个字段排序，自己根据实际情况小改一下
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
    public FarmProduce findLogicDelById(Long id) {
        return baseMapper.findLogicDelById(id);
    }

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Override
    public List<FarmProduce> findAllLogicDel() {
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
     * @param farmProduce
     */
    public Boolean updateLogicDelById(FarmProduce farmProduce){
        return baseMapper.updateLogicDelById(farmProduce);
    };
}
