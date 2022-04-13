package org.noear.solon.swagger.integration;

import java.util.Map;
import java.util.Properties;

import org.noear.solon.Utils;
import org.noear.solon.core.Props;
import org.noear.solon.extend.staticfiles.StaticMappings;
import org.noear.solon.extend.staticfiles.repository.ClassPathStaticRepository;
import org.noear.solon.swagger.annotation.EnableSwagger;
import org.noear.solon.swagger.SwaggerConst;
import org.noear.solon.swagger.SwaggerHttpCode;
import org.noear.solon.swagger.SwaggerRes;
import org.noear.solon.SolonApp;
import org.noear.solon.core.Plugin;

/**
 * @author: lbq
 * 联系方式: 526509994@qq.com
 * 创建日期: 2020/6/12
 */
public class XPluginImp implements Plugin {
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

    public XPluginImp setPropPath(String propPath) {
        this.propPath = propPath;
        return this;
    }

    public XPluginImp setHttpCode(Map httpCode) {
        this.httpCode = httpCode;
        return this;
    }

    public XPluginImp setCommonRet(Class<?> commonRet) {
        this.commonRet = commonRet;
        return this;
    }

    public XPluginImp() {
        this.propPath = "swagger.properties";
        this.httpCode = new SwaggerHttpCode().getHttpCodeKv();
        this.commonRet = SwaggerRes.class;
    }

    @Override
    public void start(SolonApp app) {
        if (app.source().isAnnotationPresent(EnableSwagger.class) == false) {
            return;
        }

        Properties properties = Utils.loadProperties(this.propPath);

        SwaggerConst.CONFIG = new Props();
        SwaggerConst.CONFIG.putAll(properties);
        SwaggerConst.HTTP_CODE = this.httpCode;
        SwaggerConst.COMMON_RES = this.commonRet;
        SwaggerConst.RESPONSE_IN_DATA = SwaggerConst.CONFIG.getBool("responseInData", true);


        StaticMappings.add("/", new ClassPathStaticRepository("META-INF/resources"));
        app.add("", SwaggerController.class);
    }
}
