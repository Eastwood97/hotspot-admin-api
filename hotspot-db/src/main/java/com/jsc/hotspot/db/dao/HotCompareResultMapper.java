package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.HotCompareResult;
import com.jsc.hotspot.db.domain.HotCompareResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HotCompareResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    long countByExample(HotCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int deleteByExample(HotCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int insert(HotCompareResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int insertSelective(HotCompareResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    HotCompareResult selectOneByExample(HotCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    HotCompareResult selectOneByExampleSelective(@Param("example") HotCompareResultExample example, @Param("selective") HotCompareResult.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    List<HotCompareResult> selectByExampleSelective(@Param("example") HotCompareResultExample example, @Param("selective") HotCompareResult.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    List<HotCompareResult> selectByExample(HotCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    HotCompareResult selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") HotCompareResult.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    HotCompareResult selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HotCompareResult record, @Param("example") HotCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HotCompareResult record, @Param("example") HotCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HotCompareResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HotCompareResult record);
}