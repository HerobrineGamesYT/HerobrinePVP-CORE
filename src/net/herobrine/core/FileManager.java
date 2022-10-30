package net.herobrine.core;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.herobrine.blockhunt.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.herobrine.gamecore.GameCoreMain;
import net.herobrine.gamecore.Games;
import net.herobrine.gamecore.Manager;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

// UUID:Rank

public class FileManager {
	private HerobrinePVPCore main;
	// private File file;
	private File coins;
	private File trophies;
	// private File plusColor;
	private YamlConfiguration rankConfig;
	private YamlConfiguration coinConfig;
	private YamlConfiguration trophyConfig;
//	private YamlConfiguration plusColorConfig;
	private List<String> unlockedEmoteStorage = new ArrayList<>();
	private List<String> unlockedClassStorage = new ArrayList<>();
	private List<String> unlockedCosmeticStorage = new ArrayList<>();

	public FileManager(HerobrinePVPCore main) {
		// mainConfig = new File(main.getDataFolder(), "config.yml");
		this.main = main;
		main.saveDefaultConfig();
		// file = new File(main.getDataFolder(), "rankdata.yml");
		// coins = new File(main.getDataFolder(), "coins.yml");
		// trophies = new File(main.getDataFolder(), "trophies.yml");
		// plusColor = new File(main.getDataFolder(), "color.yml");

		// rankConfig = YamlConfiguration.loadConfiguration(file);
		// coinConfig = YamlConfiguration.loadConfiguration(coins);
		// trophyConfig = YamlConfiguration.loadConfiguration(trophies);
		// plusColorConfig = YamlConfiguration.loadConfiguration(plusColor);

		// convertFromLegacy();
	}

