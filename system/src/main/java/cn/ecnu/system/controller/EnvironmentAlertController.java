package cn.ecnu.system.controller;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.pojo.EnvironmentAlert;
import cn.ecnu.system.service.EnvironmentAlertService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

/****
 * @Author: Ecnu
 * @Description: EnvironmentAlertController
 * @Date 2022-05-10 15:06:54
 *****/
@Api(value = "EnvironmentAlertController", tags = "EnvironmentAlertController")
@RestController
@RequestMapping("/alert")
@CrossOrigin
@Slf4j
public class EnvironmentAlertController {

    @Autowired
    private EnvironmentAlertService environmentAlertService;

    /***
     * EnvironmentAlert分页条件搜索实现
     * @param environmentAlert
     * @param pageNo
     * @param size
     * @return
     */
    @ApiOperation("EnvironmentAlert条件分页查询")
    @PostMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@RequestBody(required = false) @ApiParam(name = "EnvironmentAlert对象",value = "传入JSON数据",required = false) EnvironmentAlert environmentAlert, @PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( environmentAlertService.findPage(environmentAlert, pageNo, size));
    }

    /***
     * EnvironmentAlert分页搜索实现
     * @param pageNo:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation("EnvironmentAlert分页查询")
    @GetMapping("/search/{pageNo}/{size}" )
    public R<PageResult> findPage(@PathVariable  int pageNo, @PathVariable  int size){
        return R.ok( environmentAlertService.findPage(pageNo, size));
    }

    /***
     * 根据ID删除数据
     * @param id
     * @return
     */
    @ApiOperation("EnvironmentAlert根据ID删除")
    @DeleteMapping("/{id}" )
    public R delete(@PathVariable Long id){
        //调用EnvironmentAlertService实现根据主键删除
        return R.ok(environmentAlertService.removeById(id));
    }

    /***
     * 修改EnvironmentAlert数据
     * @param id
     * @return
     */
    @ApiOperation("只需修改handle字段，是否已处理")
    @PutMapping("/{id}")
    public R update(@PathVariable Long id){
        //调用EnvironmentAlertService实现修改EnvironmentAlert
        return R.ok(environmentAlertService.updateById(new EnvironmentAlert().setId(id).setHandled(1)));
    }

    @ApiOperation("批量处理")
    @PostMapping("/batchUpdate")
    public R batchUpdate(@RequestBody String[] ids) {
        for (String id : ids) {
            environmentAlertService.updateById(new EnvironmentAlert().setId(new Long(id)).setHandled(1));
        }
        return R.ok();
    }

    /***
     * 新增EnvironmentAlert数据
     * @param environmentAlert
     * @return
     */
    @ApiOperation("EnvironmentAlert添加")
    @PostMapping("/add")
    public R add(@RequestBody  @ApiParam(name = "EnvironmentAlert对象",value = "传入JSON数据",required = true) EnvironmentAlert environmentAlert){
        //调用EnvironmentAlertService实现添加EnvironmentAlert
        return R.ok(environmentAlertService.save(environmentAlert));
    }

    /***
     * 根据ID查询EnvironmentAlert数据
     * @param id
     * @return
     */
    @ApiOperation(value = "EnvironmentAlert根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public R<EnvironmentAlert> findById(@PathVariable Long id){
        //调用EnvironmentAlertService实现根据主键查询EnvironmentAlert
        return R.ok(environmentAlertService.getById(id));
    }

    @GetMapping("exportReport")
    public R exportReport(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<EnvironmentAlert> alertList = environmentAlertService.findPage(1, 50).getRows();
        try {

            //获得虚拟目录下的"template"文件夹下的文件绝对路径
            String filePath = System.getProperty("user.dir")+ File.separator +"智慧农场环境阈值告警.xlsx";
            log.info("\n###### 读取模板path:{}", filePath);
            //基于提供的Excel模板文件在内存中创建一个Excel表格对象
            XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            //读取第一个工作表
            XSSFSheet sheet = excel.getSheetAt(0);

            int rowNum = 4;
            for(EnvironmentAlert item: alertList){
                XSSFRow row = sheet.getRow(rowNum++);
                row.getCell(2).setCellValue(item.getProject());//日期
                row.getCell(3).setCellValue(item.getCompany());//大棚id
                row.getCell(4).setCellValue(item.getIntroduce());//空气温度
                row.getCell(5).setCellValue(item.getRate());//空气湿度
                row.getCell(6).setCellValue(item.getAmount());//CO2浓度
                row.getCell(6).setCellValue(item.getAmount());//光照度
                row.getCell(7).setCellValue(item.getAmount());//土壤温度
                row.getCell(8).setCellValue(item.getAmount());//土壤湿度
                row.getCell(9).setCellValue(item.getAmount());//是否已处理
            }

            //使用输出流进行表格下载,基于浏览器作为客户端下载
            OutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");//代表的是Excel文件类型
            String fileName = "智慧农场环境阈值告警.xlsx";
            response.setHeader("content-Disposition", "attachment;filename="+ fileName);//指定以附件形式进行下载
            excel.write(out);

            out.flush();
            out.close();
            excel.close();
            return null;
        } catch (Exception e) {
            log.info("\n###### 数据解析异常", e);
            return R.error("数据解析异常");
        }
    }


    //数据量大，只分页查询
//    /***
//     * 查询EnvironmentAlert全部数据
//     * @return
//     */
//    @ApiOperation("查询所有EnvironmentAlert")
//    @GetMapping
//    public R<List<EnvironmentAlert>> findAll(){
//        //调用EnvironmentAlertService实现查询所有EnvironmentAlert
//        return R.ok(environmentAlertService.list(Wrappers.<EnvironmentAlert>lambdaQuery().orderByDesc(EnvironmentAlert::getCreateTime))) ;
//    }
}
