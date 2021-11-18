import axios from '@/plugins/axios';
import _ from 'lodash';

import { getUserInfoApi } from '@/http/user/userApi';
import { UserType } from '@/http/user/userType';

/**
 * @description 獲取用戶信息
 * @returns User
 */
export const getUserInfo = (): Promise<UserType> => {
  const request = _.cloneDeep(getUserInfoApi);
  return axios(request).then(({ success, data }: any): UserType => {
    if (!success) return { empId: '', empName: '' ,sex:'',postNo:'',deptNo:'',deptName:'',humFactory:'',factory:'',email:''};
    console.log("service getUserInfo")
    console.log("success",data)
    return data;
  });
}