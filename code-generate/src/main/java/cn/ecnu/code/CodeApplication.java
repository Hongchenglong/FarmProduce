package cn.ecnu.code;

import cn.ecnu.code.build.TemplateBuilder;

/****
 * @Author: 千千科技
 * @Description: 
 * @Date 2022/02/08
 *****/
public class CodeApplication {

    public static void main(String[] args) {
        /**
         * tableName 为空或者null生成所有表的代码，否则生成指定表的代码
         */
        String tableName = "environment";
        String prefix = ""; //去除表的前缀，为""则不去除,例 "tb_", "t_" ...
        //调用该方法即可
        TemplateBuilder.builder(tableName, prefix);
    }
}
