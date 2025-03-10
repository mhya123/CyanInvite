package cn.cyanbukkit.invatebungee.listener

import cn.cyanbukkit.mysql.addPlayerShouts
import cn.cyanbukkit.invatebungee.CyanInvite
import cn.cyanbukkit.invatebungee.utils.isSendBlocked
import cn.cyanbukkit.invatebungee.utils.sendI18n
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.ChatEvent
import net.md_5.bungee.api.event.LoginEvent
import net.md_5.bungee.api.event.ServerConnectedEvent
import net.md_5.bungee.api.plugin.Listener
import net.md_5.bungee.event.EventHandler

object ChatListener : Listener {

    @EventHandler
    fun ocC(e: ChatEvent) {
        val message = e.message
        val send = e.sender
        if (send is ProxiedPlayer) {
            if (message.isSendBlocked()) {
                send.sendI18n("blocked-message")
                e.isCancelled = true
            }
        }
    }




    @EventHandler
    fun on(e : ServerConnectedEvent) {
        val p =  e.player
        CyanInvite.hikariDataSource.addPlayerShouts(p.uniqueId, p.name, CyanInvite.configModel.defaultShout)
    }


}