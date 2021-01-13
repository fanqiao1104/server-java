package com.sleep.server.dao.mapper.generator;

import com.sleep.server.dao.entity.User;
import com.sleep.server.dao.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends com.sleep.server.dao.IMapper<User> {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(@Param("userPwd") String userPwd, @Param("userName") String userName, @Param("id") String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int logicDelete(Long id);

    int logicDeleteWithUIndex(@Param("id") Long id, @Param("uIndexes") String ... uIndexes);

    int batchInsert(List<User> records);

    int batchInsertSelective(@Param("records") List<User> records, @Param("columns") String ... columns);

    User selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") String ... selective);

    User selectOneByExampleSelective(@Param("example") UserExample example, @Param("selective") String ... selective);

    List<User> selectByExampleSelective(@Param("example") UserExample example, @Param("selective") String ... selective);
}