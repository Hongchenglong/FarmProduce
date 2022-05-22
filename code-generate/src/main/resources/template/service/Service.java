package $

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

}
