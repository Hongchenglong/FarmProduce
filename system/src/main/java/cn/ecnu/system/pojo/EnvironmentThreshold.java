package cn.ecnu.system.pojo;

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
 * @Description: EnvironmentThresholdPojo
 * @Date 2022-04-18 21:25:40
 *****/
@ApiModel(description = "EnvironmentThreshold",value = "EnvironmentThreshold")
@TableName(value="environment_threshold")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EnvironmentThreshold implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",required = false)
	private Long id;	//

	@ApiModelProperty(value = "",required = false)
	private String airTempMax;	//

	@ApiModelProperty(value = "",required = false)
	private String airTempMin;	//

	@ApiModelProperty(value = "",required = false)
	private String airHumMax;	//

	@ApiModelProperty(value = "",required = false)
	private String airHumMin;	//

	@ApiModelProperty(value = "",required = false)
	private String CO2Max;	//

	@ApiModelProperty(value = "",required = false)
	private String CO2Min;	//

	@ApiModelProperty(value = "",required = false)
	private String illuminanceMax;	//

	@ApiModelProperty(value = "",required = false)
	private String illuminanceMin;	//

	@ApiModelProperty(value = "",required = false)
	private String soilTempMax;	//

	@ApiModelProperty(value = "",required = false)
	private String soilTempMin;	//

	@ApiModelProperty(value = "",required = false)
	private Integer greenhouseId;	//

	@ApiModelProperty(value = "创建时间",required = false)
	private LocalDateTime createTime;	//创建时间

	@ApiModelProperty(value = "更新时间",required = false)
	private LocalDateTime updateTime;	//更新时间

}
