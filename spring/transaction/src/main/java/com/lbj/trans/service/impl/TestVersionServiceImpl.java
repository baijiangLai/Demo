package com.lbj.trans.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lbj.trans.mapper.TestVersionMapper;
import com.lbj.trans.pojo.TestVersion;
import com.lbj.trans.service.TestVersionService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: {baijiang.lai}
 * @CreateTime: 2024-09-30
 * @Description:
 */

@Service
@Slf4j
public class TestVersionServiceImpl extends ServiceImpl<TestVersionMapper, TestVersion> implements TestVersionService {
    @Resource
    private TestVersionMapper testVersionMapper;
    @Resource
    private RedissonClient redissonClient;
    @Resource
    private PlatformTransactionManager transactionManager;


    @Override
    public String getJueJinLink(String id) {
//        return manualControlTrans(id);
        return transactionSynchronizationManager(id);
    }

    private String transactionSynchronizationManager(String id) {
        RLock lock = redissonClient.getLock(id);
        try {
            if (lock.tryLock(10, 60, TimeUnit.SECONDS)) {
                TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

                // 使用事务模板执行代码
                return transactionTemplate.execute(status -> {
                    // 注册事务同步回调，事务提交后释放锁
                    TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                        @Override
                        public void afterCommit() {
                            lock.unlock(); // 事务提交后释放锁
                        }

                        @Override
                        public void afterCompletion(int status) {
                            if (status != STATUS_COMMITTED) {
                                lock.unlock(); // 事务回滚时释放锁
                            }
                        }
                    });

                    // 获取当前最新版本
                    TestVersion curTextVersion = testVersionMapper.getMaxNum(id);

                    TestVersion newVersion = new TestVersion();
                    newVersion.setPid(id);
                    newVersion.setVer(curTextVersion == null ? 1 : curTextVersion.getVer() + 1);

                    // 尝试插入新的版本记录
                    int insertResult = testVersionMapper.insert(newVersion);

                    if (insertResult == 0) {
                        // 插入失败，手动回滚事务
                        status.setRollbackOnly();
                        throw new RuntimeException("Failed to insert new version");
                    }

                    return "Hello Spring Boot 3.x!";
                });
            } else {
                throw new RuntimeException("Could not obtain lock for version control");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lock acquisition interrupted", e);
        }
    }

    private String manualControlTrans(String id) {
        RLock lock = redissonClient.getLock(id);
        try {
            if (lock.tryLock(10, 60, TimeUnit.SECONDS)) {
                // 使用 TransactionTemplate 来手动管理事务
                TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
                return transactionTemplate.execute(status -> {
                    // 获取当前最新版本
                    TestVersion curTextVersion = testVersionMapper.getMaxNum(id);

                    TestVersion newVersion = new TestVersion();
                    newVersion.setPid(id);
                    newVersion.setVer(curTextVersion == null ? 1 : curTextVersion.getVer() + 1);

                    // 尝试插入新的版本记录
                    int insertResult = testVersionMapper.insert(newVersion);

                    if (insertResult == 0) {
                        // 插入失败，手动回滚事务
                        status.setRollbackOnly();
                        throw new RuntimeException("Failed to insert new version");
                    }

                    return "Hello Spring Boot 3.x!";
                });
            } else {
                throw new RuntimeException("Could not obtain lock for version control");
            }
        } catch (Exception e) {
            throw new RuntimeException("Lock acquisition interrupted", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    // 使用乐观锁解决，实体类添加version属性，表也添加version字段
    // 每次更改的时候，先查询当前版本号，然后更新，如果更新成功，则更新成功，否则，更新失败
//    private String getJueJinLinkByOptimistic(String id) {
//        RLock lock = redissonClient.getLock(id);
//        try {
//            if (lock.tryLock(10, 60, TimeUnit.SECONDS)) {
//                // 获取当前最新版本
//                TestVersion curTextVersion = testVersionMapper.getMaxNum(id);
//                Integer nextVer = (curTextVersion != null) ? curTextVersion.getVer() + 1 : 1;
//                Integer currentOptimisticVersion = (curTextVersion != null) ? curTextVersion.getVersion() : 0;
//
//                TestVersion newVersion = new TestVersion();
//                newVersion.setPid(id);
//                newVersion.setVer(nextVer);
//                newVersion.setVersion(currentOptimisticVersion + 1); // 增加乐观锁版本号
//
//                // 尝试插入新的版本记录
//                int insertResult = testVersionMapper.insert(newVersion);
//
//                if (insertResult == 0) {
//                    // 插入失败，表示乐观锁冲突
//                    throw new RuntimeException("Failed to insert new version, optimistic locking failure");
//                }
//
//            } else {
//                throw new RuntimeException("Could not obtain lock for version control");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Lock acquisition interrupted", e);
//        } finally {
//            if (lock.isHeldByCurrentThread()) {
//                lock.unlock();
//            }
//        }
//        return "Hello Spring Boot 3.x!";
//    }
}

