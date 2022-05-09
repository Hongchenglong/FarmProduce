package cn.ecnu.system.controller;

import cn.ecnu.common.exception.ErrorCode;
import cn.ecnu.common.exception.utils.ServiceExceptionUtil;
import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.convert.EnvironmentConvert;
import cn.ecnu.system.model.dto.EnvironmentDTO;
import cn.ecnu.system.model.vo.EnvironmentVO;
import cn.ecnu.system.pojo.Environment;
import cn.ecnu.system.pojo.Greenhouse;
import cn.ecnu.system.service.DataService;
import cn.ecnu.system.service.EnvironmentService;
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
 * @Author: Ecnu
 * @Description: EnvironmentController
 * @Date 2022-05-06 18:40:08
 *****/
@Api(value = "EnvironmentController", tags = "环境变量管理")
@RestController
@RequestMapping("/environment")
@CrossOrigin
@Slf4j
public class EnvironmentController {

    @Autowired
    private EnvironmentService environmentService;
    @Autowired
    private GreenhouseService greenhouseService;
    @Autowired
    private DataService dataService;

    /***
     * Environment分页条件搜索实现
     * @param environment
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("Environment条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "Environment对象",value = "传入JSON数据",required = false) Environment environment, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( environmentService.findPage(environment, pageNo, size));
    }

    /***
     * Environment分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Environment分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( environmentService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("Environment根据ID删除")
    @DeleteMapping("/{id}" )
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R delete(@PathVariable  Long id){
        //调用EnvironmentService实现根据主键删除
        return R.ok(environmentService.deleteEvn(id));
    }

    /***
     * 修改Environment数据
     * @param environment
     * @return
     */
    @ApiOperation("Environment根据ID修改")
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R update(@RequestBody @ApiParam(name = "Environment对象",value = "传入JSON数据",required = false) Environment environment){
        //调用EnvironmentService实现修改Environment
        return R.ok(environmentService.updateById(environment));
    }

    /***
     * 新增Environment数据
     * @param environmentDTO
     * @return
     */
    @ApiOperation("Environment添加")
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R addEnvnInfo(@RequestBody EnvironmentDTO environmentDTO){
        Environment environment = EnvironmentConvert.INSTANCE.convert(environmentDTO);
        Greenhouse greenhouse = greenhouseService.getById(environmentDTO.getGreenhouseId());
        if(greenhouse == null) throw ServiceExceptionUtil.exception(new ErrorCode(500, "温室大棚不存在"));
        return R.ok(dataService.addEnvnInfo(environment));
    }

    /***
     * 根据ID查询Environment数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Environment根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "")
    @GetMapping("/{id}")
    public R<Environment> findById(@PathVariable  Long id){
        //调用EnvironmentService实现根据主键查询Environment
        return R.ok(environmentService.getById(id));
    }

    /***
     * 根据ID查询Environment数据
     * @param environmentDTO
     * @return
     */
    @ApiOperation(value = "Environment根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "")
    @PostMapping("/info")
    public R<EnvironmentVO> queryInfo(@RequestBody EnvironmentDTO environmentDTO){
        Environment environment = EnvironmentConvert.INSTANCE.convert(environmentDTO);
        //调用EnvironmentService实现根据主键查询Environment
        return R.ok(environmentService.queryInfo(environment));
    }

    /***
     * 查询Environment全部数据
     * @return
     */
    @ApiOperation("查询所有Environment")
    @GetMapping("/all/{days}")
    public R<List<EnvironmentVO>> queryEnv(@PathVariable Integer days){
        //调用EnvironmentService实现查询所有Environment
        return R.ok(environmentService.queryEnv(days));
    }
}
