package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.HotCompareResultDAO;
import com.jsc.hotspot.db.domain.HotCompareResultDAOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HotCompareResultDAOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    long countByExample(HotCompareResultDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int deleteByExample(HotCompareResultDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int insert(HotCompareResultDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int insertSelective(HotCompareResultDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    HotCompareResultDAO selectOneByExample(HotCompareResultDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    HotCompareResultDAO selectOneByExampleSelective(@Param("example") HotCompareResultDAOExample example, @Param("selective") HotCompareResultDAO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    List<HotCompareResultDAO> selectByExampleSelective(@Param("example") HotCompareResultDAOExample example, @Param("selective") HotCompareResultDAO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    List<HotCompareResultDAO> selectByExample(HotCompareResultDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    HotCompareResultDAO selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") HotCompareResultDAO.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    HotCompareResultDAO selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HotCompareResultDAO record, @Param("example") HotCompareResultDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HotCompareResultDAO record, @Param("example") HotCompareResultDAOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HotCompareResultDAO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_compare_result
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HotCompareResultDAO record);
}