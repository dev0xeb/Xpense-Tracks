package org.XpenseTracks.dtos.request;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserRequest {
    @Id
    private String userId;
    private String username;
    private String email;
    private String password;
}
