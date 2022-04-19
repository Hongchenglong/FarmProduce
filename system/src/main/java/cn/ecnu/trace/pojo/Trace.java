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
 * @Description: TracePojo
 * @Date 2022-04-18 21:25:40
 *****/
@ApiModel(description = "Trace",value = "Trace")
@TableName(value="trace")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Trace implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",required = false)
	private Long id;	//

	@ApiModelProperty(value = "溯源码",required = false)
	private String qrcode;	//溯源码

	@ApiModelProperty(value = "企业id",required = false)
	private Integer enterpriseId;	//企业id

	@ApiModelProperty(value = "大棚id",required = false)
	private Integer greenhouseId;	//大棚id

	@ApiModelProperty(value = "培育人员",required = false)
	private String nurtureWorker;	//培育人员

	@ApiModelProperty(value = "生产人员",required = false)
	private String productWorker;	//生产人员

	@ApiModelProperty(value = "质检人员",required = false)
	private String qualityWorker;	//质检人员

	@ApiModelProperty(value = "运输人员",required = false)
	private String transportWorker;	//运输人员

	@ApiModelProperty(value = "农产品id",required = false)
	private Integer produceId;	//农产品id

	@ApiModelProperty(value = "更新时间",required = false)
	private LocalDateTime updateDate;	//更新时间

	@ApiModelProperty(value = "创建时间",required = false)
	private LocalDateTime createTime;	//创建时间

}
