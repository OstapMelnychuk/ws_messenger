package com.example.ws_messenger.security.component;

import com.example.ws_messenger.security.component.policy.StringValidationPolicy;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StringValidator {
    private final static String NICKNAME_PATTERN = "^[a-zA-Z0-9]*$";

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final static int DEFAULT_STRING_MIN_LENGTH = 1;
    private final static int DEFAULT_STRING_MAX_LENGTH = 128;
    private final static int DEFAULT_PASSWORD_MIN_LENGTH = 8;
    private final static int DEFAULT_PASSWORD_MAX_LENGTH = 16;
    private final static int DEFAULT_NICKNAME_MIN_LENGTH = 4;
    private final static int DEFAULT_NICKNAME_MAX_LENGTH = 16;

    public boolean validateString(String str) {
        return validateWithDefaultPolicy(str);
    }

    public boolean validateString(String str, StringValidationPolicy stringValidationPolicy) {
        switch (stringValidationPolicy) {
            case NICKNAME_POLICY -> {
                return validateWithNicknamePolicy(str);
            }
            case PASSWORD_POLICY -> {
                return validateWithPasswordPolicy(str);
            }
            case EMAIL_POLICY -> {
                return validateWithEmailPolicy(str);
            }
            default -> {
                return validateWithDefaultPolicy(str);
            }
        }
    }

    private boolean validateWithDefaultPolicy(String str) {
        return Objects.nonNull(str) && !str.isEmpty() && validateSizePolicy(str, DEFAULT_STRING_MIN_LENGTH, DEFAULT_STRING_MAX_LENGTH);
    }

    private boolean validateWithDefaultPolicyWithoutSize(String str) {
        return Objects.nonNull(str) && !str.isEmpty();
    }

    private boolean validateWithEmailPolicy(String str) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(str);
        return validateWithDefaultPolicyWithoutSize(str) && matcher.find();
    }

    private boolean validateWithNicknamePolicy(String str) {
        Pattern pattern = Pattern.compile(NICKNAME_PATTERN);
        Matcher matcher = pattern.matcher(str);
        return validateWithDefaultPolicyWithoutSize(str) && validateSizePolicy(str, DEFAULT_NICKNAME_MIN_LENGTH, DEFAULT_NICKNAME_MAX_LENGTH) && matcher.find();
    }

    private boolean validateWithPasswordPolicy(String str) {
        return validateWithDefaultPolicyWithoutSize(str) && validateSizePolicy(str, DEFAULT_PASSWORD_MIN_LENGTH, DEFAULT_PASSWORD_MAX_LENGTH);
    }

    private boolean validateSizePolicy(String str, int min, int max) {
        return str.length() >= min && str.length() <= max;
    }
}
