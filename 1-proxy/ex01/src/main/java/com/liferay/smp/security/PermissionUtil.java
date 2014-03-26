package com.liferay.smp.security;

public class PermissionUtil {

	public static boolean isAdmin(long userId) {
		if (userId == 12345L) {
			return true;
		}
		return false;
	}
}