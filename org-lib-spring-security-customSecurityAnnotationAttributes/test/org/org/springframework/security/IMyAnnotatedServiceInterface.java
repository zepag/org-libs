package org.org.springframework.security;
@AdminRoleNeeded
public interface IMyAnnotatedServiceInterface {

	@AdminRoleNeeded
	public abstract String getResult();

	public abstract String getResult2();

}