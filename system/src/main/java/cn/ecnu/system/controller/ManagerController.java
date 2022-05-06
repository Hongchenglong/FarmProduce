package cn.ecnu.system.controller;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.pojo.Manager;
import cn.ecnu.system.service.ManagerService;
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
 * @Description: ManagerController
 * @Date 2022-04-18 21:25:40
 *****/
@Api(value = "ManagerController", tags = "管理员")
@RestController
@RequestMapping("/manager")
@CrossOrigin
@Slf4j
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /***
     * Manager分页条件搜索实现
     * @param manager
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("Manager条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "Manager对象",value = "传入JSON数据",required = false) Manager manager, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( managerService.findPage(manager, pageNo, size));
    }

    /***
     * Manager分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Manager分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( managerService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("Manager根据ID删除")
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable  Long id){
        //调用ManagerService实现根据主键删除
        return R.ok(managerService.removeById(id));
    }

    /***
     * 修改Manager数据
     * @param manager
     * @return
     */
    @ApiOperation("Manager根据ID修改")
    @PutMapping
    public R update(@RequestBody @ApiParam(name = "Manager对象",value = "传入JSON数据",required = false) Manager manager){
        //调用ManagerService实现修改Manager
        return R.ok(managerService.updateById(manager));
    }

    /***
     * 新增Manager数据
     * @param manager
     * @return
     */
    @ApiOperation("Manager添加")
    @PostMapping
    public R add(@RequestBody  @ApiParam(name = "Manager对象",value = "传入JSON数据",required = true) Manager manager){
        //调用ManagerService实现添加Manager
        return R.ok(managerService.save(manager));
    }

    /***
     * 根据ID查询Manager数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Manager根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "")
    @GetMapping("/{id}")
    public R<Manager> findById(@PathVariable  Long id){
        //调用ManagerService实现根据主键查询Manager
        return R.ok(managerService.getById(id));
    }

    /***
     * 查询Manager全部数据
     * @return
     */
    @ApiOperation("查询所有Manager")
    @GetMapping
    public R<List<Manager>> findAll(){
        //调用ManagerService实现查询所有Manager
        return R.ok(managerService.list(Wrappers.<Manager>lambdaQuery().orderByDesc(Manager::getCreateTime))) ;
    }
}
