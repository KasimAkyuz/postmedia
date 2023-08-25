package com.akyuz.postmedia.security;

import com.akyuz.postmedia.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = extractJwtFormRequest(request);
            if(StringUtils.hasText(jwtToken)&& jwtTokenProvider.validateToken(jwtToken)){
                Long id= jwtTokenProvider.getUserIdFromJwt(jwtToken);
                UserDetails user= userDetailsService.loadUserById(id);
                if(user!=null){
                    UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(user,"",user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            }

        }catch (Exception e){
            return;
        }
        filterChain.doFilter(request,response);
    }

    private String extractJwtFormRequest(HttpServletRequest request) {
        String bearer=request.getHeader("Authorization");
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer"))
            return bearer.substring(bearer.length()+1);
        return null;


    }
}
