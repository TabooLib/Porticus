package ink.ptms.porticus.bukkitside

import ink.ptms.porticus.api.PorticusAPI
import io.izzel.taboolib.module.command.base.BaseCommand
import io.izzel.taboolib.module.command.base.BaseMainCommand
import io.izzel.taboolib.module.command.base.SubCommand
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit

/**
 * Porticus
 * ink.ptms.porticus.bukkitside.PorticusCommand
 *
 * @author sky
 * @since 2021/2/3 6:22 下午
 */
@BaseCommand(name = "porticus", permission = "admin")
class PorticusCommand : BaseMainCommand() {

    @SubCommand(description = "传送到服务器", arguments = ["服务器", "玩家?"])
    fun connect(sender: CommandSender, args: Array<String>) {
        if (args.size == 1) {
            if (sender is Player) {
                PorticusAPI.getAPI().createMission()
                    .command("porticus", "connect", sender.name, args[0])
                    .run(sender)
            } else {
                PorticusAPI.getAPI().createMission()
                    .command("porticus", "connect", args[1], args[0])
                    .run(sender)
            }
        }
    }

    @SubCommand(description = "检查玩家", arguments = ["玩家"])
    fun whois(sender: CommandSender, args: Array<String>) {
        val player = Bukkit.getOnlinePlayers().firstOrNull()
        if (player == null) {
            sender.sendMessage("§c[Porticus] §7这个命令需要有玩家在线才能执行.")
            return
        }
        PorticusAPI.getAPI().createMission()
            .timeout(10, TimeUnit.SECONDS)
            .command("porticus", "whois", args[0])
            .onResponse {
                sender.sendMessage("§c[Porticus] §7玩家 ${args[0]} 当前位于 ${it[0]} 服务器.")
            }.run(player)
    }
}