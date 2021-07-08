package com.leovel.carservice.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.leovel.carservice.models.OfficerDTO;

@FeignClient(name = "officer-service")
public interface OfficerServiceProxy {
	@GetMapping("/{officerId}")
	public OfficerDTO findOfficerById(@PathVariable Long officerId);
}
