package cn.ecnu.system.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/****
 * @Author: Ecnu
 * @Description: EnvironmentAlertPojo
 * @Date 2022-05-10 15:06:54
 *****/
@ApiModel(description = "EnvironmentAlert",value = "EnvironmentAlert")
@TableName(value="environment_alert")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EnvironmentAlert implements Serializable{
	private static final long serialVersionUID = 1L;

	@JSONField(serializeUsing = ToStringSerializer.class)
	@ApiModelProperty(value = "主键id",required = true)
	@TableId(type = IdType.AUTO)
	private Long id;	//主键id

	@ApiModelProperty(value = "空气温度（单位：摄氏度）",required = false)
	private BigDecimal airTemperature;	//空气温度（单位：摄氏度）

	@ApiModelProperty(value = "空气湿度：（单位：%）",required = false)
	private BigDecimal airHumidity;	//空气湿度：（单位：%）

	@ApiModelProperty(value = "二氧化碳浓度（单位：/10^-6)",required = false)
	private BigDecimal co2;	//二氧化碳浓度（单位：/10^-6)

	@ApiModelProperty(value = "光照度（单位：lx)",required = false)
	private BigDecimal illuminance;	//光照度（单位：lx)

	@ApiModelProperty(value = "土壤温度(单位：摄氏度）",required = false)
	private BigDecimal soilTemperature;	//土壤温度(单位：摄氏度）

	@ApiModelProperty(value = "土壤湿度（单位：%）",required = false)
	private BigDecimal soilHumidity;	//土壤湿度（单位：%）

	@ApiModelProperty(value = "时间",required = false)
	private LocalDateTime time;	//时间

	@ApiModelProperty(value = "大棚id",required = false)
	private Long greenhouseId;	//大棚id

	@ApiModelProperty(value = "创建时间",required = false)
	private LocalDateTime createTime;	//创建时间

	@ApiModelProperty(value = "更新时间",required = false)
	private LocalDateTime updateTime;	//更新时间

	@ApiModelProperty(value = "是否已处理",required = false)
	private Integer handled;	//是否已处理

}
