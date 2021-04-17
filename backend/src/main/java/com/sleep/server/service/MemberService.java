package com.sleep.server.service;


import com.sleep.server.dao.entity.AudioExample;
import com.sleep.server.dao.entity.MemberOrder;
import com.sleep.server.dao.entity.PlayRecord;
import com.sleep.server.dao.entity.PlayRecordExample;
import com.sleep.server.dao.mapper.generator.MemberOrderMapper;
import com.sleep.server.dao.mapper.generator.PlayRecordMapper;
import com.sleep.server.dto.PageableRequestDto;
import com.sleep.server.task.base.IGlobalIdService;
import com.sleep.server.vo.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberOrderMapper memberOrderMapper;

    public MemberOrder selectByName(String name) {
        AudioExample example = new AudioExample();
        example.createCriteria().andNameEqualTo(name);
        List<MemberOrder> audios = memberOrderMapper.selectByExample(example);
        return !audios.isEmpty() ? audios.get(0) : null;
    }

    public Long getAllMemberOrderCount(){
        PlayRecordExample example = new PlayRecordExample();
      Long count=  memberOrderMapper.countByExample(example);
      return count;
    }

    public PageView<MemberOrder> findPage(PageableRequestDto queryParams ){
        PlayRecordExample example = new PlayRecordExample();
        long count = memberOrderMapper.countByExample(example);
        if (count == 0L) {
            return PageView.empty();
        }
        example.setOffset((queryParams.getPage().getIndex() - 1) * queryParams.getPage().getLimit());
        example.setLimit(queryParams.getPage().getLimit());
        List<MemberOrder> audios = memberOrderMapper.selectByExample(example);

        return PageView.of(count, audios);
    }
}
