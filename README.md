# CyanInvite 跨服邀请插件

## 1. 项目信息
- **名称**: CyanInvite
- **功能**: 玩家跨服邀请系统
  ✅ 实时跨服传送请求
  ✅ 全局喊话消息广播
  ✅ 邀请冷却时间控制
  ✅ 敏感词过滤系统
- **适用版本**:
  - BungeeCord 1.16+
  - Velocity 3.2.0+
  - MySQL 8.0+

## 2. 使用教程
### 安装步骤
1. 将编译好的jar放入plugins目录
2. 配置config.yml数据库连接
3. 重启服务器

### 主要指令
```yml
Commands:
  喊话: "shout" # 发送全局广播
  邀请: "invite" # 发起跨服邀请
  屏蔽: "block" # 屏蔽邀请消息
```

### 配置要点
```yml
# 数据库配置
JDBC_URL: jdbc:mysql://localhost:3306/mc_db
USERNAME: db_user
PASSWORD: secure_password

# 时间设置（单位：秒）
Times:
  cooldown: 60 # 指令冷却
  expiry: 300 # 邀请有效期

BlockedServers:
  - lobby # 屏蔽指定服务器邀请
```

## 3. 编译方法
```bash
# 使用 Gradle 构建
./gradlew shadowJar
# 输出路径：
# - Bungee: bungee/build/libs/
# - Velocity: velocity/build/libs/
```

## 4. 项目结构
```
CyanInvate/
├── core/          # 公共模块
├── bungee/        # BungeeCord实现
├── velocity/      # Velocity实现
├── spigot/        # 敏感词过滤模块
└── build.gradle.kts # 多模块构建配置
```

## 5. 其他信息
⚠️ 注意事项：
- 需要预先配置MySQL数据库
- 不同平台配置同步更新
- 生产环境务必修改默认凭证

🔧 技术支持:
- 使用HikariCP连接池
- 支持多语言消息配置
- 完整的邀请生命周期管理


[![](https://www.go176.net/content/uploadfile/202309/b5111695853313.png)](https://awacode.top/lyxy)