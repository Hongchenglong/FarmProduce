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
 * @Description: ProducePojo
 * @Date 2022-04-18 21:25:40
 *****/
@ApiModel(description = "Produce",value = "Produce")
@TableName(value="produce")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Produce implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",required = true)
	@TableId(type = IdType.AUTO)
	private Long id;	//

	@ApiModelProperty(value = "名称",required = false)
	private String name;	//名称

	@ApiModelProperty(value = "大棚id",required = false)
	private Integer greenhouseId;	//大棚id

	@ApiModelProperty(value = "中间阶段",required = false)
	private LocalDateTime intermediateStage;	//中间阶段

	@ApiModelProperty(value = "完成阶段",required = false)
	private LocalDateTime finishedStage;	//完成阶段

	@ApiModelProperty(value = "原始阶段",required = false)
	private LocalDateTime originalStage;	//原始阶段

	@ApiModelProperty(value = "图像地址",required = false)
	private String image;	//图像地址

	@ApiModelProperty(value = "创建时间",required = false)
	private LocalDateTime createTime;	//创建时间

	@ApiModelProperty(value = "更新时间",required = false)
	private LocalDateTime updateTime;	//更新时间

}
