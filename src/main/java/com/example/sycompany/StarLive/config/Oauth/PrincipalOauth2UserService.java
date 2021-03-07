package com.example.sycompany.StarLive.config.Oauth;

import com.example.sycompany.StarLive.Entity.User;
import com.example.sycompany.StarLive.Repository.UserRepository;
import com.example.sycompany.StarLive.config.auth.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    //Lesson7: 후처리가 되는 함수


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;


  @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
      System.out.println("getClientRegistration:"+userRequest.getClientRegistration());
      System.out.println("getAccessToken:"+userRequest.getAccessToken());//accessToken은 이미 알아서 처리를 해줌



     OAuth2User oauth2User = super.loadUser(userRequest);

      //구글로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> 코드를 리턴(Oauth-Client 라이브러리)
      // ->AccessToken 요청 , userRequest정보 -> loadUser함수 호출 -> 구글로부터 회원프로필을 받아준다.
      System.out.println("getAttributes"+oauth2User.getAttributes());//여기에 있는 회원정보가 우리가 원하는 것

      /*강제로 회원가입 시키기*/
      String provider = userRequest.getClientRegistration().getClientId();
      String providerId = oauth2User.getAttribute("sub");
      String username = provider + "_" + providerId; //google_sub아이디가 된다.
      String password = bCryptPasswordEncoder.encode("겟인데어");
      String email = oauth2User.getAttribute("email");
      String role = "ROLE_USER";

      User userEntity = userRepository.findByUserName(username);

      if(userEntity == null){
          System.out.println("구글 로그인이 최초입니다.");
        userEntity = User.builder()
                .userName(username)
                .password(password)
                .email(email)
                .role(role)
                .provider(provider)
                .providerId(providerId)
                .build();
        userRepository.save(userEntity);
      }
      else{
          System.out.println("구글 로그인을 이미 한적이 있습니다. 당신은 자동 회원가입이 되어 있습니다.");
      }
      return new PrincipalDetails(userEntity, oauth2User.getAttributes());
      //return super.loadUser(userRequest);
    }
//구글로 부터 받은 userRequest 데이터에 대한 후처리 되는 함수

}
