package com.example.sycompany.StarLive.config.auth;


import com.example.sycompany.StarLive.Entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/*Lesson4*/
//시큐리티가 login 주소요청이 오면 낚아채서 로그인을 진행시킨다
//로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다.
//시큐리티가 자신만의 session이 생기는데 Security ContextHolder에 session 저장
// session에 들어가야한 오브젝트가 따로 정해져 있다 => Authentication 타입 객체
//Authentication 객체안에 User정보가 있어야하는데 이 때 유저 Object의 타입=> UserDetails타입 객체
// Security Session => Authentication => UserDetails(PrincipalDetails)
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {  //두개를 다 상속 받으면 PrincipalDetails 하나만으로
                                                                    // UserDetails 타입과 OAuth2User 타입의 객체를 모두 받을 수 있다.

    private User user; //콤포지션
    private Map<String,Object> attributes;

    //일반 로그인할 때 객체생성
    public PrincipalDetails(User user){
       // super();
        this.user = user;
    }

    //Oauth 로그인
    public PrincipalDetails(User user, Map<String,Object>attributes){
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    //해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority>getAuthorities(){
        Collection<GrantedAuthority>collect = new ArrayList<GrantedAuthority>();
        // arraylist는 collection의 자식
        collect.add(()->{return user.getRole();});

        return collect;
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() { //계정 만료됏니?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //계정 잠겼니?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { //기간이 지났니?
        return true;
    }

    @Override
    public boolean isEnabled() { //계정 활성화 됐니?
        //사이트에서 1년동안 회원이 로그인을 안하면 휴먼계정으로 하기로 할때 쓰인다.
        // 현재시간-login시간 >1년 휴면계정으로
        return true;
    }


    @Override
    public String getName() {
        return null;
    }
}