	public boolean isUserRegistered(Player player) {

		if (main.getConfig().get("players." + player.getUniqueId().toString()) != null) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isUserRegistered(UUID uuid) {
		if (main.getConfig().get("players." + uuid.toString()) != null) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isUserRegistered(String uuid) {
		if (main.getConfig().get("players." + uuid) != null) {
			return true;
		} else {
			return false;
		}

	}

	public void registerUser(Player player) {

		List<String> playerName = new ArrayList<>();
		playerName.add(player.getName());
		List<String> defaultEmotes = new ArrayList<>();
		List<String> defaultCosmetics = new ArrayList<>();
		List<String> defaultClasses = new ArrayList<>();
		main.getConfig().set("players." + player.getUniqueId().toString() + ".rank", Ranks.MEMBER.name());
		main.getConfig().set("players." + player.getUniqueId().toString() + ".plusColor", "&d");
		main.getConfig().set("players." + player.getUniqueId().toString() + ".level", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".xp", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".maxXP", 100);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".joinDate",
				System.currentTimeMillis() / 1000L);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".lastLogin",
				System.currentTimeMillis() / 1000L);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".playerNames", playerName);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".ipAddress", "");
		main.getConfig().set("players." + player.getUniqueId().toString() + ".isBanned", false);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".isMuted", false);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".activePunishmentId", "");
		main.getConfig().set("players." + player.getUniqueId().toString() + ".currencies.trophies", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".currencies.coins", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".punishments", "");
		main.getConfig().set("players." + player.getUniqueId().toString() + ".unlockedItems.cosmetics",
				defaultCosmetics);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".unlockedItems.emotes", defaultEmotes);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".unlockedItems.classes", defaultClasses);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.bh.wins", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.bh.roundsWon", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.bh.roundsPlayed", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.bh.blocksMined", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.bh.blocksFound", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.bc.wins", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.bc.kills", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.bc.roundsPlayed", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.wsg.wins", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.wsg.kills", 0);
		main.getConfig().set("players." + player.getUniqueId().toString() + ".stats.games.wsg.roundsPlayed", 0);
		main.saveConfig();
	}

	public void registerUser(UUID uuid) {
		DateFormat df = new SimpleDateFormat("MM-dd-yy-k-m-s");
		Date dateobj = new Date();
		List<String> playerName = new ArrayList<>();
		List<String> defaultEmotes = new ArrayList<>();
		List<String> defaultCosmetics = new ArrayList<>();
		List<String> defaultClasses = new ArrayList<>();
		main.getConfig().set("players." + uuid.toString() + ".rank", Ranks.MEMBER.name());
		main.getConfig().set("players." + uuid.toString() + ".plusColor", "&d");
		main.getConfig().set("players." + uuid.toString() + ".level", 0);
		main.getConfig().set("players." + uuid.toString() + ".xp", 0);
		main.getConfig().set("players." + uuid.toString() + ".maxXP", 100);
		main.getConfig().set("players." + uuid.toString() + ".joinDate", df.format(dateobj));
		main.getConfig().set("players." + uuid.toString() + ".lastLogin", System.currentTimeMillis() / 1000L);
		main.getConfig().set("players." + uuid.toString() + ".lastDisconnect", System.currentTimeMillis() / 1000L);
		main.getConfig().set("players." + uuid.toString() + ".playerNames", playerName);
		main.getConfig().set("players." + uuid.toString() + ".ipAddress", "");
		main.getConfig().set("players." + uuid.toString() + ".isBanned", false);
		main.getConfig().set("players." + uuid.toString() + ".isMuted", false);
		main.getConfig().set("players." + uuid.toString() + ".activePunishmentId", "");
		main.getConfig().set("players." + uuid.toString() + ".currencies.trophies", 0);
		main.getConfig().set("players." + uuid.toString() + ".currencies.coins", 0);
		main.getConfig().set("players." + uuid.toString() + ".punishments", "");
		main.getConfig().set("players." + uuid.toString() + ".unlockedItems.cosmetics", defaultCosmetics);
		main.getConfig().set("players." + uuid.toString() + ".unlockedItems.emotes", defaultEmotes);
		main.getConfig().set("players." + uuid.toString() + ".unlockedItems.classes", defaultClasses);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.bh.wins", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.bh.roundsWon", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.bh.roundsPlayed", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.bh.blocksMined", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.bh.blocksFound", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.bc.wins", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.bc.kills", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.bc.roundsPlayed", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.wsg.wins", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.wsg.kills", 0);
		main.getConfig().set("players." + uuid.toString() + ".stats.games.wsg.roundsPlayed", 0);
		main.saveConfig();
	}

	@Deprecated // This method only needed to be used once when converting from the legacy data system.
	public void convertFromLegacy() {

		HashMap<String, Object> rankObjects = (HashMap<String, Object>) rankConfig.getValues(false);

		for (String string : rankObjects.keySet()) {
			UUID uuid = UUID.fromString(string);
			if (!isUserRegistered(uuid)) {
				registerUser(uuid);
				setRank(uuid, getRankFromLegacy(uuid));
				setCoins(uuid, getCoinsFromLegacy(uuid));
				setTrophies(uuid, getTrophiesFromLegacy(uuid));
			}

		}

	}

	public void addMaxXPToConfig() {
		HashMap<String, Object> objects = (HashMap<String, Object>) main.getConfig().getConfigurationSection("players")
				.getValues(false);

		for (String object : objects.keySet()) {

			if (isUserRegistered(object)) {
				if (getPlayerLevel(UUID.fromString(object)) == 1) {
					main.getConfig().set("players." + object + ".maxXP", 100);
				}

				//let's add the missing walls sg stat to players here too because it is 4:30 am and i don't feel like making a new method
				if (getGameStats(UUID.fromString(object), Games.WALLS_SG, "roundsPlayed") != 0) {
					System.out.println("Adding Walls SG Stats For " + UUID.fromString(object));
					main.getConfig().set("players." + object + ".stats.games.wsg.wins", 0);
					main.getConfig().set("players." +  object + ".stats.games.wsg.kills", 0);
					main.getConfig().set("players." + object + ".stats.games.wsg.roundsPlayed", 0);

				}


			}

		}
		main.saveConfig();

	}

	public void resetAllLevels(Player player) {

		HashMap<String, Object> objects = (HashMap<String, Object>) main.getConfig().getConfigurationSection("players")
				.getValues(false);

		player.sendMessage(HerobrinePVPCore.translateString("&c&lRESETTING ALL PLAYER LEVELS..."));
		int i = 0;
		for (String object : objects.keySet()) {

			if (isUserRegistered(object)) {
					main.getConfig().set("players." + object + ".maxXP", 100);
					main.getConfig().set("players." + object + ".xp", 0);
					main.getConfig().set("players." + object + ".level", 0);
			i++;
			}

		}
		main.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Reset the levels of " + i + " players!");

	}

	public void removeOldUsers(Player player) {
		HashMap<String, Object> objects = (HashMap<String, Object>) main.getConfig().getConfigurationSection("players")
				.getValues(false);

		player.sendMessage(HerobrinePVPCore.translateString("&c&lREMOVING OLD USERS..."));
		int deletedUsers = 0;
		for (String object : objects.keySet()) {

			if (isUserRegistered(object)) {
                // 7889238 = 3 months apart- in seconds. Unix Timestamps FTW!
				if ((System.currentTimeMillis() / 1000) - main.getConfig().getLong("players." + object + ".lastLogin") >= 7889238) {
					main.getConfig().set("players." + object, null);
					deletedUsers++;
				}
			}

		}
		main.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Successfully deleted a total of " + deletedUsers + " inactive users!");

	}


	public void resetAllStats(Player player) {

		HashMap<String, Object> objects = (HashMap<String, Object>) main.getConfig().getConfigurationSection("players")
				.getValues(false);

		player.sendMessage(HerobrinePVPCore.translateString("&c&lRESETTING ALL PLAYER STATS..."));
		int i = 0;
		for (String object : objects.keySet()) {

			if (isUserRegistered(object)) {
				for (Games game : Games.values()) {
					if (game.areStatsShown()) {
						for (String statKey : game.getStatKeys()) {
							main.getConfig().set("players." + object + ".stats.games." + game.getKey() + "." + statKey, 0);
						}
					}
				}
				i++;
			}

		}
		main.saveConfig();
		player.sendMessage(ChatColor.GREEN + "Reset the stats of " + i + " players!");

	}


	public void updateIP(Player player, String ip) {
		main.getConfig().set("players." + player.getUniqueId().toString() + ".ipAddress", ip);
		main.saveConfig();
	}

	public String getIP(Player player) {
		return main.getConfig().getString("players." + player.getUniqueId().toString() + ".ipAddress");
	}

	public String getIP(UUID uuid) {
		return main.getConfig().getString("players." + uuid.toString() + ".ipAddress");
	}

	public void updatePlayerNames(Player player) {
		main.getConfig().set("players." + player.getUniqueId().toString() + ".playerNames", player.getName());
		main.saveConfig();
	}

	public String getNameFromUUID(UUID uuid) {

		return main.getConfig().getString("players." + uuid.toString() + ".playerNames");
	}

	public UUID getUUIDFromName(String name) {

		HashMap<String, Object> objects = (HashMap<String, Object>) main.getConfig().getConfigurationSection("players")
				.getValues(false);

		for (String string : objects.keySet()) {
			if (isUserRegistered(string)) {

				if (main.getConfig().getStringList("players." + string + ".playerNames") != null) {
					if (main.getConfig().getStringList("players." + string + ".playerNames").contains(name)) {
						UUID uuid = UUID.fromString(string);
						return uuid;
					}
				}
			}
		}

		return null;

	}

	public void updateLastJoin(Player player) {
		DateFormat df = new SimpleDateFormat("MM-dd-yy-k-m-s");
		Date dateobj = new Date();
		main.getConfig().set("players." + player.getUniqueId().toString() + ".lastLogin",
				System.currentTimeMillis() / 1000L);
		main.saveConfig();
	}

	public int getLastJoin(UUID uuid) {
		return main.getConfig().getInt("players." + uuid.toString() + ".lastLogin");
	}

	public void updateLastDisconnect(Player player) {
		DateFormat df = new SimpleDateFormat("MM-dd-yy-k-m-s");
		Date dateobj = new Date();
		main.getConfig().set("players." + player.getUniqueId().toString() + ".lastDisconnect",
				System.currentTimeMillis() / 1000L);
		main.saveConfig();
	}

	public int getLastDisconnect(UUID uuid) {
		return main.getConfig().getInt("players." + uuid.toString() + ".lastDisconnect");
	}

	public void setRank(Player player, Ranks rank) {
		main.getConfig().set("players." + player.getUniqueId().toString() + ".rank", rank.name());
		main.saveConfig();
	}

