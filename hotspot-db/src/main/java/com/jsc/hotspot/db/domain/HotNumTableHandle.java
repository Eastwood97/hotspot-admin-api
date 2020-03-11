package com.jsc.hotspot.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class HotNumTableHandle {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.imsi
     *
     * @mbg.generated
     */
    private String imsi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.imei
     *
     * @mbg.generated
     */
    private String imei;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.isdn
     *
     * @mbg.generated
     */
    private String isdn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.mode
     *
     * @mbg.generated
     */
    private Byte mode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.capture_time
     *
     * @mbg.generated
     */
    private LocalDateTime captureTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.target_id
     *
     * @mbg.generated
     */
    private Long targetId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.dev_id
     *
     * @mbg.generated
     */
    private Long devId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.create_time
     *
     * @mbg.generated
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.update_time
     *
     * @mbg.generated
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hot_num_table_handle.attribution
     *
     * @mbg.generated
     */
    private String attribution;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.id
     *
     * @return the value of hot_num_table_handle.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.id
     *
     * @param id the value for hot_num_table_handle.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.imsi
     *
     * @return the value of hot_num_table_handle.imsi
     *
     * @mbg.generated
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.imsi
     *
     * @param imsi the value for hot_num_table_handle.imsi
     *
     * @mbg.generated
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.imei
     *
     * @return the value of hot_num_table_handle.imei
     *
     * @mbg.generated
     */
    public String getImei() {
        return imei;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.imei
     *
     * @param imei the value for hot_num_table_handle.imei
     *
     * @mbg.generated
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.isdn
     *
     * @return the value of hot_num_table_handle.isdn
     *
     * @mbg.generated
     */
    public String getIsdn() {
        return isdn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.isdn
     *
     * @param isdn the value for hot_num_table_handle.isdn
     *
     * @mbg.generated
     */
    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.mode
     *
     * @return the value of hot_num_table_handle.mode
     *
     * @mbg.generated
     */
    public Byte getMode() {
        return mode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.mode
     *
     * @param mode the value for hot_num_table_handle.mode
     *
     * @mbg.generated
     */
    public void setMode(Byte mode) {
        this.mode = mode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.capture_time
     *
     * @return the value of hot_num_table_handle.capture_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCaptureTime() {
        return captureTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.capture_time
     *
     * @param captureTime the value for hot_num_table_handle.capture_time
     *
     * @mbg.generated
     */
    public void setCaptureTime(LocalDateTime captureTime) {
        this.captureTime = captureTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.target_id
     *
     * @return the value of hot_num_table_handle.target_id
     *
     * @mbg.generated
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.target_id
     *
     * @param targetId the value for hot_num_table_handle.target_id
     *
     * @mbg.generated
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.dev_id
     *
     * @return the value of hot_num_table_handle.dev_id
     *
     * @mbg.generated
     */
    public Long getDevId() {
        return devId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.dev_id
     *
     * @param devId the value for hot_num_table_handle.dev_id
     *
     * @mbg.generated
     */
    public void setDevId(Long devId) {
        this.devId = devId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.create_time
     *
     * @return the value of hot_num_table_handle.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.create_time
     *
     * @param createTime the value for hot_num_table_handle.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.update_time
     *
     * @return the value of hot_num_table_handle.update_time
     *
     * @mbg.generated
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.update_time
     *
     * @param updateTime the value for hot_num_table_handle.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hot_num_table_handle.attribution
     *
     * @return the value of hot_num_table_handle.attribution
     *
     * @mbg.generated
     */
    public String getAttribution() {
        return attribution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hot_num_table_handle.attribution
     *
     * @param attribution the value for hot_num_table_handle.attribution
     *
     * @mbg.generated
     */
    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
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
        sb.append(", imsi=").append(imsi);
        sb.append(", imei=").append(imei);
        sb.append(", isdn=").append(isdn);
        sb.append(", mode=").append(mode);
        sb.append(", captureTime=").append(captureTime);
        sb.append(", targetId=").append(targetId);
        sb.append(", devId=").append(devId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", attribution=").append(attribution);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
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
        HotNumTableHandle other = (HotNumTableHandle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getImsi() == null ? other.getImsi() == null : this.getImsi().equals(other.getImsi()))
            && (this.getImei() == null ? other.getImei() == null : this.getImei().equals(other.getImei()))
            && (this.getIsdn() == null ? other.getIsdn() == null : this.getIsdn().equals(other.getIsdn()))
            && (this.getMode() == null ? other.getMode() == null : this.getMode().equals(other.getMode()))
            && (this.getCaptureTime() == null ? other.getCaptureTime() == null : this.getCaptureTime().equals(other.getCaptureTime()))
            && (this.getTargetId() == null ? other.getTargetId() == null : this.getTargetId().equals(other.getTargetId()))
            && (this.getDevId() == null ? other.getDevId() == null : this.getDevId().equals(other.getDevId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getAttribution() == null ? other.getAttribution() == null : this.getAttribution().equals(other.getAttribution()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getImsi() == null) ? 0 : getImsi().hashCode());
        result = prime * result + ((getImei() == null) ? 0 : getImei().hashCode());
        result = prime * result + ((getIsdn() == null) ? 0 : getIsdn().hashCode());
        result = prime * result + ((getMode() == null) ? 0 : getMode().hashCode());
        result = prime * result + ((getCaptureTime() == null) ? 0 : getCaptureTime().hashCode());
        result = prime * result + ((getTargetId() == null) ? 0 : getTargetId().hashCode());
        result = prime * result + ((getDevId() == null) ? 0 : getDevId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAttribution() == null) ? 0 : getAttribution().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "BIGINT", false),
        imsi("imsi", "imsi", "VARCHAR", false),
        imei("imei", "imei", "VARCHAR", false),
        isdn("isdn", "isdn", "VARCHAR", false),
        mode("mode", "mode", "TINYINT", true),
        captureTime("capture_time", "captureTime", "TIMESTAMP", false),
        targetId("target_id", "targetId", "BIGINT", false),
        devId("dev_id", "devId", "BIGINT", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        attribution("attribution", "attribution", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_table_handle
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
         * This method corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_table_handle
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
         * This method corresponds to the database table hot_num_table_handle
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
         * This method corresponds to the database table hot_num_table_handle
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}