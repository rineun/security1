package com.cos.securityex01.controller;

import com.cos.securityex01.config.auth.PrincipalDetails;
import com.cos.securityex01.model.User;
import com.cos.securityex01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test/login")
    public @ResponseBody String loginTest(Authentication authentication){ //DI(의존성 주입)
        System.out.println("/test/login ===========>");

        PrincipalDetails principalDetails =  (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication : "+principalDetails.getUser());
        return "세션정보 확인하기";

    }

    @GetMapping({ "", "/" })
    public @ResponseBody
    String index() {
        //뷰리졸버 설정 : template(prefix), .mustache (suffix) 생략가능
        return "index"; //src/main/resources/templates/index.mustache
    }


    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principal) {
        System.out.println("Principal : " + principal);
        System.out.println("OAuth2 : "+principal.getUser().getProvider());
        // iterator 순차 출력 해보기
        Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
        while (iter.hasNext()) {
            GrantedAuthority auth = iter.next();
            System.out.println(auth.getAuthority());
        }

        return "유저 페이지입니다.";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "어드민 페이지입니다.";
    }

    //@PostAuthorize("hasRole('ROLE_MANAGER')")
    //@PreAuthorize("hasRole('ROLE_MANAGER')")
    @Secured("ROLE_MANAGER")
    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "매니저 페이지입니다.";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }


    @PostMapping("/loginProc")
    public String loginProc(User user) {
        System.out.println("로그인 진행 : " + user);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/";
    }

    @PostMapping("/joinProc")
    public String joinProc(User user) {
        System.out.println("회원가입 진행 : " + user);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user); //회원가입 잘됨, 비밀번호:1234 => 시큐리티로 로그인할수 없으, 이유는 패스워드가 암호화 안되어씩 때문
        return "redirect:/login";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }


    //함수가 실행되기 전에 권한체크
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "데이터 정보";
    }

    @GetMapping("/loginFailure") public String loginFailure() { return "loginFailure"; }

}
