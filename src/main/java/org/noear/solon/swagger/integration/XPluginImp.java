package org.noear.solon.swagger.integration;

import java.util.Properties;

import org.noear.solon.Utils;
import org.noear.solon.core.Aop;
import org.noear.solon.core.Props;
import org.noear.solon.extend.staticfiles.StaticMappings;
import org.noear.solon.extend.staticfiles.repository.ClassPathStaticRepository;
import org.noear.solon.swagger.SwaggerConfig;
import org.noear.solon.swagger.annotation.EnableSwagger;
import org.noear.solon.swagger.SwaggerConst;
import org.noear.solon.SolonApp;
import org.noear.solon.core.Plugin;

/**
 * @author: lbq
 * 联系方式: 526509994@qq.com
 * 创建日期: 2020/6/12
 */
public class XPluginImp implements Plugin {

    @Override
    public void start(SolonApp app) {
        if (app.source().isAnnotationPresent(EnableSwagger.class) == false) {
            return;
        }


        StaticMappings.add("/", new ClassPathStaticRepository("META-INF/resources"));
        app.add("", SwaggerController.class);


        Aop.beanOnloaded(()->{
            SwaggerConfig config = Aop.getOrNew(SwaggerConfig.class);

            Properties properties = Utils.loadProperties(config.getPropPath());

            SwaggerConst.CONFIG = new Props();
            SwaggerConst.CONFIG.putAll(properties);
            SwaggerConst.HTTP_CODE = config.getHttpCode();
            SwaggerConst.COMMON_RES = config.getCommonRet();
            SwaggerConst.RESPONSE_IN_DATA = SwaggerConst.CONFIG.getBool("responseInData", true);

        });
    }
}
