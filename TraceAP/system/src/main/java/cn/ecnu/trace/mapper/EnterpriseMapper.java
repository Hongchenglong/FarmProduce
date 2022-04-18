package cn.ecnu.trace.mapper;

import cn.ecnu.trace.pojo.Enterprise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/****
 * @Author: Kili
 * @Description: EnterpriseDao
 * @Date 2022-04-18 21:25:40
 *****/
public interface EnterpriseMapper extends BaseMapper<Enterprise> {
    /**
     * 根据id查询已经逻辑删除的数据
     */
    @Select("select * from enterprise where id = #{id} and deleted=1")
    public Enterprise findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Select("select * from enterprise where deleted=1")
    public List<Enterprise> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    @Delete("delete from enterprise where id=#{id}")
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param enterprise
     */
    public Boolean updateLogicDelById(Enterprise enterprise);

}
