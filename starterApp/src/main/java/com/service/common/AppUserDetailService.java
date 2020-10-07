package com.service.common;

import com.dao.CommonRepository;
import com.dto.common.UserDtl;
import com.model.common.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author avinash.a.mishra
 */

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private CommonRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser user = repository.getUserByUserName(s);
        if(user==null){
            throw new UsernameNotFoundException("Not found :"+s);
        }
        UserDtl u= new UserDtl(user);
        return u;
    }
}
