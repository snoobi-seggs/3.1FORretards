package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.command.commands.SpawnCommand;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.utils.Position;
import emu.grasscutter.server.packet.send.PacketSceneEntityAppearNotify;

import java.util.List;
import java.util.concurrent.Executors;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Command usage
@Command(label = "mobwave", aliases = {"mw","ehe"} , usage = "mw [start/end/stop] [no.Mobs] [no.waves] [lvl.mobs] [radius] [icd.milliseconds]")
public class MobWaveCommand implements CommandHandler {

    int n = 0;
    static List<String> mobs = List.of("20010101", "20010201", "20010301", "20010401", "20010501", "20010601", "20010701", "20010801", "20010901", "20011001", "20011101", "20011201", "20011301", "20011401", "20011501", "20020101", "20040101", "20040201", "20040301", "20040401", "20040501", "20040601", "20050102", "20050201", "20050301", "20050401", "20050501", "20050601", "20050701", "20050801", "20050901", "20060101", "20060201", "20060301", "20060401", "20060501", "20060601", "26120301", "26120201", "26120101", "20070101", "21010101", "21010201", "21010301", "21010401", "21010501", "21010601", "21010701", "21010901", "21011001", "21011201", "21011301", "21011401", "21011501", "21011601", "21020101", "21020201", "21020301", "21020401", "21020501", "21020601", "21020701", "21020801", "21030101", "21030201", "21030301", "21030401", "21030501", "21030601", "22010101", "22010201", "22010301", "22010401", "22020101", "22030101", "22030201", "22040101", "22040201", "22050101", "22050201", "22060101", "22070101", "22070201", "22070301", "22080101", "22090101", "23010101", "23010201", "23010301", "23010401", "23010501", "23010601", "23020101", "23020102", "23030101", "23040101", "23050101", "24010101", "24010201", "24010301", "24010401", "24020101", "24020201", "24020301", "24020401", "24030201", "24030101", "25010101", "25010102", "25010103", "25010201", "25010301", "25010401", "25010501", "25010601", "25010701", "25020101", "25020102", "25020201", "25030101", "25030201", "25030301", "25040101", "25050101", "25050201", "25050301", "25050401", "25050501", "25060101", "25070101", "25070202", "25080101", "25080201", "25080301", "25080401", "25100101", "25100201", "25100301", "25100401", "26010101", "26010102", "26010103", "26010104", "26010201", "26010301", "26020101", "26020201", "26020301", "26030101", "26040101", "26040102", "26040103", "26040104", "26040105", "26050101", "26050201", "26050301", "26050401", "26050501", "26050601", "26050701", "26050801", "26050901", "26051001", "26051101", "26060101", "26060201", "26060301", "26080101", "26090101", "26110101", "29010101", "29020101", "29020102", "29030101", "29030102", "29030103", "29050101", "29050102", "29060101", "29060102", "29060201", "31010101", "31020101", "31020201", "25210204", "25210105", "25210306", "25210504", "25310201"); // Default null list of mobs
	
    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        // Defaults for simple start
		String state = "start";
        int nuMobs = 3;  // Placeholder # of mobs spawned per wave
		int nuWaves = 100;  // Placeholder # of waves 
        int lvMobs = 100; // Placeholder level of monsters spawned
		float radius = 6f;// Placeholder radius of tp and spawns
		float icd = 500f;   // Placeholder teleport and switch char cd in milliseconds
		final Position pos = new Position(targetPlayer.getPosition().getX(),targetPlayer.getPosition().getY(),targetPlayer.getPosition().getZ()); //for reference to base place
		final Position rot = new Position(targetPlayer.getRotation().getX(),targetPlayer.getRotation().getY(),targetPlayer.getRotation().getZ()); //we sure dont want char wondering frm base pos
		
