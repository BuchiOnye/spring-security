package com.cheerios.security.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;

@Getter
public enum UserRoles {
	STUDENT("STUDENT_READ"), 
	ADMIN("COURSE_READ, COURSE_WRITE,STUDENT_READ,STUDENT_WRITE"),
	ADMIN_TRAINEE("COURSE_READ");
	
	private String permissions;

	private UserRoles(String permissions) {
		this.permissions = permissions;
	}
	
	public Set<UserPermission> getUserPermissions(UserRoles role){
		Set<UserPermission> permissionSet = new HashSet<>();
		List<String> assignedPermissions = Arrays.asList(role.permissions.split(","));
		assignedPermissions.stream()
		.map(perm -> UserPermission.getUserPermissionByName(perm))
		.anyMatch(selectedPermission -> permissionSet.add(selectedPermission));
		return permissionSet;
	}
	
	public Set<SimpleGrantedAuthority> getAuthorities(UserRoles role){
		Set<SimpleGrantedAuthority> permissio = getUserPermissions(role)
		.stream()
		.map(permit -> new SimpleGrantedAuthority(permit.getPermission())).collect(Collectors.toSet());
		
		permissio.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return permissio;
		
	}
	
	

}
