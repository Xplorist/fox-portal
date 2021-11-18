package com.foxconn.mcebg.portal.controller;

import com.foxconn.mcebg.portal.model.dto.ResultDTO;
import com.foxconn.mcebg.portal.model.vo.UserInfoVO;
import com.foxconn.mcebg.portal.service.SystemUserInfoService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "用户信息API", tags = "用户信息API")
@ApiSupport(order = 1)
@RequestMapping("/userInfo")
@RestController
public class UserInfoController {
    @Autowired
    SystemUserInfoService systemUserInfoService;

    // UserInfo【01】获取当前登录用户
    @RequestMapping(value = "/loginUser", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ApiOperation(value = "【01】获取当前登录用户")
    @ApiOperationSupport(order = 1)
    public ResultDTO<UserInfoVO> loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return systemUserInfoService.loginUser(request, response);
    }
}