		//catch args for parems yey
		switch (args.size()) {
			case 6:
				try {
					icd = Float.parseFloat(args.get(5));
				} catch (NumberFormatException e) {
					CommandHandler.sendMessage(sender,"invalid interval of tp");
				}
			case 5:
				try {
					radius = Float.parseFloat(args.get(4));
				} catch (NumberFormatException e) {
					CommandHandler.sendMessage(sender,"invalid radius of tp and spawns");
				}
			case 4:
				try {
					lvMobs = Integer.parseInt(args.get(2));
				} catch (NumberFormatException ignored) {
					CommandHandler.sendMessage(sender,"invalid level");
				}
			case 3:
				try {
					nuWaves = Integer.parseInt(args.get(3));
				} catch (NumberFormatException e) {
					CommandHandler.sendMessage(sender,"invalid number of waves");
				}
            case 2:
                try {
                    nuMobs = Integer.parseInt(args.get(1));
                } catch (NumberFormatException ignored) {
                    CommandHandler.sendMessage(sender, "invalid number of Mobs per interval");
                }
            case 1:
				if (args.get(0).toLowerCase().equals("start") || args.get(0).toLowerCase().equals("stop") || args.get(0).toLowerCase().equals("end")) {
                    state = args.get(0).toLowerCase();
                } else {
					CommandHandler.sendMessage(sender,"invalid state");
				}
				break;
			case 0:
				CommandHandler.sendMessage(sender,"used default settings of start,10,10,100,2f,5f");
				break;
            default:
                CommandHandler.sendMessage(sender,"invalid args ew");
				this.sendUsageMessage(sender);
                return;
        }
		
		//target catch incase idk
		if (targetPlayer == null) {
			targetPlayer = sender;
		}
		
		//tp to 0 0 0 for now
		//targetPlayer.getPosition().set(0,0,0);                                                       //DEFAULT PLACE FOR FIGHT
		//targetPlayer.getWorld().transferPlayerToScene(targetPlayer,3,targetPlayer.getPosition());    //IF NEED TP TO NEW SCENE
		targetPlayer.getScene().broadcastPacket(new PacketSceneEntityAppearNotify(targetPlayer));      //TRANSFER TO CURRENT POS AFTER DONE
		

		
        List<String> clistMobs = mobs;
		
		//finals coz java demands lambda snoo to have final vars
		final int nuMobsFinal = nuMobs;
		final int nuWavesFinal = nuWaves;
		final int lvMobsFinal = lvMobs;
		final float radiusFinal = radius;
		final float icdFinal = icd;
		final Player targetPlayerFinal = targetPlayer;
		
			
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
			
				//combined if (!checkWave(nuWaves)) { //CHECKS FOR BASECASE
				if ((n >= nuWavesFinal)) {	
					targetPlayerFinal.getPosition().set(pos.getX(),pos.getY(),pos.getZ());
					executor.shutdown();
					CommandHandler.sendMessage(targetPlayerFinal, "Custom waves finished.");
					n = 0;
					return;
				}
			
				//slide arnd
				targetPlayerFinal.getPosition().set(GetRandomPosition(pos,radiusFinal).getX(),targetPlayerFinal.getPosition().getY(),GetRandomPosition(pos,radiusFinal).getZ()); //tp random nearby
				targetPlayerFinal.getRotation().set(GetRandomPosition(rot,360).getX(),GetRandomPosition(rot,360).getY(),GetRandomPosition(rot,360).getZ());                      //rotate random 
				targetPlayerFinal.getScene().broadcastPacket(new PacketSceneEntityAppearNotify(targetPlayerFinal));                                                              //slide arnd heh
				
				//combined spawnWaves(sender, targetPlayer, args, nuMobs, nuWaves, clistMobs, lvMobs, radius, pos, rot);
				Random pRandom = new Random();													  	   // Initialises random object for getting random mob id per iteration
				for (int i = 0; nuMobsFinal > i; i++) {                                                // Increment to number of wav
					String randomMob = mobs.get(pRandom.nextInt(mobs.size()));                         // Get random mobId from list earlier
					args.clear();                                                                      // Clean the list for next spawn command
					args.add(randomMob);                                                               // Add mobId to spawn command parems
					args.add("1");                                                                     // Number of each mob spawned per randomly selected mob id
					args.add(Integer.toString(lvMobsFinal));                                           // Level of mobs | Change to var for user input
					args.add(String.valueOf(GetRandomPosition(pos,radiusFinal).getX()));               // Get random x
					args.add(String.valueOf(targetPlayerFinal.getPosition().getY()));                  // Get y of current player to prevent floating and sinking enemies
					args.add(String.valueOf(GetRandomPosition(pos,radiusFinal).getZ()));               // Get random z
					 
					//spawnMob(sender, targetPlayer, args);
					SpawnCommand sMob = new SpawnCommand();                                            // Call SpawnCommand to make monster
					sMob.execute(sender, targetPlayerFinal, args);  
				}
				
