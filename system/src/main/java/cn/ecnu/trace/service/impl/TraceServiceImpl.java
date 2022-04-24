package cn.ecnu.trace.service.impl;

import cn.ecnu.trace.common.utils.PageResult;
import cn.ecnu.trace.common.utils.R;
import cn.ecnu.trace.mapper.TraceMapper;
import cn.ecnu.trace.pojo.Trace;
import cn.ecnu.trace.service.TraceService;
import cn.ecnu.trace.vo.TraceQueryVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/****
 * @Author: Kili
 * @Description: TraceServiceImpl
 * @Date 2022-04-18 21:25:40
 *****/
@Service
@Slf4j
public class TraceServiceImpl extends ServiceImpl<TraceMapper, Trace> implements TraceService{

    /**
     * Trace条件+分页查询
     * @param trace 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult findPage(Trace trace, int pageNo, int size){
        Page<Trace> page = new Page<>();
        page.setCurrent(pageNo).setSize(size);
        LambdaQueryWrapper<Trace> wrapper = Wrappers.lambdaQuery(trace).orderByDesc(Trace::getCreateTime);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNo, int size){
        Page<Trace> page = new Page<>();
        LambdaQueryWrapper<Trace> wrapper = Wrappers.<Trace>lambdaQuery().orderByDesc(Trace::getCreateTime);//根据某个字段排序，自己根据实际情况小改一下
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
    public Trace findLogicDelById(Long id) {
        return baseMapper.findLogicDelById(id);
    }

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Override
    public List<Trace> findAllLogicDel() {
        return baseMapper.findAllLogicDel();
    }


    /**
     * 根据id物理删除数据
     */
    public Boolean deleteTablelogic(Long id){
        return baseMapper.deleteTablelogic(id);
    }

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param trace
     */
    public Boolean updateLogicDelById(Trace trace){
        return baseMapper.updateLogicDelById(trace);
    }

    public R findByInterval(TraceQueryVO vo) {
        LocalDate startDate = vo.getStartDate().toLocalDate();
        LocalDate endDate = vo.getEndDate().toLocalDate().plusDays(1);
        if (startDate.isAfter(endDate)) {
            return R.error("开始时间不得大于结束时间");
        }
        HashMap<LocalDate, Long> map = new HashMap<>();

        while (!startDate.isEqual(endDate)) {
            QueryWrapper<Trace> wrapper = new QueryWrapper<>();
            wrapper.eq("update_time", startDate);
            Long count = baseMapper.selectCount(wrapper);
            map.put(startDate, count);
            startDate = startDate.plusDays(1);
        }
        return R.ok(map);
    }

}
