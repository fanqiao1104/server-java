package com.sleep.server.controller;


import com.sleep.server.dao.entity.Audio;
import com.sleep.server.dto.AudioCreateRequestDto;
import com.sleep.server.util.SecureUtil;
import com.sleep.server.vo.ResultView;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping(path = "/login")
    public ResultView login(){
        return ResultView.ok();
    }
    @PostMapping(path = "/info")
    public ResultView info(){
        return ResultView.ok();
    }

}
