export function formatResponse(response: any) {
  if (response.data && response.data.code === 200) {
    return response.data.data;
  }
  throw new Error(response.data?.message || '请求失败');
}
