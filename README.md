JFinal-swagger-knife4j = JFinal to easy use knife4j UI for swagger

JFinal集成knife4j,按UI规范生成相应的Swagger Json


使用方式：
maven引入
```
<dependency>
    <groupId>com.lastB7</groupId>
    <artifactId>jfinal-swagger-knife4j</artifactId>
    <version>2.4</version>
</dependency>
```

初始化插件
```
me.add(new SwaggerRoutes());
me.add(new SwaggerPlugin());
me.add(new SwaggerHandler());

```

初始化配置文件
copy到工程resources目录下
```
# 配置 swagger

# 启用文档
enable = true

# 当前swagger的版本号
swaggerVersion = 2.0

# 文档名称
info_title = 在线文档
# 文档说明
info_description = 在线API文档
# 服务条件
info_termsOfService = https://github.com/996icu/996.ICU/blob/master/LICENSE
# 许可
info_license_name = linBq
info_license_url = http://www.jfinal.com/user/43453
# 联系方式
info_contact_name = linBq
info_contact_email = 526509994@qq.com
# 文档版本号
info_version = 1.0

# 访问地址,默认指向当前服务器
# host = 127.0.0.1:8080

# 访问前缀,默认为空,需与JFinal配置的controllerKey前缀一致
basePath = /api

# 访问许可
schemes = http, https

# 链接外部文档
externalDocs_description = Find out more about Swagger
externalDocs_url = https://swagger.io/

# 接口分组 分组名#包名 多个分组使用逗号拼接
swagger_resources = admin端接口#com.swagger.demo.admin, app端接口#com.swagger.demo.app


# 提供全局参数Debug功能,目前默认提供header(请求头)、query(form)两种方式的入参.Debug调试tab页会带上该参数
# 格式  name#in   多个全局参数使用逗号拼接
# name  参数名.
# in    header(请求头) | query(form)
globalSecurityParameters = token#header, testPara#query


#############################  扩展增强设置  #############################
# 禁用OpenApi结构显示(默认显示)
# enableOpenApi = false
# 禁用UI搜索框(默认显示)
# enableSearch = false
# 禁用调试(默认显示)
# enableDebug = false
# 调试Tab是否显示AfterScript功能(默认显示)
# enableAfterScript = false
# 是否显示界面中SwaggerModel功能(默认显示)
# enableSwaggerModels = false
# 自定义Swagger Models名称(默认显示Swagger Models)
# swaggerModelName = 我是自定义的Model名称
# 是否显示Footer(默认显示)
# enableFooter = false
# 是否启用自定义Footer
# enableFooterCustom = true
# 自定义Footer内容,支持Markdown语法. 需enableFooter=false&&enableFooterCustom=true时生效
# footerCustomContent = Apache License 2.0 | Copyright  2019-[浙江八一菜刀股份有限公司](https://gitee.com/xiaoym/knife4j)



```

使用示例
```
public class MainConfig extends JFinalConfig {

    public static void main(String[] args) {
        // UndertowServer.start(MainConfig.class);
        // 使用undertow的热加载模式
        UndertowServer.create(MainConfig.class)
            .addHotSwapClassPrefix("org.noear.solon.swagger.")
            .start();
    }

    @Override
    public void configConstant(Constants me) {}

    @Override
    public void configRoute(Routes me) {
        me.add("/index", IndexController.class);

        // swagger路由
        me.add(new SwaggerRoutes());
    }

    @Override
    public void configEngine(Engine me) {
    }

    @Override
    public void configPlugin(Plugins me) {
        me.add(new SwaggerPlugin());
    }

    @Override
    public void configInterceptor(Interceptors me) {
    }

    @Override
    public void configHandler(Handlers me) {
        me.add(new SwaggerHandler());
    }
}

```