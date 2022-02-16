package com.cos.securityex01.config.auth;

import com.cos.securityex01.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//시큐리티가 /loginProc 주소 요청이 오면 낚아채서 로그인을 진행시킨다
//로그인을 잔행이 완료가 되면 시큐리티 session을 만들어 줍니다.(Security ContextHolder)
//오브젝트 => Authertication 타입의 객체
//Authertication 안에 User 정보가 있어야 됨.
// User오브젝트타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
    private static final long serialVersionUID = 1L;
    private User user; //콤포지
    private Map<String, Object> attributes;

    // 일반 시큐리티 로그인시 사용
    public PrincipalDetails(User user) {
        this.user = user;
    }

    // OAuth2.0 로그인시 사용
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }
    public User getUser() {
        return user;
    }

    //해당 User의 권한을 리턴하는
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
        collet.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        // collet.add(()->{ return user.getRole();});
        return collet;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //우리 사이트!! 1년동안 회원이 로그인 안하면!! 휴면계정으로 하기로 함
        //현재시간 - 로그인 시간 => 1년초가하면 return false
        //user.getLonginDate();
        return true;
    }

    // 리소스 서버로 부터 받는 회원정보
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // User의 PrimaryKey
    @Override
    public String getName() {
        return user.getId()+"";
    }
}
