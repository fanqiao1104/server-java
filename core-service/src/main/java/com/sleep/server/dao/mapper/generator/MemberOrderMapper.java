package com.sleep.server.dao.mapper.generator;

import com.sleep.server.dao.entity.MemberOrder;
import com.sleep.server.dao.entity.MemberOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberOrderMapper extends com.sleep.server.dao.IMapper<MemberOrder> {
    long countByExample(MemberOrderExample example);

    int deleteByExample(MemberOrderExample example);

    int deleteByPrimaryKey(String orderNo);

    int insert(MemberOrder record);

    int insertSelective(MemberOrder record);

    List<MemberOrder> selectByExample(MemberOrderExample example);

    MemberOrder selectByPrimaryKey(String orderNo);

    int updateByExampleSelective(@Param("record") MemberOrder record, @Param("example") MemberOrderExample example);

    int updateByExample(@Param("record") MemberOrder record, @Param("example") MemberOrderExample example);

    int updateByPrimaryKeySelective(MemberOrder record);

    int updateByPrimaryKey(MemberOrder record);

    int logicDelete(Long id);

    int logicDeleteWithUIndex(@Param("id") Long id, @Param("uIndexes") String ... uIndexes);

    int batchInsert(List<MemberOrder> records);

    int batchInsertSelective(@Param("records") List<MemberOrder> records, @Param("columns") String ... columns);

    MemberOrder selectByPrimaryKeySelective(@Param("id") String id, @Param("selective") String ... selective);

    MemberOrder selectOneByExampleSelective(@Param("example") MemberOrderExample example, @Param("selective") String ... selective);

    List<MemberOrder> selectByExampleSelective(@Param("example") MemberOrderExample example, @Param("selective") String ... selective);
}