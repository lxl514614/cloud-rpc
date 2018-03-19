package cn.bqmart.provider.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;
import sun.jvm.hotspot.types.JDoubleField;

/**
 * RedisConfiguration
 *
 * @author Lee
 * @date 2018/3/16
 * description:
 * Created by Lee on 2018/3/16.
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.database}")
    private Integer database;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.pool.max-active}")
    private Integer maxActive;

    @Value("${spring.redis.pool.max-wait}")
    private Integer maxWait;

    @Value("${spring.redis.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private Integer minIdle;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory () {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 最小空闲连接
        jedisPoolConfig.setMinIdle(minIdle);
        // 连接池最大阻塞时间
        jedisPoolConfig.setMaxWaitMillis(maxWait);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(redisHost);
        jedisConnectionFactory.setPassword(redisPassword);
        jedisConnectionFactory.setDatabase(database);
        jedisConnectionFactory.setTimeout(timeout);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);

        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory, RedisSerializer fastJson2JsonRedisSerializer) {

        StringRedisTemplate redisTemplate = new StringRedisTemplate(factory);
        redisTemplate.setConnectionFactory(factory);
        // redis 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        // hash 使用jdk序列化
        redisTemplate.setHashValueSerializer(fastJson2JsonRedisSerializer/*new JdkSerializationRedisSerializer()*/);
        // StringRedisSerializer key 序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // keySerializer 对key的默认序列化器, 默认值是StringSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // vallueSerializer 对value 序列化
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);

        return redisTemplate;
    }



}
