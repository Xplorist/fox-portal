package com.foxconn.systemportal.controller;

import com.foxconn.systemportal.model.dto.ResultDTO;
import com.foxconn.systemportal.model.dto.SystemInfoDTO;
import com.foxconn.systemportal.model.query.StatisticDataQuery;
import com.foxconn.systemportal.model.vo.QueryStatisticDataVO;
import com.foxconn.systemportal.service.StatisticService;
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
@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    StatisticService svc;

    // statistic【01】查询系统list
    @RequestMapping(value = "/listSystem", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ApiOperation(value = "【01】查询系统list")
    public ResultDTO<List<SystemInfoDTO>> listSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.listSystem(request, response);
    }

    // statistic【02】根据条件查询系统统计数据
    @RequestMapping(value = "/queryStatisticData", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    @ApiOperation(value = "【02】根据条件查询系统统计数据")
    public ResultDTO<QueryStatisticDataVO> queryStatisticData(@RequestBody @Validated StatisticDataQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return svc.queryStatisticData(param, request, response);
    }

    /* 模板 放在最后 */
    // statistic【0x】模板
    @RequestMapping(value = "/template", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ApiOperation(value = "【0x】模板")
    public ResultDTO template(String param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResultDTO.of("", "");
    }
}
