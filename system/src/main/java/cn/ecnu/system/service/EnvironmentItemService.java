package cn.ecnu.system.service;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.pojo.EnvironmentItem;
import com.baomidou.mybatisplus.extension.service.IService;

/****
 * @Author: Ecnu
 * @Description: EnvironmentItemService
 * @Date 2022-05-07 13:07:16
 *****/
public interface EnvironmentItemService extends IService<EnvironmentItem> {

    /**
     * EnvironmentItem条件+分页查询
     * @param environmentItem 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageResult findPage(EnvironmentItem environmentItem, int pageNo, int size);

    PageResult findPage(int pageNo, int size);

}
