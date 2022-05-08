package cn.ecnu.system.model.vo;

import cn.ecnu.system.pojo.EnvironmentItem;
import cn.ecnu.system.pojo.Greenhouse;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/****
 * @Author: Ecnu
 * @Description: EnvironmentPojo
 * @Date 2022-05-07 11:15:40
 *****/
@ApiModel(description = "Environment",value = "Environment")
@TableName(value="environment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EnvironmentVO implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",required = true)
	@TableId(type = IdType.AUTO)
	private Long id;	//

	@ApiModelProperty(value = "空气温度",required = false)
	private BigDecimal airTemperature;	//空气温度

	@ApiModelProperty(value = "空气湿度",required = false)
	private BigDecimal airHumidity;	//空气湿度

	@ApiModelProperty(value = "二氧化碳",required = false)
	private BigDecimal co2;	//二氧化碳

	@ApiModelProperty(value = "光照度",required = false)
	private BigDecimal illuminance;	//光照度

	@ApiModelProperty(value = "土壤温度",required = false)
	private BigDecimal soilTemperature;	//土壤温度

	@ApiModelProperty(value = "土壤湿度",required = false)
	private BigDecimal soilHumidity;	//土壤湿度

	@ApiModelProperty(value = "日期",required = false)
	private LocalDate date;	//日期

	@ApiModelProperty(value = "大棚id",required = false)
	private Long greenhouseId;	//大棚id

	@ApiModelProperty(value = "创建时间",required = false)
	private LocalDateTime createTime;	//创建时间

	@ApiModelProperty(value = "更新时间",required = false)
	private LocalDateTime updateTime;	//更新时间

	@ApiModelProperty(value = "当前各个时间段的环境变量值",required = false)
	private List<EnvironmentItem> itemList = new ArrayList<>();

	@ApiModelProperty(value = "大棚信息",required = false)
	private Greenhouse greenhouse;

	@ApiModelProperty(value = "历史数据",required = false)
	private Map history;

}
