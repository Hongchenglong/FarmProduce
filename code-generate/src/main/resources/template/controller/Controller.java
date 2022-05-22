package $

import $;
import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author: ${author}
 * @Description: ${Table}Controller
 * @Date ${create_time}
 *****/
<#if swagger==true>@Api(value = "${Table}Controller", tags = "${Table}Controller")</#if>
@RestController
@RequestMapping("/${table}")
@CrossOrigin
@Slf4j
public class ${Table}Controller {

    @Autowired
    private ${Table}Service ${table}Service;

    /***
     * ${Table}分页条件搜索实现
     * @param ${table}
     * @param pageNo
     * @param size
     * @return
     */
    <#if swagger==true>
    @ApiOperation("${Table}条件分页查询")
    </#if>
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) <#if swagger==true>@ApiParam(name = "${Table}对象",value = "传入JSON数据",required = false)</#if> ${Table} ${table}, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( ${table}Service.findPage(${table}, pageNo, size));
    }

    /***
     * ${Table}分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    <#if swagger==true>
    @ApiOperation("${Table}分页查询")
    </#if>
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( ${table}Service.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    <#if swagger==true>
    @ApiOperation("${Table}根据ID删除")
    </#if>
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable ${keyType} id){
        //调用${Table}Service实现根据主键删除
        return R.ok(${table}Service.removeById(id));
    }

    /***
     * 修改${Table}数据
     * @param ${table}
     * @return
     */
    <#if swagger==true>
    @ApiOperation("${Table}根据ID修改")
    </#if>
    @PutMapping
    public R update(@RequestBody <#if swagger==true>@ApiParam(name = "${Table}对象",value = "传入JSON数据",required = false)</#if> ${Table} ${table}){
        //调用${Table}Service实现修改${Table}
        return R.ok(${table}Service.updateById(${table}));
    }

    /***
     * 新增${Table}数据
     * @param ${table}
     * @return
     */
    <#if swagger==true>
    @ApiOperation("${Table}添加")
    </#if>
    @PostMapping
    public R add(@RequestBody  <#if swagger==true>@ApiParam(name = "${Table}对象",value = "传入JSON数据",required = true)</#if> ${Table} ${table}){
        //调用${Table}Service实现添加${Table}
        return R.ok(${table}Service.save(${table}));
    }

    /***
     * 根据ID查询${Table}数据
     * @param id
     * @return
     */
    <#if swagger==true>
    @ApiOperation(value = "${Table}根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "${keyType}")
    </#if>
    @GetMapping("/{id}")
    public R<${Table}> findById(@PathVariable ${keyType} id){
        //调用${Table}Service实现根据主键查询${Table}
        return R.ok(${table}Service.getById(id));
    }

    /***
     * 查询${Table}全部数据
     * @return
     */
    <#if swagger==true>
    @ApiOperation("查询所有${Table}")
    </#if>
    @GetMapping
    public R<List<${Table}>> findAll(){
        //调用${Table}Service实现查询所有${Table}
        return R.ok(${table}Service.list(Wrappers.<${Table}>lambdaQuery().orderByDesc(${Table}::getCreateTime))) ;
    }
}
