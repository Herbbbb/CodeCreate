package com.zhicall.controller;

import com.zhicall.constants.SystemConstants;
import com.zhicall.factory.create.CodeCreater;
import com.zhicall.pojo.param.GenerateParam;
import com.zhicall.pojo.vo.ResultDTO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/25 10:36
 * 生成代码入口
 */
@RestController
@RequestMapping("/genereate")
public class MainController {

    // 默认输出路径为桌面code文件夹
    private static String writePath = "C:/Users/Administrator/Desktop/code";

    /**
     * 生成局部代码：entity/mapper/dao/service/controller/util
     * 根据入参GenerateType枚举类型决定最终生成的类型
     *
     * @param param 入参
     */
    @RequestMapping("/oneOrAll")
    public ResultDTO generateDao(@Valid @RequestBody GenerateParam param) {
        try {
            // 代码输出路径默认处理
            String outputPath = StringUtils.isEmpty(param.getWritePath()) ? writePath : param.getWritePath();

            // 生成对应场景类型的局部代码
            boolean ifSuccess = CodeCreater.StartCreate(outputPath, param);

            if(ifSuccess) {
                return new ResultDTO(SystemConstants.SUCCESS.getCode(), "操作成功");
            }
            return new ResultDTO(SystemConstants.FAIL.getCode(), "操作失败");
        } catch (Exception e) {
            return new ResultDTO(SystemConstants.ERROR.getCode(), "系统异常，请联系管理员");
        }
    }


    /**
     * 生成全量代码
     *
     * @param param 入参
     */
    @RequestMapping("/all")
    public ResultDTO generateAll(@Valid @RequestBody GenerateParam param) {
        try {
            // TODO 局部代码生成(POJO-DAO-SERVICE-UTIL....)
            return new ResultDTO(SystemConstants.SUCCESS.getCode(), "操作成功");
        } catch (Exception e) {
            return new ResultDTO(SystemConstants.ERROR.getCode(), "系统异常，请联系管理员");
        }
    }


}
