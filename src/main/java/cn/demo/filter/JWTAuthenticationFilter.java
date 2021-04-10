package cn.demo.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.demo.utils.JWTUtils;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader(jwtUtils.getHeader());
        // @formatter:off
        Optional.ofNullable(request.getHeader(jwtUtils.getHeader()))
                    .map(jwtUtils::getUsernameFromToken)
                    .map(userDetailsService::loadUserByUsername)
                    .filter(user -> jwtUtils.validateToken(token, user))
                    .map(user -> new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()))
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        // @formatter:on
        filterChain.doFilter(request, response);
    }

}
