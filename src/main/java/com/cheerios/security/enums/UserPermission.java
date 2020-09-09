package com.cheerios.security.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserPermission {
	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");
	
	private String permission;
	
	public static UserPermission getUserPermissionByName(String displayName) {
		return Arrays.asList(UserPermission.values()).stream()
				.filter(permission -> permission.name().equalsIgnoreCase(displayName))
				.findFirst()
				.orElse(null);
	}

	

}
