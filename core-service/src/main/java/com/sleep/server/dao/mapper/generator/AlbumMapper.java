package com.sleep.server.dao.mapper.generator;

import com.sleep.server.dao.entity.Album;
import com.sleep.server.dao.entity.AlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AlbumMapper extends com.sleep.server.dao.IMapper<Album> {
    long countByExample(AlbumExample example);

    int deleteByExample(AlbumExample example);

    int deleteByPrimaryKey(@Param("id") String id, @Param("cosId") String cosId);

    int insert(Album record);

    int insertSelective(Album record);

    List<Album> selectByExampleWithBLOBs(AlbumExample example);

    List<Album> selectByExample(AlbumExample example);

    Album selectByPrimaryKey(@Param("id") String id, @Param("cosId") String cosId);

    int updateByExampleSelective(@Param("record") Album record, @Param("example") AlbumExample example);

    int updateByExampleWithBLOBs(@Param("record") Album record, @Param("example") AlbumExample example);

    int updateByExample(@Param("record") Album record, @Param("example") AlbumExample example);

    int updateByPrimaryKeySelective(Album record);

    int updateByPrimaryKeyWithBLOBs(Album record);

    int updateByPrimaryKey(Album record);

    int logicDelete(Long id);

    int logicDeleteWithUIndex(@Param("id") Long id, @Param("uIndexes") String ... uIndexes);

    int batchInsert(List<Album> records);

    int batchInsertSelective(@Param("records") List<Album> records, @Param("columns") String ... columns);

    Album selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") String ... selective);

    Album selectOneByExampleSelective(@Param("example") AlbumExample example, @Param("selective") String ... selective);

    List<Album> selectByExampleSelective(@Param("example") AlbumExample example, @Param("selective") String ... selective);
}