package com.ecomindo.onboarding.sampleauth.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDTO extends User implements Cloneable {
	private String fullname;
	private String token;

	public CustomUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

    public CustomUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities,
			String fullname) {
		super(username, password, authorities);
		this.fullname = fullname;
	}

    
	public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}