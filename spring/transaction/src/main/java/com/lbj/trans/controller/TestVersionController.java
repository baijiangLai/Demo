package com.lbj.trans.controller;


import com.lbj.trans.service.TestVersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-09-30
 * @Description:
 * @link: https://juejin.cn/post/7392115478547709989?utm_source=gold_browser_extension#heading-1
 */

@RestController
@RequestMapping("/tttt")
@Slf4j
public class TestVersionController {
    @Resource
    private TestVersionService jueJinService;
    @GetMapping("/{id}")
    public String getJueJinLink(@PathVariable("id") String id) {
        log.info("id:{}", id);
        return jueJinService.getJueJinLink(id);
    }
}
