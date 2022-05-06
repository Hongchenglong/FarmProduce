package cn.ecnu.system.mapper;

import cn.ecnu.system.pojo.Environment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/****
 * @Author: Ecnu
 * @Description: EnvironmentDao
 * @Date 2022-05-06 18:40:08
 *****/
public interface EnvironmentMapper extends BaseMapper<Environment> {
    /**
     * 根据id查询已经逻辑删除的数据
     */
    @Select("select * from environment where id = #{id} and deleted=1")
    public Environment findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Select("select * from environment where deleted=1")
    public List<Environment> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    @Delete("delete from environment where id=#{id}")
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param environment
     */
    public Boolean updateLogicDelById(Environment environment);

}
