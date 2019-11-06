package com.jsc.hotspot.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class CameraCatInfoDAO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column camera_cat_info.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column camera_cat_info.full_dat_id
     *
     * @mbg.generated
     */
    private String fullDatId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column camera_cat_info.full_img_id
     *
     * @mbg.generated
     */
    private String fullImgId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column camera_cat_info.video_id
     *
     * @mbg.generated
     */
    private String videoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column camera_cat_info.capture_time
     *
     * @mbg.generated
     */
    private LocalDateTime captureTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column camera_cat_info.quality
     *
     * @mbg.generated
     */
    private Integer quality;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column camera_cat_info.target_id
     *
     * @mbg.generated
     */
    private Long targetId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column camera_cat_info.dev_id
     *
     * @mbg.generated
     */
    private Long devId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column camera_cat_info.id
     *
     * @return the value of camera_cat_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column camera_cat_info.id
     *
     * @param id the value for camera_cat_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column camera_cat_info.full_dat_id
     *
     * @return the value of camera_cat_info.full_dat_id
     *
     * @mbg.generated
     */
    public String getFullDatId() {
        return fullDatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column camera_cat_info.full_dat_id
     *
     * @param fullDatId the value for camera_cat_info.full_dat_id
     *
     * @mbg.generated
     */
    public void setFullDatId(String fullDatId) {
        this.fullDatId = fullDatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column camera_cat_info.full_img_id
     *
     * @return the value of camera_cat_info.full_img_id
     *
     * @mbg.generated
     */
    public String getFullImgId() {
        return fullImgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column camera_cat_info.full_img_id
     *
     * @param fullImgId the value for camera_cat_info.full_img_id
     *
     * @mbg.generated
     */
    public void setFullImgId(String fullImgId) {
        this.fullImgId = fullImgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column camera_cat_info.video_id
     *
     * @return the value of camera_cat_info.video_id
     *
     * @mbg.generated
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column camera_cat_info.video_id
     *
     * @param videoId the value for camera_cat_info.video_id
     *
     * @mbg.generated
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column camera_cat_info.capture_time
     *
     * @return the value of camera_cat_info.capture_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCaptureTime() {
        return captureTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column camera_cat_info.capture_time
     *
     * @param captureTime the value for camera_cat_info.capture_time
     *
     * @mbg.generated
     */
    public void setCaptureTime(LocalDateTime captureTime) {
        this.captureTime = captureTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column camera_cat_info.quality
     *
     * @return the value of camera_cat_info.quality
     *
     * @mbg.generated
     */
    public Integer getQuality() {
        return quality;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column camera_cat_info.quality
     *
     * @param quality the value for camera_cat_info.quality
     *
     * @mbg.generated
     */
    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column camera_cat_info.target_id
     *
     * @return the value of camera_cat_info.target_id
     *
     * @mbg.generated
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column camera_cat_info.target_id
     *
     * @param targetId the value for camera_cat_info.target_id
     *
     * @mbg.generated
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column camera_cat_info.dev_id
     *
     * @return the value of camera_cat_info.dev_id
     *
     * @mbg.generated
     */
    public Long getDevId() {
        return devId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column camera_cat_info.dev_id
     *
     * @param devId the value for camera_cat_info.dev_id
     *
     * @mbg.generated
     */
    public void setDevId(Long devId) {
        this.devId = devId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fullDatId=").append(fullDatId);
        sb.append(", fullImgId=").append(fullImgId);
        sb.append(", videoId=").append(videoId);
        sb.append(", captureTime=").append(captureTime);
        sb.append(", quality=").append(quality);
        sb.append(", targetId=").append(targetId);
        sb.append(", devId=").append(devId);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CameraCatInfoDAO other = (CameraCatInfoDAO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFullDatId() == null ? other.getFullDatId() == null : this.getFullDatId().equals(other.getFullDatId()))
            && (this.getFullImgId() == null ? other.getFullImgId() == null : this.getFullImgId().equals(other.getFullImgId()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getCaptureTime() == null ? other.getCaptureTime() == null : this.getCaptureTime().equals(other.getCaptureTime()))
            && (this.getQuality() == null ? other.getQuality() == null : this.getQuality().equals(other.getQuality()))
            && (this.getTargetId() == null ? other.getTargetId() == null : this.getTargetId().equals(other.getTargetId()))
            && (this.getDevId() == null ? other.getDevId() == null : this.getDevId().equals(other.getDevId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFullDatId() == null) ? 0 : getFullDatId().hashCode());
        result = prime * result + ((getFullImgId() == null) ? 0 : getFullImgId().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getCaptureTime() == null) ? 0 : getCaptureTime().hashCode());
        result = prime * result + ((getQuality() == null) ? 0 : getQuality().hashCode());
        result = prime * result + ((getTargetId() == null) ? 0 : getTargetId().hashCode());
        result = prime * result + ((getDevId() == null) ? 0 : getDevId().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table camera_cat_info
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "BIGINT", false),
        fullDatId("full_dat_id", "fullDatId", "VARCHAR", false),
        fullImgId("full_img_id", "fullImgId", "VARCHAR", false),
        videoId("video_id", "videoId", "VARCHAR", false),
        captureTime("capture_time", "captureTime", "TIMESTAMP", false),
        quality("quality", "quality", "INTEGER", false),
        targetId("target_id", "targetId", "BIGINT", false),
        devId("dev_id", "devId", "BIGINT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table camera_cat_info
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}