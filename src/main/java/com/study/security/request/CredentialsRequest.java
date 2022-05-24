package com.study.security.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredentialsRequest {

    private String login;
    private String password;

}
