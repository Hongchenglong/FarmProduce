package cn.ecnu;

import cn.ecnu.system.SystemApplication;
import cn.ecnu.system.pojo.Environment;
import cn.ecnu.system.service.EnvironmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = SystemApplication.class)
public class MainTest {
    @Autowired
    private EnvironmentService environmentService;

    @Test
    public EnvironmentService getEnvironmentService() {
        List<Long> ids = environmentService.list().stream().map(Environment::getId).collect(Collectors.toList());
        System.out.println(ids);
        environmentService.removeByIds(ids);
        return environmentService;
    }
}
