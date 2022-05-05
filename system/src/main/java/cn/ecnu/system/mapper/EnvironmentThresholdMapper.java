package cn.ecnu.system.mapper;

import cn.ecnu.system.pojo.EnvironmentThreshold;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/****
 * @Author: Kili
 * @Description: EnvironmentThresholdDao
 * @Date 2022-04-18 21:25:40
 *****/
public interface EnvironmentThresholdMapper extends BaseMapper<EnvironmentThreshold> {
    /**
     * 根据id查询已经逻辑删除的数据
     */
    @Select("select * from environment_threshold where id = #{id} and deleted=1")
    public EnvironmentThreshold findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Select("select * from environment_threshold where deleted=1")
    public List<EnvironmentThreshold> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    @Delete("delete from environment_threshold where id=#{id}")
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param environmentThreshold
     */
    public Boolean updateLogicDelById(EnvironmentThreshold environmentThreshold);

}
