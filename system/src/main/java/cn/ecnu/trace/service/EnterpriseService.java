package cn.ecnu.trace.service;

import cn.ecnu.trace.common.utils.PageResult;
import cn.ecnu.trace.common.utils.R;
import cn.ecnu.trace.pojo.Enterprise;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

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

    R<String> login(Enterprise enterprise);

    R<String> register(Enterprise enterprise);
}
