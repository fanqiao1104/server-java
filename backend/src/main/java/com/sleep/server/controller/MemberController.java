package com.sleep.server.controller;

import com.sleep.server.dto.PageableRequestDto;
import com.sleep.server.service.MemberService;
import com.sleep.server.vo.PageView;
import com.sleep.server.vo.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService  memberService;

    @PostMapping(path = "/all_member_order_count")
    public ResultView allVisitCount(){

        return ResultView.ok(memberService.getAllMemberOrderCount());
    }
    @PostMapping(path = "/member_order_list")
    public PageView getVisitList(@RequestBody @Validated PageableRequestDto queryParam){
        return memberService.findPage(queryParam);

    }
}
