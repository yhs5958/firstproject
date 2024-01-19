package com.example.firstproject.controller;

//컨트롤러 선언과 동시에 자동으로 임포트

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class FirstController {
    @GetMapping("/hi") //url요청접수
    public String niceToMeetYou(Model model){
        return "greetings";//greetings.mustache 파일변환
    }
    @GetMapping("/page2") //url요청접수
    public String SeeYouNext(Model model){
        return "page2";//greetings.mustache 파일변환
    }
}
