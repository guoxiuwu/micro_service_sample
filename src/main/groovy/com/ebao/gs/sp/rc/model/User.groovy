package com.ebao.gs.sp.rc.model

import com.ebao.gs.sp.rc.common.model.BaseDataVerEntity
import com.ebao.gs.sp.rc.common.utils.LimitQueue
import com.ebao.gs.sp.rc.common.utils.LimitQueueDeserializer
import com.ebao.gs.sp.rc.common.utils.LimitQueueSerializer
import com.ebao.gs.sp.rc.common.validation.anno.SizeValidate
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class User extends BaseDataVerEntity implements UserDetails {

    Long userId
    @SizeValidate(max = 20)
    String userName
    @SizeValidate(max = 400)
    String realName
    @SizeValidate(max = 200)
    String email
    @SizeValidate(max = 50)
    String mobile
    @SizeValidate(max = 50)
    String telPhone
    @JsonIgnore
    @SizeValidate(max = 32)
    String passWord

    List<UserAuth> authorities
    UserProfile userProfile

    @JsonIgnore
    boolean accountNonExpired = true
    @JsonIgnore
    boolean accountNonLocked = true
    @JsonIgnore
    boolean credentialsNonExpired = true
    @JsonIgnore
    boolean enabled = true

    @JsonIgnore
    int loginFailedTimes = 0
    @JsonIgnore
    Date credentialsValidBy
    @JsonIgnore
    Date lockedUntilTo

    @Override
    List<UserAuth> getAuthorities() {
        return authorities
    }

    @Override
    boolean isAccountNonExpired() {
        return accountNonExpired
    }

    @Override
    boolean isAccountNonLocked() {
        return accountNonLocked
    }

    @Override
    boolean isCredentialsNonExpired() {
        return credentialsNonExpired
    }

    @Override
    boolean isEnabled() {
        return enabled
    }

    @Override
    String getPassword() {
        return passWord
    }

    @Override
    String getUsername() {
        return userName
    }

    @Override
    public Serializable getPK() {
        return userId
    }
}

class UserProfile implements Serializable {
    String preferredLang
    @JsonDeserialize(as = LimitQueue.class, using = LimitQueueDeserializer.class)
    @JsonSerialize(as = LimitQueue.class, using = LimitQueueSerializer.class)
    // keep previous password 3 times
    LimitQueue<String> oldPassWords = new LimitQueue<String>(3)
}

class UserAuth implements GrantedAuthority {
    String authority
}

