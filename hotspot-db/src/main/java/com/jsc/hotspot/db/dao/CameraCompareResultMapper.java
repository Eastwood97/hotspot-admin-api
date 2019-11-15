package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.CameraCompareResult;
import com.jsc.hotspot.db.domain.CameraCompareResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CameraCompareResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    long countByExample(CameraCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    int deleteByExample(CameraCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    int insert(CameraCompareResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    int insertSelective(CameraCompareResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    CameraCompareResult selectOneByExample(CameraCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    CameraCompareResult selectOneByExampleSelective(@Param("example") CameraCompareResultExample example, @Param("selective") CameraCompareResult.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    List<CameraCompareResult> selectByExampleSelective(@Param("example") CameraCompareResultExample example, @Param("selective") CameraCompareResult.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    List<CameraCompareResult> selectByExample(CameraCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    CameraCompareResult selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") CameraCompareResult.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    CameraCompareResult selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CameraCompareResult record, @Param("example") CameraCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CameraCompareResult record, @Param("example") CameraCompareResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CameraCompareResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_compare_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CameraCompareResult record);
}