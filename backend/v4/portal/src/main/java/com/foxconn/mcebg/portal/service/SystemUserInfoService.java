package com.foxconn.mcebg.portal.service;

import com.foxconn.mcebg.portal.model.dto.ResultDTO;
import com.foxconn.mcebg.portal.model.vo.UserInfoVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SystemUserInfoService {
    // UserInfo【01】获取当前登录用户
    ResultDTO<UserInfoVO> loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
