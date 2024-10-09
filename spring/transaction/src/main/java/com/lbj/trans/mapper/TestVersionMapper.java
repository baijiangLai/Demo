package com.lbj.trans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbj.trans.pojo.TestVersion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestVersionMapper extends BaseMapper<TestVersion> {
    TestVersion getMaxNum(@Param("pid") String pid);

}
