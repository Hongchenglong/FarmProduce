package cn.ecnu.oauth.service.impl;

import cn.ecnu.oauth.mapper.EnterpriseMapper;
import cn.ecnu.oauth.pojo.Enterprise;
import cn.ecnu.oauth.service.EnterpriseService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/****
 * @Author: Kili
 * @Description: EnterpriseServiceImpl
 * @Date 2022-04-18 21:25:40
 *****/
@Service
@Slf4j
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements EnterpriseService{

    @Override
    public Enterprise findByAccount(String accout) {
        return getOne(Wrappers.<Enterprise>lambdaQuery().eq(Enterprise::getAccount,accout));
    }
}
