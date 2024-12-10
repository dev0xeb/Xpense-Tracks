package org.XpenseTracks.services;

import org.XpenseTracks.dtos.request.UserRequest;
import org.XpenseTracks.dtos.response.UserResponse;

public interface UserServices {
    UserResponse register (UserRequest userRequest);
    UserResponse login (UserRequest userRequest);
}
