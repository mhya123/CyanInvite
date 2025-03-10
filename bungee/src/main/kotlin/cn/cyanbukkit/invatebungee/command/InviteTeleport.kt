package cn.cyanbukkit.invatebungee.command

import cn.cyanbukkit.invatebungee.CyanInvite
import cn.cyanbukkit.invatebungee.utils.sendI18n
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command


object InviteTeleport : Command("itp") {
    override fun execute(sender: CommandSender, args: Array<out String>) {
        if (sender !is ProxiedPlayer) {
            return
        }
        //检查是否在不允许传送的服务器中
        for (blockedServer in CyanInvite.instance.config.getStringList("BlockedServers")) {
            if (sender.server.info.name == blockedServer) {
                sender.sendI18n("messages.in-blocked-server")
                return
            }
        }
        //itp <token> 该命令不允许玩家手动执行，无需帮助提示
        if (args.size != 1) {
            return
        }
        val token: String = args[0]
        val optionalInviteRequest  = CyanInvite.inviteRequests.find { it.token.toString() == token }
        if (optionalInviteRequest == null) {
            sender.sendI18n("messages.invitation-expired")
            return
        }
        val serverInfo = optionalInviteRequest.serverInfo
        val serverName = serverInfo.name
        if (CyanInvite.instance.config.getStringList("NeedPermissionServers").contains(serverName) && !sender.hasPermission("cyanin.tp.$serverName")) {
            sender.sendI18n("messages.no-permission")
            return
        }
        sender.connect(serverInfo)
    }
}

