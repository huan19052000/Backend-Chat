package com.example.bruce.security;

import com.example.bruce.entity.UserProfileEntity;
import com.example.bruce.model.response.ResponseException;
import com.example.bruce.model.response.UserContext;
import com.example.bruce.repository.UserProfileRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter extends AbstractAuthenticationProcessingFilter {
    private UserProfileRepository userProfileRepository;
    public AuthorizationFilter(RequestMatcher matcher, UserProfileRepository userProfileRepository) {
        super(matcher);
        this.userProfileRepository = userProfileRepository;
    }
    //xac thuc, verify: xác định xem api vừa call có được đến controller không
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token == null){
            try {
                throw new ResponseException("Token invalid");
            } catch (ResponseException e) {
                throw new RuntimeException(e);
            }
        }
        token = token.substring("Bearer ".length());
        UserProfileEntity user = null;
        try {
            user = parseToken(token);
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
        return new UsernamePasswordAuthenticationToken(user,null);
    }

    private UserProfileEntity parseToken(String token) throws ResponseException{
        //Giải mã token để lấy thông tin
        Claims claims = Jwts.parser()
                .setSigningKey("123a@")
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        UserProfileEntity user = userProfileRepository.findOneByUserName(username);
        if ( user == null ){
            throw new ResponseException("User not exist");
        }
        return user;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)authResult;
        UserProfileEntity userProfile = (UserProfileEntity)auth.getPrincipal();
        UserContext userContext = new UserContext();
        userContext.setId(userProfile.getId());
        userContext.setEmail(userProfile.getEmail());
        userContext.setUsername(userProfile.getUserName());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new JwtAuthenticationToken(userContext));
        SecurityContextHolder.setContext(context);
        if (!response.isCommitted()) {
            chain.doFilter(request, response);
        }
    }

    public static int getCurrentUserId() {
        UserContext user = (UserContext) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return user.getId();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}
