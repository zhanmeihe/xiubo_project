package com.epwk.software.boot.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * 
 * @author zhanmeihe
 *
 *
 * @param <HK>
 * @param <V>
 */
@Component
public class RedisService<HK, V> {
	/**
	 * 默认过期时长，单位：秒
	 */
	public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

	/**
	 * 不设置过期时长
	 */
	public static final long NOT_EXPIRE = -1;
	private RedisTemplate<String, V> redisTemplate;
	// 在构造器中通过redisTemplate的工厂方法实例化操作对象
	private HashOperations<String, HK, V> hashOperations;
	private ListOperations<String, V> listOperations;
//	private ZSetOperations<String, V> zSetOperations;
//	private SetOperations<String, V> setOperations;
	private ValueOperations<String, V> valueOperations;

	@Autowired
	public RedisService(RedisTemplate<String, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = redisTemplate.opsForHash();
		this.listOperations = redisTemplate.opsForList();
//		this.zSetOperations = redisTemplate.opsForZSet();
//		this.setOperations = redisTemplate.opsForSet();
		this.valueOperations = redisTemplate.opsForValue();
	}

	public void hashPut(String key, HK hashKey, V value) {
		hashOperations.put(key, hashKey, value);
	}

	public Map<HK, V> hashFindAll(String key) {
		return hashOperations.entries(key);
	}

	public V hashGet(String key, HK hashKey) {
		return hashOperations.get(key, hashKey);
	}

	public void hashRemove(String key, HK hashKey) {
		hashOperations.delete(key, hashKey);
	}

	public Long listPush(String key, V value) {
		return listOperations.rightPush(key, value);
	}

	public Long listUnshift(String key, V value) {
		return listOperations.leftPush(key, value);
	}

	public List<V> listFindAll(String key) {
		if (!redisTemplate.hasKey(key)) {
			return null;
		}
		return listOperations.range(key, 0, listOperations.size(key));
	}

	public V listLPop(String key) {
		return listOperations.leftPop(key);
	}

	public void setValue(String key, V value) {
		valueOperations.set(key, value);
	}

	public void setValue(String key, V value, long timeout) {
		ValueOperations<String, V> vo = redisTemplate.opsForValue();
		vo.set(key, value, timeout, TimeUnit.MILLISECONDS);
	}

	public V getValue(String key) {
		return valueOperations.get(key);
	}

	public void remove(String key) {
		redisTemplate.delete(key);
	}

	// 设置key的生命周期
	public boolean expire(String key, long timeout, TimeUnit timeUnit) {
		return redisTemplate.expire(key, timeout, timeUnit);
	}

	/**
	 * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
	 *
	 * @param oldKey
	 * @param newKey
	 */
	public void renameKey(String oldKey, String newKey) {
		redisTemplate.rename(oldKey, newKey);
	}

	/**
	 * newKey不存在时才重命名
	 *
	 * @param oldKey
	 * @param newKey
	 * @return 修改成功返回true
	 */
	public boolean renameKeyNotExist(String oldKey, String newKey) {
		return redisTemplate.renameIfAbsent(oldKey, newKey);
	}

	/**
	 * 指定key在指定的日期过期
	 *
	 * @param key
	 * @param date
	 */
	public void expireKeyAt(String key, Date date) {
		redisTemplate.expireAt(key, date);
	}

	/**
	 * 查询key的生命周期
	 *
	 * @param key
	 * @param timeUnit
	 * @return
	 */
	public long getKeyExpire(String key, TimeUnit timeUnit) {
		return redisTemplate.getExpire(key, timeUnit);
	}

	/**
	 * 将key设置为永久有效
	 *
	 * @param key
	 */
	public void persistKey(String key) {
		redisTemplate.persist(key);
	}

	/**
	 * 删除多个key
	 *
	 * @param keys
	 */
	public void deleteKey(String... keys) {
		Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
		redisTemplate.delete(kSet);
	}

	/**
	 * 删除Key的集合
	 *
	 * @param keys
	 */
	public void deleteKey(Collection<String> keys) {
		Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
		redisTemplate.delete(kSet);
	}

	public Boolean exists(String key) {

		return redisTemplate.hasKey(key);
	}
}
