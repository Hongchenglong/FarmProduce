package cn.ecnu.system.service;

import cn.ecnu.common.utils.PageResult;
import cn.ecnu.system.pojo.Qrcode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/****
 * @Author: Kili
 * @Description: QrcodeService
 * @Date 2022-04-18 21:25:40
 *****/
public interface QrcodeService extends IService<Qrcode> {

    /**
     * Qrcode条件+分页查询
     * @param qrcode 查询条件
     * @param pageNo 页码
     * @param size 页大小
     * @return 分页结果
     */
    PageResult findPage(Qrcode qrcode, int pageNo, int size);

    PageResult findPage(int pageNo, int size);

    /**
     * 根据id查询已经逻辑删除的数据
     */
    public Qrcode findLogicDelById(Long id);

    /**
     * 查询所有已经逻辑删除的数据
     */
    public List<Qrcode> findAllLogicDel();

    /**
     * 根据id物理删除数据
     */
    public Boolean deleteTablelogic(Long id);

    /**
     * 根据id更新逻辑删除的数据
     * @param qrcode
     */
    public Boolean updateLogicDelById(Qrcode qrcode);

}
