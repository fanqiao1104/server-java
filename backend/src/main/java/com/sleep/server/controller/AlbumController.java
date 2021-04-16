package com.sleep.server.controller;

import com.sleep.server.dao.entity.Album;
import com.sleep.server.dto.PageableRequestDto;
import com.sleep.server.dto.mapper.AlbumCreateRequestDto;
import com.sleep.server.dto.mapper.AlbumMapperConvert;
import com.sleep.server.task.AlbumService;
import com.sleep.server.util.SecureUtil;
import com.sleep.server.vo.PageView;
import com.sleep.server.vo.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumMapperConvert albumMapperConvert;
//    @PostMapping(path = "/address")
//    public ResultView getAudioAddress(@RequestBody @Validated AudioQueryRequestDto params){
//        Audio audio=albumService.selectById(params.audioId);
//        return ResultView.ok(audio);
//    }
    @PostMapping(path = "/create")
    public ResultView createAudio(@RequestBody @Validated AlbumCreateRequestDto params){
        Album audio =albumMapperConvert.toEntity(params);
        audio.setId(UUID.randomUUID().toString());
        audio.setCosId(SecureUtil.doubleMD5(params.getName()));

        System.out.print(audio);

        albumService.createAlbum(audio);
        return ResultView.ok(audio.getId());
    }
    @PostMapping(path = "/list")
    public PageView getAlbumList(@RequestBody @Validated PageableRequestDto queryParam){
        return albumService.findPage(queryParam);
    }
}
