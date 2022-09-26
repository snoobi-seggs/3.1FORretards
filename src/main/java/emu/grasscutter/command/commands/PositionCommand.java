package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.utils.Position;

import java.util.List;

@Command(label = "position", aliases = {"pos"})
public final class PositionCommand implements CommandHandler {

    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        Position pos = targetPlayer.getPosition();
		Position rot = targetPlayer.getRotation();
		int sceneId = targetPlayer.getSceneId();
        CommandHandler.sendMessage(sender, "commands.position.success" + "\n\nposition = " + pos.getX() + " , " + pos.getY() + " , " + pos.getZ() + "\n\nSceneId = " + sceneId + "," + "\n\nrotation = " + rot.getX() + " , " + rot.getY() + " , " + rot.getZ());
    }
}
