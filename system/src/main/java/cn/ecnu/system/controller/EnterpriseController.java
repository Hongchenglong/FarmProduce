package cn.ecnu.system.controller;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.pojo.Enterprise;
import cn.ecnu.system.service.EnterpriseService;
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
 * @Description: EnterpriseController
 * @Date 2022-04-18 21:25:40
 *****/
@Api(value = "EnterpriseController", tags = "EnterpriseController")
@RestController
@RequestMapping("/enterprise")
@CrossOrigin
@Slf4j
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    /***
     * Enterprise分页条件搜索实现
     * @param enterprise
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("Enterprise条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "Enterprise对象",value = "传入JSON数据",required = false) Enterprise enterprise, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( enterpriseService.findPage(enterprise, pageNo, size));
    }

    /***
     * Enterprise分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Enterprise分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( enterpriseService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("Enterprise根据ID删除")
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable Long id){
        //调用EnterpriseService实现根据主键删除
        return R.ok(enterpriseService.removeById(id));
    }

    /***
     * 修改Enterprise数据
     * @param enterprise
     * @return
     */
    @ApiOperation("Enterprise根据ID修改")
    @PutMapping
    public R update(@RequestBody @ApiParam(name = "Enterprise对象",value = "传入JSON数据",required = false) Enterprise enterprise){
        //调用EnterpriseService实现修改Enterprise
        return R.ok(enterpriseService.updateById(enterprise));
    }

    /***
     * 新增Enterprise数据
     * @param enterprise
     * @return
     */
    @ApiOperation("Enterprise添加")
    @PostMapping
    public R add(@RequestBody  @ApiParam(name = "Enterprise对象",value = "传入JSON数据",required = true) Enterprise enterprise){
        //调用EnterpriseService实现添加Enterprise
        return R.ok(enterpriseService.save(enterprise));
    }

    /***
     * 根据ID查询Enterprise数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Enterprise根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public R<Enterprise> findById(@PathVariable Long id){
        //调用EnterpriseService实现根据主键查询Enterprise
        return R.ok(enterpriseService.getById(id));
    }

    /***
     * 查询Enterprise全部数据
     * @return
     */
    @ApiOperation("查询所有Enterprise")
    @GetMapping
    public R<List<Enterprise>> findAll(){
        //调用EnterpriseService实现查询所有Enterprise
        return R.ok(enterpriseService.list(Wrappers.<Enterprise>lambdaQuery().orderByDesc(Enterprise::getCreateTime))) ;
    }
}
