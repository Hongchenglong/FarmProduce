package cn.ecnu.system.mapper;

import cn.ecnu.system.pojo.EnvironmentItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/****
 * @Author: Ecnu
 * @Description: EnvironmentItemDao
 * @Date 2022-05-07 13:07:16
 *****/
public interface EnvironmentItemMapper extends BaseMapper<EnvironmentItem> {
    /**
     * 根据id查询已经逻辑删除的数据
     */
    @Select("select * from environment_item where id = #{id} and deleted=1")
    public EnvironmentItem findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Select("select * from environment_item where deleted=1")
    public List<EnvironmentItem> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    @Delete("delete from environment_item where id=#{id}")
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param environmentItem
     */
    public Boolean updateLogicDelById(EnvironmentItem environmentItem);

}
