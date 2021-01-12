package com.sleep.server.dao.mapper.generator;

import com.sleep.server.dao.entity.PlayRecord;
import com.sleep.server.dao.entity.PlayRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlayRecordMapper extends com.sleep.server.dao.IMapper<PlayRecord> {
    long countByExample(PlayRecordExample example);

    int deleteByExample(PlayRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(PlayRecord record);

    int insertSelective(PlayRecord record);

    List<PlayRecord> selectByExample(PlayRecordExample example);

    PlayRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PlayRecord record, @Param("example") PlayRecordExample example);

    int updateByExample(@Param("record") PlayRecord record, @Param("example") PlayRecordExample example);

    int updateByPrimaryKeySelective(PlayRecord record);

    int updateByPrimaryKey(PlayRecord record);

    int logicDelete(Long id);

    int logicDeleteWithUIndex(@Param("id") Long id, @Param("uIndexes") String ... uIndexes);

    int batchInsert(List<PlayRecord> records);

    int batchInsertSelective(@Param("records") List<PlayRecord> records, @Param("columns") String ... columns);

    PlayRecord selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") String ... selective);

    PlayRecord selectOneByExampleSelective(@Param("example") PlayRecordExample example, @Param("selective") String ... selective);

    List<PlayRecord> selectByExampleSelective(@Param("example") PlayRecordExample example, @Param("selective") String ... selective);
}