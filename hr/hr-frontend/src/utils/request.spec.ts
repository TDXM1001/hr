import { describe, it, expect } from 'vitest';
import { formatResponse } from './request';

describe('Request Utility', () => {
    it('should format API response correctly', () => {
        const rawResponse = { data: { code: 200, message: '操作成功', data: 'ok' } };
        const result = formatResponse(rawResponse);
        expect(result).toBe('ok');
    });
});
