package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.entity.EntityItem;
import emu.grasscutter.game.entity.GameEntity;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.world.Scene;
import emu.grasscutter.net.proto.VisionTypeOuterClass;

import java.util.List;

@Command(label = "killdroppeditems", usage = "killdroppeditems to remove all dropped items on floor", aliases = {"kdi","kd","removeitems"})
public final class KillDroppedItemsCommand implements CommandHandler {
    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        Scene scene = targetPlayer.getScene();
        List<GameEntity> items = scene.getEntities().values().stream().filter(gameEntity -> gameEntity instanceof EntityItem).toList();
        scene.removeEntities(items, VisionTypeOuterClass.VisionType.VISION_TYPE_REMOVE);
        CommandHandler.sendMessage(sender, "successfully removed " + items.size() + " items");
    }
}
