package cn.ecnu.system.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Hongchenglong
 * @since 2022-05-08
 */
@Data
public class TraceAndProduceVO {

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
    private LocalDateTime updateTime;	//更新时间

    @ApiModelProperty(value = "创建时间",required = false)
    private LocalDateTime createTime;	//创建时间

    @ApiModelProperty("农产品名称")
    private String produceName;
}
