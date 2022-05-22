package cn.ecnu.system.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/****
 * @Author: Kili
 * @Description: EnterprisePojo
 * @Date 2022-04-18 21:25:40
 *****/
@ApiModel(description = "Enterprise",value = "Enterprise")
@TableName(value="enterprise")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EnterpriseVO implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",required = true)
	@TableId(type = IdType.AUTO)
	private Long id;	//

	@ApiModelProperty(value = "账号",required = false)
	private String account;	//账号

	@ApiModelProperty(value = "企业名称",required = false)
	private String name;	//企业名称

	@ApiModelProperty(value = "企业地址",required = false)
	private String address;	//企业地址

	@ApiModelProperty(value = "电话",required = false)
	private String phone;	//电话

	@ApiModelProperty(value = "邮箱",required = false)
	private String email;	//邮箱

}
