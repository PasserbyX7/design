package cn.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import cn.demo.service.JWTService;
import cn.demo.utils.JWTUtils;

@Service
public class JWTServiceImpl implements JWTService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public String login(String username, String password) {
        var token = new UsernamePasswordAuthenticationToken(username, password);
        // 构建authentication并置入context
        // DaoAuthenticationProvider发现authentication已被填充，就不会再次认证
        var authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtils.generateToken(userDetails);
    }
}
