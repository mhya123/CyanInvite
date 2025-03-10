package cn.cyanbukkit.invatevelocity.commands.main

import cn.cyanbukkit.invatevelocity.CyanInvite
import cn.cyanbukkit.invatevelocity.utils.sendI18n
import cn.cyanbukkit.invatevelocity.utils.sendShout
import cn.cyanbukkit.mysql.getPlayerShouts
import cn.cyanbukkit.mysql.removePlayerShouts
import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.Player

object HornMessage : SimpleCommand {
    override fun execute(p0: SimpleCommand.Invocation) {
        val args = p0.arguments()
        val sender = p0.source()
        val p = if (sender is Player) sender else return
        if (args.isEmpty()) {
            p.sendI18n("messages.shout-is-empty")
            return
        }
        val has = CyanInvite.hikariDataSource.getPlayerShouts(p.uniqueId)
        if (has >= 1) {
            CyanInvite.hikariDataSource.removePlayerShouts(p.uniqueId, 1)
            p.sendShout(p.currentServer.get().server, args.joinToString(" "))
            val map = mutableMapOf<String, String>()
            map["%shouts%"] = CyanInvite.hikariDataSource.getPlayerShouts(p.uniqueId).toString()
            p.sendI18n("messages.shout-success",map)
        } else {
            p.sendI18n("messages.no-shouts")
        }
    }
}