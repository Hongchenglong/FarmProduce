package cn.ecnu.system.service.impl;

import cn.ecnu.common.utils.IdWorker;
import cn.ecnu.system.convert.EnvironmentConvert;
import cn.ecnu.system.model.vo.EnvironmentVO;
import cn.ecnu.system.pojo.Environment;
import cn.ecnu.system.pojo.EnvironmentItem;
import cn.ecnu.system.service.DataService;
import cn.ecnu.system.service.EnvironmentItemService;
import cn.ecnu.system.service.EnvironmentService;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataServiceImpl implements DataService {
    @Autowired
    private EnvironmentService environmentService;
    @Autowired
    private EnvironmentItemService environmentItemService;
    @Autowired
    private IdWorker idWorker;

    @Override
    public EnvironmentVO addEnvnInfo(Environment environment) {
        log.info("\n###### addEnvnInfo:{}", environment);
        List<EnvironmentItem> environmentItemList = new ArrayList<>();
        Environment env = environmentService.getOne(Wrappers.lambdaQuery(environment));
        if(env != null) throw new RuntimeException("当前日期的大棚已存在数据");
        //获取当天零点时间
        LocalDateTime zeroTime = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);

        double airTemperatureCount = 0;
        double airHumidityCount = 0;
        double co2Count = 0;
        double illuminanceCount = 0;
        double soilTemperatureCount = 0;
        double soilHumiditCount = 0;

        environment.setId(idWorker.nextId()); //先设置id，方便environmentItem赋值
        for(int i = 0; i < 6; i++){
            EnvironmentItem environmentItem = new EnvironmentItem();
            double airTemperature = RandomUtil.randomDouble(25, 42);    //空气温度
            double airHumidity = RandomUtil.randomDouble(30, 80); //空气湿度
            double co2 = RandomUtil.randomDouble(500, 1200); //二氧化碳浓度
            double illuminance = RandomUtil.randomDouble(300, 800); //光照度
            double soilTemperature = RandomUtil.randomDouble(25, 40); //土壤温度
            double soilHumidity = RandomUtil.randomDouble(30,90); //土壤湿度

            airTemperatureCount += airTemperature;
            airHumidityCount += airHumidity;
            co2Count += co2;
            illuminanceCount += illuminance;
            soilTemperatureCount += soilTemperature;
            soilHumiditCount += soilHumidity;

            environmentItem.setAirTemperature(airTemperature).setAirHumidity(airHumidity).setCo2(co2).setIlluminance(illuminance)
                    .setSoilTemperature(soilTemperature).setSoilHumidity(soilHumidity).setEnvnId(environment.getId()).setTime(zeroTime.plusHours(4 * i));
            environmentItemList.add(environmentItem);
        }
        environment.setAirTemperature(new BigDecimal(airTemperatureCount).divide(new BigDecimal(6),2))
                .setAirHumidity(new BigDecimal(airHumidityCount).divide(new BigDecimal(6),2))
                .setCo2(new BigDecimal(co2Count).divide(new BigDecimal(6),2))
                .setIlluminance(new BigDecimal(illuminanceCount).divide(new BigDecimal(6),2))
                .setSoilTemperature(new BigDecimal(soilTemperatureCount).divide(new BigDecimal(6),2))
                .setSoilHumidity(new BigDecimal(soilHumiditCount).divide(new BigDecimal(6),2));

        environmentService.save(environment);
        environmentItemService.saveBatch(environmentItemList);
        EnvironmentVO environmentVO = EnvironmentConvert.INSTANCE.convert(environment);
        return environmentVO.setItemList(environmentItemList);
    }

    @Override
    public Environment synEnvnInfo(Environment environment) {

        double airTemperature = RandomUtil.randomDouble(25, 42);    //空气温度
        double airHumidity = RandomUtil.randomDouble(30, 80); //空气湿度
        double co2 = RandomUtil.randomDouble(500, 1200); //二氧化碳浓度
        double illuminance = RandomUtil.randomDouble(300, 800); //光照度
        double soilTemperature = RandomUtil.randomDouble(25, 40); //土壤温度
        double soilHumidity = RandomUtil.randomDouble(30,90); //土壤湿度

        environment.setAirTemperature(new BigDecimal(airTemperature))
                .setAirHumidity(new BigDecimal(airHumidity))
                .setCo2(new BigDecimal(co2))
                .setIlluminance(new BigDecimal(illuminance))
                .setSoilTemperature(new BigDecimal(soilTemperature))
                .setSoilHumidity(new BigDecimal(soilHumidity)).setUpdateTime(LocalDateTime.now());
        return environment;
    }

    public static void main(String[] args) {
        System.out.println(RandomUtil.randomDouble(25, 42));
    }
}
