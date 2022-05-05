package cn.ecnu.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Hongchenglong
 * @since 2022-04-23
 */
@Data
public class TraceQueryVO {

    @ApiModelProperty(value = "开始日期", required = false)
    private LocalDateTime startDate;

    @ApiModelProperty(value = "结束日期", required = false)
    private LocalDateTime endDate;
}
