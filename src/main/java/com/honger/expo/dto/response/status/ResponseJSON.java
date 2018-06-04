package com.honger.expo.dto.response.status;

import java.io.Serializable;

/**
 * Created by chenjian on 2018/4/14.
 */
public class ResponseJSON implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final boolean CODE = true;

    private boolean code;

    private String msg;

    private Object result;

    public ResponseJSON() {

    }

    public ResponseJSON(boolean code) {
        this.code = code;
    }

    public ResponseJSON(boolean code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseJSON(boolean code, Object result) {
        this.code = code;
        this.result = result;
    }

    public ResponseJSON(Object result) {
        this.result = result;
    }

    public static ResponseJSON error() {
        return error(false, "未知异常，请联系管理员");
    }

    public static ResponseJSON error(String msg) {
        return error(false, msg);
    }

    public static ResponseJSON error(boolean code, String msg) {
        return new ResponseJSON(code, msg);
    }

    public static ResponseJSON ok(String msg) {
        return new ResponseJSON(CODE, msg);
    }

    public static ResponseJSON ok(Object result) {
        return new ResponseJSON(CODE, result);
    }

    public static ResponseJSON ok() {
        return new ResponseJSON(CODE);
    }


    public boolean getCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseJSON{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
