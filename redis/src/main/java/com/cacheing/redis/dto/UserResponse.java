package com.cacheing.redis.dto;


import com.cacheing.redis.demo.User;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record UserResponse(User user) implements Serializable {

}
