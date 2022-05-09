package cn.ecnu.oauth.service;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.oauth.pojo.Enterprise;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/****
 * @Author: Kili
 * @Description: EnterpriseService
 * @Date 2022-04-18 21:25:40
 *****/
public interface EnterpriseService extends IService<Enterprise> {

    public Enterprise findByAccount(String accout);
}
