package com.dvm.evyap.thingworxrestconsumer.dvmexception;

import lombok.Data;

@Data
public class ParserException extends Exception{

    private Exception ex;
    private String code;
    private static final String AUTHORIZATION_PARSE_FAIL = "Authorization key in not valid.Please be sure base64 encoded username:password";
    private static final String AUTHORIZATION_NOT_BASIC = "The authorization header is either empty or isn't Basic.";
    private static final String INVALID_EMAIL = "User Mail is not valid.Please confirm email.";

    public ParserException(Exception ex,String code) {
        this.ex = ex;
        this.code = code;
    }

    public static ParserException authorizationHeaderFail() {
        return new ParserException(new Exception(AUTHORIZATION_PARSE_FAIL),AUTHORIZATION_PARSE_FAIL);
    }
    public static ParserException notBasic() {
        return new ParserException(new Exception(AUTHORIZATION_NOT_BASIC),AUTHORIZATION_NOT_BASIC);
    }
    public static ParserException invalidMail() {
        return new ParserException(new Exception(INVALID_EMAIL),INVALID_EMAIL);
    }
}
