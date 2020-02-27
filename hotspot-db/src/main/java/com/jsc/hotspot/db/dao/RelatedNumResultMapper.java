package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.RelatedNumResult;
import com.jsc.hotspot.db.domain.RelatedNumResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelatedNumResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    long countByExample(RelatedNumResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    int deleteByExample(RelatedNumResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    int insert(RelatedNumResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    int insertSelective(RelatedNumResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    RelatedNumResult selectOneByExample(RelatedNumResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    RelatedNumResult selectOneByExampleSelective(@Param("example") RelatedNumResultExample example, @Param("selective") RelatedNumResult.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    List<RelatedNumResult> selectByExampleSelective(@Param("example") RelatedNumResultExample example, @Param("selective") RelatedNumResult.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    List<RelatedNumResult> selectByExample(RelatedNumResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    RelatedNumResult selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") RelatedNumResult.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    RelatedNumResult selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") RelatedNumResult record, @Param("example") RelatedNumResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") RelatedNumResult record, @Param("example") RelatedNumResultExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RelatedNumResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table related_num_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RelatedNumResult record);
}