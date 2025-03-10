package cn.cyanbukkit.invatebungee.command.main

import cn.cyanbukkit.invatebungee.CyanInvite
import cn.cyanbukkit.invatebungee.utils.sendI18n
import cn.cyanbukkit.invatebungee.utils.sendShout
import cn.cyanbukkit.mysql.getPlayerShouts
import cn.cyanbukkit.mysql.removePlayerShouts
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command

/**
 * 喇叭消息发送指令 /hh xxxxxx 无冷却消耗喇叭数量
 */
object HornMessage : Command(CyanInvite.configModel.shoutCommand) {
    override fun execute(sender: CommandSender, args: Array<out String>) {
        val p = if (sender is ProxiedPlayer) sender else return
        if (args.isEmpty()) {
            p.sendI18n("messages.shout-is-empty")
            return
        }
        val has = CyanInvite.hikariDataSource.getPlayerShouts(p.uniqueId)
        if (has >= 1) {
            CyanInvite.hikariDataSource.removePlayerShouts(p.uniqueId, 1)
            p.sendShout(p.server.info, args.joinToString(" "))
            val map = mutableMapOf<String, String>()
            map["%shouts%"] = CyanInvite.hikariDataSource.getPlayerShouts(p.uniqueId).toString()
            p.sendI18n("messages.shout-success",map)
        } else {
            p.sendI18n("messages.no-shouts")
        }
    }

}