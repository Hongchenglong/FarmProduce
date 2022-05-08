package cn.ecnu.system.controller;

import cn.ecnu.common.exception.ErrorCode;
import cn.ecnu.common.exception.utils.ServiceExceptionUtil;
import cn.ecnu.common.utils.IdWorker;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.convert.EnvironmentConvert;
import cn.ecnu.system.model.dto.EnvironmentDTO;
import cn.ecnu.system.pojo.Environment;
import cn.ecnu.system.pojo.Greenhouse;
import cn.ecnu.system.service.DataService;
import cn.ecnu.system.service.EnvironmentItemService;
import cn.ecnu.system.service.EnvironmentService;
import cn.ecnu.system.service.GreenhouseService;
import cn.hutool.core.util.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("data")
@Slf4j
@Api(value = "测试用", tags = "后端测试用,造数据使用")
public class DataController {
    @Autowired
    private EnvironmentService environmentService;
    @Autowired
    private EnvironmentItemService environmentItemService;
    @Autowired
    private GreenhouseService greenhouseService;
    @Autowired
    private DataService dataService;
    @Autowired
    private IdWorker idWorker;

    @RequestMapping("err")
    public void err(){
        throw ServiceExceptionUtil.exception(new ErrorCode(401,"抛出一个带错误码的自定义异常，401未授权"));
    }

    @ApiOperation("添加环境变量数据")
    @PostMapping("env")
    @Transactional
    public R addEnvnInfo(@RequestBody EnvironmentDTO environmentDTO){
        Environment environment = EnvironmentConvert.INSTANCE.convert(environmentDTO);
        Greenhouse greenhouse = greenhouseService.getById(environmentDTO.getGreenhouseId());
        if(greenhouse == null) throw ServiceExceptionUtil.exception(new ErrorCode(500, "温室大棚不存在"));
        return R.ok(dataService.addEnvnInfo(environment));
    }

    @ApiOperation("创建历史数据")
    @GetMapping("days/{num}")
    public R<List<LocalDate>> addHistory(@ApiParam("创建历史数据") @PathVariable Integer num){
        if(num > 31) throw new RuntimeException("只造近一个月的数据");
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        List<Long> ids = greenhouseService.list().stream().map(Greenhouse::getId).collect(Collectors.toList());
        for(int i = 0 ; i < num ; i++){
            try {
                LocalDate ldt = localDate.minusDays(i);
                dateList.add(ldt);
                ids.forEach(id ->{
                    dataService.addEnvnInfo(new Environment().setDate(ldt).setGreenhouseId(id));
                });
            } catch (Exception e) {
                log.error("\n###### {}", e.getMessage());;
            }
        }
        return R.ok(dateList);
    }

    @PostMapping("testDate")
    @ApiOperation("测试接收前端date")
    public R testDate(@RequestBody EnvironmentDTO environmentDTO){
        Environment environment = EnvironmentConvert.INSTANCE.convert(environmentDTO);
        System.out.println(environmentDTO);
        return R.ok();
    }

    public static void main(String[] args) {
        System.out.println(RandomUtil.randomBigDecimal(new BigDecimal(25), new BigDecimal(40)));
    }


}
