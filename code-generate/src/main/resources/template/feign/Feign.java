package ${package_feign};

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import ${package_pojo}.${Table};
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author: ${author}
 * @Description: ${Table}Feign
 * @Date ${create_time}
 *****/
@FeignClient(name="${serviceName}")
@RequestMapping("/${table}")
public interface ${Table}Feign {

    /***
     * ${Table}分页条件搜索实现
     * @param ${table}
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    R<PageResult> findPage(@RequestBody(required = false) ${Table} ${table}, @PathVariable  int page, @PathVariable  int size);

    /***
     * ${Table}分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    R<PageResult> findPage(@PathVariable  int page, @PathVariable  int size);

    /***
     * 多条件搜索品牌数据
     * @param ${table}
     * @return
     */
    @PostMapping(value = "/search" )
    R<List<${Table}>> findList(@RequestBody(required = false) ${Table} ${table});

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    R delete(@PathVariable ${keyType} id);

    /***
     * 修改${Table}数据
     * @param ${table}
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    R update(@RequestBody ${Table} ${table},@PathVariable ${keyType} id);

    /***
     * 新增${Table}数据
     * @param ${table}
     * @return
     */
    @PostMapping
    R add(@RequestBody ${Table} ${table});

    /***
     * 根据ID查询${Table}数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    R<${Table}> findById(@PathVariable ${keyType} id);

    /***
     * 查询${Table}全部数据
     * @return
     */
    @GetMapping
    R<List<${Table}>> findAll();
}