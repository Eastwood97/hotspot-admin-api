package com.jsc.hotspot.db.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
@Data
public class TargetFaceResult {
    private Long targetId;
    private String targetName;
    private String desc;
    private String fileName;
    private String targetFaceImg1;
    private String targetFaceImg2;
    private String targetFaceImg3;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private Long operatorId;

    @Value("seaweedfsURL")
    private String seaweedfsURL;

    public String getTargetFaceImg1() {
        JSONObject jsonObject= JSON.parseObject(this.fileName);
        String fileId1=jsonObject.getString("fileId1");
        return seaweedfsURL+fileId1;
    }

    public String getTargetFaceImg2() {
        JSONObject jsonObject= JSON.parseObject(this.fileName);
        String fileId2=jsonObject.getString("fileId2");
        return seaweedfsURL+fileId2;
    }

    public String getFileId3() {
        JSONObject jsonObject= JSON.parseObject(this.fileName);
        String fileId3=jsonObject.getString("fileId3");
        return seaweedfsURL+fileId3;
    }
}
