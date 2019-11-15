package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.CameraHotRelation;
import com.jsc.hotspot.db.domain.CameraHotRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CameraHotRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    long countByExample(CameraHotRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    int deleteByExample(CameraHotRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long targetId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    int insert(CameraHotRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    int insertSelective(CameraHotRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    CameraHotRelation selectOneByExample(CameraHotRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    CameraHotRelation selectOneByExampleSelective(@Param("example") CameraHotRelationExample example, @Param("selective") CameraHotRelation.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    List<CameraHotRelation> selectByExampleSelective(@Param("example") CameraHotRelationExample example, @Param("selective") CameraHotRelation.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    List<CameraHotRelation> selectByExample(CameraHotRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    CameraHotRelation selectByPrimaryKeySelective(@Param("targetId") Long targetId, @Param("selective") CameraHotRelation.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    CameraHotRelation selectByPrimaryKey(Long targetId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CameraHotRelation record, @Param("example") CameraHotRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CameraHotRelation record, @Param("example") CameraHotRelationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CameraHotRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table camera_hot_relation
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CameraHotRelation record);
}