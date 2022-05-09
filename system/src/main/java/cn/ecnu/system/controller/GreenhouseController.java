package cn.ecnu.system.controller;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.pojo.Greenhouse;
import cn.ecnu.system.service.GreenhouseService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author: Kili
 * @Description: GreenhouseController
 * @Date 2022-04-18 21:25:40
 *****/
@Api(value = "GreenhouseController", tags = "温室大鹏管理")
@RestController
@RequestMapping("/greenhouse")
@CrossOrigin
@Slf4j
public class GreenhouseController {

    @Autowired
    private GreenhouseService greenhouseService;

    /***
     * Greenhouse分页条件搜索实现
     * @param greenhouse
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("Greenhouse条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "Greenhouse对象",value = "传入JSON数据",required = false) Greenhouse greenhouse, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( greenhouseService.findPage(greenhouse, pageNo, size));
    }

    /***
     * Greenhouse分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Greenhouse分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( greenhouseService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("Greenhouse根据ID删除")
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R delete(@PathVariable  Long id){
        //调用GreenhouseService实现根据主键删除
        return R.ok(greenhouseService.removeById(id));
    }

    /***
     * 修改Greenhouse数据
     * @param greenhouse
     * @return
     */
    @ApiOperation("Greenhouse根据ID修改")
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R update(@RequestBody @ApiParam(name = "Greenhouse对象",value = "传入JSON数据",required = false) Greenhouse greenhouse){
        //调用GreenhouseService实现修改Greenhouse
        return R.ok(greenhouseService.updateById(greenhouse));
    }

    /***
     * 新增Greenhouse数据
     * @param greenhouse
     * @return
     */
    @ApiOperation("Greenhouse添加")
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R add(@RequestBody  @ApiParam(name = "Greenhouse对象",value = "传入JSON数据",required = true) Greenhouse greenhouse){
        //调用GreenhouseService实现添加Greenhouse
        return R.ok(greenhouseService.save(greenhouse));
    }

    /***
     * 根据ID查询Greenhouse数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Greenhouse根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "")
    @GetMapping("/{id}")
    public R<Greenhouse> findById(@PathVariable  Long id){
        //调用GreenhouseService实现根据主键查询Greenhouse
        return R.ok(greenhouseService.getById(id));
    }

    /***
     * 查询Greenhouse全部数据
     * @return
     */
    @ApiOperation("查询所有Greenhouse")
    @GetMapping("/findAll")
    public R<List<Greenhouse>> findAll(){
        //调用GreenhouseService实现查询所有Greenhouse
        return R.ok(greenhouseService.list(Wrappers.<Greenhouse>lambdaQuery().orderByDesc(Greenhouse::getCreateTime))) ;
    }
}
