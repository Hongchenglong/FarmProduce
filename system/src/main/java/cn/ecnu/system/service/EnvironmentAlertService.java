package cn.ecnu.system.service;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.pojo.EnvironmentAlert;
import com.baomidou.mybatisplus.extension.service.IService;

/****
 * @Author: Ecnu
 * @Description: EnvironmentAlertService
 * @Date 2022-05-10 15:06:54
 *****/
public interface EnvironmentAlertService extends IService<EnvironmentAlert> {

    /**
     * EnvironmentAlert条件+分页查询
     * @param environmentAlert 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageResult findPage(EnvironmentAlert environmentAlert, int pageNo, int size);

    PageResult findPage(int pageNo, int size);

}
