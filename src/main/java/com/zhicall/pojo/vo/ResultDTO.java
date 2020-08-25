package com.zhicall.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/25 10:37
 */
@Data
public class ResultDTO {

    @Builder.Default
    private String system = "智康自助机代码自动生成系统";

    private int code;

    private String msg;

    public ResultDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
