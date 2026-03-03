package com.hr.common.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {
    @Test
    public void testSuccessResponseFormat() {
        Result<String> result = Result.success("ok");
        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMessage());
        assertEquals("ok", result.getData());
        assertTrue(result.getTimestamp() > 0);
    }
}
