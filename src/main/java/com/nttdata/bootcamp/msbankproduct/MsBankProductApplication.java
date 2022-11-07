package com.nttdata.bootcamp.msbankproduct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.entity.ProductEntity;




@SpringBootApplication
@EnableEurekaClient
public class MsBankProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBankProductApplication.class, args);
	}
	
	
  @Bean
  public ReactiveRedisTemplate<String, ProductEntity> reactiveJsonPostRedisTemplate(
      ReactiveRedisConnectionFactory connectionFactory) {

      RedisSerializationContext<String, ProductEntity> serializationContext = RedisSerializationContext
          .<String, ProductEntity>newSerializationContext(new StringRedisSerializer())
          .hashKey(new StringRedisSerializer())
          .hashValue(new Jackson2JsonRedisSerializer<>(ProductEntity.class))
          .build();


      return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
  }

}
