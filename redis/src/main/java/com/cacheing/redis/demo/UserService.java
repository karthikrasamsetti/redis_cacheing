package com.cacheing.redis.demo;


import com.cacheing.redis.dto.ListUserResponse;
import com.cacheing.redis.dto.UserDto;
import com.cacheing.redis.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    @CacheEvict(key = "'allUsers'",value = "user")
    public UserResponse insert(UserDto userDto){
        var user= User.builder()
                .name(userDto.name())
                .email(userDto.email())
                .build();
        userRepository.save(user);
        return UserResponse.builder()
                .user(user)
                .build();
    }
    @Cacheable(key = "'allUsers'",value = "user")
    public ListUserResponse getAll(){
        List<User>users= userRepository.findAll();
        log.info("user list: "+users);
         return ListUserResponse.builder()
                 .users(users)
                 .build();
    }
    @Cacheable(key = "#id", value = "userById")
    public UserResponse getById(int id){
        User user= userRepository.findById(id).orElseThrow();
        log.info("get by id method executed");
        return UserResponse.builder()
                .user(user)
                .build();
    }
    public  UserResponse update(UserDto userDto){
        User user=userRepository.findById(userDto.id()).orElseThrow();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        User newUser= userRepository.saveAndFlush(user);
        return UserResponse.builder()
                .user(newUser)
                .build();
    }
    public UserResponse patch(int id, Map<String,String> updates){
        log.info("Enter Patch method");
        User user=userRepository.findById(id).orElseThrow();
        if(updates.containsKey("name")){
          user.setName(updates.get("name"));}
        else {
            user.setEmail(updates.get("email"));
        }
        User patchUser=userRepository.save(user);
        return UserResponse.builder()
                .user(patchUser)
                .build();
    }
}
