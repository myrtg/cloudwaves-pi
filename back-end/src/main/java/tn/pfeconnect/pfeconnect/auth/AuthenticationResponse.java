package tn.pfeconnect.pfeconnect.auth;
import tn.pfeconnect.pfeconnect.entities.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class AuthenticationResponse {
    private String token;
    private String refreshToken;
    private boolean mfaEnabled;
    private String secretImageUri;
        private User user;



}
