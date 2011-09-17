package org.org.springframework.security;

@AdminRoleNeeded
public class MyAnnotatedServiceImpl implements IMyNotAnnotatedServiceInterface {
    @SuppressWarnings("unused")
    private String phonyField;

    @AdminRoleNeeded
    public String getResult() {
        return "result";
    }

    public String getResult2() {
        return "result2";
    }
}
