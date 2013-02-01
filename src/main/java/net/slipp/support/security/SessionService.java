package net.slipp.support.security;

import java.util.List;

import javax.annotation.Resource;

import net.slipp.domain.user.SocialUser;
import net.slipp.repository.user.SocialUserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 로그인 사용자를 관리한다.
 */
@Service("sessionService")
public class SessionService {
    @Resource(name = "socialUserRepository")
    private SocialUserRepository socialUserRepository;

    public SocialUser getLoginUser() {
        if (!isAuthenticated()) {
            return SocialUser.GUEST_USER;
        }
        
        List<SocialUser> socialUsers = socialUserRepository.findsByUserId(getAuthenticatedUserName());
        if (socialUsers.isEmpty()){
        	return SocialUser.GUEST_USER;
        }
        
        return socialUsers.get(0);
    }
    
    public boolean isAuthenticated() {
        return getAuthentication() == null ? false : getAuthentication().isAuthenticated();
    }
    
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private String getAuthenticatedUserName() {
        return getAuthentication() == null ? null : getAuthentication().getName();
    }
}
