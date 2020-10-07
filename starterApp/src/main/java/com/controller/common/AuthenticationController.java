package com.controller.common;

import com.ExceptionHandling.AppExceptions;
import com.dto.request.AuthRequest;
import com.dto.response.AuthResponse;
import com.service.common.AppUserDetailService;
import com.service.common.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author avinash.a.mishra
 */

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserDetailService appUserDetailService;

    @Autowired
    private JwtService jwtService;

    @RequestMapping("/autenticate")
    public AuthResponse authenticateUser(@RequestBody AuthRequest request){
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           request.getUsername(),
                           request.getPassword()
                   )
           );
       }catch (BadCredentialsException e){
           throw new AppExceptions("Invalid username/password");
       }

      final   UserDetails userDetails = appUserDetailService.loadUserByUsername(request.getUsername());
        String jwt = jwtService.generateTokenFromUserDetail(userDetails);

        return new AuthResponse(jwt);
    }


}
