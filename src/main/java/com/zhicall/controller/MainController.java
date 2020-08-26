package com.zhicall.controller;

import com.zhicall.constants.SystemConstants;
import com.zhicall.factory.create.CodeCreater;
import com.zhicall.pojo.param.GenerateParam;
import com.zhicall.pojo.vo.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
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
@Slf4j
@RestController
@RequestMapping("/genereate")
@Api(tags = "智康自助机自动生成代码接口")
public class MainController {

    // 默认输出路径为桌面code文件夹
    private static String writePath = "C:/Users/Administrator/Desktop/code";

    /**
     * 生成局部代码：entity/mapper/dao/service/controller/util/上述全部文件
     * 根据入参GenerateType枚举类型决定最终生成的类型
     *
     * @param param 入参
     */
    @PostMapping(value = "/oneOrAll")
    @ApiOperation("模块代码生成接口")
    public ResultDTO generateDao(@Valid @RequestBody GenerateParam param) {
        try {
            log.info("【入参】" + param);
            // 代码输出路径默认处理
            String outputPath = StringUtils.isEmpty(param.getWritePath()) ? writePath : param.getWritePath();
            log.info("输出路径：" + outputPath);

            // 生成对应场景类型的局部代码
            boolean ifSuccess = CodeCreater.StartCreate(outputPath, param);

            if(ifSuccess) {
                return new ResultDTO(SystemConstants.SUCCESS.getCode(), "操作成功");
            }
            return new ResultDTO(SystemConstants.FAIL.getCode(), "操作失败");
        } catch (Exception e) {
            log.error("系统错误:{}", e);
            return new ResultDTO(SystemConstants.ERROR.getCode(), "系统异常，请联系管理员");
        }
    }

    /**
     * 生成全量代码
     *
     * @param param 入参
     */
    @PostMapping("/all")
    @ApiOperation("数据库代码生成接口")
    public ResultDTO generateDB(@Valid @RequestBody GenerateParam param) {
        try {
            // TODO 生成有关DB的自动化代码
            return new ResultDTO(SystemConstants.SUCCESS.getCode(), "操作成功");
        } catch (Exception e) {
            return new ResultDTO(SystemConstants.ERROR.getCode(), "系统异常，请联系管理员");
        }
    }




}
