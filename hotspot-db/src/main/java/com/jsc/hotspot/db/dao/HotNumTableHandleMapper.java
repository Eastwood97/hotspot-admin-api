package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.HotNumTableHandle;
import com.jsc.hotspot.db.domain.HotNumTableHandleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HotNumTableHandleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    long countByExample(HotNumTableHandleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    int deleteByExample(HotNumTableHandleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    int insert(HotNumTableHandle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    int insertSelective(HotNumTableHandle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    HotNumTableHandle selectOneByExample(HotNumTableHandleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    HotNumTableHandle selectOneByExampleSelective(@Param("example") HotNumTableHandleExample example, @Param("selective") HotNumTableHandle.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    List<HotNumTableHandle> selectByExampleSelective(@Param("example") HotNumTableHandleExample example, @Param("selective") HotNumTableHandle.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    List<HotNumTableHandle> selectByExample(HotNumTableHandleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    HotNumTableHandle selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") HotNumTableHandle.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    HotNumTableHandle selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HotNumTableHandle record, @Param("example") HotNumTableHandleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HotNumTableHandle record, @Param("example") HotNumTableHandleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HotNumTableHandle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_num_table_handle
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HotNumTableHandle record);
}