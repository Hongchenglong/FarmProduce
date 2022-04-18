package cn.ecnu.trace.mapper;

import cn.ecnu.trace.pojo.Produce;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/****
 * @Author: Kili
 * @Description: ProduceDao
 * @Date 2022-04-18 21:25:40
 *****/
public interface ProduceMapper extends BaseMapper<Produce> {
    /**
     * 根据id查询已经逻辑删除的数据
     */
    @Select("select * from produce where id = #{id} and deleted=1")
    public Produce findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Select("select * from produce where deleted=1")
    public List<Produce> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    @Delete("delete from produce where id=#{id}")
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param produce
     */
    public Boolean updateLogicDelById(Produce produce);

}
