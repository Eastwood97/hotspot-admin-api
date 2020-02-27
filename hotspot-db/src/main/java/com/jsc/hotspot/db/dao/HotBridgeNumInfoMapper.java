package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.HotBridgeNumInfo;
import com.jsc.hotspot.db.domain.HotBridgeNumInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HotBridgeNumInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    long countByExample(HotBridgeNumInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    int deleteByExample(HotBridgeNumInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    int insert(HotBridgeNumInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    int insertSelective(HotBridgeNumInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    HotBridgeNumInfo selectOneByExample(HotBridgeNumInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    HotBridgeNumInfo selectOneByExampleSelective(@Param("example") HotBridgeNumInfoExample example, @Param("selective") HotBridgeNumInfo.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    List<HotBridgeNumInfo> selectByExampleSelective(@Param("example") HotBridgeNumInfoExample example, @Param("selective") HotBridgeNumInfo.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    List<HotBridgeNumInfo> selectByExample(HotBridgeNumInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    HotBridgeNumInfo selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") HotBridgeNumInfo.Column... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    HotBridgeNumInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") HotBridgeNumInfo record, @Param("example") HotBridgeNumInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") HotBridgeNumInfo record, @Param("example") HotBridgeNumInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(HotBridgeNumInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hot_bridge_num_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(HotBridgeNumInfo record);
}