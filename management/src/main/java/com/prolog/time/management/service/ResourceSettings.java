package com.prolog.time.management.service;

import java.util.concurrent.TimeUnit;

import com.prolog.time.management.resources.ISysUserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.prolog.common.util.ToolKits;
import com.prolog.framework.authority.core.model.MyUserDetails;
import com.prolog.framework.authority.core.service.IUserService;
import com.prolog.framework.authority.core.util.AuthorityUtils;
import com.prolog.framework.authority.core.vo.UserVO;
import com.prolog.framework.common.message.RestMessage;
import com.prolog.user.model.PlgFxUser;


@Service
public class ResourceSettings implements IUserService {

	@Autowired(required=false)
	private ISysUserResource userService;
	@Autowired
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	public static final String PREFIX_USER = "OA_USER_";
	public static final String PREFIX_AUTH = "OA_AUTH_";
	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(AuthorityCenters.class);

	@SuppressWarnings("unchecked")
	@Override
	public UserVO getByUserName(String username) {
		
		// 从redis中查询用户信息，若没有，从数据库中获取，然后转存到redis
		Object obj = redisTemplate.opsForValue().get(PREFIX_USER + username);
		PlgFxUser user;
		UserVO auths;
		if (ToolKits.isNotEmpty(obj)) {
			user = (PlgFxUser) obj;
			auths =  (UserVO) redisTemplate.opsForValue().get(PREFIX_AUTH + username);

		} else {
			RestMessage<PlgFxUser> userData = userService.getUserByUsername(username);
			RestMessage<UserVO> authsData = userService.getUserServiceAuthority(username);
			user = userData.getData();
			auths = authsData.getData();
			if (!userData.isSuccess()) {
				return null;
			}

			if (ToolKits.isEmpty(user)) {
				return null;
			}
			
			if (!authsData.isSuccess()) {
				return null;
			}

			if (ToolKits.isEmpty(auths)) {
				return null;
			}
			
			redisTemplate.opsForValue().set(PREFIX_USER + username, user, 10, TimeUnit.HOURS);
			redisTemplate.opsForValue().set(PREFIX_AUTH + username, auths, 10, TimeUnit.HOURS);
		}
		//auths.getAuthorities().forEach((x) -> logger.debug(x));
		return auths;

	}

	// 获取当前登录人
	public PlgFxUser getUser() {
		MyUserDetails loginUser = AuthorityUtils.getLoginUser();
		String username = loginUser.getUsername();
		PlgFxUser userInfo = (PlgFxUser) redisTemplate.opsForValue().get(PREFIX_USER + username);
		return userInfo;
	}
}
