package com.jsc.hotspot.common.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存储服务 返回结果父类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResult extends WebServiceResult {

    int returnCode;
    private String errorMessage;
}
