package dev.sakkke.bedforce

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Bedforce: JavaPlugin() {
    override fun onEnable() {
        logger.info("Bedforce enabled")
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        if (cmd.name == "bedforce" || cmd.name == "bdf" || cmd.name == "b") {
            if (sender is Player) {
                val inventory = sender.inventory
                val hasBed = inventory.contents.any { itemStack ->
                    itemStack?.type.toString().endsWith("_BED")
                }

                if (hasBed) {
                    if (sender.world.time >= 13000 || sender.world.isThundering) {
                        sender.sendMessage("Changing the time to day")
                        sender.world.time = 1000
                    } else {
                        sender.sendMessage("It's not night")
                    }
                } else {
                    sender.sendMessage("You need a bed in your inventory")
                }
            } else {
                sender.sendMessage("Changing the time to day in all worlds")
                server.worlds.forEach { world ->
                    world.time = 1000
                }
            }
            return true
        }
        return false
    }
}