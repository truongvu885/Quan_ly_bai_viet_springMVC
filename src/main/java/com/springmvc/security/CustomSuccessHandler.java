package com.springmvc.security;

import com.springmvc.util.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication); //định tuyến url trả về
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication){
        String url = "";
        // if role is ADMIN so redirect to controller : /admin/home
        //if role is USER so redirect to controller :/trang-chu
        List<String> roleList = SecurityUtils.getAuthorities();
        if(isAdmin(roleList)){
            url = "/admin/home";
        }
        else if(isUser(roleList)){
            url = "/trang-chu";
        }
        return url;
    }

    private boolean isAdmin(List<String> roleList){
        if(roleList.contains("ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isUser(List<String> userList){
        if(userList.contains("USER")) {
            return true;
        }
        return false;
    }

}
