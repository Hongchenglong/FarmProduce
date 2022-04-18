package cn.ecnu.trace.mapper;

import cn.ecnu.trace.pojo.Qrcode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/****
 * @Author: Kili
 * @Description: QrcodeDao
 * @Date 2022-04-18 21:25:40
 *****/
public interface QrcodeMapper extends BaseMapper<Qrcode> {
    /**
     * 根据id查询已经逻辑删除的数据
     */
    @Select("select * from qrcode where id = #{id} and deleted=1")
    public Qrcode findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Select("select * from qrcode where deleted=1")
    public List<Qrcode> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    @Delete("delete from qrcode where id=#{id}")
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param qrcode
     */
    public Boolean updateLogicDelById(Qrcode qrcode);

}
