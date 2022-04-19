package cn.ecnu.trace.service.impl;

import cn.ecnu.trace.common.utils.PageResult;
import cn.ecnu.trace.mapper.QrcodeMapper;
import cn.ecnu.trace.pojo.Qrcode;
import cn.ecnu.trace.service.QrcodeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/****
 * @Author: Kili
 * @Description: QrcodeServiceImpl
 * @Date 2022-04-18 21:25:40
 *****/
@Service
@Slf4j
public class QrcodeServiceImpl extends ServiceImpl<QrcodeMapper, Qrcode> implements QrcodeService{

    /**
     * Qrcode条件+分页查询
     * @param qrcode 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult findPage(Qrcode qrcode, int pageNo, int size){
        Page<Qrcode> page = new Page<>();
        page.setCurrent(pageNo).setSize(size);
        LambdaQueryWrapper<Qrcode> wrapper = Wrappers.lambdaQuery(qrcode).orderByDesc(Qrcode::getCreateTime);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNo, int size){
        Page<Qrcode> page = new Page<>();
        LambdaQueryWrapper<Qrcode> wrapper = Wrappers.<Qrcode>lambdaQuery().orderByDesc(Qrcode::getCreateTime);//根据某个字段排序，自己根据实际情况小改一下
        page.setCurrent(pageNo).setSize(size);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    /**
     * 根据id查询已经逻辑删除的数据
     * 需在配置文件设置好逻辑删除字段
     * 并在该字段上加上 @TableLogic注解
     */
    @Override
    public Qrcode findLogicDelById(Long id) {
        return baseMapper.findLogicDelById(id);
    }

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Override
    public List<Qrcode> findAllLogicDel() {
        return baseMapper.findAllLogicDel();
    }


    /**
     * 根据id物理删除数据
     */
    public Boolean deleteTablelogic(Long id){
        return baseMapper.deleteTablelogic(id);
    };

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param qrcode
     */
    public Boolean updateLogicDelById(Qrcode qrcode){
        return baseMapper.updateLogicDelById(qrcode);
    };
}
