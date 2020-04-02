package io.jaziu.todoapi.message.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String username;
	private Long userId;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities,Long userId) {
		this.token = accessToken;
		this.username = username;
		this.authorities = authorities;
		this.userId = userId;
	}


}
