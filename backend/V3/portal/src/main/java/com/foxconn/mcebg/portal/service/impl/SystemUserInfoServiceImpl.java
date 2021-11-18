package com.foxconn.mcebg.portal.service.impl;

import com.foxconn.mcebg.portal.mapper.base.StatisticMapper;
import com.foxconn.mcebg.portal.model.dto.ResultDTO;
import com.foxconn.mcebg.portal.model.vo.UserInfoVO;
import com.foxconn.mcebg.portal.oidc.model.UserInfo;
import com.foxconn.mcebg.portal.service.SystemUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SystemUserInfoServiceImpl implements SystemUserInfoService {
    @Autowired
    StatisticMapper mapper;

    // UserInfo【01】获取当前登录用户
    @Override
    public ResultDTO<UserInfoVO> loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");

        if (userInfo != null) {
            String empId = userInfo.getSub();
            // StatisticMapper【10】根据工号查询用户信息
            UserInfoVO vo = mapper.queryUserInfo(empId.toUpperCase());
            vo.setEmail(userInfo.getEmail());

            String deptNo = vo.getDeptNo();
            String factory = vo.getFactory();
            if (factory != null && !"CD".equals(factory)) {
                factory = "SZ";
            }
            if (deptNo != null && !"".equals(deptNo) && factory != null && !"".equals(factory)) {
                // StatisticMapper【11】根据部门代码查询部门名称
                String deptName = mapper.queryDeptName(deptNo, factory);
                vo.setDeptName(deptName);
            }

            return new ResultDTO<>("1", "获取当前登录用户成功", vo);
        } else {
            return new ResultDTO<>("0", "未登录或登录失效,获取当前登录用户失败，请登录", null);
        }
    }
}
