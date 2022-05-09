package cn.ecnu.system.task;

import cn.ecnu.system.model.vo.EnvironmentVO;
import cn.ecnu.system.pojo.Environment;
import cn.ecnu.system.pojo.EnvironmentItem;
import cn.ecnu.system.pojo.Greenhouse;
import cn.ecnu.system.service.DataService;
import cn.ecnu.system.service.EnvironmentItemService;
import cn.ecnu.system.service.EnvironmentService;
import cn.ecnu.system.service.GreenhouseService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class EnvironmentTask {
    @Autowired
    private GreenhouseService greenhouseService;
    @Autowired
    private EnvironmentService environmentService;
    @Autowired
    private EnvironmentItemService environmentItemService;
    @Autowired
    private DataService dataService;

    //每天0点1分添加新的一天所有大棚的环境数据
    @Scheduled(cron = "0 1 0 * * ?")
    public void addEnv(){
        List<Greenhouse> greenhouses = greenhouseService.list();
        for(Greenhouse greenhouse : greenhouses){
            try {
                dataService.addEnvnInfo(new Environment().setDate(LocalDate.now()).setGreenhouseId(greenhouse.getId()));
            } catch (Exception e) {
                log.info("\n###### {}",e.getMessage());;
            }
        }
        log.info("\n###### updateEnv 已更新{} 所有大棚数据，数量：{}", LocalDate.now(), greenhouses.size());

    }


    //一个小时更新一次
    @Scheduled(cron = "0 1 * * * ?")
    //每隔30秒实时更新大棚环境指标
    //@Scheduled(cron = "0/30 * * * * ?")
    public void synEnv(){
        //定时任务已在服务器上跑了，本地Windows不执行定时任务，防止频率太快日志刷屏且占用系统资源
        if(System.getProperty("os.name").contains("Windows")) return;

        List<Environment> environments = environmentService.list(Wrappers.lambdaQuery(new Environment().setDate(LocalDate.now())));
        List<Environment> environmentList = new ArrayList<>();

        for(Environment env : environments){
            try {
                Environment environment = dataService.synEnvnInfo(env);
                environmentList.add(environment);
            } catch (Exception e) {
                log.info("\n###### {}",e.getMessage());
            }
        }

        if(CollectionUtils.isNotEmpty(environmentList)){
            environmentService.updateBatchById(environmentList);
            log.info("\n###### synEnv 已实时更新{}个大棚数据，时间：{}", environmentList.size(), LocalDateTime.now());
        }
        //删除超过一个月的历史数据
        environmentService.remove(Wrappers.<Environment>lambdaQuery().lt(Environment::getDate,LocalDate.now().minusDays(31)));
        environmentItemService.remove(Wrappers.<EnvironmentItem>lambdaQuery().lt(EnvironmentItem::getTime,LocalDateTime.now().minusDays(31)));
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.name").contains("Windows"));
    }
}
