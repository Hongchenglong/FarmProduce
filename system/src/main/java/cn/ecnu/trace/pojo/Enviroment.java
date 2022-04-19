package cn.ecnu.trace.pojo;

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
 * @Description: EnviromentPojo
 * @Date 2022-04-18 21:25:40
 *****/
@ApiModel(description = "Enviroment",value = "Enviroment")
@TableName(value="enviroment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Enviroment implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",required = true)
	@TableId(type = IdType.AUTO)
	private Long id;	//

	@ApiModelProperty(value = "空气温度",required = false)
	private String airTemperature;	//空气温度

	@ApiModelProperty(value = "空气湿度",required = false)
	private String airHumidity;	//空气湿度

	@ApiModelProperty(value = "二氧化碳",required = false)
	private String CO2;	//二氧化碳

	@ApiModelProperty(value = "光照度",required = false)
	private String illuminance;	//光照度

	@ApiModelProperty(value = "土壤温度",required = false)
	private String soilTemperature;	//土壤温度

	@ApiModelProperty(value = "土壤湿度",required = false)
	private String soilHumidity;	//土壤湿度

	@ApiModelProperty(value = "日期",required = false)
	private LocalDateTime date;	//日期

	@ApiModelProperty(value = "大棚id",required = false)
	private Integer greenhouseId;	//大棚id

	@ApiModelProperty(value = "创建时间",required = false)
	private LocalDateTime createTime;	//创建时间

	@ApiModelProperty(value = "更新时间",required = false)
	private LocalDateTime updateTime;	//更新时间

}
