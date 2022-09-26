package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.GadgetData;
import emu.grasscutter.data.excels.ItemData;
import emu.grasscutter.data.excels.MonsterData;
import emu.grasscutter.game.entity.*;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.FightProperty;
import emu.grasscutter.utils.Position;
import emu.grasscutter.game.world.Scene;

import java.util.List;

import static emu.grasscutter.config.Configuration.*;
import static emu.grasscutter.utils.Language.translate;

@Command(
    label = "windy",
    usage = {"windy <element> <amount>\n[all element names + paimon + army]"},
    aliases = {"burn","bih","hell","fuck","w","wind","windshitclientnotify"},
    permission = "server.spawn",
    permissionTargeted = "server.spawn.others")
public final class WindyCommand implements CommandHandler {

    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        int id = 0;  // This is just to shut up the linter, it's not a real default
		String element = "electro";
		int amount = 1;
		Position rot = targetPlayer.getRotation();
		
        switch (args.size()) {
            case 2:
				//get amount
                try {
                    amount = Integer.parseInt(args.get(1));
					CommandHandler.sendMessage(sender, "amount = " + amount);
                } catch (NumberFormatException ignored) {
                    CommandHandler.sendMessage(sender, translate(sender, "commands.generic.invalid.amount"));
                }  // Fallthrough
            case 1:
				//get element
				element = args.get(0).toString();
				if (element.equals("electro") || element.equals("pyro") || element.equals("hydro") || element.equals("geo") || element.equals("cryo") || element.equals("dendro") || element.equals("anemo") || element.equals("paimon") || element.equals("army")) {
					CommandHandler.sendMessage(sender, "bombing with " + element + " element");
				} else {
					CommandHandler.sendMessage(sender,"attempted to use " + element + " but is not of the right type");
				}
			case 0:
				CommandHandler.sendMessage(sender,"windy!");
				break;
            default:
                sendUsageMessage(sender);
				return;
        }
		
		Scene scene = targetPlayer.getScene();
		
		GameEntity entity = null;
		if (element.equals("electro")) {
			for (int i = 0; i < amount; i++) {
				Position pos = GetRandomPositionInCircle(targetPlayer.getPosition(),10).addY(0);
				entity = new EntityVehicle(scene,targetPlayer,41058009,0,pos,targetPlayer.getRotation());
				scene.addEntity(entity);
			}
		} else if (element.equals("dendro")) {
			for (int i = 0; i < amount; i++) {
				Position pos = GetRandomPositionInCircle(targetPlayer.getPosition(),5).addY(0);
				entity = new EntityVehicle(scene,targetPlayer,41067021,0,pos,targetPlayer.getRotation());
				scene.addEntity(entity);
			}
		} else if (element.equals("paimon")) {
			for (int i = 0; i < amount; i++) {
				Position pos = GetRandomPositionInCircle(targetPlayer.getPosition(),5).addY(0);
				entity = new EntityVehicle(scene,targetPlayer,90000003,0,pos,targetPlayer.getRotation());
				scene.addEntity(entity);
			}
		} else if (element.equals("pyro")) {
			for (int i = 0; i < amount; i++) {
				Position pos = GetRandomPositionInCircle(targetPlayer.getPosition(),10).addY(0);
				entity = new EntityVehicle(scene,targetPlayer,42904012,0,pos,targetPlayer.getRotation());
				scene.addEntity(entity);
			}
		} else if (element.equals("hydro")) {
			for (int i = 0; i < amount; i++) {
				Position pos = GetRandomPositionInCircle(targetPlayer.getPosition(),10).addY(0);
				entity = new EntityVehicle(scene,targetPlayer,42906119,0,pos,targetPlayer.getRotation());
				scene.addEntity(entity);
			}
		} else if (element.equals("geo")) {
			for (int i = 0; i < amount; i++) {
				Position pos = GetRandomPositionInCircle(targetPlayer.getPosition(),20).addY(0);
				entity = new EntityVehicle(scene,targetPlayer,45001001,0,pos,targetPlayer.getRotation());
				scene.addEntity(entity);
			}
		} else if (element.equals("anemo")) {
			for (int i = 0; i < amount; i++) {
				Position pos = GetRandomPositionInCircle(targetPlayer.getPosition(),7).addY(0);
				entity = new EntityVehicle(scene,targetPlayer,41023011,0,pos,targetPlayer.getRotation());
				scene.addEntity(entity);
			}
		} else if (element.equals("cryo")) {
			for (int i = 0; i < amount; i++) {
				Position pos = GetRandomPositionInCircle(targetPlayer.getPosition(),10).addY(0);
				entity = new EntityVehicle(scene,targetPlayer,70340011,0,pos,targetPlayer.getRotation());
				scene.addEntity(entity);
			}
		} else if (element.equals("army")) {
			for (int i = 0; i < amount; i++) {
				Position pos = GetRandomPositionInCircle(targetPlayer.getPosition(),4).addY(0);
				entity = new EntityVehicle(scene,targetPlayer,35210101,0,pos,targetPlayer.getRotation());
				scene.addEntity(entity);
			}
		} 
	}

    private Position GetRandomPositionInCircle(Position origin, double radius) {
        Position target = origin.clone();
        double angle = Math.random() * 360;
        double r = Math.sqrt(Math.random() * radius * radius);
        target.addX((float) (r * Math.cos(angle))).addZ((float) (r * Math.sin(angle)));
        return target;
    }
}
