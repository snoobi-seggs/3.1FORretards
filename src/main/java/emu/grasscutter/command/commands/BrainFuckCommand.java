package emu.grasscutter.command.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import emu.grasscutter.utils.Position;
import emu.grasscutter.server.packet.send.PacketSceneEntityAppearNotify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static java.util.Map.entry;

import static emu.grasscutter.utils.Language.translate;

@Command(label = "brainfuck", usage = "/bf {number of reset packets sent] [interval of packets sent] [radius from current pos]\n\n description : use it and then skill or burst to mimic melons ability to freeze", aliases = {"bf"}, permission = "player.spawn", permissionTargeted = "player.spawn.others")
public final class BrainFuckCommand implements CommandHandler {
	
	private Position GetRandomPosition(Position pos,int radius) {
		Position posNew = new Position();
		Random rand = new Random();
		//rand.nextInt(3) - 1 returns -1,0,1 for chance of (-)
		posNew.setX((float) (pos.getX() + (rand.nextInt(3)-1) * (Math.random() * radius)));
		posNew.setY((float) (pos.getY() + (rand.nextInt(3)-1) * (Math.random() * radius)));
		posNew.setZ((float) (pos.getZ() + (rand.nextInt(3)-1) * (Math.random() * radius)));
		return posNew;
	}
	
	@Override
	public void execute(Player sender, Player targetPlayer, List<String> args) {
		int interval = 250;
		int times = 50;
		int radius = 10;
		final Position pos = new Position(targetPlayer.getPosition().getX(),targetPlayer.getPosition().getY(),targetPlayer.getPosition().getZ());
		final Position rot = new Position(targetPlayer.getRotation().getX(),targetPlayer.getRotation().getY(),targetPlayer.getRotation().getZ());
		
		if (targetPlayer == null) {
			targetPlayer = sender;
		}
		
		switch (args.size()) {
			case 3:
				try {
					radius = Integer.parseInt(args.get(2));
				} catch (NumberFormatException ignored) {
					CommandHandler.sendMessage(sender,"invalid radius");
				}
            case 2:
                try {
                    interval = Integer.parseInt(args.get(1));
                } catch (NumberFormatException ignored) {
                    CommandHandler.sendMessage(sender, translate(sender, "invalid interval in ms"));
                }
            case 1:
                try {
                    times = Integer.parseInt(args.get(0));
                } catch (NumberFormatException ignored) {
                    CommandHandler.sendMessage(sender, translate(sender, "invalid times"));
                }
			case 0:
				break;
            default:
                this.sendUsageMessage(sender);
                return;
        }
		
		CommandHandler.sendMessage(sender,"brainfuck for " + times + " times with interval = " + interval + " ms, with radius = " + radius + " during which no other commands or packets can be sent due to my shitty ability to code");
		
		//Position targetPos = new Position(x,y,z);
		for (int i = 0;i < times;i++) {
			try {
				targetPlayer.getPosition().set(GetRandomPosition(pos,radius).getX(),GetRandomPosition(pos,radius).getY(),GetRandomPosition(pos,radius).getZ());
				targetPlayer.getRotation().set(GetRandomPosition(rot,360).getX(),GetRandomPosition(rot,360).getY(),GetRandomPosition(rot,360).getZ());
				targetPlayer.getScene().broadcastPacket(new PacketSceneEntityAppearNotify(targetPlayer));
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				;
			}
		}
		// return to original
		targetPlayer.getPosition().set(pos.getX(),pos.getY(),pos.getZ());
		targetPlayer.getRotation().set(rot.getX(),rot.getY(),rot.getZ());
		targetPlayer.getScene().broadcastPacket(new PacketSceneEntityAppearNotify(targetPlayer));
		CommandHandler.sendMessage(sender,"returned to original pos");
	}
}

