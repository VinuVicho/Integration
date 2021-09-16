package me.vinuvicho.integration.registration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String nickname;
    private String email;
    private String password;

    public RegistrationRequest() {
    }
}
