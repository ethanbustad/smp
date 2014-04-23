package com.liferay.smp.security.auth;

public class UserThreadLocal {

	public static Long getUserId() {
		return _userId.get();
	}

	public static void setUserId(long userId) {
		_userId.set(userId);
	}

	private static ThreadLocal<Long> _userId =
		new ThreadLocal<Long>();
}