package top.baozoulolw.webchat.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.baozoulolw.webchat.common.enums.ResultCode;

/**
 * 统一返回类
 *
 * @author Baozoulolw
 * @version 1.0
 * @date 2021-06-09 15:04
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    /**
     * 1.status状态值：代表本次请求response的状态结果。
     */
    private Integer status;
    /**
     * 2.response描述：对本次状态码的描述。
     */
    private String desc;
    /**
     * 3.data数据：本次返回的数据。
     */
    private T data;

    /**
     * 成功，创建ResResult：没data数据
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功，创建ResResult：有data数据
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 失败，指定status、desc
     */
    public static <T> Result<T> fail(Integer status, String desc) {
        Result<T> result = new Result<>();
        result.setStatus(status);
        result.setDesc(desc);
        return result;
    }

    /**
     * 失败，指定ResultCode枚举
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 把ResultCode枚举转换为ResResult
     */
    private void setResultCode(ResultCode code) {
        this.status = code.code();
        this.desc = code.message();
    }
}

