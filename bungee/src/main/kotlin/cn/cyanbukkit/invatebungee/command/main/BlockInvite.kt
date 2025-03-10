package cn.cyanbukkit.invatebungee.command.main

import cn.cyanbukkit.invatebungee.CyanInvite
import cn.cyanbukkit.invatebungee.utils.sendI18n
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command

/**
 * 阻止/显示喊话邀请的消息
 */
object BlockInvite : Command(CyanInvite.configModel.blockCommand) {
    override fun execute(sender: CommandSender, args: Array<out String>) {
        if (sender !is ProxiedPlayer) {
            sender.sendMessage(TextComponent(CyanInvite.instance.message.getString(
                "messages.run-in-console").replace("&", "§")))
            return
        }


        if (CyanInvite.blockedMessagePlayers.contains(sender)) {
            CyanInvite.blockedMessagePlayers.remove(sender)
            sender.sendI18n("messages.unblock-invite-message")
        } else {
            CyanInvite.blockedMessagePlayers.add(sender)
            sender.sendI18n("messages.block-invite-message")
        }
    }
}