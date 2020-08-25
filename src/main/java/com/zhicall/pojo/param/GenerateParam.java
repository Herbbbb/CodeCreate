package com.zhicall.pojo.param;

import com.zhicall.constants.GenerateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/25 10:46
 * @Desc 统一入参
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateParam {

    // 输出路径
    private String writePath;

    @NotBlank(message = "类名信息不能为空")
    private String entityName;

    @NotBlank(message = "作者信息不能为空")
    private String author;

    // 生成类型
    @NotBlank(message = "生成类型不能为空")
    private GenerateType generateType;
}
