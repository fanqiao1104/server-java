package com.sleep.server.task;

import com.sleep.server.dao.entity.AlbumExample;
import com.sleep.server.dao.entity.AudioExample;
import com.sleep.server.dao.mapper.generator.AudioMapper;
import com.sleep.server.dto.AudioListQueryRequestDto;
import com.sleep.server.vo.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sleep.server.task.base.IGlobalIdService;
import com.sleep.server.dao.entity.Audio;

import com.sleep.server.constant.ErrorCode;
import com.sleep.server.exception.BusinessException;

import java.util.List;

@Service
public class AudioService {
    @Autowired
    private AudioMapper audioMapper;

    public Audio selectByName(String name) {
        AudioExample example = new AudioExample();
        example.createCriteria().andNameEqualTo(name);
        List<Audio> audios = audioMapper.selectByExample(example);
        return !audios.isEmpty() ? audios.get(0) : null;
    }

    public int createAudio(Audio audio){
        Audio existAudio =  selectByName(audio.getName());
        if(existAudio!=null){
            throw BusinessException.builder().message("audio has existed").errorCode(ErrorCode.AUDIO_EXIST).build();
        }
        return audioMapper.insert(audio);
    }

    public Audio selectById(String id){
        AudioExample example = new AudioExample();
        example.createCriteria().andIdEqualTo(id);

        List<Audio> audios = audioMapper.selectByExample(example);
        return !audios.isEmpty() ? audios.get(0) : null;
    }

    public PageView<Audio> findPage(AudioListQueryRequestDto queryParams ){
        AudioExample example = new AudioExample();
        long count = audioMapper.countByExample(example);
        if (count == 0L) {
            return PageView.empty();
        }
        example.setOffset((queryParams.getPage().getIndex() - 1) * queryParams.getPage().getLimit());
        example.setLimit(queryParams.getPage().getLimit());
        List<Audio> audios = audioMapper.selectByExampleWithBLOBs(example);

        return PageView.of(count, audios);
    }

    public Long getAllAudioCount(){

        AudioExample example = new AudioExample();
        Long count = audioMapper.countByExample(example);
        return count;
    }
}
