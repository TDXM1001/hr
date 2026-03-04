package com.hr.common.exception;

/**
 * 通用业务异常类
 */
public class BusinessException extends RuntimeException {

  /**
   * 业务错误码
   */
  private final int code;

  /**
   * 构造函数
   *
   * @param code    错误码
   * @param message 错误信息
   */
  public BusinessException(int code, String message) {
    super(message);
    this.code = code;
  }

  /**
   * 获取错误码
   *
   * @return 错误码
   */
  public int getCode() {
    return code;
  }
}
