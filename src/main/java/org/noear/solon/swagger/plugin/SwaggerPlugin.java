package org.noear.solon.swagger.plugin;

import java.util.Map;

import org.noear.solon.swagger.common.SwaggerConst;
import org.noear.solon.swagger.common.SwaggerHttpCode;
import org.noear.solon.swagger.common.SwaggerRes;
import org.noear.solon.swagger.controller.SwaggerController;
import org.noear.solon.Solon;
import org.noear.solon.SolonApp;
import org.noear.solon.core.Plugin;

/**
 * @author: lbq
 * 联系方式: 526509994@qq.com
 * 创建日期: 2020/6/12
 */
public class SwaggerPlugin implements Plugin {
    /**
     * swagger配置文件
     */
    private String propPath;

    /**
     * http返回状态
     */
    private Map httpCode;

    /**
     * http返回状态200时的通用返回格式
     */
    private Class<?> commonRet;

    public SwaggerPlugin setPropPath(String propPath) {
        this.propPath = propPath;
        return this;
    }

    public SwaggerPlugin setHttpCode(Map httpCode) {
        this.httpCode = httpCode;
        return this;
    }

    public SwaggerPlugin setCommonRet(Class<?> commonRet) {
        this.commonRet = commonRet;
        return this;
    }

    public SwaggerPlugin() {
        this.propPath = "swagger.properties";
        this.httpCode = new SwaggerHttpCode().getHttpCodeKv();
        this.commonRet = SwaggerRes.class;
    }

    @Override
    public void start(SolonApp app) {
        SwaggerConst.CONFIG = Solon.cfg().getProp(this.propPath);
        SwaggerConst.HTTP_CODE = this.httpCode;
        SwaggerConst.COMMON_RES = this.commonRet;
        SwaggerConst.RESPONSE_IN_DATA = SwaggerConst.CONFIG.getBool("responseInData", true);


        app.add("/swagger", SwaggerController.class);

//        Engine engine = Engine.create("swagger");
//        engine.setDevMode(false);
//        engine.setBaseTemplatePath("/swagger-template");
//        engine.setToClassPathSourceFactory();
    }
}
