package cn.cyanbukkit.invatebungee.command.main

import cn.cyanbukkit.invatebungee.CyanInvite
import cn.cyanbukkit.invatebungee.CyanInvite.Companion.playerCooldown
import cn.cyanbukkit.invatebungee.utils.sendI18n
import cn.cyanbukkit.invatebungee.utils.sendInviteRequest
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command

/**
 * 有冷却 不消耗喇叭
 */
object CallInvite : Command(CyanInvite.configModel.inviteCommand) {
    override fun execute(send: CommandSender, args: Array<out String>?) {
        val now = System.currentTimeMillis()
        val cooldown = CyanInvite.configModel.cooldownTime * 1000L
        println("冷却值: $cooldown")
        if (send !is ProxiedPlayer) {
            send.sendMessage(TextComponent(CyanInvite.instance.message.getString("messages.run-in-console").replace("&", "§")))
            return
        }
        if (CyanInvite.playerCooldown.contains(send)) {
            val x = now - CyanInvite.playerCooldown[send]!!
            println("距离上次邀请已经过去了 $x 毫秒")
            if (x < cooldown) {
                CyanInvite.blockedMessagePlayers.remove(send)
                val map = mapOf("timeleft" to ((cooldown - x) / 1000).toString())
                send.sendI18n("messages.in-cooldown", map)
                return
            } else {
                send.sendInvite()
            }
        } else {
            send.sendInvite()
        }
    }



    fun ProxiedPlayer.sendInvite() {
        val serverInfo = server.info
        if (CyanInvite.instance.config.getStringList("BlockedServers").contains(serverInfo.name)) {
            sendI18n("messages.in-blocked-server")
            return
        }
        sendInviteRequest(serverInfo)
        playerCooldown[this] = System.currentTimeMillis()
    }
}