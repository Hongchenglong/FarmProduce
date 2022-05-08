package cn.ecnu.system.service;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.pojo.Trace;
import cn.ecnu.system.model.vo.TraceQueryVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/****
 * @Author: Kili
 * @Description: TraceService
 * @Date 2022-04-18 21:25:40
 *****/
public interface TraceService extends IService<Trace> {

    /**
     * Trace条件+分页查询
     * @param trace 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageResult findPage(Trace trace, int pageNo, int size);

    PageResult findPage(int pageNo, int size);

    /**
     * 根据id查询已经逻辑删除的数据
     */
    public Trace findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    public List<Trace> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * @param trace
     */
    public Boolean updateLogicDelById(Trace trace);

    /**
     * 根据时间间隔查找
     */
    R findByInterval(TraceQueryVO vo);

    List<Trace> selectTraceMap();

}
