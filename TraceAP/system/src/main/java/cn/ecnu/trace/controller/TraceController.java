package cn.ecnu.trace.controller;

import cn.ecnu.trace.common.utils.PageResult;
import cn.ecnu.trace.common.utils.R;
import cn.ecnu.trace.pojo.Trace;
import cn.ecnu.trace.service.TraceService;
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
 * @Description: TraceController
 * @Date 2022-04-18 21:25:40
 *****/
@Api(value = "TraceController", tags = "TraceController")
@RestController
@RequestMapping("/trace")
@CrossOrigin
@Slf4j
public class TraceController {

    @Autowired
    private TraceService traceService;

    /***
     * Trace分页条件搜索实现
     * @param trace
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("Trace条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "Trace对象",value = "传入JSON数据",required = false) Trace trace, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( traceService.findPage(trace, pageNo, size));
    }

    /***
     * Trace分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Trace分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( traceService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("Trace根据ID删除")
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable  Long id){
        //调用TraceService实现根据主键删除
        return R.ok(traceService.removeById(id));
    }

    /***
     * 修改Trace数据
     * @param trace
     * @return
     */
    @ApiOperation("Trace根据ID修改")
    @PutMapping
    public R update(@RequestBody @ApiParam(name = "Trace对象",value = "传入JSON数据",required = false) Trace trace){
        //调用TraceService实现修改Trace
        return R.ok(traceService.updateById(trace));
    }

    /***
     * 新增Trace数据
     * @param trace
     * @return
     */
    @ApiOperation("Trace添加")
    @PostMapping
    public R add(@RequestBody  @ApiParam(name = "Trace对象",value = "传入JSON数据",required = true) Trace trace){
        //调用TraceService实现添加Trace
        return R.ok(traceService.save(trace));
    }

    /***
     * 根据ID查询Trace数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Trace根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "")
    @GetMapping("/{id}")
    public R<Trace> findById(@PathVariable  Long id){
        //调用TraceService实现根据主键查询Trace
        return R.ok(traceService.getById(id));
    }

    /***
     * 查询Trace全部数据
     * @return
     */
    @ApiOperation("查询所有Trace")
    @GetMapping
    public R<List<Trace>> findAll(){
        //调用TraceService实现查询所有Trace
        return R.ok(traceService.list(Wrappers.<Trace>lambdaQuery().orderByDesc(Trace::getCreateTime))) ;
    }
}
