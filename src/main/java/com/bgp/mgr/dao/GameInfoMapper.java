package com.bgp.mgr.dao;

import com.bgp.mgr.dao.domain.GameInfo;
import com.bgp.mgr.dao.domain.GameInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gameInfo
     *
     * @mbggenerated
     */
    int countByExample(GameInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gameInfo
     *
     * @mbggenerated
     */
    int deleteByExample(GameInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gameInfo
     *
     * @mbggenerated
     */
    int insert(GameInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gameInfo
     *
     * @mbggenerated
     */
    int insertSelective(GameInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gameInfo
     *
     * @mbggenerated
     */
    List<GameInfo> selectByExample(GameInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gameInfo
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") GameInfo record, @Param("example") GameInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gameInfo
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") GameInfo record, @Param("example") GameInfoExample example);
}