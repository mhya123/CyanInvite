package cn.cyanbukkit.invatevelocity;

@com.velocitypowered.api.plugin.Plugin(id = "cyaninvite", name = "CyanInvite", version = "0.2", description = "VC\u7248", authors = {"CyanBukkit"})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$J\u0012\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0007R\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\f\"\u0004\b\u001b\u0010\u000eR\u001a\u0010\u001c\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u0006*"}, d2 = {"Lcn/cyanbukkit/invatevelocity/CyanInvite;", "", "server", "Lcom/velocitypowered/api/proxy/ProxyServer;", "logger", "Lorg/slf4j/Logger;", "dataDirectory", "Ljava/nio/file/Path;", "(Lcom/velocitypowered/api/proxy/ProxyServer;Lorg/slf4j/Logger;Ljava/nio/file/Path;)V", "config", "Lcn/cyanbukkit/invatevelocity/config/file/YamlConfiguration;", "getConfig", "()Lcn/cyanbukkit/invatevelocity/config/file/YamlConfiguration;", "setConfig", "(Lcn/cyanbukkit/invatevelocity/config/file/YamlConfiguration;)V", "configFile", "Ljava/io/File;", "getConfigFile", "()Ljava/io/File;", "setConfigFile", "(Ljava/io/File;)V", "getDataDirectory", "()Ljava/nio/file/Path;", "getLogger", "()Lorg/slf4j/Logger;", "message", "getMessage", "setMessage", "messageFile", "getMessageFile", "setMessageFile", "getServer", "()Lcom/velocitypowered/api/proxy/ProxyServer;", "getResourceAsStream", "Ljava/io/InputStream;", "resourcePath", "", "onProxyInitialization", "", "e", "Lcom/velocitypowered/api/event/proxy/ProxyInitializeEvent;", "Companion", "velocity"})
public final class CyanInvite {
    @org.jetbrains.annotations.NotNull()
    private final com.velocitypowered.api.proxy.ProxyServer server = null;
    @org.jetbrains.annotations.NotNull()
    private final org.slf4j.Logger logger = null;
    @org.jetbrains.annotations.NotNull()
    private final java.nio.file.Path dataDirectory = null;
    public java.io.File configFile;
    public cn.cyanbukkit.invatevelocity.config.file.YamlConfiguration config;
    public cn.cyanbukkit.invatevelocity.config.file.YamlConfiguration message;
    public java.io.File messageFile;
    public static cn.cyanbukkit.invatevelocity.utils.ConfigModel configModel;
    public static cn.cyanbukkit.invatevelocity.CyanInvite instance;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Set<com.velocitypowered.api.proxy.Player> blockedMessagePlayers = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.List<cn.cyanbukkit.invatevelocity.utils.InviteRequest> inviteRequests = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<java.lang.String, java.lang.String> serverAlias = null;
    public static com.zaxxer.hikari.HikariDataSource hikariDataSource;
    
    /**
     * 记录上一次的喊话的时间戳 每次计算下次的统计
     */
    @org.jetbrains.annotations.NotNull()
    private static final java.util.Map<com.velocitypowered.api.proxy.Player, java.lang.Long> playerCooldown = null;
    @org.jetbrains.annotations.NotNull()
    public static final cn.cyanbukkit.invatevelocity.CyanInvite.Companion Companion = null;
    
    @com.google.inject.Inject()
    public CyanInvite(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.proxy.ProxyServer server, @org.jetbrains.annotations.NotNull()
    org.slf4j.Logger logger, @com.velocitypowered.api.plugin.annotation.DataDirectory()
    @org.jetbrains.annotations.NotNull()
    java.nio.file.Path dataDirectory) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.velocitypowered.api.proxy.ProxyServer getServer() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final org.slf4j.Logger getLogger() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.nio.file.Path getDataDirectory() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.InputStream getResourceAsStream(@org.jetbrains.annotations.NotNull()
    java.lang.String resourcePath) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getConfigFile() {
        return null;
    }
    
