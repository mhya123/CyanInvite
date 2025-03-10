package cn.cyanbukkit.invatevelocity.commands

import cn.cyanbukkit.invatevelocity.CyanInvite
import cn.cyanbukkit.invatevelocity.utils.sendI18n
import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.Player
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver.Single

object InviteTeleport : SimpleCommand {
    override fun execute(si: SimpleCommand.Invocation) {
        if (si.source() !is Player) {
            return
        }
        val sender = si.source() as Player
        //检查是否在不允许传送的服务器中
        for (blockedServer in CyanInvite.instance.config.getStringList("BlockedServers")) {
            if (sender.currentServer.get().serverInfo.name == blockedServer) {
                sender.sendI18n("messages.in-blocked-server")
                return
            }
        }
        //  `
        // itp <token> 该命令不允许玩家手动执行，无需帮助提示
        if (si.arguments().size != 1) {
            return
        }
        val args = si.arguments()
        val token: String = args[0]
        val optionalInviteRequest = CyanInvite.inviteRequests.find { it.token.toString() == token }
        if (optionalInviteRequest == null) {
            sender.sendI18n("messages.invitation-expired")
            return
        }
        val serverInfo = optionalInviteRequest.serverInfo
        val serverName = serverInfo.serverInfo.name
        if (CyanInvite.instance.config.getStringList("NeedPermissionServers").contains(serverName) && !sender.hasPermission("cyanin.tp.$serverName")) {
            sender.sendI18n("messages.no-permission")
            return
        }
        val reg = CyanInvite.instance.server.getServer(serverName).get()
        sender.createConnectionRequest(reg).connect()
    }
}