package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody       // HTTP 통신 프로토콜의 바디 영역에 데이터를 직접 넣겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;     // 페이지 소스보기를 확인해보면 HTML 코드가 아닌 문자 데이터 그대로 박혀있음
    }

    @GetMapping("hello-api")
    @ResponseBody                   // 객체를 반환하며 RESPONSEBODY 사용 시 JSON으로 데이터 전달
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;               // JSON 방식으로 데이터 전달 (JSON : KEY-VALUE 로 이루어진 방식)
    }                               // html 방식은 무거워서 대체 JSON 방식으로 사용

    static class Hello {
        private String name;

        public String getName() {       // getter setter : java bin 표준 방식(규약)
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
