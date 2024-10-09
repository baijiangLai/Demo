> 在并发情况下，如果方法级别加上`@Transactional`注解，此时会导致数据写入重复，解决方法如下
# 使用乐观锁
1. 数据库表和实体类增加`version`字段
2. 在每次更改的时候，先查询当前版本号，然后更新，如果更新成功，则更新成功，否则，更新失败

# 手动管理事务
1. 取消方法上的`@Transactional`注解
2. 注入`PlatformTransactionManager`
3. 在执行方法的时候开启手动开启事务
```java
TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
transactionTemplate.execute(status -> {
    // 执行业务逻辑    
    return null;
});
```

4. 如果有失败的则执行`status.setRollbackOnly();`，并手动抛出异常

# 使用spring事务同步机制
1. 取消方法上的`@Transactional`注解
2. 注入`PlatformTransactionManager`
3. 在执行方法的时候开启事务同步
```java
try {
    // 注册事务同步回调，在事务提交后释放锁
    TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
        // 事务提交
        @Override
        public void afterCommit() {
            lock.unlock();
        }

        // 事务回滚
        @Override
        public void afterCompletion(int status) {
            if (status != STATUS_COMMITTED) {
                lock.unlock();
            }
        }
    });

    // 你的业务逻辑
} catch (Exception e) {
    lock.unlock();
    throw e;
}
```