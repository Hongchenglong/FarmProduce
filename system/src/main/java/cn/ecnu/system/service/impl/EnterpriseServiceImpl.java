package cn.ecnu.system.service.impl;

import cn.ecnu.common.utils.BCrypt;
import cn.ecnu.common.utils.JwtUtil;
import cn.ecnu.common.utils.PageResult;
import cn.ecnu.common.utils.R;
import cn.ecnu.system.mapper.EnterpriseMapper;
import cn.ecnu.system.pojo.Enterprise;
import cn.ecnu.system.service.EnterpriseService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/****
 * @Author: Kili
 * @Description: EnterpriseServiceImpl
 * @Date 2022-04-18 21:25:40
 *****/
@Service
@Slf4j
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements EnterpriseService{

    /**
     * Enterprise条件+分页查询
     * @param enterprise 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult findPage(Enterprise enterprise, int pageNo, int size){
        Page<Enterprise> page = new Page<>();
        page.setCurrent(pageNo).setSize(size);
        LambdaQueryWrapper<Enterprise> wrapper = Wrappers.lambdaQuery(enterprise).orderByDesc(Enterprise::getCreateTime);
        IPage result = this.page(page, wrapper);
        PageResult pageResult=new PageResult(result.getTotal(),result.getRecords());
        return pageResult;
    }

    @Override
    public PageResult findPage(int pageNo, int size){
        Page<Enterprise> page = new Page<>();
        LambdaQueryWrapper<Enterprise> wrapper = Wrappers.<Enterprise>lambdaQuery().orderByDesc(Enterprise::getCreateTime);//根据某个字段排序，自己根据实际情况小改一下
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
    public Enterprise findLogicDelById(Long id) {
        return baseMapper.findLogicDelById(id);
    }

    /**
     * 查询所有已经逻辑删除的数据
     */
    @Override
    public List<Enterprise> findAllLogicDel() {
        return baseMapper.findAllLogicDel();
    }


    /**
     * 根据id物理删除数据
     */
    public Boolean deleteTablelogic(Long id){
        return baseMapper.deleteTablelogic(id);
    }

    /**
     * 根据id更新逻辑删除的数据
     * 复杂sql语句写在xml里面
     * @param enterprise
     */
    public Boolean updateLogicDelById(Enterprise enterprise){
        return baseMapper.updateLogicDelById(enterprise);
    }

    @Override
    public String login(Enterprise enterprise) {

        //查询用户信息
        Enterprise user = this.getOne(Wrappers.<Enterprise>lambdaQuery().eq(Enterprise::getAccount,enterprise.getAccount()));
        if(user!=null && BCrypt.checkpw(enterprise.getPassword(), user.getPassword())){
            //设置令牌信息
            Map<String,Object> info = new HashMap<String,Object>();
            info.put("role","USER");
            info.put("success","SUCCESS");
            info.put("username",enterprise.getAccount());
            //生成令牌
           return JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info),null);
        }else {
            throw new RuntimeException("用户名或密码错误");
        }
    }

    @Override
    public R<String> register(Enterprise enterprise) {
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();
        wrapper.eq("account", enterprise.getAccount());
        if (baseMapper.selectOne(wrapper) == null) {
            baseMapper.insert(enterprise.setPassword(BCrypt.hashpw(enterprise.getPassword(),BCrypt.gensalt())));
            return R.ok();
        } else {
            return R.error("账号已存在");
        }
    }
}
