package ${package_service};

import ${package_pojo}.${Table};
import cn.ecnu.trace.common.utils.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/****
 * @Author: ${author}
 * @Description: ${Table}Service
 * @Date ${create_time}
 *****/
public interface ${Table}Service extends IService<${Table}> {

    /**
     * ${Table}条件+分页查询
     * @param ${table} 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageResult findPage(${Table} ${table}, int pageNo, int size);

    PageResult findPage(int pageNo, int size);

    /**
     * 根据id查询已经逻辑删除的数据
     */
    public ${Table} findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    public List<${Table}> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * @param ${table}
     */
    public Boolean updateLogicDelById(${Table} ${table});

}
