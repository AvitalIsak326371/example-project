package com.example.securitylesson.controllers;

import com.example.securitylesson.Config.JwtUtils;
import com.example.securitylesson.dao.AuthenticationRequest;
import com.example.securitylesson.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")

public class authenticationController {


    public authenticationController(UserDao userDao, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    private  final UserDao userDao;
   private  final JwtUtils jwtUtils;
   private  final AuthenticationManager authenticationManager;
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request)
    {
        System.out.println("hello");
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
    final UserDetails user=userDao.findUserByEmail(request.getEmail());
    if(user!=null)
    {
        return  ResponseEntity.ok(jwtUtils.generateToken(user));
    }
    return  ResponseEntity.status(400).body("user not found");
    }
    @GetMapping()
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("hello User");
    }


}
