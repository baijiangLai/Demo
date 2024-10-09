package com.lbj.trans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbj.trans.pojo.TestVersion;

public interface TestVersionService extends IService<TestVersion> {
    String getJueJinLink(String id);
}
