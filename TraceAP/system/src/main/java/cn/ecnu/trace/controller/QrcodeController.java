package cn.ecnu.trace.controller;

import cn.ecnu.trace.common.utils.PageResult;
import cn.ecnu.trace.common.utils.R;
import cn.ecnu.trace.pojo.Qrcode;
import cn.ecnu.trace.service.QrcodeService;
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
 * @Description: QrcodeController
 * @Date 2022-04-18 21:25:40
 *****/
@Api(value = "QrcodeController", tags = "QrcodeController")
@RestController
@RequestMapping("/qrcode")
@CrossOrigin
@Slf4j
public class QrcodeController {

    @Autowired
    private QrcodeService qrcodeService;

    /***
     * Qrcode分页条件搜索实现
     * @param qrcode
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("Qrcode条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "Qrcode对象",value = "传入JSON数据",required = false) Qrcode qrcode, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( qrcodeService.findPage(qrcode, pageNo, size));
    }

    /***
     * Qrcode分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("Qrcode分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( qrcodeService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("Qrcode根据ID删除")
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable  Long id){
        //调用QrcodeService实现根据主键删除
        return R.ok(qrcodeService.removeById(id));
    }

    /***
     * 修改Qrcode数据
     * @param qrcode
     * @return
     */
    @ApiOperation("Qrcode根据ID修改")
    @PutMapping
    public R update(@RequestBody @ApiParam(name = "Qrcode对象",value = "传入JSON数据",required = false) Qrcode qrcode){
        //调用QrcodeService实现修改Qrcode
        return R.ok(qrcodeService.updateById(qrcode));
    }

    /***
     * 新增Qrcode数据
     * @param qrcode
     * @return
     */
    @ApiOperation("Qrcode添加")
    @PostMapping
    public R add(@RequestBody  @ApiParam(name = "Qrcode对象",value = "传入JSON数据",required = true) Qrcode qrcode){
        //调用QrcodeService实现添加Qrcode
        return R.ok(qrcodeService.save(qrcode));
    }

    /***
     * 根据ID查询Qrcode数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Qrcode根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "")
    @GetMapping("/{id}")
    public R<Qrcode> findById(@PathVariable  Long id){
        //调用QrcodeService实现根据主键查询Qrcode
        return R.ok(qrcodeService.getById(id));
    }

    /***
     * 查询Qrcode全部数据
     * @return
     */
    @ApiOperation("查询所有Qrcode")
    @GetMapping
    public R<List<Qrcode>> findAll(){
        //调用QrcodeService实现查询所有Qrcode
        return R.ok(qrcodeService.list(Wrappers.<Qrcode>lambdaQuery().orderByDesc(Qrcode::getCreateTime))) ;
    }
}
