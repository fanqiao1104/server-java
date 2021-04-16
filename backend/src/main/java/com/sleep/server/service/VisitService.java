package com.sleep.server.service;


import com.sleep.server.constant.ErrorCode;
import com.sleep.server.dao.entity.Audio;
import com.sleep.server.dao.entity.AudioExample;
import com.sleep.server.dao.entity.PlayRecord;
import com.sleep.server.dao.entity.PlayRecordExample;
import com.sleep.server.dao.mapper.generator.AudioMapper;
import com.sleep.server.dao.mapper.generator.PlayRecordMapper;
import com.sleep.server.dto.AudioListQueryRequestDto;
import com.sleep.server.dto.PageableRequestDto;
import com.sleep.server.exception.BusinessException;
import com.sleep.server.task.base.IGlobalIdService;
import com.sleep.server.vo.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {
    @Autowired
    private PlayRecordMapper playRecordMapper;

    @Autowired
    private IGlobalIdService globalIdService;

    public PlayRecord selectByName(String name) {
        AudioExample example = new AudioExample();
        example.createCriteria().andNameEqualTo(name);
        List<PlayRecord> audios = playRecordMapper.selectByExample(example);
        return !audios.isEmpty() ? audios.get(0) : null;
    }

    public Long getAllPlayRecordCount(){
        PlayRecordExample example = new PlayRecordExample();
      Long count=  playRecordMapper.countByExample(example);
      return count;
    }

    public PageView<PlayRecord> findPage(PageableRequestDto queryParams ){
        PlayRecordExample example = new PlayRecordExample();
        long count = playRecordMapper.countByExample(example);
        if (count == 0L) {
            return PageView.empty();
        }
        example.setOffset((queryParams.getPage().getIndex() - 1) * queryParams.getPage().getLimit());
        example.setLimit(queryParams.getPage().getLimit());
        List<PlayRecord> audios = playRecordMapper.selectByExample(example);

        return PageView.of(count, audios);
    }
}
