package com.kangwon.jediscache;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/users/{id}/email")
    public String getUserEmail(@PathVariable Long id) {
        try (Jedis jedis = new JedisPool("127.0.0.1", 6379).getResource()){
            var userEmailRedisKey = "users:%d:email".formatted(id);

            // 1. request to cache
            String userEmail = jedis.get("users:%d:email".formatted(id));
            if(userEmail != null) {
                return userEmail;
            }
            // 2. else db
            userEmail = userRepository.findById(id).orElse(User.builder().build()).getEmail();

            // 3. cache
            jedis.set("users:%d:email".formatted(id), userEmail);
            jedis.setex(userEmailRedisKey, 8, userEmail);
            // end
            return userEmail;
        }
    }
}
