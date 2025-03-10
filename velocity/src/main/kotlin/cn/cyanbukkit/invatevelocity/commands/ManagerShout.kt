package cn.cyanbukkit.invatevelocity.commands

import cn.cyanbukkit.invatevelocity.CyanInvite
import cn.cyanbukkit.invatevelocity.utils.sendMess
import cn.cyanbukkit.mysql.addPlayerShouts
import cn.cyanbukkit.mysql.getPlayerShouts
import cn.cyanbukkit.mysql.removePlayerShouts
import cn.cyanbukkit.mysql.setPlayerShouts
import com.velocitypowered.api.command.SimpleCommand
import net.kyori.adventure.text.Component

object ManagerShout : SimpleCommand{
    override fun execute(p0: SimpleCommand.Invocation) {
        if (p0.arguments().size < 2 && p0.source().hasPermission("cyaninvite.admin")) {
            return
        }
        val args = p0.arguments()
        val sender = p0.source()
        when (args[0]) {
            "give" -> {
                if (args.size < 3) {
                    sender.sendMess("§c/mshout give <player> <amount>")
                    return
                }
                val player = (CyanInvite.instance.server.getPlayer(args[1]) ?: run {
                    sender.sendMess("§cPlayer not found")
                    return
                }).get()
                val amount = args[2].toIntOrNull() ?: run {
                    sender.sendMess("§c/mshout give <player> <amount>")
                    return
                }
                CyanInvite.hikariDataSource.addPlayerShouts(player.uniqueId, player.username, amount)
                sender.sendMess("§aSuccess")
            }
            "set" -> {
                if (args.size < 3) {
                    sender.sendMess("§c/mshout set <player> <amount>")
                    return
                }
                val player = (CyanInvite.instance.server.getPlayer(args[1]) ?: run {
                    sender.sendMess("§cPlayer not found")
                    return
                }).get()
                val amount = args[2].toIntOrNull() ?: run {
                    sender.sendMess("§c/mshout set <player> <amount>")
                    return
                }
                // amount 不能是负数如果是负数不设置
                if (amount < 0) {
                    sender.sendMess("§c不能设置负数")
                    return
                }
                CyanInvite.hikariDataSource.setPlayerShouts(player.uniqueId,amount)
                sender.sendMess("已经设置玩家 ${player.username} 的喊话次数为 $amount")
            }
            "take" -> {
                // /mshout take <player> <amount>
                if (args.size < 3) {
                    sender.sendMess("§c/mshout take <player> <amount>")
                    return
                }
                val player = (CyanInvite.instance.server.getPlayer(args[1]) ?: run {
                    sender.sendMess("§cPlayer not found")
                    return
                }).get()
                val amount = args[2].toIntOrNull() ?: run {
                    sender.sendMess("§c/mshout take <player> <amount>")
                    return
                }
                val has = CyanInvite.hikariDataSource.getPlayerShouts(player.uniqueId)
                if (has < amount) {
                    sender.sendMess("§c玩家 ${player.username} 的喊话次数不足")
                    return
                }
                CyanInvite.hikariDataSource.removePlayerShouts(player.uniqueId, amount)
                sender.sendMess("已经扣除玩家 ${player.username} 的喊话次数 $amount")
            }
            "look" -> {
                // /mshout look <player>
                val player = (CyanInvite.instance.server.getPlayer(args[1]) ?: run {
                    sender.sendMess("§cPlayer not found")
                    return
                }).get()
                val has = CyanInvite.hikariDataSource.getPlayerShouts(player.uniqueId)
                sender.sendMess("§a玩家 ${player.username} 的喊话次数为 $has")
            }
            else -> {
                sender.sendMess("§c/mshout give/set/take/look <player> <amount>")
            }
        }
    }
}