    public final void setConfigFile(@org.jetbrains.annotations.NotNull()
    java.io.File p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cn.cyanbukkit.invatevelocity.config.file.YamlConfiguration getConfig() {
        return null;
    }
    
    public final void setConfig(@org.jetbrains.annotations.NotNull()
    cn.cyanbukkit.invatevelocity.config.file.YamlConfiguration p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cn.cyanbukkit.invatevelocity.config.file.YamlConfiguration getMessage() {
        return null;
    }
    
    public final void setMessage(@org.jetbrains.annotations.NotNull()
    cn.cyanbukkit.invatevelocity.config.file.YamlConfiguration p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getMessageFile() {
        return null;
    }
    
    public final void setMessageFile(@org.jetbrains.annotations.NotNull()
    java.io.File p0) {
    }
    
    @com.velocitypowered.api.event.Subscribe()
    public final void onProxyInitialization(@org.jetbrains.annotations.Nullable()
    com.velocitypowered.api.event.proxy.ProxyInitializeEvent e) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001d\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020!0 \u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001d\u0010$\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%0 \u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010#\u00a8\u0006\'"}, d2 = {"Lcn/cyanbukkit/invatevelocity/CyanInvite$Companion;", "", "()V", "blockedMessagePlayers", "", "Lcom/velocitypowered/api/proxy/Player;", "getBlockedMessagePlayers", "()Ljava/util/Set;", "configModel", "Lcn/cyanbukkit/invatevelocity/utils/ConfigModel;", "getConfigModel", "()Lcn/cyanbukkit/invatevelocity/utils/ConfigModel;", "setConfigModel", "(Lcn/cyanbukkit/invatevelocity/utils/ConfigModel;)V", "hikariDataSource", "Lcom/zaxxer/hikari/HikariDataSource;", "getHikariDataSource", "()Lcom/zaxxer/hikari/HikariDataSource;", "setHikariDataSource", "(Lcom/zaxxer/hikari/HikariDataSource;)V", "instance", "Lcn/cyanbukkit/invatevelocity/CyanInvite;", "getInstance", "()Lcn/cyanbukkit/invatevelocity/CyanInvite;", "setInstance", "(Lcn/cyanbukkit/invatevelocity/CyanInvite;)V", "inviteRequests", "", "Lcn/cyanbukkit/invatevelocity/utils/InviteRequest;", "getInviteRequests", "()Ljava/util/List;", "playerCooldown", "", "", "getPlayerCooldown", "()Ljava/util/Map;", "serverAlias", "", "getServerAlias", "velocity"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final cn.cyanbukkit.invatevelocity.utils.ConfigModel getConfigModel() {
            return null;
        }
        
        public final void setConfigModel(@org.jetbrains.annotations.NotNull()
        cn.cyanbukkit.invatevelocity.utils.ConfigModel p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final cn.cyanbukkit.invatevelocity.CyanInvite getInstance() {
            return null;
        }
        
        public final void setInstance(@org.jetbrains.annotations.NotNull()
        cn.cyanbukkit.invatevelocity.CyanInvite p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Set<com.velocitypowered.api.proxy.Player> getBlockedMessagePlayers() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<cn.cyanbukkit.invatevelocity.utils.InviteRequest> getInviteRequests() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Map<java.lang.String, java.lang.String> getServerAlias() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.zaxxer.hikari.HikariDataSource getHikariDataSource() {
            return null;
        }
        
        public final void setHikariDataSource(@org.jetbrains.annotations.NotNull()
        com.zaxxer.hikari.HikariDataSource p0) {
        }
        
        /**
         * 记录上一次的喊话的时间戳 每次计算下次的统计
         */
        @org.jetbrains.annotations.NotNull()
        public final java.util.Map<com.velocitypowered.api.proxy.Player, java.lang.Long> getPlayerCooldown() {
            return null;
        }
    }
}