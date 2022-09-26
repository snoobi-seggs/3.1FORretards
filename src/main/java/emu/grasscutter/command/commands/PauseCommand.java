package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import emu.grasscutter.server.packet.send.PacketPlayerSetPauseRsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

import static emu.grasscutter.utils.Language.translate;

@Command(label = "pause", usage = "/pause", aliases = {"p","freeze"}, permission = "player.spawn", permissionTargeted = "player.spawn.others")
public final class PauseCommand implements CommandHandler {
	@Override
	public void execute(Player sender, Player targetPlayer, List<String> args) {
		
		if (targetPlayer == null) {
			targetPlayer = sender;
		}
		
		targetPlayer.sendPacket(new PacketPlayerSetPauseRsp(1));
		targetPlayer.setPaused(true);
		/*if (targetPlayer.paused == true) {
			targetPlayer.setPaused(false);
		} else if (targetPlayer.paused == false) {
			targetPlayer.setPaused(true);
		}*/
		
		CommandHandler.sendMessage(sender,"paused state = " + "true");
		CommandHandler.sendMessage(sender,"this command is bugged and is useless since i hate my life");
	}
}

