package cn.ecnu.common.MQ;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class MsgInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private T data;
}
