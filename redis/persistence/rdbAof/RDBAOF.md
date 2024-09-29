# 混合持久化
- 打开配置: `aof-use-rdb-preamble yes`
- 当拥有aof和rdb两种持久化方式时, redis会优先使用aof, aof文件中会包含rdb文件(头信息为rdb文件), 便于恢复。