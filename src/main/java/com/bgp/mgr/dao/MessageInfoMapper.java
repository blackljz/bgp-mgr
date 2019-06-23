package com.bgp.mgr.dao;

import com.bgp.mgr.dao.domain.MessageInfo;
import com.bgp.mgr.dao.domain.MessageInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    int countByExample(MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    int deleteByExample(MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    int insert(MessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    int insertSelective(MessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    List<MessageInfo> selectByExample(MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    MessageInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MessageInfo record, @Param("example") MessageInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MessageInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table messageInfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MessageInfo record);
}