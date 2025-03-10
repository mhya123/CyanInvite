package cn.cyanbukkit.invatevelocity.listener;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\u0007\u00a8\u0006\t"}, d2 = {"Lcn/cyanbukkit/invatevelocity/listener/ChatListener;", "", "()V", "onChat", "", "e", "Lcom/velocitypowered/api/event/player/PlayerChatEvent;", "onJoin", "Lcom/velocitypowered/api/event/player/PlayerChooseInitialServerEvent;", "velocity"})
public final class ChatListener {
    @org.jetbrains.annotations.NotNull()
    public static final cn.cyanbukkit.invatevelocity.listener.ChatListener INSTANCE = null;
    
    private ChatListener() {
        super();
    }
    
    @com.velocitypowered.api.event.Subscribe(order = com.velocitypowered.api.event.PostOrder.EARLY)
    public final void onChat(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.event.player.PlayerChatEvent e) {
    }
    
    @com.velocitypowered.api.event.Subscribe(order = com.velocitypowered.api.event.PostOrder.EARLY)
    public final void onJoin(@org.jetbrains.annotations.NotNull()
    com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent e) {
    }
}