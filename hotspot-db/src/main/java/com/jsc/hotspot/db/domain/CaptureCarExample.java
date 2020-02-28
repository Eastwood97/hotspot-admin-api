package com.jsc.hotspot.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CaptureCarExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public CaptureCarExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public CaptureCarExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public CaptureCarExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public static Criteria newAndCreateCriteria() {
        CaptureCarExample example = new CaptureCarExample();
        return example.createCriteria();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public CaptureCarExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public CaptureCarExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIdEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIdNotEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIdGreaterThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIdGreaterThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIdLessThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIdLessThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlIsNull() {
            addCriterion("capture_car_storage_url is null");
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlIsNotNull() {
            addCriterion("capture_car_storage_url is not null");
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlEqualTo(String value) {
            addCriterion("capture_car_storage_url =", value, "captureCarStorageUrl");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureCarStorageUrlEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_car_storage_url = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlNotEqualTo(String value) {
            addCriterion("capture_car_storage_url <>", value, "captureCarStorageUrl");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureCarStorageUrlNotEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_car_storage_url <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlGreaterThan(String value) {
            addCriterion("capture_car_storage_url >", value, "captureCarStorageUrl");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureCarStorageUrlGreaterThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_car_storage_url > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("capture_car_storage_url >=", value, "captureCarStorageUrl");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureCarStorageUrlGreaterThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_car_storage_url >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlLessThan(String value) {
            addCriterion("capture_car_storage_url <", value, "captureCarStorageUrl");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureCarStorageUrlLessThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_car_storage_url < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlLessThanOrEqualTo(String value) {
            addCriterion("capture_car_storage_url <=", value, "captureCarStorageUrl");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureCarStorageUrlLessThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_car_storage_url <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlLike(String value) {
            addCriterion("capture_car_storage_url like", value, "captureCarStorageUrl");
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlNotLike(String value) {
            addCriterion("capture_car_storage_url not like", value, "captureCarStorageUrl");
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlIn(List<String> values) {
            addCriterion("capture_car_storage_url in", values, "captureCarStorageUrl");
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlNotIn(List<String> values) {
            addCriterion("capture_car_storage_url not in", values, "captureCarStorageUrl");
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlBetween(String value1, String value2) {
            addCriterion("capture_car_storage_url between", value1, value2, "captureCarStorageUrl");
            return (Criteria) this;
        }

        public Criteria andCaptureCarStorageUrlNotBetween(String value1, String value2) {
            addCriterion("capture_car_storage_url not between", value1, value2, "captureCarStorageUrl");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIsNull() {
            addCriterion("plate_number is null");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIsNotNull() {
            addCriterion("plate_number is not null");
            return (Criteria) this;
        }

        public Criteria andPlateNumberEqualTo(String value) {
            addCriterion("plate_number =", value, "plateNumber");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andPlateNumberEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("plate_number = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotEqualTo(String value) {
            addCriterion("plate_number <>", value, "plateNumber");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andPlateNumberNotEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("plate_number <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlateNumberGreaterThan(String value) {
            addCriterion("plate_number >", value, "plateNumber");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andPlateNumberGreaterThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("plate_number > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("plate_number >=", value, "plateNumber");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andPlateNumberGreaterThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("plate_number >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlateNumberLessThan(String value) {
            addCriterion("plate_number <", value, "plateNumber");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andPlateNumberLessThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("plate_number < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlateNumberLessThanOrEqualTo(String value) {
            addCriterion("plate_number <=", value, "plateNumber");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andPlateNumberLessThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("plate_number <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andPlateNumberLike(String value) {
            addCriterion("plate_number like", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotLike(String value) {
            addCriterion("plate_number not like", value, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberIn(List<String> values) {
            addCriterion("plate_number in", values, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotIn(List<String> values) {
            addCriterion("plate_number not in", values, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberBetween(String value1, String value2) {
            addCriterion("plate_number between", value1, value2, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andPlateNumberNotBetween(String value1, String value2) {
            addCriterion("plate_number not between", value1, value2, "plateNumber");
            return (Criteria) this;
        }

        public Criteria andIsTargetIsNull() {
            addCriterion("is_target is null");
            return (Criteria) this;
        }

        public Criteria andIsTargetIsNotNull() {
            addCriterion("is_target is not null");
            return (Criteria) this;
        }

        public Criteria andIsTargetEqualTo(Byte value) {
            addCriterion("is_target =", value, "isTarget");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIsTargetEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("is_target = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsTargetNotEqualTo(Byte value) {
            addCriterion("is_target <>", value, "isTarget");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIsTargetNotEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("is_target <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsTargetGreaterThan(Byte value) {
            addCriterion("is_target >", value, "isTarget");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIsTargetGreaterThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("is_target > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsTargetGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_target >=", value, "isTarget");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIsTargetGreaterThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("is_target >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsTargetLessThan(Byte value) {
            addCriterion("is_target <", value, "isTarget");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIsTargetLessThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("is_target < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsTargetLessThanOrEqualTo(Byte value) {
            addCriterion("is_target <=", value, "isTarget");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andIsTargetLessThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("is_target <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIsTargetIn(List<Byte> values) {
            addCriterion("is_target in", values, "isTarget");
            return (Criteria) this;
        }

        public Criteria andIsTargetNotIn(List<Byte> values) {
            addCriterion("is_target not in", values, "isTarget");
            return (Criteria) this;
        }

        public Criteria andIsTargetBetween(Byte value1, Byte value2) {
            addCriterion("is_target between", value1, value2, "isTarget");
            return (Criteria) this;
        }

        public Criteria andIsTargetNotBetween(Byte value1, Byte value2) {
            addCriterion("is_target not between", value1, value2, "isTarget");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeIsNull() {
            addCriterion("capture_time is null");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeIsNotNull() {
            addCriterion("capture_time is not null");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeEqualTo(LocalDateTime value) {
            addCriterion("capture_time =", value, "captureTime");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureTimeEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_time = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureTimeNotEqualTo(LocalDateTime value) {
            addCriterion("capture_time <>", value, "captureTime");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureTimeNotEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_time <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureTimeGreaterThan(LocalDateTime value) {
            addCriterion("capture_time >", value, "captureTime");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureTimeGreaterThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_time > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("capture_time >=", value, "captureTime");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureTimeGreaterThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_time >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureTimeLessThan(LocalDateTime value) {
            addCriterion("capture_time <", value, "captureTime");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureTimeLessThanColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_time < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("capture_time <=", value, "captureTime");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria andCaptureTimeLessThanOrEqualToColumn(CaptureCar.Column column) {
            addCriterion(new StringBuilder("capture_time <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureTimeIn(List<LocalDateTime> values) {
            addCriterion("capture_time in", values, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeNotIn(List<LocalDateTime> values) {
            addCriterion("capture_time not in", values, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("capture_time between", value1, value2, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("capture_time not between", value1, value2, "captureTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table capture_car
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        private CaptureCarExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        protected Criteria(CaptureCarExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public CaptureCarExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        @Deprecated
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            } else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {
            /**
             * This method was generated by MyBatis Generator.
             * This method corresponds to the database table capture_car
             *
             * @mbg.generated
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table capture_car
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public interface ICriteriaWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table capture_car
         *
         * @mbg.generated
         */
        void example(com.jsc.hotspot.db.domain.CaptureCarExample example);
    }
}