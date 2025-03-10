package cn.cyanbukkit.invatevelocity.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00000\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0004\u001a(\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00042\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\t\u001a\u0012\u0010\n\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f\u001a\u0012\u0010\r\u001a\u00020\u0006*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004\u001a\u001a\u0010\u0010\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0004\u00a8\u0006\u0012"}, d2 = {"canShowSendMessage", "", "Lcom/velocitypowered/api/proxy/Player;", "isSendBlocked", "", "sendI18n", "", "key", "map", "", "sendInviteRequest", "serverName", "Lcom/velocitypowered/api/proxy/server/RegisteredServer;", "sendMess", "Lcom/velocitypowered/api/command/CommandSource;", "str", "sendShout", "message", "velocity"})
public final class PlayerOtherKt {
    
    /**
     * 发送国际化消息
     */
    public static final void sendI18n(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.proxy.Player $this$sendI18n, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> map) {
    }
    
    /**
     * 发送喊话给全服的玩家
     */
    public static final void sendShout(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.proxy.Player $this$sendShout, @org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.proxy.server.RegisteredServer serverName, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    /**
     * 发送邀请给全服的玩家
     */
    public static final void sendInviteRequest(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.proxy.Player $this$sendInviteRequest, @org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.proxy.server.RegisteredServer serverName) {
    }
    
    public static final void sendMess(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.command.CommandSource $this$sendMess, @org.jetbrains.annotations.NotNull()
    java.lang.String str) {
    }
    
    /**
     * 可以显示 邀请和喇叭的 消息
     */
    public static final boolean canShowSendMessage(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.proxy.Player $this$canShowSendMessage) {
        return false;
    }
    
    /**
     * 发的文字是否包含敏感词
     */
    public static final boolean isSendBlocked(@org.jetbrains.annotations.NotNull()
    java.lang.String $this$isSendBlocked) {
        return false;
    }
}