package com.cacheing.redis.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record UserDto(int id,String name,String email) implements Serializable {
}
