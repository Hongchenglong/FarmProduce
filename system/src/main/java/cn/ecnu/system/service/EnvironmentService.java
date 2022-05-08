package cn.ecnu.system.service;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.model.vo.EnvironmentVO;
import cn.ecnu.system.pojo.Environment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/****
 * @Author: Ecnu
 * @Description: EnvironmentService
 * @Date 2022-05-06 18:40:08
 *****/
public interface EnvironmentService extends IService<Environment> {

    /**
     * Environment条件+分页查询
     * @param environment 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageResult findPage(Environment environment, int pageNo, int size);

    PageResult findPage(int pageNo, int size);

    Boolean deleteEvn(Long id);

    EnvironmentVO queryInfo(Environment environment);

    List<EnvironmentVO> queryEnv(Integer days);
}
