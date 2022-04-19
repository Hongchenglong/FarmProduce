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
 * @Description: QrcodePojo
 * @Date 2022-04-18 21:25:40
 *****/
@ApiModel(description = "Qrcode",value = "Qrcode")
@TableName(value="qrcode")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Qrcode implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "",required = false)
	private Long id;	//

	@ApiModelProperty(value = "",required = false)
	private String qrcode;	//

	@ApiModelProperty(value = "",required = false)
	private LocalDateTime createTime;	//

	@ApiModelProperty(value = "",required = false)
	private LocalDateTime updateTiime;	//

}
