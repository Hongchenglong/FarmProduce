package cn.ecnu.system.convert;

import cn.ecnu.system.model.dto.EnvironmentDTO;
import cn.ecnu.system.model.vo.EnvironmentVO;
import cn.ecnu.system.pojo.Environment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @InterfaceName PersonConverter
 * @Description DO 映射 DTO 的mapper接口，此接口编译之后会自动生成一个实现类（位于target/classes中）
 *  此接口使用到@Mapper注解(org.mapstruct.Mapper)，
 *  @Mapper 注解的 componentModel 属性，componentModel 属性用于指定自动生成的接口实现类的组件类型，这个属性支持四个值：
 *      default: 这是默认的情况，mapstruct 不使用任何组件类型, 可以通过Mappers.getMapper(Class)方式获取自动生成的实例对象。
 *      spring: 生成的实现类上面会自动添加一个@Component注解，可以通过Spring的 @Autowired方式进行注入
 *      cdi: the generated mapper is an application-scoped CDI bean and can be retrieved via @Inject
 *      jsr330: 生成的实现类上会添加@javax.inject.Named 和@Singleton注解，可以通过 @Inject注解获取
 * @Author zyf
 * @Date 2020/9/3 9:52
 * @Version 1.0
 **/
@Mapper
public interface EnvironmentConvert {

    /** 默认的方式 **/
    EnvironmentConvert INSTANCE = Mappers.getMapper(EnvironmentConvert.class);

    Environment convert(EnvironmentDTO environmentDTO);

    EnvironmentVO convert(Environment environment);


}