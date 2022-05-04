package cn.ecnu.trace.mapper;

import cn.ecnu.trace.pojo.FarmProduce;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/****
 * @Author: Ecnu
 * @Description: FarmProduceDao
 * @Date 2022-04-22 23:40:46
 *****/
public interface FarmProduceMapper extends BaseMapper<FarmProduce> {
    /**
     * 根据id查询已经逻辑删除的数据
     */
    @Select("select * from farm_produce where id = #{id} and deleted=1")
    public FarmProduce findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Select("select * from farm_produce where deleted=1")
    public List<FarmProduce> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    @Delete("delete from farm_produce where id=#{id}")
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param farmProduce
     */
    public Boolean updateLogicDelById(FarmProduce farmProduce);

}
