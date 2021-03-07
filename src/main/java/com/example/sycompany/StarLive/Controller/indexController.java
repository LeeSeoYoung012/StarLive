package com.example.sycompany.StarLive.Controller;

import com.example.sycompany.StarLive.Entity.User;
import com.example.sycompany.StarLive.Repository.UserRepository;
import com.example.sycompany.StarLive.config.auth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //View를 리턴하겠다.
public class indexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //구글로그인을 했을 떄 세션정보 확인 Test
    @GetMapping("/test/oauth/login")
   public @ResponseBody String testOAuthLogin(Authentication authentication, @AuthenticationPrincipal OAuth2User oauth){ //DI 의존성 주입
        System.out.println("/test/login===========");
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication:"+oauth2User.getAttributes());
        System.out.println("oauth2User:"+oauth.getAttributes());
        return "OAuth 세션정보 확인하기";
   }

   //그냥 로그인 했을때 세션정보 확인 Test
    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication, @AuthenticationPrincipal UserDetails userDetails){ //DI 의존성 주입
        System.out.println("/test/login===========");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication:"+principalDetails.getUser());//유저의 이름을 받는 방법1
        System.out.println("userDetails:"+userDetails.getUsername());//유저의 이름을 받는 방법2

        return "세션정보 확인하기";
    }


    @GetMapping({"","/"})
    public String index(){
        //머스테치 기본폴더 src/main/resources
        return "index";
    }

    //OAuthDetialsService, DetailsService를 굳이 만든 이유도 principalDetails를 반환하기 위한것
    //OAuth 로그인을 해도 PrincipalDetails 로 받을 수 있고
    // 일반 로그인을 해도 PrincipalDetails로 받을 수 있다.
    @GetMapping("/user")
    public @ResponseBody  String user(@AuthenticationPrincipal PrincipalDetails principalDetails)
    {
        System.out.println("principalDetails:"+principalDetails.getUser());
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody
    String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody  String  manager(){
        return "manager";
    }

    @GetMapping("/joinForm")
    public  String joinForm(){
        return "joinForm";
    }


    //스프링 시큐리티가 해당 주소를 낚아챔 - Security Config 한 후에는 작동을 안함
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @PostMapping("/join")
    public String join(User user){
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword); //인코딩된 password로 설정
        userRepository.save(user);// 비밀번호 1234 일경우 시큐리티로 로그인할 수 없음
        return "redirect:/loginForm"; //리다이렉트는 함수 재사용이 가능하다.
    }

    @Secured("ROLE_ADMIN") //Role_admin만 해당 뷰를 볼수 있음.
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }


    //prePostEnable 을 해놨기 때문에 PostAuthorize 또한 쓰게 된다.
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")  //여러개가 걸릴 수 있다.
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "데이터 정보";
    }




}

