package cn.ecnu.common.constant.enums;

import org.apache.commons.lang.StringUtils;

public enum OrderStatusEnum {
    OPENEDPPP(1, "opened", "未完成"),
    PLACED(2, "placed", "进行中"),
    FINISHED(3 ,"finished", "已完成"),
    CANCELLED(4, "cancelled", "已取消");

    private Integer code;
    private String status;
    private String desc;

    private OrderStatusEnum(Integer code, String status, String desc) {
        this.code = code;
        this.status = status;
        this.desc = desc;
    }

    public static OrderStatusEnum getEnumByStatus(String status) {
        if (StringUtils.isNotBlank(status)) {
            OrderStatusEnum[] arry = values();

            for(int i = 0; i < arry.length; ++i) {
                if (arry[i].getStatus().equals(status)) {
                    return arry[i];
                }
            }
        }

        return null;
    }

    public static OrderStatusEnum getEnumByCode(Integer code) {
        if (code != null) {
            OrderStatusEnum[] arry = values();

            for(int i = 0; i < arry.length; ++i) {
                if (arry[i].getCode().equals(code)) {
                    return arry[i];
                }
            }
        }

        return null;
    }

    public static String getStatusByCode(Integer code) {
        OrderStatusEnum statusEnum = getEnumByCode(code);
        return statusEnum != null ? statusEnum.getStatus() : null;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDesc() {
        return this.desc;
    }
}
