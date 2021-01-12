package com.sleep.server.dao.mapper.generator;

import com.sleep.server.dao.entity.Channel;
import com.sleep.server.dao.entity.ChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChannelMapper extends com.sleep.server.dao.IMapper<Channel> {
    long countByExample(ChannelExample example);

    int deleteByExample(ChannelExample example);

    int deleteByPrimaryKey(@Param("id") String id, @Param("appSecret") String appSecret, @Param("appKey") String appKey, @Param("name") String name, @Param("userId") String userId);

    int insert(Channel record);

    int insertSelective(Channel record);

    List<Channel> selectByExampleWithBLOBs(ChannelExample example);

    List<Channel> selectByExample(ChannelExample example);

    Channel selectByPrimaryKey(@Param("id") String id, @Param("appSecret") String appSecret, @Param("appKey") String appKey, @Param("name") String name, @Param("userId") String userId);

    int updateByExampleSelective(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByExampleWithBLOBs(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByExample(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKeyWithBLOBs(Channel record);

    int updateByPrimaryKey(Channel record);

    int logicDelete(Long id);

    int logicDeleteWithUIndex(@Param("id") Long id, @Param("uIndexes") String ... uIndexes);

    int batchInsert(List<Channel> records);

    int batchInsertSelective(@Param("records") List<Channel> records, @Param("columns") String ... columns);

    Channel selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") String ... selective);

    Channel selectOneByExampleSelective(@Param("example") ChannelExample example, @Param("selective") String ... selective);

    List<Channel> selectByExampleSelective(@Param("example") ChannelExample example, @Param("selective") String ... selective);
}