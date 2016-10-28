package com.balancika.crm.configuration.securities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmUserDao;
import com.balancika.crm.model.CrmRole;
import com.balancika.crm.model.CrmUser;

@Repository("UserCustomDetailService")
public class UserCustomDetailService implements UserDetailsService {

	@Autowired
	private CrmUserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CrmUser us = new CrmUser();
		us.setUsername(username);
		CrmUser user = userDao.findUserByUsername(us);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		System.out.println("User ID : " + user.getUserID());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
				true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(CrmUser user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		CrmRole userRole = user.getRole();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRoleName()));
		System.out.print("authorities :" + authorities);
		return authorities;	
	}

}
