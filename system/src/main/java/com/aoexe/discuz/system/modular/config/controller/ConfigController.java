package com.aoexe.discuz.system.modular.config.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.core.annotion.Ignore;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-27
 */
@RestController
public class ConfigController {

	@GetMapping("/forum")
	@Ignore
	public void forum() {
		
	}
	
}