// 


	public boolean hasBeenPunished(UUID uuid) {
		HashMap<String, Object> objects = (HashMap<String, Object>) main.getConfig().getConfigurationSection("players." + uuid.toString() + ".punishments.bans")
				.getValues(false);
		HashMap<String, Object> objects2 = (HashMap<String, Object>) main.getConfig().getConfigurationSection("players." + uuid.toString() + ".punishments.mutes")
				.getValues(false);
		if (objects.isEmpty() && objects2.isEmpty()) {
			return false;
		}
		return true;
	}

	// not implemented
	public String[] getPunishments(UUID uuid) {
		return null;
	}
    // not implemented
	public String[] getPunishment(String punishmentID, UUID uuid) {
		return null;
	}

	public String[] getPunishments(UUID uuid, PunishmentType type) {
		HashMap<String, Object> punishments = new HashMap<>();
		if (type.equals(PunishmentType.BAN)) {
			punishments.putAll(main.getConfig().getConfigurationSection("players." + uuid.toString() + ".punishments.bans").getValues(false));


		}
		else if (type.equals(PunishmentType.MUTE)) {
			punishments.putAll(main.getConfig().getConfigurationSection("players." + uuid.toString() + ".punishments.mutes").getValues(false));
		}



		// arr = [id, player, type, timestamps, moderator, reason]

		String[] info = new String[] {};


     return null;
	}

	public long getMaxXP(UUID uuid) {

		return main.getConfig().getLong("players." + uuid.toString() + ".maxXP");
	}

	public void setMaxXP(UUID uuid, long maxXP) {

		main.getConfig().set("players." + uuid.toString() + ".maxXP", maxXP);
		main.saveConfig();

		if (getPlayerXP(uuid) >= getMaxXP(uuid)) {
			levelUp(uuid);
		}
	}

	// only set to PRODUCTION or DEV
	public void setEnvironment(String environment) {

		main.getConfig().set("settings.environment", environment);

		main.saveConfig();
	}

	public String getEnvironment() {
		return main.getConfig().getString("settings.environment");
	}

	public void setPlusColor(Player player, ChatColor color) {
		String colorCode = null;
		switch (color) {
		case RED:
			colorCode = "&c";
			break;
		case YELLOW:
			colorCode = "&e";
			break;

		case WHITE:
			colorCode = "&f";
			break;

		case GREEN:
			colorCode = "&a";
			break;

		case UNDERLINE:
			System.out.println("INVALID COLOR: Underline. Special Plus Colors are not allowed!");
			break;

		case DARK_GREEN:
			colorCode = "&2";
			break;

		case STRIKETHROUGH:
			System.out.println("INVALID COLOR: Strikethrough. Special Plus Colors are not allowed!");
			break;

		case RESET:
			System.out.println("INVALID COLOR: Reset. Special Plus Colors are not allowed!");
			break;

		case MAGIC:
			System.out.println("INVLALID COLOR: Magic. Special Plus Colors are not allowed!");
			break;

		case LIGHT_PURPLE:
			colorCode = "&d";
			break;

		case ITALIC:
			System.out.println("INVLALID COLOR: Italic. Special Plus Colors are not allowed!");
			break;

		case GRAY:
			colorCode = "&7";
			break;

		case GOLD:
			colorCode = "&6";
			break;

		case DARK_RED:
			colorCode = "&4";
			break;

		case DARK_PURPLE:
			colorCode = "&5";
			break;

		case BLACK:
			colorCode = "&0";
			break;

		case DARK_GRAY:
			colorCode = "&8";
			break;

		case DARK_BLUE:
			colorCode = "&1";
			break;

		case BLUE:
			colorCode = "&9";
			break;

		case DARK_AQUA:
			colorCode = "&3";
			break;

		case AQUA:
			colorCode = "&b";
			break;

		case BOLD:
			System.out.println("INVLALID COLOR: Bold. Special Plus Colors are not allowed!");
			break;

		default:
			return;

		}
		main.getConfig().set("players." + player.getUniqueId().toString() + ".plusColor", colorCode);
		main.saveConfig();
		System.out.println("Plus Color Changed: " + player.getUniqueId().toString() + ":" + colorCode);
		if (player.isOnline()) {
			if (getRank(player).hasPlusColor()) {
				if (player.getPlayer().getScoreboard().getTeam("rank") != null) {
					player.getPlayer().getScoreboard().getTeam("rank").setSuffix(getRank(player.getPlayer()).getColor()
							+ getRank(player.getPlayer()).getName()
							+ ChatColor.translateAlternateColorCodes('&', getPlusColor(player.getUniqueId()) + "+"));
				}

				player.setPlayerListName(getRank(player).getColor() + getRank(player).getName()
						+ ChatColor.translateAlternateColorCodes('&', getPlusColor(player.getUniqueId()) + "+") + " "
						+ getRank(player).getColor() + player.getName());

			}

		}
	}

	public String getPlusColor(UUID uuid) {
		return main.getConfig().getString("players." + uuid.toString() + ".plusColor");
	}

	public void setRank(UUID uuid, Ranks rank) {
		main.getConfig().set("players." + uuid.toString() + ".rank", rank.name());
		main.saveConfig();
	}

	public Ranks getRank(Player player) {
		return Ranks.valueOf(main.getConfig().getString("players." + player.getUniqueId().toString() + ".rank"));
	}

	@Deprecated
	public Ranks getRankFromLegacy(Player player) {
		return Ranks.valueOf(rankConfig.getString(player.getUniqueId().toString()));
	}

	public void setCoins(Player player, int coins) {
		main.getConfig().set("players." + player.getUniqueId() + ".currencies.coins", coins);
		main.saveConfig();
	}

	public void setCoins(UUID uuid, int coins) {
		main.getConfig().set("players." + uuid.toString() + ".currencies.coins", coins);
		main.saveConfig();
	}

	public void addCoins(UUID uuid, int coins) {

		main.getConfig().set("players." + uuid.toString() + ".currencies.coins",
				main.getConfig().getInt("players." + uuid.toString() + ".currencies.coins") + coins);
		main.saveConfig();
	}

	public void addCoins(Player player, int coins) {
		main.getConfig().set("players." + player.getUniqueId().toString() + ".currencies.coins",
				main.getConfig().getInt("players." + player.getUniqueId().toString() + ".currencies.coins") + coins);
		main.saveConfig();
	}

	public void removeCoins(UUID uuid, int coins) {
		main.getConfig().set("players." + uuid.toString() + ".currencies.coins",
				main.getConfig().getInt("players." + uuid.toString() + ".currencies.coins") - coins);
		main.saveConfig();
	}

	public void removeCoins(Player player, int coins) {
		main.getConfig().set("players." + player.getUniqueId().toString() + ".currencies.coins",
				main.getConfig().getInt("players." + player.getUniqueId().toString() + ".currencies.coins") - coins);
		main.saveConfig();
	}

	public int getCoins(Player player) {
		return main.getConfig().getInt("players." + player.getUniqueId().toString() + ".currencies.coins");

	}

	public int getCoins(UUID uuid) {
		return main.getConfig().getInt("players." + uuid.toString() + ".currencies.coins");
	}

	@Deprecated
	public int getCoinsFromLegacy(Player player) {
		return coinConfig.getInt(player.getUniqueId().toString());
	}

	@Deprecated
	public int getCoinsFromLegacy(UUID uuid) {
		return coinConfig.getInt(uuid.toString());
	}

	public void setTrophies(Player player, int trophies) {
		main.getConfig().set("players." + player.getUniqueId().toString() + ".currencies.trophies", trophies);
		main.saveConfig();
	}

	public void setTrophies(UUID uuid, int trophies) {
		main.getConfig().set("players." + uuid.toString() + ".currencies.trophies", trophies);
		main.saveConfig();
	}

	public void addTrophies(Player player, int trophies) {
		main.getConfig().set("players." + player.getUniqueId().toString() + ".currencies.trophies",
				main.getConfig().getInt("players." + player.getUniqueId().toString() + ".currencies.trophies")
						+ trophies);
		main.saveConfig();
	}

	public void addTrophies(UUID uuid, int trophies) {
		main.getConfig().set("players." + uuid.toString() + ".currencies.trophies",
				main.getConfig().getInt("players." + uuid.toString() + ".currencies.trophies") + trophies);
		main.saveConfig();
	}

	public void removeTrophies(Player player, int trophies) {
		main.getConfig().set("players." + player.getUniqueId().toString() + ".currencies.trophies",
				main.getConfig().getInt("players." + player.getUniqueId().toString() + ".currencies.trophies")
						- trophies);
		main.saveConfig();
	}

	public void removeTrophies(UUID uuid, int trophies) {
		main.getConfig().set("players." + uuid.toString() + ".currencies.trophies",
				main.getConfig().getInt("players." + uuid.toString() + ".currencies.trophies") - trophies);
		main.saveConfig();
	}

	public int getTrophies(Player player) {
		return main.getConfig().getInt("players." + player.getUniqueId().toString() + ".currencies.trophies");
	}

	public int getTrophies(UUID uuid) {
		return main.getConfig().getInt("players." + uuid.toString() + ".currencies.trophies");
	}

	@Deprecated
	public int getTrophiesFromLegacy(Player player) {
		return trophyConfig.getInt(player.getUniqueId().toString());
	}

	@Deprecated
	public int getTrophiesFromLegacy(UUID uuid) {
		return trophyConfig.getInt(uuid.toString());
	}

	// GAME STAT TYPES
	// ALL GAMES HAVE: roundsPlayed, wins
	// BLOCK HUNT HAS: roundsWon, blocksMined, blocksFound
	// BATTLE CLASH/OTHER PVP GAMES HAVE: kills
	// INT IS TO SET A STAT TO SOMETHING. BE SURE TO GET THE STAT FIRST AND ADD TO
	// BEFORE SETTING!
	public void setGameStats(UUID uuid, Games game, String stat, int number) {

		main.getConfig().set("players." + uuid.toString() + ".stats.games." + game.getKey() + "." + stat, number);
		main.saveConfig();

	}

	public int getGameStats(UUID uuid, Games game, String stat) {

		return main.getConfig().getInt("players." + uuid.toString() + ".stats.games." + game.getKey() + "." + stat);

	}

	public void unlockItem(ItemTypes type, String itemName, UUID uuid) {

		if (type == ItemTypes.COSMETIC) {
			List<String> unlockedItem = new ArrayList<>();
			unlockedItem.add(itemName);
			for (String items : main.getConfig()
					.getStringList("players." + uuid.toString() + ".unlockedItems.cosmetics")) {

				unlockedItem.add(items);

			}
			unlockedItem.add(itemName);
			main.getConfig().set("players." + uuid.toString() + ".unlockedItems.cosmetics", unlockedItem);
			main.saveConfig();
		} else if (type == ItemTypes.EMOTE) {
			List<String> unlockedItem = main.getConfig()
					.getStringList("players." + uuid.toString() + ".unlockedItems.emotes");
			unlockedItem.add(itemName);

			main.getConfig().set("players." + uuid.toString() + ".unlockedItems.emotes", unlockedItem);
			main.saveConfig();
		} else if (type == ItemTypes.CLASS) {
			List<String> unlockedItem = new ArrayList<>();
			unlockedItem.add(itemName);
			for (String items : main.getConfig()
					.getStringList("players." + uuid.toString() + ".unlockedItems.classes")) {

				unlockedItem.add(items);

			}
			unlockedItem.add(itemName);
			main.getConfig().set("players." + uuid.toString() + ".unlockedItems.classes", unlockedItem);
			main.saveConfig();
		}

	}

	public String getBanReason(UUID uuid, String banId) {

		return main.getConfig().getString("players." + uuid.toString() + ".punishments.bans." + banId + ".banReason");
	}

	public String getMuteReason(UUID uuid, String banId) {

		return main.getConfig().getString("players." + uuid.toString() + ".punishments.mutes." + banId + ".muteReason");
	}

	public void createBan(UUID uuid, String moderatorName, String reason) {

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		String punishmentId = generatedString;

		main.getConfig().set("players." + uuid.toString() + ".isBanned", true);
		main.getConfig().set("players." + uuid.toString() + ".activePunishmentId", punishmentId);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banDate",
				System.currentTimeMillis() / 1000);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banType",
				"PERMANENT");
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".bannedBy",
				moderatorName);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banExpires",
				"NEVER");
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banReason", reason);
		main.saveConfig();

	}

	public void removeBan(UUID uuid, String pardonName, String reason) {
		main.getConfig().set("pardons.bans." + getActivePunishmentId(uuid) + ".unbanReason", reason);

		main.getConfig().set("pardons.bans." + getActivePunishmentId(uuid) + ".unbannedBy", pardonName);
		main.getConfig().set("pardons.bans." + getActivePunishmentId(uuid) + ".unbannedTime",
				System.currentTimeMillis() / 1000);

		main.getConfig().set("players." + uuid.toString() + ".isBanned", false);

		main.getConfig().set("players." + uuid.toString() + ".activePunishmentId", "");

	}

	public void removeMute(UUID uuid, String pardonName, String reason) {
		main.getConfig().set("pardons.mutes." + getActivePunishmentId(uuid) + ".unmuteReason", reason);

		main.getConfig().set("pardons.mutes." + getActivePunishmentId(uuid) + ".unmutedBy", pardonName);
		main.getConfig().set("pardons.mutes." + getActivePunishmentId(uuid) + ".unmutedTime",
				System.currentTimeMillis() / 1000);

		main.getConfig().set("players." + uuid.toString() + ".isMuted", false);

		main.getConfig().set("players." + uuid.toString() + ".activePunishmentId", "");

	}

	public long getBanTime(UUID uuid, String banID) {
		return main.getConfig().getLong("players." + uuid.toString() + ".punishments.bans." + banID + ".banDate");
	}

	public long getMuteTime(UUID uuid, String banID) {
		return main.getConfig().getLong("players." + uuid.toString() + ".punishments.mutes." + banID + ".muteDate");
	}

	public void createIPBan(UUID uuid, String moderatorName, String reason) {
		List<String> bannedIps = main.getConfig().getStringList("settings.bannedIps");
		bannedIps.add(getIP(uuid));
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		String punishmentId = generatedString;

		main.getConfig().set("players." + uuid.toString() + ".isIpBanned", true);
		main.getConfig().set("players." + uuid.toString() + ".activePunishmentId", punishmentId);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banDate",
				System.currentTimeMillis() / 1000);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banType", "IP");
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".bannedBy",
				moderatorName);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banExpires",
				"NEVER");
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banReason", reason);
		main.getConfig().set("settings.bannedIps", bannedIps);
		main.saveConfig();

	}

	public boolean isBannedIP(String ip) {

		if (main.getConfig().getStringList("settings.bannedIps").contains(ip)) {
			return true;
		}

		else {
			return false;
		}

	}

	public int getPlayerLevel(UUID uuid) {
		return main.getConfig().getInt("players." + uuid.toString() + ".level");
	}

	public long getPlayerXP(UUID uuid) {
		return main.getConfig().getLong("players." + uuid.toString() + ".xp");
	}

	public void setPlayerLevel(UUID uuid, int level) {

		main.getConfig().set("players." + uuid.toString() + ".level", level);
		main.saveConfig();
		if (Bukkit.getPlayer(uuid).isOnline()) {
			Player player = Bukkit.getPlayer(uuid);
			if (!Manager.isPlaying(player)) {
				player.setLevel(level);
			}
		}
	}

	public void levelUp(UUID uuid) {
		main.getConfig().set("players." + uuid.toString() + ".level", getPlayerLevel(uuid) + 1);
		main.saveConfig();

		if (Bukkit.getPlayer(uuid).isOnline()) {

			Player player = Bukkit.getPlayer(uuid);
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 1f, 1f);
			player.sendMessage(
					ChatColor.translateAlternateColorCodes('&', "&b&m&l----------------------------------------"));
			player.sendMessage(
					ChatColor.translateAlternateColorCodes('&', "                        &3&k!!&r&a&lLEVEL UP!&3&k!!"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&bYou are now level " + "&l" + getPlayerLevel(uuid) + "&r&b!"));
			player.sendMessage("");

			if (getPlayerLevel(uuid) < 100) {


				if (checkPrestige(getPlayerLevel(uuid)).containsValue(true)) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou are now ") + getPrestige(getPlayerLevel(uuid)).name + ChatColor.AQUA + "!");
					player.sendMessage(ChatColor.YELLOW + "Coin Multiplier " + getPreviousPrestige(getPlayerLevel(uuid)).prestigeColor + getPreviousPrestige(getPlayerLevel(uuid)).gameCoinMultiplier
							+ ChatColor.RED + ChatColor.BOLD + "»" + getPrestige(getPlayerLevel(uuid)).prestigeColor + getPrestige(getPlayerLevel(uuid)).gameCoinMultiplier);
					player.sendMessage(ChatColor.AQUA + "XP Multiplier " + getPreviousPrestige(getPlayerLevel(uuid)).prestigeColor + getPreviousPrestige(getPlayerLevel(uuid)).baseXPBoost
							+ ChatColor.RED + ChatColor.BOLD + "»" + getPrestige(getPlayerLevel(uuid)).prestigeColor + getPrestige(getPlayerLevel(uuid)).baseXPBoost);

					player.sendMessage("");
					player.sendMessage(ChatColor.GREEN + "You also got:");
					player.sendMessage(ChatColor.GREEN + "- " + ChatColor.YELLOW + "+" + getPrestige(getPlayerLevel(uuid)).coins + " Coins");
					HerobrinePVPCore.getFileManager().addCoins(uuid, getPrestige(getPlayerLevel(uuid)).coins);
					if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.getDisplayName().equalsIgnoreCase(
									ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

						player.getScoreboard().getTeam("coins").setSuffix(
								ChatColor.translateAlternateColorCodes('&', "&e" + HerobrinePVPCore
										.getFileManager().getCoins(player)));
					}
				} else {
					player.sendMessage(ChatColor.GREEN + "Rewards: ");
					int earnedCoins = ((int) Math.round(getPlayerLevel(uuid) / getPrestige(getPlayerLevel(uuid)).coinDivisorPerLevel * 100) + getPrestige(getPlayerLevel(uuid)).coins);
					player.sendMessage(ChatColor.GREEN + "- " + ChatColor.YELLOW + "+" + earnedCoins + " Coins");
					HerobrinePVPCore.getFileManager().addCoins(player, earnedCoins);
					if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
							.getDisplayName().equalsIgnoreCase(
									ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

						player.getScoreboard().getTeam("coins").setSuffix(
								ChatColor.translateAlternateColorCodes('&', "&e" + HerobrinePVPCore
										.getFileManager().getCoins(player)));
					}
				}
			}
			else {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYou are now ") + getPrestige(getPlayerLevel(uuid)).name + ChatColor.AQUA + "!");
				player.sendMessage(ChatColor.YELLOW + "Coin Multiplier " + getPreviousPrestige(getPlayerLevel(uuid)).prestigeColor + getPreviousPrestige(getPlayerLevel(uuid)).gameCoinMultiplier
						+ ChatColor.RED + ChatColor.BOLD + "»" + getPrestige(getPlayerLevel(uuid)).prestigeColor + getPrestige(getPlayerLevel(uuid)).gameCoinMultiplier);
				player.sendMessage(ChatColor.AQUA + "XP Multiplier " + getPreviousPrestige(getPlayerLevel(uuid)).prestigeColor + getPreviousPrestige(getPlayerLevel(uuid)).baseXPBoost
						+ ChatColor.RED + ChatColor.BOLD + "»" + getPrestige(getPlayerLevel(uuid)).prestigeColor + getPrestige(getPlayerLevel(uuid)).baseXPBoost);

				player.sendMessage("");
				player.sendMessage(ChatColor.GREEN + "You also got:");
				player.sendMessage(ChatColor.GREEN + "- " + ChatColor.YELLOW + "+" + getPrestige(getPlayerLevel(uuid)).coins + " Coins");
				HerobrinePVPCore.getFileManager().addCoins(uuid, getPrestige(getPlayerLevel(uuid)).coins);
				if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
						.getDisplayName().equalsIgnoreCase(
								ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

					player.getScoreboard().getTeam("coins").setSuffix(
							ChatColor.translateAlternateColorCodes('&', "&e" + HerobrinePVPCore
									.getFileManager().getCoins(player)));
				}
			}
			player.sendMessage(
					ChatColor.translateAlternateColorCodes('&', "&b&m&l----------------------------------------"));

			if (!Manager.isPlaying(player)) {
				GameCoreMain.getInstance().sendTitle(player, "&a&lLEVEL UP!",
						"&bYou are now level " + "&l" + getPlayerLevel(uuid) + "&r&b!", 0, 2, 1);
			}

			if (!Manager.isPlaying(player)) {
				player.setLevel(getPlayerLevel(uuid));
				player.setExp(getPlayerXP(uuid) / getMaxXP(uuid));
			}

			if (getPlayerLevel(uuid) >= 20) {
				setMaxXP(uuid, (long) getMaxXP(uuid) + 20000);
			} else {
				setMaxXP(uuid, (long) Math.round(getMaxXP(uuid) * 1.5));
			}

		}
	}

	public HashMap<LevelRewards, Boolean> checkPrestige(int level) {
        int i = 0;
		HashMap<LevelRewards, Boolean> prestigeMap = new HashMap<>();
		for (LevelRewards prestige : LevelRewards.values()) {

            System.out.println(i);
			System.out.println(prestige.startingLevel);

			if (prestige.startingLevel != 100) {
				if (level >= prestige.startingLevel && level < LevelRewards.values()[i+1].startingLevel ) {

					prestigeMap.put(prestige, false);

					return prestigeMap;


				}

				else if (LevelRewards.values()[i+1].startingLevel == level) {
					prestigeMap.put(LevelRewards.values()[i+1], true);
					return prestigeMap;
				}

			}
			else {
				System.out.println("Something is seriously wrong here.");
			}



				i++;
		}


		return null;
	}
	public LevelRewards getPrestige(int level) {
		int i = 0;

		for (LevelRewards prestige : LevelRewards.values()) {



			if (prestige.startingLevel != 100) {
				if (level >= prestige.startingLevel && level < LevelRewards.values()[i + 1].startingLevel) {


					return prestige;


				}
			}
			else {
				return LevelRewards.TEN;
			}


			i++;
		}


		return null;
	}
	public LevelRewards getPreviousPrestige(int level) {
		int i = 0;

		for (LevelRewards prestige : LevelRewards.values()) {



			if (prestige.startingLevel != 100) {
				if (level >= prestige.startingLevel && level < LevelRewards.values()[i + 1].startingLevel) {


					if (i > 0) {
						return LevelRewards.values()[i - 1];
					} else {
						return LevelRewards.BASE;
					}


				}
			}
			else {
				return LevelRewards.NINE;
			}


			i++;
		}


		return null;
	}


	public void setPlayerXP(UUID uuid, long xp) {
		main.getConfig().set("players." + uuid.toString() + ".xp", xp);
		main.saveConfig();
	}

	public void addPlayerXP(UUID uuid, long xp) {

		if (getPlayerLevel(uuid) < 100) {
			main.getConfig().set("players." + uuid.toString() + ".xp", getPlayerXP(uuid) + xp);
			main.saveConfig();

		}

		if (getMaxXP(uuid) <= getPlayerXP(uuid) && getPlayerLevel(uuid) < 100) {

			new BukkitRunnable() {
				public void run() {
					levelUp(uuid);
				}
			}.runTaskLater(main, 1L);


		} else {
			if (Bukkit.getPlayer(uuid) != null) {
				Player player = Bukkit.getPlayer(uuid);
				if (player.isOnline()) {
					if (!Manager.isPlaying(player)) {
						player.setExp(getPlayerXP(uuid) / getMaxXP(uuid));
					}

				}
			}
		}
	}

	public void addBannedIP(String ip) {
		List<String> bannedIps = main.getConfig().getStringList("settings.bannedIps");
		bannedIps.add(ip);

	}

	public List<UUID> getUsersWithIP(String ip) {

		List<UUID> usersWithIP = new ArrayList<>();
		HashMap<String, Object> objects = (HashMap<String, Object>) main.getConfig().getConfigurationSection("players")
				.getValues(false);

		for (String string : objects.keySet()) {
			if (isUserRegistered(string)) {

				System.out.println("string is registered!");

				if (main.getConfig().getStringList("players." + string + ".playerNames") != null) {

					if (getIP(UUID.fromString(string)) == ip) {
						usersWithIP.add(UUID.fromString(string));
					}

				}

			}

		}

		return usersWithIP;

	}

	public void createTempBan(UUID uuid, String moderatorName, String reason, long expiryTime) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		String punishmentId = generatedString;

		long currentTime = (System.currentTimeMillis() / 1000);
		long expireTime = currentTime + expiryTime;

		main.getConfig().set("players." + uuid.toString() + ".isBanned", true);
		main.getConfig().set("players." + uuid.toString() + ".activePunishmentId", punishmentId);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banDate",
				currentTime);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banType",
				"TEMPORARY");
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".bannedBy",
				moderatorName);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banExpires",
				expireTime);
		main.getConfig().set("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banReason", reason);
		main.saveConfig();

	}

	public void createMute(UUID uuid, String moderatorName, String reason) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		String punishmentId = generatedString;

		main.getConfig().set("players." + uuid.toString() + ".isMuted", true);
		main.getConfig().set("players." + uuid.toString() + ".activePunishmentId", punishmentId);
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteDate",
				System.currentTimeMillis() / 1000);
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteType",
				"PERMANENT");
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".mutedBy",
				moderatorName);
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteExpires",
				"NEVER");
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteReason",
				reason);
		main.saveConfig();
	}

	public void createTempMute(UUID uuid, String moderatorName, String reason, long expiryTime) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		String punishmentId = generatedString;

		long currentTime = (System.currentTimeMillis() / 1000);
		long expireTime = currentTime + expiryTime;

		main.getConfig().set("players." + uuid.toString() + ".isMuted", true);
		main.getConfig().set("players." + uuid.toString() + ".activePunishmentId", punishmentId);
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteDate",
				System.currentTimeMillis() / 1000);
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteType",
				"TEMPORARY");
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".mutedBy",
				moderatorName);
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteExpires",
				expireTime);
		main.getConfig().set("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteReason",
				reason);
		main.saveConfig();
	}

	public boolean isBanned(UUID uuid) {
		if (main.getConfig().getBoolean("players." + uuid.toString() + ".isBanned")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isIPBanned(UUID uuid) {
		if (main.getConfig().getBoolean("players." + uuid.toString() + ".isIpBanned")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isMuted(UUID uuid) {
		if (main.getConfig().getBoolean("players." + uuid.toString() + ".isMuted")) {
			return true;
		} else {
			return false;
		}
	}

	public String getActivePunishmentId(UUID uuid) {
		return main.getConfig().getString("players." + uuid.toString() + ".activePunishmentId");
	}

	public long getBanExpiryTime(UUID uuid, String punishmentId) {

		return main.getConfig()
				.getLong("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banExpires");

	}

	public long getMuteExpiryTime(UUID uuid, String punishmentId) {

		return main.getConfig()
				.getLong("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteExpires");

	}

	public PunishmentType getBanType(UUID uuid, String punishmentId) {

		return PunishmentType.valueOf(main.getConfig()
				.getString("players." + uuid.toString() + ".punishments.bans." + punishmentId + ".banType"));

	}

	public PunishmentType getMuteType(UUID uuid, String punishmentId) {

		return PunishmentType.valueOf(main.getConfig()
				.getString("players." + uuid.toString() + ".punishments.mutes." + punishmentId + ".muteType"));

	}

	public boolean isItemUnlocked(ItemTypes type, String itemName, UUID uuid) {

		if (type == ItemTypes.COSMETIC) {

			if (main.getConfig().getStringList("players." + uuid.toString() + ".unlockedItems.cosmetics")
					.contains(itemName)) {

				return true;
			}

		} else if (type == ItemTypes.EMOTE) {

			if (main.getConfig().getStringList("players." + uuid.toString() + ".unlockedItems.emotes")
					.contains(itemName)) {

				return true;
			}

		} else if (type == ItemTypes.CLASS) {

			if (main.getConfig().getStringList("players." + uuid.toString() + ".unlockedItems.classes")
					.contains(itemName)) {

				return true;
			}

		}

		return false;
	}

	@Deprecated
	private void save() {
		try {
			// config.save(file);
			coinConfig.save(coins);
			trophyConfig.save(trophies);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public Ranks getRank(UUID uniqueId) {
		return Ranks.valueOf(main.getConfig().getString("players." + uniqueId.toString() + ".rank"));

	}

	@Deprecated
	public Ranks getRankFromLegacy(UUID uniqueId) {
		return Ranks.valueOf(rankConfig.getString(uniqueId.toString()));
	}
}
