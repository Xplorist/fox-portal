export const setResponse = (data: any) => ({ data, code: 500 });

export default {
  'GET /api/users': setResponse({ name: 'hello user', empno: 'hello world' }),
}