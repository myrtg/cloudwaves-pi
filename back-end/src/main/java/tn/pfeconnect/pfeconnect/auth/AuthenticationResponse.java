package tn.pfeconnect.pfeconnect.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tn.pfeconnect.pfeconnect.entities.User;

@Getter
@Setter
@Builder

public class AuthenticationResponse {
    private String token;
    private User user;

}
