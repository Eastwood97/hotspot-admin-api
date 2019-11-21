package com.jsc.hotspot.api.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @author huixing
 * @description
 * @date 2019/11/20
 */
@Getter
public class RTSPStreamDTO {
    /**
     * http://192.201.102.100:10008/api/v1/stream/start?url=rtsp%3A%2F%2Fadmin%3Aadmin12345%40192.201.102.201&customPath=stream_265&transType=TCP&idleTimeout=10&heartbeatInterval=60
     */
    private String url;
    private String customPath;
    private String transType;
    private Integer idleTimeout;
    private Integer heartbeatInterval;

    public void create(String url, String customPath, String transType, Integer idleTimeout, Integer heartbeatInterval){
        this.url = url;
        this.customPath = customPath;
        this.transType = transType;
        this.idleTimeout = idleTimeout;
        this.heartbeatInterval = heartbeatInterval;
    }

}
