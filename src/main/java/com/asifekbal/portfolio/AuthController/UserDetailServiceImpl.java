package com.asifekbal.portfolio.AuthController;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.asifekbal.portfolio.model.User;
import com.asifekbal.portfolio.repository.UserRepository;




public class UserDetailServiceImpl implements UserDetailsService{
   
    @Autowired
    private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repository.getUserByUsername(username);
        
        if(user==null){
            throw new UsernameNotFoundException("username not found");
        }
		return new MyUserDetail(user);
	}
    
}