package com.sleep.server.dao.mapper.generator;

import com.sleep.server.dao.entity.Audio;
import com.sleep.server.dao.entity.AudioExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
public interface AudioMapper extends com.sleep.server.dao.IMapper<Audio> {
    long countByExample(AudioExample example);

    int deleteByExample(AudioExample example);

    int deleteByPrimaryKey(@Param("id") String id, @Param("cosId") String cosId);

    int insert(Audio record);

    int insertSelective(Audio record);

    List<Audio> selectByExampleWithBLOBs(AudioExample example);

    List<Audio> selectByExample(AudioExample example);

    Audio selectByPrimaryKey(@Param("id") String id, @Param("cosId") String cosId);

    int updateByExampleSelective(@Param("record") Audio record, @Param("example") AudioExample example);

    int updateByExampleWithBLOBs(@Param("record") Audio record, @Param("example") AudioExample example);

    int updateByExample(@Param("record") Audio record, @Param("example") AudioExample example);

    int updateByPrimaryKeySelective(Audio record);

    int updateByPrimaryKeyWithBLOBs(Audio record);

    int updateByPrimaryKey(Audio record);

    int logicDelete(Long id);

    int logicDeleteWithUIndex(@Param("id") Long id, @Param("uIndexes") String ... uIndexes);

    int batchInsert(List<Audio> records);

    int batchInsertSelective(@Param("records") List<Audio> records, @Param("columns") String ... columns);

    Audio selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") String ... selective);

    Audio selectOneByExampleSelective(@Param("example") AudioExample example, @Param("selective") String ... selective);

    List<Audio> selectByExampleSelective(@Param("example") AudioExample example, @Param("selective") String ... selective);
}