package cn.ecnu.trace.controller;

import cn.ecnu.trace.common.utils.PageResult;
import cn.ecnu.trace.common.utils.R;
import cn.ecnu.trace.pojo.Enviroment;
import cn.ecnu.trace.service.EnviromentService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author: Kili
 * @Description: EnviromentController
 * @Date 2022-04-18 21:25:40
 *****/
@Api(value = "EnviromentController", tags = "EnviromentController")
@RestController
@RequestMapping("/enviroment")
@CrossOrigin
@Slf4j
public class EnviromentController {

    @Autowired
    private EnviromentService enviromentService;

    /***
     * Enviroment分页条件搜索实现
     * @param enviroment
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("Enviroment条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "Enviroment对象",value = "传入JSON数据",required = false) Enviroment enviroment, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( enviromentService.findPage(enviroment, pageNo, size));
    }

    /***
     * Enviroment分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Enviroment分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( enviromentService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("Enviroment根据ID删除")
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable Long id){
        //调用EnviromentService实现根据主键删除
        return R.ok(enviromentService.removeById(id));
    }

    /***
     * 修改Enviroment数据
     * @param enviroment
     * @return
     */
    @ApiOperation("Enviroment根据ID修改")
    @PutMapping
    public R update(@RequestBody @ApiParam(name = "Enviroment对象",value = "传入JSON数据",required = false) Enviroment enviroment){
        //调用EnviromentService实现修改Enviroment
        return R.ok(enviromentService.updateById(enviroment));
    }

    /***
     * 新增Enviroment数据
     * @param enviroment
     * @return
     */
    @ApiOperation("Enviroment添加")
    @PostMapping
    public R add(@RequestBody  @ApiParam(name = "Enviroment对象",value = "传入JSON数据",required = true) Enviroment enviroment){
        //调用EnviromentService实现添加Enviroment
        return R.ok(enviromentService.save(enviroment));
    }

    /***
     * 根据ID查询Enviroment数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Enviroment根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public R<Enviroment> findById(@PathVariable Long id){
        //调用EnviromentService实现根据主键查询Enviroment
        return R.ok(enviromentService.getById(id));
    }

    /***
     * 查询Enviroment全部数据
     * @return
     */
    @ApiOperation("查询所有Enviroment")
    @GetMapping
    public R<List<Enviroment>> findAll(){
        //调用EnviromentService实现查询所有Enviroment
        return R.ok(enviromentService.list(Wrappers.<Enviroment>lambdaQuery().orderByDesc(Enviroment::getCreateTime))) ;
    }
}
