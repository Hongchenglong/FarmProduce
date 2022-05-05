package cn.ecnu.code.build;

import java.util.Map;

/*****
 * @Author:  千千科技
 * @Date: 2022/01/08 11:34
 * @Description:  cn.ecnu.code.build
 *  生成Swagger
 ****/
public class SwaggerBuilder {

    /***
     * ServiceImpl构建
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap){
        //swagger的文件名字
        modelMap.put("Table","swagger");

        //生成ServiceImpl层文件
        BuilderFactory.builder(modelMap,
                "/template/swagger",
                "swagger.json",
                TemplateBuilder.SWAGGERUI_PATH,
                ".json");
    }
}
