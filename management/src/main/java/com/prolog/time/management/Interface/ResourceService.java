package com.prolog.time.management.Interface;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.prolog.framework.common.message.RestMessage;
import com.prolog.framework.cs.authorization.model.Resource;
import com.prolog.framework.cs.authorization.model.ResourceGroup;

@Service
@FeignClient(value="service-resource")//,url="192.168.10.167:8700"
public interface ResourceService {

	@GetMapping("/resource/servicename/{serviceName}")
	public RestMessage<List<Resource>> getByServiceName(@PathVariable("serviceName") String serviceName);
	
	@GetMapping("/resourcegroup/names")
	public RestMessage<List<ResourceGroup>> getByName(@RequestParam("names") String names);
	
}
