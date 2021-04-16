package com.sleep.server.controller;

import com.sleep.server.dto.*;
import com.sleep.server.service.VisitService;
import com.sleep.server.vo.PageView;
import com.sleep.server.vo.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private VisitService visitService;

    @PostMapping(path = "/all_visit_count")
    public ResultView allVisitCount(){

        return ResultView.ok(visitService.getAllPlayRecordCount());
    }
    @PostMapping(path = "/visit_list")
    public PageView getVisitList(@RequestBody @Validated PageableRequestDto queryParam){
        return visitService.findPage(queryParam);

    }
}
