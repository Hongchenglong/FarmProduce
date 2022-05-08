package cn.ecnu.system.service;

import cn.ecnu.system.model.vo.EnvironmentVO;
import cn.ecnu.system.pojo.Environment;

public interface DataService {
    EnvironmentVO addEnvnInfo(Environment environment);

    Environment synEnvnInfo(Environment setGreenhouseId);
}
