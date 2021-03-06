package cn.ecnu.system.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
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
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal airTempMax;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal airTempMin;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal airHumMax;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal airHumMin;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal co2Max;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal co2Min;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal illuminanceMax;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal illuminanceMin;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal soilTempMax;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal soilTempMin;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal soilHumidMax;	//

	@ApiModelProperty(value = "",required = false)
	private BigDecimal soilHumidMin;	//

	@ApiModelProperty(value = "",required = false)
	private Long greenhouseId;	//

	@ApiModelProperty(value = "???????????????1??????????????????0",required = false)
	private Integer open;	//???????????????1??????????????????0

	@ApiModelProperty(value = "????????????",required = false)
	private LocalDateTime createTime;	//????????????

	@ApiModelProperty(value = "????????????",required = false)
	private LocalDateTime updateTime;	//????????????

}
