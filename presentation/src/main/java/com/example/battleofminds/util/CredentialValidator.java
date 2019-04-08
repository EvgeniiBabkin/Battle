package com.example.battleofminds.util;

import android.support.annotation.Nullable;

public class CredentialValidator {

    public CredentialValidator() {
    }

    private final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    public boolean isLoginCorrect(@Nullable final String login) {
        return login != null && !login.isEmpty() && !login.contains(" ");
    }

    public boolean isPasswordCorrect(@Nullable final String password) {
        return password != null && !password.isEmpty() && !password.contains(" ") && password.length() >= 4;
    }

    public boolean isEmailCorrect(@Nullable final String email) {
        if (email == null || email.isEmpty() || email.contains(" ")) return false;
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(EMAIL_PATTERN);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
