package org.noear.solon.swagger.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.noear.solon.swagger.util.IOUtils;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;

/**
 * 访问Jar资源
 *
 * @author: lbq
 * 联系方式: 526509994@qq.com
 * 创建日期: 2020/6/11
 */
public class SwaggerHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Throwable {
        if (ctx.path().contains("webjars/")) {
            String path = "META-INF/resources" + ctx.path();

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
            OutputStream outputStream = null;
            try {
                if (inputStream != null) {
                    outputStream = ctx.outputStream();
                    IOUtils.copy(inputStream, ctx.outputStream());
                }
            } catch (IOException e) {
                System.err.println("无法读取Swagger UI静态资源:");
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(outputStream);
            }

            ctx.setHandled(true);
        }
    }
}
