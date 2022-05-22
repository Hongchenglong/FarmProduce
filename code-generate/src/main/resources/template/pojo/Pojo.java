package $

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
	</#if>
</#list>

/****
 * @Author: ${author}
 * @Description: ${Table}Pojo
 * @Date ${create_time}
 *****/
<#if swagger==true>
@ApiModel(description = "${Table}",value = "${Table}")
</#if>
@TableName(value="${TableName}")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ${Table} implements Serializable{
	private static final long serialVersionUID = 1L;

<#list models as model>
	<#if swagger==true>
	@ApiModelProperty(value = "${model.desc!""}",required = ${model.required?string('true','false')})
	</#if>
	<#if model.id==true>
	@TableId<#if model.identity=='YES'>(type = IdType.AUTO)</#if>
	</#if>
	<#if model.tablelogic==true>
	@TableLogic
	</#if>
	private ${model.simpleType} ${model.name};	//${model.desc}

</#list>
}
