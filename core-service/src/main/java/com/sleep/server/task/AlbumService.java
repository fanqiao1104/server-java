package com.sleep.server.task;

import com.sleep.server.constant.ErrorCode;
import com.sleep.server.dao.entity.Album;
import com.sleep.server.dao.entity.AlbumExample;
import com.sleep.server.dao.entity.Audio;
import com.sleep.server.dao.entity.AudioExample;
import com.sleep.server.dao.mapper.generator.AlbumMapper;
import com.sleep.server.dao.mapper.generator.AudioMapper;
import com.sleep.server.dto.AudioListQueryRequestDto;
import com.sleep.server.dto.PageableRequestDto;
import com.sleep.server.exception.BusinessException;
import com.sleep.server.task.base.IGlobalIdService;
import com.sleep.server.vo.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private IGlobalIdService globalIdService;

    public Album selectByName(String name) {
        AlbumExample example = new AlbumExample();
        example.createCriteria().andNameEqualTo(name);
        List<Album> albums = albumMapper.selectByExample(example);
        return !albums.isEmpty() ? albums.get(0) : null;
    }

    public int createAlbum(Album audio){
        Album existAudio =  selectByName(audio.getName());
        if(existAudio!=null){
            throw BusinessException.builder().message("audio has existed").errorCode(ErrorCode.AUDIO_EXIST).build();
        }
        return albumMapper.insert(audio);
    }

    public Album selectById(String id){
        AudioExample example = new AudioExample();
        example.createCriteria().andIdEqualTo(id);

        List<Album> audios =albumMapper.selectByExample(example);
        return !audios.isEmpty() ? audios.get(0) : null;
    }

    public PageView<Album> findPage(PageableRequestDto queryParams ){
        AlbumExample example = new AlbumExample();
        long count = albumMapper.countByExample(example);
        if (count == 0L) {
            return PageView.empty();
        }
        example.setOffset((queryParams.getPage().getIndex() - 1) * queryParams.getPage().getLimit());
        example.setLimit(queryParams.getPage().getLimit());
        List<Album> audios = albumMapper.selectByExample(example);

        return PageView.of(count, audios);
    }
}
