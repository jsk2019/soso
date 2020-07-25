package com.whu.soso.model;

public class MyResult {

private int code;
private String msg;
private Object result;

 public MyResult status(int code){
     this.code=code;
     return this;
 }
 public MyResult msg(String msg){
     this.msg=msg;
     return this;
 }
 public MyResult result(Object data){
     this.result=data;
     return this;
 }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
}
