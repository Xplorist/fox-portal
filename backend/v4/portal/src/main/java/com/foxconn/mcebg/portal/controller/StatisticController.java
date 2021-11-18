package com.foxconn.mcebg.portal.controller;

import com.foxconn.mcebg.portal.model.dto.ResultDTO;
import com.foxconn.mcebg.portal.model.dto.SystemInfoDTO;
import com.foxconn.mcebg.portal.model.dto.SystemVipDTO;
import com.foxconn.mcebg.portal.model.query.ListActiveUserQuery;
import com.foxconn.mcebg.portal.model.query.StatisticDataQuery;
import com.foxconn.mcebg.portal.model.query.VipAcessNumQuery;
import com.foxconn.mcebg.portal.model.vo.ListActiveUserVO;
import com.foxconn.mcebg.portal.model.vo.QueryStatisticDataVO;
import com.foxconn.mcebg.portal.model.vo.QueryVipAccessNumVO;
import com.foxconn.mcebg.portal.model.vo.UserInfoVO;
import com.foxconn.mcebg.portal.service.StatisticService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "系统统计API", tags = "系统统计API")
@ApiSupport(order = 999)
@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    StatisticService svc;

    // statistic【01】查询系统list
    @RequestMapping(value = "/listSystem", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ApiOperation(value = "【01】查询系统list")
    @ApiOperationSupport(order = 01)
    public ResultDTO<List<SystemInfoDTO>> listSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.listSystem(request, response);
    }

    // statistic【02】根据条件查询系统统计数据
    @RequestMapping(value = "/queryStatisticData", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ApiOperation(value = "【02】根据条件查询系统统计数据")
    @ApiOperationSupport(order = 02)
    public ResultDTO<QueryStatisticDataVO> queryStatisticData(@RequestBody @Validated StatisticDataQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.queryStatisticData(param, request, response);
    }

    // statistic【03】查询系统主管list
    @RequestMapping(value = "/listSystemVip", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ApiOperation(value = "【03】查询系统主管list")
    @ApiOperationSupport(order = 03)
    public ResultDTO<List<SystemVipDTO>> listSystemVip(String systemId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.listSystemVip(systemId, request, response);
    }

    // statistic【04】查询系统主管近一月每天的访问次数
    @RequestMapping(value = "/queryVipAccessNum", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ApiOperation(value = "【04】查询系统主管近一月每天的访问次数")
    @ApiOperationSupport(order = 04)
    public ResultDTO<List<QueryVipAccessNumVO>> queryVipAccessNum(@RequestBody @Validated VipAcessNumQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.queryVipAccessNum(param, request, response);
    }

    // statistic【05】查询活跃用户list(包含闲置用户list)
    @RequestMapping(value = "/listActiveUser", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ApiOperation(value = "【05】查询活跃用户list(包含闲置用户list)")
    @ApiOperationSupport(order = 05)
    public ResultDTO<ListActiveUserVO> listActiveUser(@RequestBody @Validated ListActiveUserQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.listActiveUser(param, request, response);
    }

    // statistic【06】根据工号或姓名查询人员信息
    @RequestMapping(value = "/queryEmpInfo", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ApiOperation(value = "【06】根据工号或姓名查询人员信息")
    @ApiOperationSupport(order = 06)
    public ResultDTO<List<UserInfoVO>> queryEmpInfo(String param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.queryEmpInfo(param, request, response);
    }

    /* 模板 放在最后 */
    // statistic【0x】模板
    @RequestMapping(value = "/template", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ApiOperation(value = "【0x】模板")
    @ApiOperationSupport(order = 999)
    public ResultDTO<Object> template(String param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResultDTO.of("", "");
    }
}
