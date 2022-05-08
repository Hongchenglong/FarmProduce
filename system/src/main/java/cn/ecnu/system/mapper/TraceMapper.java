package cn.ecnu.system.mapper;

import cn.ecnu.system.model.vo.TraceAndProduceVO;
import cn.ecnu.system.pojo.Trace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TraceMapper extends BaseMapper<Trace> {
    /**
     * 根据id查询已经逻辑删除的数据
     */
    @Select("select * from trace where id = #{id} and deleted=1")
    public Trace findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Select("select * from trace where deleted=1")
    public List<Trace> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    @Delete("delete from trace where id=#{id}")
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param trace
     */
    public Boolean updateLogicDelById(Trace trace);

    List<TraceAndProduceVO> selectTraceMap();
}
