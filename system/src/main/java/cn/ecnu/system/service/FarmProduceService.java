package cn.ecnu.system.service;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.pojo.FarmProduce;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/****
 * @Author: Ecnu
 * @Description: FarmProduceService
 * @Date 2022-04-22 23:40:46
 *****/
public interface FarmProduceService extends IService<FarmProduce> {

    /**
     * FarmProduce条件+分页查询
     * @param farmProduce 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageResult findPage(FarmProduce farmProduce, int pageNo, int size);

    PageResult findPage(int pageNo, int size);

    /**
     * 根据id查询已经逻辑删除的数据
     */
    public FarmProduce findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    public List<FarmProduce> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * @param farmProduce
     */
    public Boolean updateLogicDelById(FarmProduce farmProduce);

}
