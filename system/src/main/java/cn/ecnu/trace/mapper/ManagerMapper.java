package cn.ecnu.trace.mapper;

import cn.ecnu.trace.pojo.Manager;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/****
 * @Author: Kili
 * @Description: ManagerDao
 * @Date 2022-04-18 21:25:40
 *****/
public interface ManagerMapper extends BaseMapper<Manager> {
    /**
     * 根据id查询已经逻辑删除的数据
     */
    @Select("select * from manager where id = #{id} and deleted=1")
    public Manager findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Select("select * from manager where deleted=1")
    public List<Manager> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    @Delete("delete from manager where id=#{id}")
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param manager
     */
    public Boolean updateLogicDelById(Manager manager);

}
