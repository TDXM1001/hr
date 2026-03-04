package com.hr.common.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 业务异常类相关测试
 */
public class BusinessExceptionTest {

  /**
   * 测试业务异常类的创建与属性获取
   */
  @Test
  public void testBusinessExceptionCreation() {
    BusinessException ex = new BusinessException(400, "参数错误");

    // 验证错误码和错误信息
    assertEquals(400, ex.getCode(), "错误码应为400");
    assertEquals("参数错误", ex.getMessage(), "错误信息应为'参数错误'");
  }
}
