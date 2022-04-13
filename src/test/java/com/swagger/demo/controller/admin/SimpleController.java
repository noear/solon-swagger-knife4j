package com.swagger.demo.controller.admin;

import com.swagger.demo.model.bean.DeviceParamBean;
import com.swagger.demo.model.dto.EquipDto;
import com.swagger.demo.model.dto.MachineDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.noear.snack.ONode;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.swagger.annotation.ApiRes;
import org.noear.solon.swagger.annotation.ApiResProperty;
import org.noear.solon.swagger.annotation.ApiEnum;
import org.noear.solon.swagger.SwaggerRes;
import org.noear.solon.swagger.util.KvMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lbq
 * 联系方式: 526509994@qq.com
 * 创建日期: 2020/9/16
 */
@Mapping("/simple")
@Api(description = "HelloWorld", tags = "最简用法")
@Controller
public class SimpleController{
    //
//    @ApiOperation(value = "不描述返回值", notes = "SwaggerConst.COMMON_RES定义返回")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paramA", value = "参数a", defaultValue = "1111"),
//            @ApiImplicitParam(name = "paramB", value = "参数b", defaultValue = "222"),
//    })
//    public void test1() {
//        this.renderJson();
//    }
//
//
//    @ApiOperation(value = "简单返回值", notes = "SwaggerConst.COMMON_RES.data中返回值")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paramA", value = "参数a", defaultValue = "1111"),
//            @ApiImplicitParam(name = "paramB", value = "参数b", defaultValue = "222"),
//    })
//    @ApiRes({
//            @ApiResProperty(name = "resA", value = "返回值A", example = "hello word1"),
//            @ApiResProperty(name = "resB", value = "返回值b", example = "hello word2"),
//    })
//    public void test2() {
//
//        this.renderJson();
//    }
//
//    @ApiOperation(value = "url参数Json", notes = "多个参数在url中提交,json参数支持多个")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paramA", value = "参数a", defaultValue = "1111"),
//            @ApiImplicitParam(name = "paramB", value = "参数b", defaultValue = "222"),
//            @ApiImplicitParam(name = "device", value = "网关附加数据JSON", dataTypeClass = DeviceParamBean.class),
//            @ApiImplicitParam(name = "equip", value = "农机具JSON", dataTypeClass = EquipDto.class),
//    })
//    @ApiRes({
//            @ApiResProperty(name = "resA", value = "返回值A", example = "hello word1"),
//            @ApiResProperty(name = "resB", value = "返回值b", example = "hello word2"),
//    })
//    public void test3(String paramA, String paramB, String device) {
//        this.renderJson();
//    }
//
//    @ApiOperation(value = "body接口参数Json", notes = "在body中提交JSON.注意body只支持一个json串. body提交多个json将失效", httpMethod = ApiEnum.METHOD_POST)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paramA", value = "参数a", defaultValue = "1111"),
//            @ApiImplicitParam(name = "paramB", value = "参数b", defaultValue = "222"),
//            @ApiImplicitParam(name = "device", value = "网关附加数据JSON", dataTypeClass = DeviceParamBean.class, paramType = ApiEnum.PARAM_TYPE_BODY),
//    })
//    @ApiRes({
//            @ApiResProperty(name = "resA", value = "返回值A", example = "hello word1"),
//            @ApiResProperty(name = "resB", value = "返回值b", example = "hello word2"),
//    })
//    public void test4(String paramA, String paramB, String device) {
//        this.renderJson();
//    }
//
//    @ApiOperation(value = "参数Object嵌套Object", notes = "递归实现")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paramA", value = "参数a", defaultValue = "1111"),
//            @ApiImplicitParam(name = "paramB", value = "参数b", defaultValue = "222"),
//            @ApiImplicitParam(name = "machine", value = "农机JSON", dataTypeClass = MachineDto.class, paramType = ApiEnum.PARAM_TYPE_BODY),
//    })
//    @ApiRes({
//            @ApiResProperty(name = "resA", value = "返回值A", example = "hello word1"),
//            @ApiResProperty(name = "resB", value = "返回值b", example = "hello word2"),
//    })
//    public void test5(String paramA, String paramB, String device) {
//        this.renderJson();
//    }
//
    @ApiOperation(value = "返回值Object嵌套Object", notes = "递归实现")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramA", value = "参数a", defaultValue = "1111"),
            @ApiImplicitParam(name = "paramB", value = "参数b", defaultValue = "222"),
    })
    @ApiRes({
            @ApiResProperty(name = "resA", value = "返回值A", example = "hello word1"),
            @ApiResProperty(name = "resB", value = "返回值b", example = "hello word2"),
            @ApiResProperty(name = "equip", value = "农机具信息", dataTypeClass = EquipDto.class),
            @ApiResProperty(name = "equipList", value = "农机具集合", dataTypeClass = EquipDto.class, allowMultiple = true),
            @ApiResProperty(name = "machine", value = "农机信息", dataTypeClass = MachineDto.class),
    })
    public SwaggerRes test6(String paramA, String paramB, String device) {
        DeviceParamBean deviceParamBean = ONode.deserialize(device, DeviceParamBean.class);

        KvMap kv = new KvMap();
        kv.set("paramA", paramA);
        kv.set("paramB", paramB);
        kv.set("device", deviceParamBean);

        SwaggerRes swaggerRes = new SwaggerRes();
        swaggerRes.setData(kv);

        return swaggerRes;
    }
//
//    @ApiOperation(value = "url数组参数Json", notes = "数组参数在url中提交,json参数支持多个")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paramA", value = "参数a", defaultValue = "1111"),
//            @ApiImplicitParam(name = "paramB", value = "参数b", defaultValue = "222"),
//            @ApiImplicitParam(name = "device", value = "网关附加数据JSON", dataTypeClass = DeviceParamBean.class, allowMultiple = true),
//            @ApiImplicitParam(name = "equip", value = "农机具JSON", dataTypeClass = EquipDto.class, allowMultiple = true),
//    })
//    @ApiRes({
//            @ApiResProperty(name = "resA", value = "返回值A", example = "hello word1"),
//            @ApiResProperty(name = "resB", value = "返回值b", example = "hello word2"),
//    })
//    public void test7(String paramA, String paramB, String device) {
//        this.renderJson();
//    }
//
//    @ApiOperation(value = "body接口数组参数Json", notes = "在body中提交JSON.注意body只支持一个json串. body提交多个json将失效", httpMethod = ApiEnum.METHOD_POST)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "paramA", value = "参数a", defaultValue = "1111"),
//            @ApiImplicitParam(name = "paramB", value = "参数b", defaultValue = "222"),
//            @ApiImplicitParam(name = "device", value = "网关附加数据JSON", dataTypeClass = DeviceParamBean.class, allowMultiple = true, paramType = ApiEnum.PARAM_TYPE_BODY),
//    })
//    @ApiRes({
//            @ApiResProperty(name = "resA", value = "返回值A", example = "hello word1"),
//            @ApiResProperty(name = "resB", value = "返回值b", example = "hello word2"),
//    })
//    public void test8(String paramA, String paramB, String device) {
//        this.renderJson();
//    }


    @ApiOperation(value = "示例2", httpMethod = ApiEnum.METHOD_POST, produces = ApiEnum.PRODUCES_JSON, consumes = ApiEnum.CONSUMES_FORM_DATA)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传文件", dataType = ApiEnum.FILE),
    })
    @Mapping("upFile")
    public Map upFile() {
        return new HashMap();
    }
}
