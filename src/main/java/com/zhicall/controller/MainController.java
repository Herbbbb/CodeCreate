package com.zhicall.controller;

import com.zhicall.constants.SystemConstants;
import com.zhicall.pojo.param.GenerateParam;
import com.zhicall.pojo.vo.ResultDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/25 10:36
 * 生成代码
 */
@RestController
@RequestMapping("/genereate")
public class MainController {

    /**
     * 生成局部代码-DAO
     *
     * @param param
     * @return
     */
    @RequestMapping("/dao")
    public ResultDTO generateDao(@Valid @RequestBody GenerateParam param) {
        try {
            // TODO 局部代码生成(POJO-DAO-SERVICE-UTIL....)
            return new ResultDTO(SystemConstants.SUCCESS.getCode(), "操作成功");
        } catch (Exception e) {
            return new ResultDTO(SystemConstants.ERROR.getCode(), "系统异常，请联系管理员");
        }
    }

    /**
     * 生成局部代码-Service
     *
     * @param param
     * @return
     */
    @RequestMapping("/service")
    public ResultDTO generateService(@Valid @RequestBody GenerateParam param) {
        try {
            // TODO 局部代码生成(POJO-DAO-SERVICE-UTIL....)
            return new ResultDTO(SystemConstants.SUCCESS.getCode(), "操作成功");
        } catch (Exception e) {
            return new ResultDTO(SystemConstants.ERROR.getCode(), "系统异常，请联系管理员");
        }
    }

    /**
     * 生成局部代码-Util
     *
     * @param param
     * @return
     */
    @RequestMapping("/util")
    public ResultDTO generateUtil(@Valid @RequestBody GenerateParam param) {
        try {
            // TODO 局部代码生成(POJO-DAO-SERVICE-UTIL....)
            return new ResultDTO(SystemConstants.SUCCESS.getCode(), "操作成功");
        } catch (Exception e) {
            return new ResultDTO(SystemConstants.ERROR.getCode(), "系统异常，请联系管理员");
        }
    }

    /**
     * 生成局部代码-db
     *
     * @param param
     * @return
     */
    @RequestMapping("/db")
    public ResultDTO generateDb(@Valid @RequestBody GenerateParam param) {
        try {
            // TODO 局部代码生成(POJO-DAO-SERVICE-UTIL....)
            return new ResultDTO(SystemConstants.SUCCESS.getCode(), "操作成功");
        } catch (Exception e) {
            return new ResultDTO(SystemConstants.ERROR.getCode(), "系统异常，请联系管理员");
        }
    }

    /**
     * 生成全量代码
     *
     * @param param
     * @return
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
