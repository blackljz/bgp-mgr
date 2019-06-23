package com.bgp.mgr.dao;

import com.bgp.mgr.dao.domain.Friend;
import com.bgp.mgr.dao.domain.FriendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    int countByExample(FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    int deleteByExample(FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    int insert(Friend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    int insertSelective(Friend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    List<Friend> selectByExample(FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    Friend selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Friend record, @Param("example") FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Friend record, @Param("example") FriendExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Friend record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table friend
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Friend record);
}