				//combined incrementWaves();
				n++;
				
				
				
			}, (long) icdFinal , (long) icdFinal, TimeUnit.MILLISECONDS);                              // changed the timing of each interval here

            if(nuWavesFinal > 1){
            CommandHandler.sendMessage(targetPlayer,
                    "Custom waves started! You have " + icd + " seconds before the next wave starts!");
            }
            
        }
	
	
	private Position GetRandomPosition(Position pos,float radius) {                                    // rand pos creator, pos is  current pos and radius is max distance the new pos is frm original pos
		Position posNew = new Position();
		Random rand = new Random();                                                         
		posNew.setX((float) (pos.getX() + (rand.nextInt(3)-1) * (Math.random() * radius)));            // rand.nextInt(3) - 1 returns -1,0,1 for chance of (-) for full coverage
		posNew.setY((float) (pos.getY() + (rand.nextInt(3)-1) * (Math.random() * radius)));
		posNew.setZ((float) (pos.getZ() + (rand.nextInt(3)-1) * (Math.random() * radius)));
		return posNew;
	}
	
	// SPAWNING
    // 1.2.3
	/*public void spawnWaves(Player sender, Player targetPlayer, List<String> args, int nuMobs, int nuWaves, List<String> mobs, int lvMobs, float radius, Position pos, Position rot) {
		Random pRandom = new Random();													  	   // Initialises random object for getting random mob id per iteration
        for (int i = 0; nuMobs > i; i++) {                                                     // Increment to number of wav
            String randomMob = mobs.get(pRandom.nextInt(mobs.size()));                         // Get random mobId from list earlier
            args.clear();                                                                      // Clean the list for next spawn command
            args.add(randomMob);                                                               // Add mobId to spawn command parems
            args.add("1");                                                                     // Number of each mob spawned per randomly selected mob id
            args.add(Integer.toString(lvMobs));                                                // Level of mobs | Change to var for user input
			args.add(String.valueOf(GetRandomPosition(pos,radius).getX()));             // Get random x
			args.add(String.valueOf(targetPlayer.getPosition().getY()));                                       // Get y of current player to prevent floating and sinking enemies
			args.add(String.valueOf(GetRandomPosition(pos,radius).getZ()));             // Get random z
			 
			
			targetPlayer.getPosition().set(GetRandomPosition(pos,radius).getX(),targetPlayer.getPosition().getY(),GetRandomPosition(pos,radius).getZ()); //tp random nearby
			targetPlayer.getRotation().set(GetRandomPosition(rot,360).getX(),GetRandomPosition(rot,360).getY(),GetRandomPosition(rot,360).getZ());       //rotate random 
			targetPlayer.getScene().broadcastPacket(new PacketSceneEntityAppearNotify(targetPlayer));                                                    //slide arnd heh
			
            //spawnMob(sender, targetPlayer, args);
			SpawnCommand sMob = new SpawnCommand();                                           // Call SpawnCommand to make monster
			sMob.execute(sender, targetPlayer, args);  
        } 
    } */
	
	/*public void spawnMob(Player sender, Player targetPlayer, List<String> args) {
        SpawnCommand sMob = new SpawnCommand();                                           // Call SpawnCommand to make monster
        sMob.execute(sender, targetPlayer, args);                                         // Spawn the mob
    }*/
				
				
	/*			
	// 1.3.x
    public void spawnWaves(Player sender, Player targetPlayer, List<String> args, int nuMobs, int nuWaves,
            List<String> mobs, int mLevel) {
        Random pRandom = new Random();
        for (int i = 0; nuMobs > i; i++) {
            String randomMob = mobs.get(pRandom.nextInt(mobs.size()));
            args.clear(); // Clean the list
            args.add(0, randomMob); // Add mobId to command
            args.add("x1"); // Number of each mob spawned per randomly selected mob id
            args.add("lv"+Integer.toString(mLevel)); // Level of mobs | Change to var for user input
            spawnMob(sender, targetPlayer, args);
        } // nuMobs
    } // spawnWaves
	
    public void spawnMob(Player sender, Player targetPlayer, List<String> args) {
        SpawnCommand sMob = new SpawnCommand(); // Call SpawnCommand to make monster
        sMob.execute(sender, targetPlayer, args); // Spawn the mob
    }// spawnMob */

}
