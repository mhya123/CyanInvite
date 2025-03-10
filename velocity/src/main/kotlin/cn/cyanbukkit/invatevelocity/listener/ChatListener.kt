package cn.cyanbukkit.invatevelocity.listener

import cn.cyanbukkit.invatevelocity.CyanInvite
import cn.cyanbukkit.invatevelocity.utils.isSendBlocked
import cn.cyanbukkit.invatevelocity.utils.sendI18n
import cn.cyanbukkit.mysql.addPlayerShouts
import com.velocitypowered.api.event.PostOrder
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.PlayerChatEvent
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent

object ChatListener {

    @Subscribe(order = PostOrder.EARLY)
    fun onChat(e: PlayerChatEvent) {
        val message = e.message
        val send = e.player
        if (message.isSendBlocked()) {
            send.sendI18n("blocked-message")
            e.result = PlayerChatEvent.ChatResult.denied() // 关了
        }
    }


    @Subscribe(order = PostOrder.EARLY)
    fun onJoin(e: PlayerChooseInitialServerEvent) {
        val p = e.player
        CyanInvite.hikariDataSource.addPlayerShouts(p.uniqueId, p.username, CyanInvite.configModel.defaultShout)
    }

}