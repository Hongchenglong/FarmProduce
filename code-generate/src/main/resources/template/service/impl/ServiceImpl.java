package $

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/****
 * @Author: ${author}
 * @Description: ${Table}ServiceImpl
 * @Date ${create_time}
 *****/
@Service
@Slf4j
public class ${Table}ServiceImpl extends ServiceImpl<${Table}Mapper, ${Table}> implements ${Table}Service{

    /**
     * ${Table}条件+分页查询
     * @param ${table} 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult findPage(${Table} ${table}, int pageNo, int size){
        Page<${Table}> page = new Page<>();
        page.setCurrent(pageNo).setSize(size);
        LambdaQueryWrapper<${Table}> wrapper = Wrappers.lambdaQuery(${table}).orderByDesc(${Table}::getCreateTime);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNo, int size){
        Page<${Table}> page = new Page<>();
        LambdaQueryWrapper<${Table}> wrapper = Wrappers.<${Table}>lambdaQuery().orderByDesc(${Table}::getCreateTime);//根据某个字段排序，自己根据实际情况小改一下
        page.setCurrent(pageNo).setSize(size);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }
}
