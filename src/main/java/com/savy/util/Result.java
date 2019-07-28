package com.savy.util;



import com.savy.util.ResultStatus;


 
public class Result<T> {

    private Integer status; //接口返回状态
    private String message; //接口提示信息
    private T data;         //接口所含数据


    public static <R> Result<R> newSuccessResult(R data){
        Result<R> result = new Result<>();
        result.setStatus(ResultStatus.SUCCESS.getCode());
        result.setMessage(ResultStatus.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.status = resultStatus.getCode();
        this.message = resultStatus.getMessage();
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
