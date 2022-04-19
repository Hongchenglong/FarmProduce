package cn.ecnu.trace.pojo;

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
 * @Description: GreenhousePojo
 * @Date 2022-04-18 21:25:40
 *****/
@ApiModel(description = "Greenhouse",value = "Greenhouse")
@TableName(value="greenhouse")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Greenhouse implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",required = false)
	private Long id;	//

	@ApiModelProperty(value = "名称",required = false)
	private String name;	//名称

	@ApiModelProperty(value = "图像地址",required = false)
	private String image;	//图像地址

	@ApiModelProperty(value = "企业id",required = false)
	private Integer enterpriseId;	//企业id

	@ApiModelProperty(value = "创建时间",required = false)
	private LocalDateTime createTime;	//创建时间

	@ApiModelProperty(value = "更新时间",required = false)
	private LocalDateTime updateTime;	//更新时间

}
