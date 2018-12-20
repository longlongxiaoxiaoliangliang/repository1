package com.prolog.time.management.service;

import java.util.ArrayList;
import java.util.List;

import com.prolog.time.management.resources.ISysUserResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.prolog.common.util.ToolKits;
import com.prolog.framework.authority.core.service.IAuthorityService;
import com.prolog.framework.authority.core.vo.AuthorityVO;
import com.prolog.framework.common.message.RestMessage;


@Service
public class AuthorityCenters implements IAuthorityService {
	
	private final static Logger logger = LoggerFactory.getLogger(AuthorityCenters.class);
	@Value("${spring.application.name:}")
	private String servicename;
	
	
	@Autowired(required=false)
	private ISysUserResource userService;
	
	@Override
	public List<AuthorityVO> loadAuthorities() {
		RestMessage<List<AuthorityVO>> list=null;
		try {
			list = userService.getSystemServiceAuthority(servicename);
		}catch(Exception e){
			e.printStackTrace();
			logger.info("无用户权限");
			return null;
		}
		
		
		if(!list.isSuccess()) {
			return  new ArrayList<AuthorityVO>();
		}
		
	    if(ToolKits.isEmpty(list.getData())) {
	    	return new ArrayList<AuthorityVO>();
	    }
	    
	    //list.getData().forEach((x) -> logger.debug(x.getAuthorityNo()+":"+x.getResource()));
	    
		return list.getData();
	}

	@Override
	public String[] loadPermitResource() {
		return new String[]{"/**/*.html","/**/*.css","/**/*.js","/**/*.ico","/**/*.png","/**/*.gif","/**/*.jpg","/**/font/*","/v2/api-docs","/auth/login"};
	}
}
