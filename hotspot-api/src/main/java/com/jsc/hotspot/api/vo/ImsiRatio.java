package com.jsc.hotspot.api.vo;

import lombok.Data;

/**
 * @author tzm
 * 关联的imsi以及关联度
 */
@Data
public class ImsiRatio {
   private Double ratio;
   private String imsi;
   private double ratioTwo;
   private double relatedCount;
}
