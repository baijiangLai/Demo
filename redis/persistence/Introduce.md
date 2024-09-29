# 官网指引
https://redis.io/docs/latest/operate/oss_and_stack/management/persistence/

# 分类
1. rdb:  RDB persistence performs point-in-time snapshots of your dataset at specified intervals.
   - 翻译: 对当前内存中的数据按照指定的时间进行快照写入到磁盘
2. aof: AOF persistence logs every write operation received by the server. These operations can then be replayed again at server startup, reconstructing the original dataset. Commands are logged using the same format as the Redis protocol itself
   - 翻译: 只对写命令进行backup