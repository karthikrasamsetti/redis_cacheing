package com.cacheing.redis.dto;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Map;
@Builder
public record PatchRequest(@Validated Map<String,String> map) implements Serializable {
}
