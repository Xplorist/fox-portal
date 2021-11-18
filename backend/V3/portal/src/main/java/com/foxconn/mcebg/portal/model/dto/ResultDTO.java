package com.foxconn.mcebg.portal.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class ResultDTO<T> {
    @NonNull
    @ApiModelProperty(value = "结果响应编码, 0:失败，1：成功, 500:服务器发生错误", example = "1")
    private String code;
    @NonNull
    @ApiModelProperty(value = "结果响应消息", example = "结果响应消息")
    private String msg;
    @ApiModelProperty(value = "结果响应数据对象")
    private T data;
}
