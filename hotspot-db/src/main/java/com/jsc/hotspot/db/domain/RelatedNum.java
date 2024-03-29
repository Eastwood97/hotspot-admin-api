package com.jsc.hotspot.db.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class RelatedNum {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column related_num.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column related_num.target_name
     *
     * @mbg.generated
     */
    private String targetName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column related_num.imei
     *
     * @mbg.generated
     */
    private String imei;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column related_num.imsi
     *
     * @mbg.generated
     */
    private String imsi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column related_num.isdn
     *
     * @mbg.generated
     */
    private String isdn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column related_num.number_id
     *
     * @mbg.generated
     */
    private Long numberId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column related_num.id
     *
     * @return the value of related_num.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column related_num.id
     *
     * @param id the value for related_num.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column related_num.target_name
     *
     * @return the value of related_num.target_name
     *
     * @mbg.generated
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column related_num.target_name
     *
     * @param targetName the value for related_num.target_name
     *
     * @mbg.generated
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column related_num.imei
     *
     * @return the value of related_num.imei
     *
     * @mbg.generated
     */
    public String getImei() {
        return imei;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column related_num.imei
     *
     * @param imei the value for related_num.imei
     *
     * @mbg.generated
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column related_num.imsi
     *
     * @return the value of related_num.imsi
     *
     * @mbg.generated
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column related_num.imsi
     *
     * @param imsi the value for related_num.imsi
     *
     * @mbg.generated
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column related_num.isdn
     *
     * @return the value of related_num.isdn
     *
     * @mbg.generated
     */
    public String getIsdn() {
        return isdn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column related_num.isdn
     *
     * @param isdn the value for related_num.isdn
     *
     * @mbg.generated
     */
    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column related_num.number_id
     *
     * @return the value of related_num.number_id
     *
     * @mbg.generated
     */
    public Long getNumberId() {
        return numberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column related_num.number_id
     *
     * @param numberId the value for related_num.number_id
     *
     * @mbg.generated
     */
    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num
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
        sb.append(", targetName=").append(targetName);
        sb.append(", imei=").append(imei);
        sb.append(", imsi=").append(imsi);
        sb.append(", isdn=").append(isdn);
        sb.append(", numberId=").append(numberId);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num
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
        RelatedNum other = (RelatedNum) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTargetName() == null ? other.getTargetName() == null : this.getTargetName().equals(other.getTargetName()))
            && (this.getImei() == null ? other.getImei() == null : this.getImei().equals(other.getImei()))
            && (this.getImsi() == null ? other.getImsi() == null : this.getImsi().equals(other.getImsi()))
            && (this.getIsdn() == null ? other.getIsdn() == null : this.getIsdn().equals(other.getIsdn()))
            && (this.getNumberId() == null ? other.getNumberId() == null : this.getNumberId().equals(other.getNumberId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTargetName() == null) ? 0 : getTargetName().hashCode());
        result = prime * result + ((getImei() == null) ? 0 : getImei().hashCode());
        result = prime * result + ((getImsi() == null) ? 0 : getImsi().hashCode());
        result = prime * result + ((getIsdn() == null) ? 0 : getIsdn().hashCode());
        result = prime * result + ((getNumberId() == null) ? 0 : getNumberId().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table related_num
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "BIGINT", false),
        targetName("target_name", "targetName", "VARCHAR", false),
        imei("imei", "imei", "VARCHAR", false),
        imsi("imsi", "imsi", "VARCHAR", false),
        isdn("isdn", "isdn", "VARCHAR", false),
        numberId("number_id", "numberId", "BIGINT", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table related_num
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table related_num
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table related_num
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table related_num
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table related_num
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table related_num
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table related_num
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table related_num
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table related_num
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table related_num
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table related_num
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
         * This method corresponds to the database table related_num
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table related_num
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table related_num
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
         * This method corresponds to the database table related_num
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
         * This method corresponds to the database table related_num
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}