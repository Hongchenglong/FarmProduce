package cn.ecnu.system.controller;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.pojo.FarmProduce;
import cn.ecnu.system.service.FarmProduceService;
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

/**
 * @author Hongchenglong
 * @since 2022/4/23
 */
@Api(value = "农产品管理", tags = "农产品管理")
@RestController
@RequestMapping("/farmProduce")
@CrossOrigin
@Slf4j
public class FarmProduceController {

    @Autowired
    private FarmProduceService farmProduceService;

    /***
     * FarmProduce分页条件搜索实现
     * @param farmProduce
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("FarmProduce条件分页查询")
    @PostMapping("/search/{pageNo}/{size}")
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "FarmProduce对象", value = "传入JSON数据", required = false) FarmProduce farmProduce, @PathVariable int pageNo, @PathVariable int size) {
        return R.ok(farmProduceService.findPage(farmProduce, pageNo, size));
    }

    /***
     * FarmProduce分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("FarmProduce分页查询")
    @GetMapping("/search/{pageNo}/{size}")
    public R<PageResult> findPage(@PathVariable int pageNo, @PathVariable int size) {
        return R.ok(farmProduceService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("FarmProduce根据ID删除")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R delete(@PathVariable Long id) {
        //调用FarmProduceService实现根据主键删除
        return R.ok(farmProduceService.removeById(id));
    }

    /***
     * 修改FarmProduce数据
     * @param farmProduce
     * @return
     */
    @ApiOperation("FarmProduce根据ID修改")
    @PutMapping("/update")
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R update(@RequestBody @ApiParam(name = "FarmProduce对象", value = "传入JSON数据", required = false) FarmProduce farmProduce) {
        //调用FarmProduceService实现修改FarmProduce
        return R.ok(farmProduceService.updateById(farmProduce));
    }

    /***
     * 新增FarmProduce数据
     * @param farmProduce
     * @return
     */
    @ApiOperation("FarmProduce添加")
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('super_admin')")
    public R add(@RequestBody @ApiParam(name = "FarmProduce对象", value = "传入JSON数据", required = true) FarmProduce farmProduce) {
        //调用FarmProduceService实现添加FarmProduce
        return R.ok(farmProduceService.save(farmProduce));
    }

    /***
     * 根据ID查询FarmProduce数据
     * @param id
     * @return
     */
    @ApiOperation(value = "FarmProduce根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public R<FarmProduce> findById(@PathVariable Long id) {
        //调用FarmProduceService实现根据主键查询FarmProduce
        return R.ok(farmProduceService.getById(id));
    }

    /***
     * 查询FarmProduce全部数据
     * @return
     */
    @ApiOperation("查询所有FarmProduce")
    @GetMapping("/findAll")
    public R<List<FarmProduce>> findAll() {
        //调用FarmProduceService实现查询所有FarmProduce
        return R.ok(farmProduceService.list(Wrappers.<FarmProduce>lambdaQuery().orderByDesc(FarmProduce::getCreateTime)));
    }
}
