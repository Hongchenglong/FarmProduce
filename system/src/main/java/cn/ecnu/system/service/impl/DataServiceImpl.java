package cn.ecnu.system.service.impl;

import cn.ecnu.common.utils.IdWorker;
import cn.ecnu.system.convert.EnvironmentConvert;
import cn.ecnu.system.model.vo.EnvironmentVO;
import cn.ecnu.system.pojo.Environment;
import cn.ecnu.system.pojo.EnvironmentAlert;
import cn.ecnu.system.pojo.EnvironmentItem;
import cn.ecnu.system.pojo.EnvironmentThreshold;
import cn.ecnu.system.service.*;
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
    @Autowired
    private EnvironmentThresholdService thresholdService;
    @Autowired
    private EnvironmentAlertService alertService;

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
        EnvironmentThreshold threshold = thresholdService.getOne(Wrappers.lambdaQuery(new EnvironmentThreshold().setGreenhouseId(environment.getGreenhouseId()).setOpen(1)));
        Boolean flag = false; //警告标志
        EnvironmentAlert alert = new EnvironmentAlert();

        double airTemperature = RandomUtil.randomDouble(10, 45);    //空气温度
        double airHumidity = RandomUtil.randomDouble(10, 90); //空气湿度
        double co2 = RandomUtil.randomDouble(200, 2000); //二氧化碳浓度
        double illuminance = RandomUtil.randomDouble(100, 1500); //光照度
        double soilTemperature = RandomUtil.randomDouble(10, 45); //土壤温度
        double soilHumidity = RandomUtil.randomDouble(10,90); //土壤湿度

        if(threshold != null){
            if(airTemperature > threshold.getAirTempMax().doubleValue() || airTemperature < threshold.getAirTempMin().doubleValue()) {
                flag = true;//超出阈值
                alert.setAirTemperature(new BigDecimal( airTemperature - threshold.getAirTempMin().doubleValue() < 0 ? (airTemperature - threshold.getAirTempMin().doubleValue()) : (airTemperature -threshold.getAirTempMax().doubleValue())));
            }else alert.setAirTemperature(new BigDecimal(0));
            if(airHumidity > threshold.getAirHumMax().doubleValue() || airHumidity < threshold.getAirHumMin().doubleValue()) {
                flag = true;//超出阈值
                alert.setAirHumidity(new BigDecimal( airHumidity - threshold.getAirHumMin().doubleValue() < 0 ? (airHumidity - threshold.getAirHumMin().doubleValue()) : (airHumidity - threshold.getAirHumMax().doubleValue())));
            }else alert.setAirHumidity(new BigDecimal(0));
            if(co2 > threshold.getCo2Max().doubleValue() || co2 < threshold.getCo2Min().doubleValue()) {
                flag = true;//超出阈值
                alert.setCo2(new BigDecimal( co2 - threshold.getCo2Min().doubleValue() < 0 ? (co2 - threshold.getCo2Min().doubleValue()) : (co2 - threshold.getCo2Max().doubleValue())));
            }else alert.setCo2(new BigDecimal(0));
            if(illuminance > threshold.getIlluminanceMax().doubleValue() || illuminance < threshold.getIlluminanceMin().doubleValue()) {
                flag = true;//超出阈值
                alert.setIlluminance(new BigDecimal( illuminance - threshold.getIlluminanceMin().doubleValue() < 0 ? (illuminance - threshold.getIlluminanceMin().doubleValue()) : (illuminance - threshold.getIlluminanceMax().doubleValue())));
            }else alert.setIlluminance(new BigDecimal(0));
            if(soilTemperature > threshold.getSoilTempMax().doubleValue() || soilTemperature < threshold.getSoilTempMin().doubleValue()) {
                flag = true;//超出阈值
                alert.setSoilTemperature(new BigDecimal( soilTemperature - threshold.getSoilTempMin().doubleValue() < 0 ? (soilTemperature - threshold.getSoilTempMin().doubleValue()) : (soilTemperature - threshold.getSoilTempMax().doubleValue())));
            }else alert.setSoilTemperature(new BigDecimal(0));
            if(soilHumidity > threshold.getSoilHumidMax().doubleValue() || soilHumidity < threshold.getSoilHumidMin().doubleValue()) {
                flag = true;//超出阈值
                alert.setSoilHumidity(new BigDecimal( soilHumidity - threshold.getSoilHumidMin().doubleValue() < 0 ? (soilHumidity - threshold.getSoilHumidMin().doubleValue()) : (soilHumidity - threshold.getSoilHumidMax().doubleValue())));
            }else alert.setSoilHumidity(new BigDecimal(0));
        }

        environment.setAirTemperature(new BigDecimal(airTemperature))
                .setAirHumidity(new BigDecimal(airHumidity))
                .setCo2(new BigDecimal(co2))
                .setIlluminance(new BigDecimal(illuminance))
                .setSoilTemperature(new BigDecimal(soilTemperature))
                .setSoilHumidity(new BigDecimal(soilHumidity)).setUpdateTime(LocalDateTime.now());
        if(flag) alertService.save(alert.setTime(LocalDateTime.now()).setGreenhouseId(environment.getGreenhouseId()));
        return environment;
    }

    public static void main(String[] args) {
        System.out.println(RandomUtil.randomDouble(25, 42));
    }
}
