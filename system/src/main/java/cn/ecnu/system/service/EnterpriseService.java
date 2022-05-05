package cn.ecnu.system.service;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.pojo.Enterprise;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/****
 * @Author: Kili
 * @Description: EnterpriseService
 * @Date 2022-04-18 21:25:40
 *****/
public interface EnterpriseService extends IService<Enterprise> {

    /**
     * Enterprise条件+分页查询
     * @param enterprise 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageResult findPage(Enterprise enterprise, int pageNo, int size);

    PageResult findPage(int pageNo, int size);

    /**
     * 根据id查询已经逻辑删除的数据
     */
    public Enterprise findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    public List<Enterprise> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * @param enterprise
     */
    public Boolean updateLogicDelById(Enterprise enterprise);

    String login(Enterprise enterprise);

    R<String> register(Enterprise enterprise);
}
