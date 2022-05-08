package cn.ecnu.system.service.impl;

import cn.ecnu.common.exception.ErrorCode;
import cn.ecnu.common.exception.utils.ServiceExceptionUtil;
import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.convert.EnvironmentConvert;
import cn.ecnu.system.mapper.EnvironmentMapper;
import cn.ecnu.system.model.vo.EnvironmentVO;
import cn.ecnu.system.pojo.Environment;
import cn.ecnu.system.pojo.EnvironmentItem;
import cn.ecnu.system.pojo.Greenhouse;
import cn.ecnu.system.service.EnvironmentItemService;
import cn.ecnu.system.service.EnvironmentService;
import cn.ecnu.system.service.GreenhouseService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/****
 * @Author: Ecnu
 * @Description: EnvironmentServiceImpl
 * @Date 2022-05-06 18:40:08
 *****/
@Service
@Slf4j
public class EnvironmentServiceImpl extends ServiceImpl<EnvironmentMapper, Environment> implements EnvironmentService{
    @Autowired
    private EnvironmentItemService environmentItemService;
    @Autowired
    private GreenhouseService greenhouseService;

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

    @Override
    @Transactional
    public Boolean deleteEvn(Long id) {
        //删除环境变量项和环境
        environmentItemService.remove(Wrappers.<EnvironmentItem>lambdaQuery().eq(EnvironmentItem::getEnvnId,id));
        return this.removeById(id);
    }

    @Override
    public EnvironmentVO queryInfo(Environment environment) {
        log.info("\n###### queryInfo:{}",environment);
        Environment env = this.getOne(Wrappers.lambdaQuery(environment));
        if(env == null) throw ServiceExceptionUtil.exception(new ErrorCode(500,"环境变量查询为空"));
        List<EnvironmentItem> itemList = environmentItemService.list(Wrappers.<EnvironmentItem>lambdaQuery().eq(EnvironmentItem::getEnvnId, env.getId()));
        EnvironmentVO environmentVO = EnvironmentConvert.INSTANCE.convert(env);
        return environmentVO.setItemList(itemList);
    }

    @Override
    public List<EnvironmentVO> queryEnv(Integer days) {
        List<EnvironmentVO> environmentVOList = new ArrayList<>();

        LocalDate localDate = LocalDate.now();
        LocalDate historyDate = localDate.minusDays(days); //显示过去最早多久的日期
        
        List<Environment> environments = list(Wrappers.<Environment>lambdaQuery().eq(Environment::getDate, localDate));
        for(Environment env : environments){
            Map map = new HashMap();
            Greenhouse greenhouse = greenhouseService.getById(env.getGreenhouseId());
            EnvironmentVO environmentVO = EnvironmentConvert.INSTANCE.convert(env);
            List<EnvironmentItem> envItemList = environmentItemService.list(Wrappers.<EnvironmentItem>lambdaQuery().eq(EnvironmentItem::getEnvnId, env.getId()));
           
            //查出指定天数的历史数据
            List<Environment> envns = list(Wrappers.<Environment>lambdaQuery().eq(Environment::getGreenhouseId, env.getGreenhouseId())
                    .ge(Environment::getDate, historyDate).le(Environment::getDate, localDate));
            List<BigDecimal> airTemperature = envns.stream().map(Environment::getAirTemperature).collect(Collectors.toList());
            List<BigDecimal> airHumidity = envns.stream().map(Environment::getAirHumidity).collect(Collectors.toList());
            List<BigDecimal> co2 = envns.stream().map(Environment::getCo2).collect(Collectors.toList());
            List<BigDecimal> illuminance = envns.stream().map(Environment::getIlluminance).collect(Collectors.toList());
            List<BigDecimal> soilTemperature = envns.stream().map(Environment::getSoilTemperature).collect(Collectors.toList());
            List<BigDecimal> soilHumidity = envns.stream().map(Environment::getSoilHumidity).collect(Collectors.toList());
            
            map.put("airTemperature",airTemperature);
            map.put("airHumidity",airHumidity);
            map.put("co2",co2);
            map.put("illuminance",illuminance);
            map.put("soilTemperature",soilTemperature);
            map.put("soilHumidity",soilHumidity);
            //赋值环境变量项，大棚信息和历史数据
            environmentVO.setItemList(envItemList).setGreenhouse(greenhouse).setHistory(map);
            environmentVOList.add(environmentVO);
        }
        return environmentVOList;
    }
}
