package cn.ecnu.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
import java.time.LocalDateTime;

/****
 * @Author: Ecnu
 * @Description: EnvironmentItemPojo
 * @Date 2022-05-07 13:07:16
 *****/
@ApiModel(description = "EnvironmentItem",value = "EnvironmentItem")
@TableName(value="environment_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EnvironmentItem implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",required = true)
	@TableId(type = IdType.AUTO)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;	//

	@ApiModelProperty(value = "空气温度（单位：摄氏度）",required = false)
	private Double airTemperature;	//空气温度（单位：摄氏度）

	@ApiModelProperty(value = "空气湿度：（单位：%）",required = false)
	private Double airHumidity;	//空气湿度：（单位：%）

	@ApiModelProperty(value = "二氧化碳浓度（单位：/10^-6)",required = false)
	private Double co2;	//二氧化碳浓度（单位：/10^-6)

	@ApiModelProperty(value = "光照度（单位：lx)",required = false)
	private Double illuminance;	//光照度（单位：lx)

	@ApiModelProperty(value = "土壤温度(单位：摄氏度）",required = false)
	private Double soilTemperature;	//土壤温度(单位：摄氏度）

	@ApiModelProperty(value = "土壤湿度（单位：%）",required = false)
	private Double soilHumidity;	//土壤湿度（单位：%）

	@ApiModelProperty(value = "时间",required = false)
	private LocalDateTime time;	//时间

	@ApiModelProperty(value = "环境id",required = false)
	private Long envnId;	//环境id

	@ApiModelProperty(value = "创建时间",required = false)
	private LocalDateTime createTime;	//创建时间

	@ApiModelProperty(value = "更新时间",required = false)
	private LocalDateTime updateTime;	//更新时间

}
