package com.blisssmp1.commands;

import com.blisssmp1.gems.GemFactory;
import com.blisssmp1.gems.GemType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

/*
 This class handles the command that the player sends in the chat: "/givegem <type>"
 The class will read the type, convert it into a GemType, generate the corresponding
 gem using the GemFactory, and add it to the player's inventory.
 */

// By implementing CommandExecutor, Bukkit knows to run this class when the /givegem command is used.
public class GiveGemCommand implements CommandExecutor {

    /*
    sender: person sending the command.
    command: the command being executed (e.g., givegem)
    label: the actual command name (could be an alias)
    args: the list of arguments -- args[0] should be the gem type liked "fire"
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Prevents command usage from non-player command senders such as console or command blocks.
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        // If the player didn't provide a gem type argument, we show usage instructions.
        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /givegem <type>");
            return true;
        }

        // Converts the player's type input to a GemType enum and returns an
        // Optional<GemType> so we can safely check if it matched something.
        String typeArg = args[0].toLowerCase();
        Optional<GemType> gemType = GemType.fromId(typeArg);

        // Handling invalid gem names.
        if (gemType.isEmpty()) {
            player.sendMessage(ChatColor.RED + "Unknown gem type: " + typeArg);
            return true;
        }

        // Uses GemFactory to create a gem of the requested type and puts it in the
        // player's inventory.
        player.getInventory().addItem(GemFactory.createGem(gemType.get()));

        // Player gets feedback confirming which gem they received.
        player.sendMessage(ChatColor.GREEN + "You received the " + gemType.get().getDisplayName());
        return true;
    }
}
