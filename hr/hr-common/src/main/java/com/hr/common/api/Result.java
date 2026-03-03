package com.hr.common.api;

public class Result<T> {
  private int code;
  private String message;
  private T data;
  private long timestamp;

  public static <T> Result<T> success(T data) {
    Result<T> r = new Result<>();
    r.code = 200;
    r.message = "操作成功";
    r.data = data;
    r.timestamp = System.currentTimeMillis();
    return r;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public T getData() {
    return data;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
