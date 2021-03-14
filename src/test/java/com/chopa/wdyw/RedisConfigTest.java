package com.chopa.wdyw;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
@Disabled
public class RedisConfigTest {
	@Autowired
	public RedisTemplate<String, Object> redisTemplate;

	@Test
	void stringTest() {
//		Given
		String key = "iamkey";
		String value = "iamvalue";

//		redis에 set
		redisTemplate.opsForValue().set(key, value);


//		redis에서 data delete
		redisTemplate.delete(key);

		if (!redisTemplate.hasKey("999")) {
			System.out.println("key 미존재");
		}
	}
}
