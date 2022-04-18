package cn.ecnu.trace.controller;

import cn.ecnu.trace.common.utils.PageResult;
import cn.ecnu.trace.common.utils.R;
import cn.ecnu.trace.pojo.Produce;
import cn.ecnu.trace.service.ProduceService;
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
 * @Description: ProduceController
 * @Date 2022-04-18 21:25:40
 *****/
@Api(value = "ProduceController", tags = "ProduceController")
@RestController
@RequestMapping("/produce")
@CrossOrigin
@Slf4j
public class ProduceController {

    @Autowired
    private ProduceService produceService;

    /***
     * Produce分页条件搜索实现
     * @param produce
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("Produce条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "Produce对象",value = "传入JSON数据",required = false) Produce produce, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( produceService.findPage(produce, pageNo, size));
    }

    /***
     * Produce分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Produce分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( produceService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("Produce根据ID删除")
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable Long id){
        //调用ProduceService实现根据主键删除
        return R.ok(produceService.removeById(id));
    }

    /***
     * 修改Produce数据
     * @param produce
     * @return
     */
    @ApiOperation("Produce根据ID修改")
    @PutMapping
    public R update(@RequestBody @ApiParam(name = "Produce对象",value = "传入JSON数据",required = false) Produce produce){
        //调用ProduceService实现修改Produce
        return R.ok(produceService.updateById(produce));
    }

    /***
     * 新增Produce数据
     * @param produce
     * @return
     */
    @ApiOperation("Produce添加")
    @PostMapping
    public R add(@RequestBody  @ApiParam(name = "Produce对象",value = "传入JSON数据",required = true) Produce produce){
        //调用ProduceService实现添加Produce
        return R.ok(produceService.save(produce));
    }

    /***
     * 根据ID查询Produce数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Produce根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public R<Produce> findById(@PathVariable Long id){
        //调用ProduceService实现根据主键查询Produce
        return R.ok(produceService.getById(id));
    }

    /***
     * 查询Produce全部数据
     * @return
     */
    @ApiOperation("查询所有Produce")
    @GetMapping
    public R<List<Produce>> findAll(){
        //调用ProduceService实现查询所有Produce
        return R.ok(produceService.list(Wrappers.<Produce>lambdaQuery().orderByDesc(Produce::getCreateTime))) ;
    }
}
