package com.example.securitylesson.Config;

import com.example.securitylesson.dao.UserDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Component

@RequiredArgsConstructor
public class JwtAthFilter extends OncePerRequestFilter {

  private final UserDao userDao;
  private final  JwtUtils jwtutils;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader(AUTHORIZATION);
        final  String userEmail;
        final  String jwtToken;
        if(authHeader==null || !authHeader.startsWith("Bearer"))
        {
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken=authHeader.substring(7);
        userEmail=jwtutils.extractUsername(jwtToken);
        if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails=userDao.findUserByEmail(userEmail);
            if(jwtutils.isTokenValid(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken authToken=
              new  UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        filterChain.doFilter(request,response);
    }
}
