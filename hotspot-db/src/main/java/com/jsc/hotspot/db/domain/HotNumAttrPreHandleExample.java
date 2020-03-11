package com.jsc.hotspot.db.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HotNumAttrPreHandleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public HotNumAttrPreHandleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
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
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public HotNumAttrPreHandleExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public HotNumAttrPreHandleExample orderBy(String ... orderByClauses) {
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
     * This method corresponds to the database table hot_num_attr_pre_handle
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
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
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
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public static Criteria newAndCreateCriteria() {
        HotNumAttrPreHandleExample example = new HotNumAttrPreHandleExample();
        return example.createCriteria();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public HotNumAttrPreHandleExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated
     */
    public HotNumAttrPreHandleExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hot_num_attr_pre_handle
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
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andIdEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andIdNotEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andIdGreaterThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andIdGreaterThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andIdLessThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andIdLessThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
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

        public Criteria andCaptureDateIsNull() {
            addCriterion("capture_date is null");
            return (Criteria) this;
        }

        public Criteria andCaptureDateIsNotNull() {
            addCriterion("capture_date is not null");
            return (Criteria) this;
        }

        public Criteria andCaptureDateEqualTo(LocalDateTime value) {
            addCriterion("capture_date =", value, "captureDate");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andCaptureDateEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("capture_date = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureDateNotEqualTo(LocalDateTime value) {
            addCriterion("capture_date <>", value, "captureDate");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andCaptureDateNotEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("capture_date <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureDateGreaterThan(LocalDateTime value) {
            addCriterion("capture_date >", value, "captureDate");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andCaptureDateGreaterThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("capture_date > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureDateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("capture_date >=", value, "captureDate");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andCaptureDateGreaterThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("capture_date >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureDateLessThan(LocalDateTime value) {
            addCriterion("capture_date <", value, "captureDate");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andCaptureDateLessThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("capture_date < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureDateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("capture_date <=", value, "captureDate");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andCaptureDateLessThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("capture_date <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCaptureDateIn(List<LocalDateTime> values) {
            addCriterion("capture_date in", values, "captureDate");
            return (Criteria) this;
        }

        public Criteria andCaptureDateNotIn(List<LocalDateTime> values) {
            addCriterion("capture_date not in", values, "captureDate");
            return (Criteria) this;
        }

        public Criteria andCaptureDateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("capture_date between", value1, value2, "captureDate");
            return (Criteria) this;
        }

        public Criteria andCaptureDateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("capture_date not between", value1, value2, "captureDate");
            return (Criteria) this;
        }

        public Criteria andAttributionIsNull() {
            addCriterion("attribution is null");
            return (Criteria) this;
        }

        public Criteria andAttributionIsNotNull() {
            addCriterion("attribution is not null");
            return (Criteria) this;
        }

        public Criteria andAttributionEqualTo(String value) {
            addCriterion("attribution =", value, "attribution");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionNotEqualTo(String value) {
            addCriterion("attribution <>", value, "attribution");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionNotEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionGreaterThan(String value) {
            addCriterion("attribution >", value, "attribution");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionGreaterThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionGreaterThanOrEqualTo(String value) {
            addCriterion("attribution >=", value, "attribution");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionGreaterThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionLessThan(String value) {
            addCriterion("attribution <", value, "attribution");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionLessThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionLessThanOrEqualTo(String value) {
            addCriterion("attribution <=", value, "attribution");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionLessThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionLike(String value) {
            addCriterion("attribution like", value, "attribution");
            return (Criteria) this;
        }

        public Criteria andAttributionNotLike(String value) {
            addCriterion("attribution not like", value, "attribution");
            return (Criteria) this;
        }

        public Criteria andAttributionIn(List<String> values) {
            addCriterion("attribution in", values, "attribution");
            return (Criteria) this;
        }

        public Criteria andAttributionNotIn(List<String> values) {
            addCriterion("attribution not in", values, "attribution");
            return (Criteria) this;
        }

        public Criteria andAttributionBetween(String value1, String value2) {
            addCriterion("attribution between", value1, value2, "attribution");
            return (Criteria) this;
        }

        public Criteria andAttributionNotBetween(String value1, String value2) {
            addCriterion("attribution not between", value1, value2, "attribution");
            return (Criteria) this;
        }

        public Criteria andAttributionNumIsNull() {
            addCriterion("attribution_num is null");
            return (Criteria) this;
        }

        public Criteria andAttributionNumIsNotNull() {
            addCriterion("attribution_num is not null");
            return (Criteria) this;
        }

        public Criteria andAttributionNumEqualTo(Long value) {
            addCriterion("attribution_num =", value, "attributionNum");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionNumEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution_num = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionNumNotEqualTo(Long value) {
            addCriterion("attribution_num <>", value, "attributionNum");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionNumNotEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution_num <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionNumGreaterThan(Long value) {
            addCriterion("attribution_num >", value, "attributionNum");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionNumGreaterThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution_num > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionNumGreaterThanOrEqualTo(Long value) {
            addCriterion("attribution_num >=", value, "attributionNum");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionNumGreaterThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution_num >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionNumLessThan(Long value) {
            addCriterion("attribution_num <", value, "attributionNum");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionNumLessThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution_num < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionNumLessThanOrEqualTo(Long value) {
            addCriterion("attribution_num <=", value, "attributionNum");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andAttributionNumLessThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("attribution_num <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andAttributionNumIn(List<Long> values) {
            addCriterion("attribution_num in", values, "attributionNum");
            return (Criteria) this;
        }

        public Criteria andAttributionNumNotIn(List<Long> values) {
            addCriterion("attribution_num not in", values, "attributionNum");
            return (Criteria) this;
        }

        public Criteria andAttributionNumBetween(Long value1, Long value2) {
            addCriterion("attribution_num between", value1, value2, "attributionNum");
            return (Criteria) this;
        }

        public Criteria andAttributionNumNotBetween(Long value1, Long value2) {
            addCriterion("attribution_num not between", value1, value2, "attributionNum");
            return (Criteria) this;
        }

        public Criteria andDevIdIsNull() {
            addCriterion("dev_id is null");
            return (Criteria) this;
        }

        public Criteria andDevIdIsNotNull() {
            addCriterion("dev_id is not null");
            return (Criteria) this;
        }

        public Criteria andDevIdEqualTo(Long value) {
            addCriterion("dev_id =", value, "devId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andDevIdEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("dev_id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDevIdNotEqualTo(Long value) {
            addCriterion("dev_id <>", value, "devId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andDevIdNotEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("dev_id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDevIdGreaterThan(Long value) {
            addCriterion("dev_id >", value, "devId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andDevIdGreaterThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("dev_id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDevIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dev_id >=", value, "devId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andDevIdGreaterThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("dev_id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDevIdLessThan(Long value) {
            addCriterion("dev_id <", value, "devId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andDevIdLessThanColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("dev_id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDevIdLessThanOrEqualTo(Long value) {
            addCriterion("dev_id <=", value, "devId");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public Criteria andDevIdLessThanOrEqualToColumn(HotNumAttrPreHandle.Column column) {
            addCriterion(new StringBuilder("dev_id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDevIdIn(List<Long> values) {
            addCriterion("dev_id in", values, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdNotIn(List<Long> values) {
            addCriterion("dev_id not in", values, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdBetween(Long value1, Long value2) {
            addCriterion("dev_id between", value1, value2, "devId");
            return (Criteria) this;
        }

        public Criteria andDevIdNotBetween(Long value1, Long value2) {
            addCriterion("dev_id not between", value1, value2, "devId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hot_num_attr_pre_handle
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        private HotNumAttrPreHandleExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        protected Criteria(HotNumAttrPreHandleExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        public HotNumAttrPreHandleExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
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
         * This method corresponds to the database table hot_num_attr_pre_handle
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
         * This method corresponds to the database table hot_num_attr_pre_handle
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
             * This method corresponds to the database table hot_num_attr_pre_handle
             *
             * @mbg.generated
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table hot_num_attr_pre_handle
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
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table hot_num_attr_pre_handle
         *
         * @mbg.generated
         */
        void example(com.jsc.hotspot.db.domain.HotNumAttrPreHandleExample example);
    }
}