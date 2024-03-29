package com.jsc.hotspot.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class HotspotLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotspot_log.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotspot_log.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotspot_log.operation
     *
     * @mbg.generated
     */
    private String operation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotspot_log.method
     *
     * @mbg.generated
     */
    private String method;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotspot_log.params
     *
     * @mbg.generated
     */
    private String params;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotspot_log.ip
     *
     * @mbg.generated
     */
    private String ip;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotspot_log.createDate
     *
     * @mbg.generated
     */
    private LocalDateTime createdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotspot_log.id
     *
     * @return the value of hotspot_log.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotspot_log.id
     *
     * @param id the value for hotspot_log.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotspot_log.username
     *
     * @return the value of hotspot_log.username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotspot_log.username
     *
     * @param username the value for hotspot_log.username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotspot_log.operation
     *
     * @return the value of hotspot_log.operation
     *
     * @mbg.generated
     */
    public String getOperation() {
        return operation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotspot_log.operation
     *
     * @param operation the value for hotspot_log.operation
     *
     * @mbg.generated
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotspot_log.method
     *
     * @return the value of hotspot_log.method
     *
     * @mbg.generated
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotspot_log.method
     *
     * @param method the value for hotspot_log.method
     *
     * @mbg.generated
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotspot_log.params
     *
     * @return the value of hotspot_log.params
     *
     * @mbg.generated
     */
    public String getParams() {
        return params;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotspot_log.params
     *
     * @param params the value for hotspot_log.params
     *
     * @mbg.generated
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotspot_log.ip
     *
     * @return the value of hotspot_log.ip
     *
     * @mbg.generated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotspot_log.ip
     *
     * @param ip the value for hotspot_log.ip
     *
     * @mbg.generated
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotspot_log.createDate
     *
     * @return the value of hotspot_log.createDate
     *
     * @mbg.generated
     */
    public LocalDateTime getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotspot_log.createDate
     *
     * @param createdate the value for hotspot_log.createDate
     *
     * @mbg.generated
     */
    public void setCreatedate(LocalDateTime createdate) {
        this.createdate = createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hotspot_log
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
        sb.append(", username=").append(username);
        sb.append(", operation=").append(operation);
        sb.append(", method=").append(method);
        sb.append(", params=").append(params);
        sb.append(", ip=").append(ip);
        sb.append(", createdate=").append(createdate);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hotspot_log
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
        HotspotLog other = (HotspotLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getOperation() == null ? other.getOperation() == null : this.getOperation().equals(other.getOperation()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getCreatedate() == null ? other.getCreatedate() == null : this.getCreatedate().equals(other.getCreatedate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hotspot_log
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getOperation() == null) ? 0 : getOperation().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getCreatedate() == null) ? 0 : getCreatedate().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table hotspot_log
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "BIGINT", false),
        username("username", "username", "VARCHAR", false),
        operation("operation", "operation", "VARCHAR", true),
        method("method", "method", "VARCHAR", true),
        params("params", "params", "VARCHAR", false),
        ip("ip", "ip", "VARCHAR", false),
        createdate("createDate", "createdate", "TIMESTAMP", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hotspot_log
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
         * This method corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hotspot_log
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
         * This method corresponds to the database table hotspot_log
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
         * This method corresponds to the database table hotspot_log
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}