package com.jsc.hotspot.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class CaptureCar {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column capture_car.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column capture_car.capture_car_storage_url
     *
     * @mbg.generated
     */
    private String captureCarStorageUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column capture_car.plate_number
     *
     * @mbg.generated
     */
    private String plateNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column capture_car.is_target
     *
     * @mbg.generated
     */
    private Byte isTarget;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column capture_car.capture_time
     *
     * @mbg.generated
     */
    private LocalDateTime captureTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column capture_car.id
     *
     * @return the value of capture_car.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column capture_car.id
     *
     * @param id the value for capture_car.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column capture_car.capture_car_storage_url
     *
     * @return the value of capture_car.capture_car_storage_url
     *
     * @mbg.generated
     */
    public String getCaptureCarStorageUrl() {
        return captureCarStorageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column capture_car.capture_car_storage_url
     *
     * @param captureCarStorageUrl the value for capture_car.capture_car_storage_url
     *
     * @mbg.generated
     */
    public void setCaptureCarStorageUrl(String captureCarStorageUrl) {
        this.captureCarStorageUrl = captureCarStorageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column capture_car.plate_number
     *
     * @return the value of capture_car.plate_number
     *
     * @mbg.generated
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column capture_car.plate_number
     *
     * @param plateNumber the value for capture_car.plate_number
     *
     * @mbg.generated
     */
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column capture_car.is_target
     *
     * @return the value of capture_car.is_target
     *
     * @mbg.generated
     */
    public Byte getIsTarget() {
        return isTarget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column capture_car.is_target
     *
     * @param isTarget the value for capture_car.is_target
     *
     * @mbg.generated
     */
    public void setIsTarget(Byte isTarget) {
        this.isTarget = isTarget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column capture_car.capture_time
     *
     * @return the value of capture_car.capture_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCaptureTime() {
        return captureTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column capture_car.capture_time
     *
     * @param captureTime the value for capture_car.capture_time
     *
     * @mbg.generated
     */
    public void setCaptureTime(LocalDateTime captureTime) {
        this.captureTime = captureTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
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
        sb.append(", captureCarStorageUrl=").append(captureCarStorageUrl);
        sb.append(", plateNumber=").append(plateNumber);
        sb.append(", isTarget=").append(isTarget);
        sb.append(", captureTime=").append(captureTime);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
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
        CaptureCar other = (CaptureCar) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCaptureCarStorageUrl() == null ? other.getCaptureCarStorageUrl() == null : this.getCaptureCarStorageUrl().equals(other.getCaptureCarStorageUrl()))
            && (this.getPlateNumber() == null ? other.getPlateNumber() == null : this.getPlateNumber().equals(other.getPlateNumber()))
            && (this.getIsTarget() == null ? other.getIsTarget() == null : this.getIsTarget().equals(other.getIsTarget()))
            && (this.getCaptureTime() == null ? other.getCaptureTime() == null : this.getCaptureTime().equals(other.getCaptureTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCaptureCarStorageUrl() == null) ? 0 : getCaptureCarStorageUrl().hashCode());
        result = prime * result + ((getPlateNumber() == null) ? 0 : getPlateNumber().hashCode());
        result = prime * result + ((getIsTarget() == null) ? 0 : getIsTarget().hashCode());
        result = prime * result + ((getCaptureTime() == null) ? 0 : getCaptureTime().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "BIGINT", false),
        captureCarStorageUrl("capture_car_storage_url", "captureCarStorageUrl", "VARCHAR", false),
        plateNumber("plate_number", "plateNumber", "VARCHAR", false),
        isTarget("is_target", "isTarget", "TINYINT", false),
        captureTime("capture_time", "captureTime", "TIMESTAMP", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
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
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
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
         * This method corresponds to the database table capture_car
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
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}