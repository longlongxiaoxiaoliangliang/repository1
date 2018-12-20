package com.prolog.time.management.util;

import com.prolog.time.management.service.ResourceSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.prolog.framework.authority.core.util.AuthorityUtils;
import com.prolog.user.model.PlgFxUser;


@Service
public class ContexToken {
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	public PlgFxUser getUser() {
		String username = AuthorityUtils.getLoginUser().getUsername();
		username = ResourceSettings.PREFIX_USER+username;
		Object obj = redisTemplate.opsForValue().get(username);
		PlgFxUser user = (PlgFxUser)obj;
		return user;
	}
}
