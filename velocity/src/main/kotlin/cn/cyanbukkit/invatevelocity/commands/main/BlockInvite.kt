package cn.cyanbukkit.invatevelocity.commands.main

import cn.cyanbukkit.invatevelocity.CyanInvite
import cn.cyanbukkit.invatevelocity.utils.sendI18n
import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.Player
import net.kyori.adventure.text.Component

object BlockInvite : SimpleCommand {
    override fun execute(p0: SimpleCommand.Invocation) {
        if (p0.source() !is Player) {
            val sender = p0.source()
            sender.sendMessage(
                Component.text(
                    CyanInvite.instance.message.getString(
                    "messages.run-in-console")!!.replace("&", "§"))
                              )
            return
        }
        val sender = p0.source() as Player
        if (CyanInvite.blockedMessagePlayers.contains(sender)) {
            CyanInvite.blockedMessagePlayers.remove(sender)
            sender.sendI18n("messages.unblock-invite-message")
        } else {
            CyanInvite.blockedMessagePlayers.add(sender)
            sender.sendI18n("messages.block-invite-message")
        }
    }

}