package cn.cyanbukkit.invatevelocity.commands.main

import cn.cyanbukkit.invatevelocity.CyanInvite
import cn.cyanbukkit.invatevelocity.CyanInvite.Companion.playerCooldown
import cn.cyanbukkit.invatevelocity.utils.sendI18n
import cn.cyanbukkit.invatevelocity.utils.sendInviteRequest
import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.Player
import net.kyori.adventure.text.Component

object CallInvite : SimpleCommand{
    override fun execute(p0: SimpleCommand.Invocation) {
        val now = System.currentTimeMillis()
        val cooldown = CyanInvite.configModel.cooldownTime * 1000L
        println("冷却值: $cooldown")
        if (p0.source() !is Player) {
            p0.source().sendMessage(Component.text(CyanInvite.instance.message.getString("messages.run-in-console")!!.replace("&", "§")))
            return
        }
        val send = p0.source() as Player
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



    fun Player.sendInvite() {
        val serverInfo = this.currentServer.get()
        if (CyanInvite.instance.config.getStringList("BlockedServers").contains(serverInfo.serverInfo.name)) {
            sendI18n("messages.in-blocked-server")
            return
        }
        sendInviteRequest(serverInfo.server)
        playerCooldown[this] = java.lang.System.currentTimeMillis()
    }


}