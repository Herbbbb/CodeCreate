package com.zhicall.constants;

/**
 * @author hebo@zhicall.cn
 * @version 1.0
 * @date 2020/8/25 0025 10:49
 * @Desc
 */
public enum SystemConstants {

    // 系统异常
    ERROR(500, "服务器异常"),

    // 业务失败
    FAIL(-1, "业务失败"),

    // 业务成功
    SUCCESS(200, "业务成功");

    private int code;

    private String msg;

    SystemConstants(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
