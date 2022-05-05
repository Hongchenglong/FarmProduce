package cn.ecnu.system.pojo;

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
import java.time.LocalDateTime;

/****
 * @Author: Kili
 * @Description: ManagerPojo
 * @Date 2022-04-18 21:25:40
 *****/
@ApiModel(description = "Manager",value = "Manager")
@TableName(value="manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Manager implements Serializable{
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "",required = false)
	private Long id;	//

	@ApiModelProperty(value = "",required = false)
	private String account;	//

	@ApiModelProperty(value = "",required = false)
	private String username;	//

	@ApiModelProperty(value = "",required = false)
	private String password;	//

	@ApiModelProperty(value = "",required = false)
	private LocalDateTime createTime;	//

	@ApiModelProperty(value = "",required = false)
	private LocalDateTime updateTime;	//

}
