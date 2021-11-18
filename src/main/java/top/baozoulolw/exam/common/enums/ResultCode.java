package top.baozoulolw.exam.common.enums;

public enum ResultCode {
    /* 成功状态码 */
    SUCCESS(1000, "成功"),

    /* 登录成功状态码 */
    LOGIN_SUCCESS(2000, "登录成功"),

    /* 登录失败状态码 */
    LOGIN_FAILED(2001, "登录失败"),

    /* 系统500错误*/
    SYSTEM_ERROR(10000, "系统异常，请稍后重试"),
    UNAUTHORIZED(10401, "签名验证失败"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),

    /* 用户错误：20001-29999*/
    USER_HAS_EXISTED(20001, "用户名已存在"),
    USER_NOT_FIND(20002, "用户名不存在"),
    /* 登录失败状态码 */
    USER_NEED_AUTHORITIES(2011, "用户需要认证");
    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
