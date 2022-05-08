package cn.ecnu.system.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

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
public class EnvironmentDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "日期",required = false)
	private LocalDate date;	//日期

	@ApiModelProperty(value = "大棚id",required = false)
	private Long greenhouseId;	//大棚id

}
