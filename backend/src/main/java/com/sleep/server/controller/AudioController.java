package com.sleep.server.controller;

import com.sleep.server.dao.entity.Audio;
import com.sleep.server.dto.AudioCreateRequestDto;
import com.sleep.server.dto.AudioListQueryRequestDto;
import com.sleep.server.dto.mapper.AudioMapperConvert;
import com.sleep.server.task.AudioService;
import com.sleep.server.util.SecureUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sleep.server.vo.PageView;
import com.sleep.server.vo.ResultView;

import com.sleep.server.dto.AudioQueryRequestDto;

import java.util.UUID;

@RestController
@RequestMapping("/audio")
public class AudioController {
    @Autowired
    private AudioService audioService;

    @Autowired
    private AudioMapperConvert audioMapperConvert;
    @PostMapping(path = "/address")
    public  ResultView getAudioAddress(@RequestBody @Validated AudioQueryRequestDto params){
        Audio audio=audioService.selectById(params.audioId);
        return ResultView.ok(audio);
    }
    @PostMapping(path = "/create")
    public ResultView createAudio(@RequestBody @Validated AudioCreateRequestDto params){
        Audio audio =audioMapperConvert.toEntity(params);
        audio.setId(UUID.randomUUID().toString());
        audio.setCosId(SecureUtil.doubleMD5(params.getName()));

        System.out.print(audio);

        audioService.createAudio(audio);
       return ResultView.ok(audio.getId());
    }
    @PostMapping(path = "/list")
    public  PageView getAudioList(@RequestBody @Validated AudioListQueryRequestDto queryParam){
        return audioService.findPage(queryParam);
    }
}
