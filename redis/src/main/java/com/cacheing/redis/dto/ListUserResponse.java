package com.cacheing.redis.dto;


import com.cacheing.redis.demo.User;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;
@Builder
public record ListUserResponse(List<User> users) implements Serializable {
}
