package cn.ecnu.system.controller;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.pojo.EnvironmentThreshold;
import cn.ecnu.system.service.EnvironmentThresholdService;
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
 * @Description: EnvironmentThresholdController
 * @Date 2022-04-18 21:25:40
 *****/
@Api(value = "EnvironmentThresholdController", tags = "EnvironmentThresholdController")
@RestController
@RequestMapping("/environmentThreshold")
@CrossOrigin
@Slf4j
public class EnvironmentThresholdController {

    @Autowired
    private EnvironmentThresholdService environmentThresholdService;

    /***
     * EnvironmentThreshold分页条件搜索实现
     * @param environmentThreshold
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("EnvironmentThreshold条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "EnvironmentThreshold对象",value = "传入JSON数据",required = false) EnvironmentThreshold environmentThreshold, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( environmentThresholdService.findPage(environmentThreshold, pageNo, size));
    }

    /***
     * EnvironmentThreshold分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("EnvironmentThreshold分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( environmentThresholdService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("EnvironmentThreshold根据ID删除")
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable  Long id){
        //调用EnvironmentThresholdService实现根据主键删除
        return R.ok(environmentThresholdService.removeById(id));
    }

    /***
     * 修改EnvironmentThreshold数据
     * @param environmentThreshold
     * @return
     */
    @ApiOperation("EnvironmentThreshold根据ID修改")
    @PutMapping
    public R update(@RequestBody @ApiParam(name = "EnvironmentThreshold对象",value = "传入JSON数据",required = false) EnvironmentThreshold environmentThreshold){
        //调用EnvironmentThresholdService实现修改EnvironmentThreshold
        return R.ok(environmentThresholdService.updateById(environmentThreshold));
    }

    /***
     * 新增EnvironmentThreshold数据
     * @param environmentThreshold
     * @return
     */
    @ApiOperation("EnvironmentThreshold添加")
    @PostMapping
    public R add(@RequestBody  @ApiParam(name = "EnvironmentThreshold对象",value = "传入JSON数据",required = true) EnvironmentThreshold environmentThreshold){
        //调用EnvironmentThresholdService实现添加EnvironmentThreshold
        return R.ok(environmentThresholdService.save(environmentThreshold));
    }

    /***
     * 根据ID查询EnvironmentThreshold数据
     * @param id
     * @return
     */
    @ApiOperation(value = "EnvironmentThreshold根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "")
    @GetMapping("/{id}")
    public R<EnvironmentThreshold> findById(@PathVariable Long id){
        //调用EnvironmentThresholdService实现根据主键查询EnvironmentThreshold
        return R.ok(environmentThresholdService.getById(id));
    }

    /***
     * 查询EnvironmentThreshold全部数据
     * @return
     */
    @ApiOperation("查询所有EnvironmentThreshold")
    @GetMapping
    public R<List<EnvironmentThreshold>> findAll(){
        //调用EnvironmentThresholdService实现查询所有EnvironmentThreshold
        return R.ok(environmentThresholdService.list(Wrappers.<EnvironmentThreshold>lambdaQuery().orderByDesc(EnvironmentThreshold::getCreateTime))) ;
    }
}
