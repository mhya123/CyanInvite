package cn.cyanbukkit.invatespigot

import cn.cyanbukkit.invatespigot.cyanlib.launcher.CyanPluginLauncher.cyanPlugin
import cn.cyanbukkit.mysql.addPlayerShouts
import cn.cyanbukkit.mysql.getPlayerShouts
import cn.cyanbukkit.mysql.removePlayerShouts
import cn.cyanbukkit.mysql.setPlayerShouts
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

object SpigotShoutInvite : Command("ssi") {



    init {
        permission = "cyaninvite.admin"
    }


    override fun execute(sender: CommandSender, p1: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("§c参数错误 /ssi give/set/take/look <player> <number>")
            return true
        }
        when (args[0]) {
            "give" -> {
                if (args.size < 3) {
                    sender.sendMessage("§c/ssi give <player> <amount>")
                    return true
                }
                val player = Bukkit.getPlayer(args[1]) ?: run {
                    sender.sendMessage("§cPlayer not found")
                    return true
                }
                val amount = args[2].toIntOrNull() ?: run {
                    sender.sendMessage("§c/ssi give <player> <amount>")
                    return true
                }
                cyanPlugin.hikariDataSource.addPlayerShouts(player.uniqueId, player.name, amount)
                player.sendMessage("§a你的喊话次数增加了 $amount 次")
            }
            "set" -> {
                if (args.size < 3) {
                    sender.sendMessage("§c/ssi set <player> <amount>")
                    return true
                }
                val player = Bukkit.getPlayer(args[1]) ?: run {
                    sender.sendMessage("§cPlayer not found")
                    return true
                }
                val amount = args[2].toIntOrNull() ?: run {
                    sender.sendMessage("§c/ssi set <player> <amount>")
                    return true
                }
                if (amount < 0) {
                    sender.sendMessage("§c不能设置负数")
                    return true
                }
                cyanPlugin.hikariDataSource.setPlayerShouts(player.uniqueId, amount)
                player.sendMessage("§a你的喊话次数设置为 $amount 次")
            }
            "take" -> {
                if (args.size < 3) {
                    sender.sendMessage("§c/ssi take <player> <amount>")
                    return true
                }
                val player = Bukkit.getPlayer(args[1]) ?: run {
                    sender.sendMessage("§cPlayer not found")
                    return true
                }
                val amount = args[2].toIntOrNull() ?: run {
                    sender.sendMessage("§c/ssi take <player> <amount>")
                    return true
                }
                val has = cyanPlugin.hikariDataSource.getPlayerShouts(player.uniqueId)
                if (has < amount) {
                    player.sendMessage("§c你的喊话次数不足")
                    return true
                }
                cyanPlugin.hikariDataSource.removePlayerShouts(player.uniqueId,  amount)
                player.sendMessage("§a你的喊话次数减少了 $amount 次")
            }
            "look" -> {
                // /mshout look <player>
                val player = Bukkit.getPlayer(args[1]) ?: run {
                    sender.sendMessage("§cPlayer not found")
                    return true
                }
                val has = cyanPlugin.hikariDataSource.getPlayerShouts(player.uniqueId)
                player.sendMessage("§a你的喊话次数为 $has 次")
            }
            else -> {
                sender.sendMessage("§c参数错误 /ssi give/set/take/look <player> <number>")
            }
        }
        return true
    }
}