package emu.grasscutter.command.commands;

import java.util.*;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.GameData;
import emu.grasscutter.game.player.Player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.tools.Tools;
import emu.grasscutter.utils.Utils;
import static emu.grasscutter.config.Configuration.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import java.util.*;

//import uk.haku.idlook.IdLookPlugin;
//import uk.haku.idlook.utils.StringSimilarity;
import emu.grasscutter.command.me.xdrop.fuzzywuzzy.FuzzySearch;

//import uk.haku.idlook.objects.PluginConfig;
//import uk.haku.idlook.objects.QueryResult;


@Command(label = "look", 
        usage = "look <keywords> [g/gadget/m/monster/i/item/a/avatar/all -> default]",
        aliases = {"l", "gm"},
        permission = "player.look",
        targetRequirement = Command.TargetRequirement.NONE)
		
public final class LookCommand implements CommandHandler {
    //private static final PluginConfig config = IdLookPlugin.getInstance().getConfiguration();
    private int resultLimit = 10;
    private int similarityScoreTreshold = 50;
	private Map<Long,String> itemTextMap;
	
	//classes in 1 coz i suck
	public class QueryResult implements Comparable<QueryResult> {
		public int Id;
		public String Name;
		public String ItemType;
		public int Score;

		public QueryResult(int id, String Name, String itemType, int score) {
			this.Id = id;
			this.Name = Name;
			this.ItemType = itemType;
			this.Score = score;
		}

		public int getScore() {
			return this.Score;
		}

		public int compareTo(QueryResult compareq) {
			int compareScore = ((QueryResult)compareq).getScore();
			return compareScore - this.Score;
		}
	}
	
	public class StringSimilarity {
		public static double Fuzzy(String x, String y) {
			return (FuzzySearch.tokenSetRatio(x.toLowerCase(), y.toLowerCase()) + FuzzySearch.ratio(x.toLowerCase(), y.toLowerCase()))/2;
		}
	}
	//end classes in 1
	

    @Override public void execute(Player sender, Player targetPlayer, List<String> args) {
		String type = "all";
		String temp = "";
		
		//init textmaps
		var language = "EN";//Tools.getLanguageOption();
		try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(Utils.toFilePath(RESOURCE("TextMap/TextMap"+language+".json"))), StandardCharsets.UTF_8)) {
			this.itemTextMap = Grasscutter.getGsonFactory().fromJson(fileReader, new TypeToken<Map<Long, String>>() {}.getType());
		} catch (IOException e) {
			Grasscutter.getLogger().warn("Resource does not exist");
			this.itemTextMap = new HashMap<>();
		}
		
		//
		try {
			temp = args.get(args.size()-1).toLowerCase();
			if (temp.equals("g") || temp.equals("gadget") || temp.equals("gadgets") || temp.equals("m") || temp.equals("monster") || temp.equals("monsters") || temp.equals("i") || temp.equals("item") || temp.equals("items") || temp.equals("a") || temp.equals("avatar") || temp.equals("avatars") || temp.equals("all")) {
				type = temp;
				args.remove(args.size()-1); //pop it away
			} else {
				;
			}
		} catch (IndexOutOfBoundsException e) {
			CommandHandler.sendMessage(sender,"cannot search nothing");
		}
		
        String lookQuery = String.join(" ", args);
        ArrayList<QueryResult> resultList = new ArrayList<QueryResult>();

        lookFor(lookQuery, resultList, type);

        Collections.sort(resultList);

        sendResult(sender, resultList, lookQuery);
    }


    public void sendResult(Player player, List<QueryResult> lookResult, String query) {
        if (lookResult.size() == 0) {
            CommandHandler.sendMessage(player, "Cannot find anything, try different keyword");
            return;
        } else if (lookResult.size() > resultLimit) {
            lookResult = lookResult.subList(0, resultLimit);
        }

        CommandHandler.sendMessage(player, "Result for: " + query);
        lookResult.forEach((data) -> {
            String name = data.Name;
            String itemType = data.ItemType;
            String responseMsg = "Id: " + data.Id + " | Name: " + name + " | Type: " + itemType;
            CommandHandler.sendMessage(player, responseMsg);
        });
        return;
    }


    public void lookFor(String query, ArrayList<QueryResult> lookResult, String type) {
        if (type.equals("all")) {
			lookForAvatar(query, lookResult);
			lookForItem(query, lookResult);
			lookForMonster(query, lookResult);
			lookForGadget(query, lookResult);
		} else if (type.equals("a") || type.equals("avatar") || type.equals("avatars")) {
			lookForAvatar(query, lookResult);
		} else if (type.equals("i") || type.equals("item") || type.equals("items")) {
			lookForItem(query, lookResult);
		} else if (type.equals("g") || type.equals("gadget") || type.equals("gadgets")) {
			lookForGadget(query, lookResult);
		} else if (type.equals("m") || type.equals("monster") || type.equals("monsters")) {
			lookForMonster(query, lookResult);
		} else {
			// shld be handled already
		}
        return;
    }


    public void lookForMonster(String query, ArrayList<QueryResult> lookResult) {
        // Monster
        GameData.getMonsterDataMap().forEach((id, data) -> {
            String name = itemTextMap.get(data.getNameTextMapHash());
            if (name != null) {
                Double similarityScore = StringSimilarity.Fuzzy(query, name);
                if (similarityScore > similarityScoreTreshold) {
                    lookResult.add(new QueryResult(id, name, "Monsters", similarityScore.intValue()));
                }
            }
        });
        return;
    }


    public void lookForAvatar(String query, ArrayList<QueryResult> lookResult) {
        // Avatars
        GameData.getAvatarDataMap().forEach((id, data) -> {
            String name = itemTextMap.get(data.getNameTextMapHash());
            if (name != null) {
                Double similarityScore = StringSimilarity.Fuzzy(query, name);
                if (similarityScore > similarityScoreTreshold) {
                    lookResult.add(new QueryResult(id, name, "Avatars", similarityScore.intValue()));
                }
            }
        });
        return;
    }


    public void lookForItem(String query, ArrayList<QueryResult> lookResult) {
        // Item
        GameData.getItemDataMap().forEach((id, data) -> {
            String name = itemTextMap.get(data.getNameTextMapHash());
            if (name != null) {
                Double similarityScore = StringSimilarity.Fuzzy(query, name);
                if (similarityScore > similarityScoreTreshold) {
                    lookResult.add(new QueryResult(id, name, data.getItemType().name(), similarityScore.intValue()));
                }
            }
        });
        return;
    }
	
	public void lookForGadget(String query, ArrayList<QueryResult> lookResult) {
		// Gadget
		GameData.getGadgetDataMap().forEach((id,data) -> {
			String name = itemTextMap.get(data.getNameTextMapHash());
            if (name != null) {
                Double similarityScore = StringSimilarity.Fuzzy(query, name);
                if (similarityScore > similarityScoreTreshold) {
                    lookResult.add(new QueryResult(id, name, "gadget", similarityScore.intValue()));
                }
            }
        });
        return;
    }
}