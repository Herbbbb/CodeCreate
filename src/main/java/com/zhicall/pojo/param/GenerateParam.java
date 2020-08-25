package com.zhicall.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/25 0025 10:46
 * @Desc
 */

@Data
public class GenerateParam {

    private String packageName;

    @NotBlank(message = "作者信息不能为空")
    private String author;
}
