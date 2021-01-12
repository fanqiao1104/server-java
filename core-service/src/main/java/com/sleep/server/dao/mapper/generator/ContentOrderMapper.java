package com.sleep.server.dao.mapper.generator;

import com.sleep.server.dao.entity.ContentOrder;
import com.sleep.server.dao.entity.ContentOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ContentOrderMapper extends com.sleep.server.dao.IMapper<ContentOrder> {
    long countByExample(ContentOrderExample example);

    int deleteByExample(ContentOrderExample example);

    int deleteByPrimaryKey(@Param("id") Long id, @Param("orderNo") String orderNo);

    int insert(ContentOrder record);

    int insertSelective(ContentOrder record);

    List<ContentOrder> selectByExample(ContentOrderExample example);

    ContentOrder selectByPrimaryKey(@Param("id") Long id, @Param("orderNo") String orderNo);

    int updateByExampleSelective(@Param("record") ContentOrder record, @Param("example") ContentOrderExample example);

    int updateByExample(@Param("record") ContentOrder record, @Param("example") ContentOrderExample example);

    int updateByPrimaryKeySelective(ContentOrder record);

    int updateByPrimaryKey(ContentOrder record);

    int logicDelete(Long id);

    int logicDeleteWithUIndex(@Param("id") Long id, @Param("uIndexes") String ... uIndexes);

    int batchInsert(List<ContentOrder> records);

    int batchInsertSelective(@Param("records") List<ContentOrder> records, @Param("columns") String ... columns);

    ContentOrder selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") String ... selective);

    ContentOrder selectOneByExampleSelective(@Param("example") ContentOrderExample example, @Param("selective") String ... selective);

    List<ContentOrder> selectByExampleSelective(@Param("example") ContentOrderExample example, @Param("selective") String ... selective);
}