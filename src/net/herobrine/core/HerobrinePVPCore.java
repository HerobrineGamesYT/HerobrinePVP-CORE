package net.herobrine.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;

import net.herobrine.blockhunt.BlockHuntMain;
import net.herobrine.gamecore.GameCoreMain;

public class HerobrinePVPCore extends JavaPlugin {
	private static FileManager fileManager;

	private Connection connection;
	private String host, database, username, password;
	private int port;

	@Override
	public void onEnable() {
		fileManager = new FileManager(this);

		if (getGameCoreAPI() == null) {
			System.out.println("[HBPVP-CORE] You need the game core to use the HBPVP-CORE plugin.");
			Bukkit.getPluginManager().disablePlugin(this);
			this.saveDefaultConfig();
		}

		else {
		}
		if (getBlockHuntAPI() == null) {
			System.out.println(
					"[HBPVP-CORE] Missing Minigame Plugin: BlockHunt.\n[HBPVP-CORE] All minigame plugins are required for the core to function properly, disabling...");
			Bukkit.getPluginManager().disablePlugin(this);
		} else {
			System.out.println("[HBPVP-CORE] Successfully hooked into Block Hunt!");
		}

		if (getNBAPI() == null) {
			System.out.println(
					"[HBPVP-CORE] Missing Note Block API! This plugin is required for many sound effects. Disabling..");
			Bukkit.getPluginManager().disablePlugin(this);
		}

		// host = "localhost";
		// port = 3306;
		// database = "hbpvp";
		// username = "root";
		// password = "";

		// try {
		// openConnection();
		// System.out.println("Connected to the " + "database!");
//
//			createTables();
//			updateTables();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		// if (connection != null) {
		// prepareStatement("");
		// }
		// if (getCRAPI() == null) {
		// System.out.println(
		// "[HBPVP-CORE] Missing Minigame Plugin: Clash Royale \n[HBPVP-CORE] All
		// minigame plugins are required for the core to function properly,
		// disabling...");
		// } else {
		// System.out.println("[HBPVP-CORE] Successfully hooked into Clash Royale!");
		// }
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		getCommand("rank").setExecutor(new RankCommand());
		getCommand("emotes").setExecutor(new EmotesCommand());
		getCommand("admin").setExecutor(new AdminCommand());
		getCommand("gmc").setExecutor(new GMCCommand());
		getCommand("gms").setExecutor(new GMSCommand());
		getCommand("gma").setExecutor(new GMACommand());
		getCommand("gmsp").setExecutor(new GMSPCommand());
		getCommand("build").setExecutor(new BuildCommand());
		getCommand("ban").setExecutor(new BanCommand());
		getCommand("tempban").setExecutor(new TempBanCommand());
		getCommand("mute").setExecutor(new MuteCommand());
		getCommand("unban").setExecutor(new UnbanCommand());
		getCommand("unmute").setExecutor(new UnmuteCommand());
		getCommand("speed").setExecutor(new SpeedCommand());
		getCommand("coins").setExecutor(new CoinsCommand());
		getCommand("trophies").setExecutor(new TrophyCommand());
		getCommand("fly").setExecutor(new FlyCommand());
		getCommand("message").setExecutor(new MessageCommand());
		getCommand("announce").setExecutor(new AnnounceCommand());
		getCommand("kaboom").setExecutor(new KaboomCommand());
		getCommand("nick").setExecutor(new NickCommand());
		getCommand("forcecredits").setExecutor(new ForceCreditsCommand());
		getCommand("rankcolor").setExecutor(new PlusColorCMD());
		getCommand("mutechat").setExecutor(new MuteChatCMD());
		getCommand("songtest").setExecutor(new SongTestCMD());
		getCommand("hologram").setExecutor(new HologramCommand());
		getCommand("shop").setExecutor(new ShopCommand());
		getCommand("rename").setExecutor(new RenameItemCommand());
		getCommand("unlockitem").setExecutor(new UnlockItemCommand());
		getCommand("discord").setExecutor(new DiscordCommand());
		getCommand("sudo").setExecutor(new SudoCommand());
		getCommand("leveltest").setExecutor(new LevelTest());
		getCommand("level").setExecutor(new LevelCommand());
		getCommand("lore").setExecutor(new LoreCommand());
		getCommand("resetlevels").setExecutor(new LevelResetCommand(this));
		getCommand("resetstats").setExecutor(new StatResetCommand(this));
		getCommand("setenv").setExecutor(new EnvironmentCommand());
		getCommand("history").setExecutor(new HistoryCommand());
		getCommand("showdemo").setExecutor(new ShowDemoCommand());
		getCommand("rainbowparticle").setExecutor(new RainbowParticleCommand());
		getCommand("clearoldusers").setExecutor(new ClearOldUsersCommand(this));
		getCommand("onepiece").setExecutor(new OnePieceCommand(this));
		getCommand("sbelection").setExecutor(new SkyBlockElectionCommand(this));
		getCommand("tpall").setExecutor(new TPAllCommand());
		getCommand("warpbuildarea").setExecutor(new WarpBuildAreaCommand());
		getCommand("whereami").setExecutor(new WhereAmICommand());







	}

	private void openConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			return;
		}

		connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database,
				this.username, this.password);
	}

	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ps;
	}


	private void createTables() {
		if (!tableExists("userinformation")) {
			executeUpdate("CREATE TABLE `userinformation` (\n"
					+ "  `UUID` varchar(36) NOT NULL COMMENT 'Player UUID',\n"
					+ "  `RANK` varchar(25) NOT NULL DEFAULT 'Member|Member' COMMENT 'Format: PERMRANK|VISRANK',\n"
					+ "  `PERMS` text NOT NULL,\n"
					+ "  `NICK` varchar(16) NOT NULL COMMENT 'Set to their name if unnicked',\n"
					+ "  `SKIN` varchar(16) NOT NULL COMMENT 'Set to their name if unnicked',\n"
					+ "  `VANISH` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'True/False',\n"
					+ "  `CREDITS` int(11) NOT NULL DEFAULT '0' COMMENT 'Global credits',\n"
					+ "  `PARTYOWNERUUID` text COMMENT 'Personal UUID if they own party',\n"
					+ "  `FRIENDS` mediumtext COMMENT 'List of UUIDs, seperated by |',\n"
					+ "  `PLUSCOLOR` text COMMENT 'Plus Color for King+',\n"
					+ "  `LEVEL` int(11) NOT NULL DEFAULT '1' COMMENT 'Network Level'\n" + ")\n");
			executeUpdate("ALTER TABLE `userinformation`\n" + "  ADD PRIMARY KEY (`UUID`);\n" + "");
		}
		if (!tableExists("playersettings")) {
			executeUpdate("CREATE TABLE `playersettings` (\n" + "  `UUID` varchar(36) NOT NULL,\n"
					+ "  `PLAYERVIS` tinyint(1) NOT NULL,\n" + "  `ACTIVEGADGET` tinytext,\n"
					+ "  `PMPRIVACY` tinytext NOT NULL,\n" + "  `FRIENDPRIVACY` tinytext NOT NULL,\n"
					+ "  `PARTYPRIVACY` tinytext NOT NULL,\n" + "  `JOINMSG` tinyint(1) NOT NULL,\n"
					+ "  `PROFANITYFILTER` tinytext NOT NULL,\n" + "  `LOBBYMENTION` tinyint(1) NOT NULL\n" + ")\n");
			executeUpdate("ALTER TABLE `playersettings`\n" + "  ADD PRIMARY KEY (`UUID`);\n" + "");
		}
		if (!tableExists("parties")) {
			executeUpdate("CREATE TABLE `parties` (\n" + "  `PARTYID` int(11) NOT NULL,\n"
					+ "  `POWNERUUID` varchar(36) NOT NULL,\n" + "  `MEMBERS` text NOT NULL,\n"
					+ "  `OPEN` tinyint(1) NOT NULL\n" + ")\n");
			executeUpdate("ALTER TABLE `parties`\n" + "  ADD PRIMARY KEY (`POWNERUUID`,`PARTYID`);\n" + "");
		}
		if (!tableExists("bans")) {
			executeUpdate("CREATE TABLE `bans` (\n" + "  `UUID` varchar(36) NOT NULL,\n" + "  `BANNED` text,\n"
					+ "  `MUTED` text,\n"
					+ "  `TEMPBAN` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
					+ "  `TEMPMUTE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'\n" + ")\n");
			executeUpdate("ALTER TABLE `bans`\n" + "  ADD PRIMARY KEY (`UUID`);\n" + "");
		}
		if (!tableExists("messages")) {
				executeUpdate("CREATE TABLE `messages` (\n" + "  `VALUE` tinytext NOT NULL,\n"
					+ "  `MESSAGE` text NOT NULL\n" + ")\n" + "");
		}
		if (!tableExists("ranks")) {
			executeUpdate("CREATE TABLE `ranks` (\n" + "  `ID` int(11) NOT NULL,\n" + "  `DISPLAY` tinytext NOT NULL,\n"
					+ "  `PREFIX` tinytext NOT NULL,\n" + "  `SUFFIX` tinytext NOT NULL,\n"
					+ "  `PERMS` text NOT NULL,\n" + "  `PREFIXCOLOR` text NOT NULL,\n" + "  `PARENTS` text" + ")\n");
			executeUpdate("ALTER TABLE `ranks`\n" + "  ADD PRIMARY KEY (`ID`);\n" + "");
		}
		if (!tableExists("nickhistory")) {
			executeUpdate("CREATE TABLE `nickhistory` (\n" + "  `UUID` varchar(36) NOT NULL,\n"
					+ "  `NICK` varchar(16) NOT NULL,\n" + "  `START` timestamp NOT NULL,\n"
					+ "  `END` timestamp NOT NULL\n" + ");");
			executeUpdate("ALTER TABLE `nickhistory`\n" + "  ADD PRIMARY KEY (`UUID`,`NICK`);");
		}
		if (!tableExists("settings")) {
			executeUpdate("CREATE TABLE `settings` (\n" + "  `KEY` varchar(50) NOT NULL,\n"
					+ "  `VALUE` varchar(1000) NOT NULL\n" + ");");
			executeUpdate("ALTER TABLE `nickhistory`\n" + "  ADD PRIMARY KEY (`KEY`);");
		}
		if (!tableExists("nickdata")) {
			executeUpdate("CREATE TABLE `nickdata` (\n" + "  `ID` int NOT NULL,\n" + "  `TYPE` varchar(4) NOT NULL,\n"
					+ "  `NAME` varchar(40) NOT NULL,\n" + "  `DATA` mediumtext NOT NULL\n" + ");");
			executeUpdate("ALTER TABLE `nickhistory`\n" + "  ADD PRIMARY KEY (`ID`);");
		}
	}

	public boolean tableExists(String name) {
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, name, null);
			if (tables.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}



	private void updateTables() {
		try {
			if (!rowExists("messages", "VALUE", "noperm"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('noperm', '&cYou do not have permission to execute this command!')");

			if (!rowExists("messages", "VALUE", "invalidarg"))
				executeUpdate("INSERT INTO `messages` VALUES ('invalidarg', '&cInvalid argument.')");

			if (!rowExists("messages", "VALUE", "invalidplayer"))
				executeUpdate("INSERT INTO `messages` VALUES ('invalidplayer', '&cInvalid player.')");

			if (!rowExists("messages", "VALUE", "playeroffline"))
				executeUpdate("INSERT INTO `messages` VALUES ('playeroffline', '&cThat player is currently offline.')");

			if (!rowExists("messages", "VALUE", "admin"))
				executeUpdate("INSERT INTO `messages` VALUES ('admin', 'Ranks updated accordingly.')");

			if (!rowExists("messages", "VALUE", "announce"))
				executeUpdate("INSERT INTO `messages` VALUES ('announce', '&aCreating announcement...')");

			if (!rowExists("messages", "VALUE", "newannouncement"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('newannounce', '&aAnnouncement created! Dispatching it now...')");

			if (!rowExists("messages", "VALUE", "ban"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('ban', '&a{arg1} &7 has been banned for &a{arg2} &7because &a{arg3}')");

			if (!rowExists("messages", "VALUE", "banpublic"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('banpublic', '&a{arg1} &7has been banned for &a{arg2} &7because &a{arg3}')");

			if (!rowExists("messages", "VALUE", "feed"))
				executeUpdate("INSERT INTO `messages` VALUES ('feed', '&aYou have been fed')");

			if (!rowExists("messages", "VALUE", "feedother"))
				executeUpdate("INSERT INTO `messages` VALUES ('feedother', '&aYou have fed {arg1}')");

			if (!rowExists("messages", "VALUE", "flyen"))
				executeUpdate("INSERT INTO `messages` VALUES ('flyen', '&aFlight has been enabled.')");

			if (!rowExists("messages", "VALUE", "flydis"))
				executeUpdate("INSERT INTO `messages` VALUES ('flydis', '&aFlight has been disabled.')");

			if (!rowExists("messages", "VALUE", "flyenoth"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('flyenoth', '&aFlight has been enabled for &6{arg1}&a.')");

			if (!rowExists("messages", "VALUE", "flydisoth"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('flydisoth', '&aFlight has been disabled for &6{arg1}&a.')");

			if (!rowExists("messages", "VALUE", "gm"))
				executeUpdate("INSERT INTO `messages` VALUES ('gm', '&aYour gamemode has been set to &6{arg1}&a.')");

			if (!rowExists("messages", "VALUE", "gmother"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('gmother', '&aYou have set the gamemode of &6{arg1} &ato &6{arg2}&a.')");

			if (!rowExists("messages", "VALUE", "heal"))
				executeUpdate("INSERT INTO `messages` VALUES ('heal', '&aYou have been healed.')");

			if (!rowExists("messages", "VALUE", "healo"))
				executeUpdate("INSERT INTO `messages` VALUES ('healo', '&aYou have healed &6{arg1}&a.')");

			if (!rowExists("messages", "VALUE", "kaboom"))
				executeUpdate("INSERT INTO `messages` VALUES ('kaboom', '&6Launched {arg1}!')");

			if (!rowExists("messages", "VALUE", "killself"))
				executeUpdate("INSERT INTO `messages` VALUES ('killself', '&cYou cannot kill yourself!')");

			if (!rowExists("messages", "VALUE", "kill"))
				executeUpdate("INSERT INTO `messages` VALUES ('kill', '&aYou have killed &6{arg1}&a.')");

			if (!rowExists("messages", "VALUE", "killo"))
				executeUpdate("INSERT INTO `messages` VALUES ('killo', '&aYou were killed by &6{arg1}&a.')");

			if (!rowExists("messages", "VALUE", "mute"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('mute', '&cYou do not have permission to execute this command!')");
			/*
			 * - Todo: All ban msgs and mute msgs - pls do this - dont forget - pls sir -
			 * dont forgetti spaghetti
			 */

			if (!rowExists("messages", "VALUE", "nickname"))
				executeUpdate("INSERT INTO `messages` VALUES ('nickname', '&aYou are now nicked as {arg1}')");

			if (!rowExists("messages", "VALUE", "nickskin"))
				executeUpdate("INSERT INTO `messages` VALUES ('nickskin', '&aYour skin is now {arg1}')");

			if (!rowExists("messages", "VALUE", "nicklength"))
				executeUpdate("INSERT INTO `messages` VALUES ('nicklength', '&aThat nickname is too long!')");

			if (!rowExists("messages", "VALUE", "nickself"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('nickself', '&aThat is your name! To unnick, do /nick reset OR /unnick')");

			if (!rowExists("messages", "VALUE", "unnick"))
				executeUpdate("INSERT INTO `messages` VALUES ('unnick', '&aNickname reset')");

			if (!rowExists("messages", "VALUE", "nickhistory"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('nickhistory', '&6-- Nickname history from the past {arg1}{arg2} --')");

			if (!rowExists("messages", "VALUE", "rankedit"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('rankedit', '&7You are now &6editing &7the rank of &6{arg1}')");

			if (!rowExists("messages", "VALUE", "realname"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('realname', '&cYou do not have permission to execute this command!')");

			if (!rowExists("messages", "VALUE", "servertype"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('servertype', '&cYou do not have permission to execute this command!')");

			if (!rowExists("messages", "VALUE", "set"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('set', '&cYou do not have permission to execute this command!')");

			if (!rowExists("messages", "VALUE", "sudo"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('sudo', '&cYou do not have permission to execute this command!')");

			if (!rowExists("messages", "VALUE", "teleport"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('teleport', '&cYou do not have permission to execute this command!')");

			if (!rowExists("messages", "VALUE", "time"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('time', '&cYou do not have permission to execute this command!')");

			if (!rowExists("messages", "VALUE", "unnick"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('unnick', '&cYou do not have permission to execute this command!')");

			if (!rowExists("messages", "VALUE", "vanish"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('vanish', '&cYou do not have permission to execute this command!')");

			if (!rowExists("messages", "VALUE", "weather"))
				executeUpdate(
						"INSERT INTO `messages` VALUES ('weather', '&cYou do not have permission to execute this command!')");

			if (!rowExists("ranks", "DISPLAY", "Member")) {
				executeUpdate(
						"INSERT INTO `ranks` (`ID`, `DISPLAY`, `PREFIX`, `SUFFIX`, `PERMS`, `PREFIXCOLOR`, `PARENTS`) VALUES\n"
								+ "(0, 'Owner', '&c&lOWNER', '&c', 'farwatch.nick%farwatch.nick.plus%farwatch.admin%farwatch.announce%farwatch.ban%farwatch.feed%farwatch.fly%farwatch.fly.other%farwatch.gamemode.adventure%farwatch.gamemode.adventure.other%farwatch.gamemode.creative%farwatch.gamemode.creative.other%farwatch.gamemode.survival%farwatch.gamemode.survival.other%farwatch.gamemode.spectator%farwatch.gamemode.spectator.other%farwatch.servertype%farwatch.nick.reset%farwatch.vanish%farwatch.weather%farwatch.sudo%farwatch.set%farwatch.rank%farwatch.kaboom%farwatch.heal%farwatch.kill%farwatch.mute%farwatch.nick.history%farwatch.realname%farwatch.teleport%farwatch.teleport.other%farwatch.time', 'RED', 'Admin%Mod%Helper%YouTube%PlatinumPlus%Platinum%Ruby%Sapphire%Cobalt%Member'),\n"
								+ "(1, 'Admin', '&c&lADMIN', '&c', 'farwatch.nick%farwatch.nick.plus%farwatch.admin%farwatch.announce%farwatch.ban%farwatch.feed%farwatch.fly%farwatch.fly.other%farwatch.gamemode.adventure%farwatch.gamemode.adventure.other%farwatch.gamemode.creative%farwatch.gamemode.creative.other%farwatch.gamemode.survival%farwatch.gamemode.survival.other%farwatch.gamemode.spectator%farwatch.gamemode.spectator.other%farwatch.servertype%farwatch.nick.reset%farwatch.vanish%farwatch.weather%farwatch.sudo%farwatch.set%farwatch.rank%farwatch.kaboom%farwatch.heal%farwatch.kill%farwatch.mute%farwatch.nick.history%farwatch.realname%farwatch.teleport%farwatch.teleport.other%farwatch.time', 'RED', 'Mod%Helper%YouTube%PlatinumPlus%Platinum%Ruby%Sapphire%Cobalt%Member'),\n"
								+ "(3, 'Mod', '&2&lMOD', '&2', 'farwatch.help%farwatch.nick%farwatch.mute', 'GREEN', 'YouTube%PlatinumPlus%Platinum%Ruby%Sapphire%Cobalt%Member'),\n"
								+ "(4, 'Helper', '&9&lHELPER', '&d', 'farwatch.help%farwatch.nick%farwatch.nick.plus', 'LIGHT_PURPLE', 'PlatinumPlus%Platinum%Ruby%Sapphire%Cobalt%Member'),\n"
								+ "(5, 'YouTube', '&4&lY&f&lT', '&c', 'farwatch.help%farwatch.nick%farwatch.nick.plus', 'RED', 'PlatinumPlus%Platinum%Ruby%Sapphire%Cobalt%Member'),\n"
								+ "(6, 'KingPlus', '&6KING', '&b', 'farwatch.help%farwatch.nick', 'BLUE', 'Platinum%Ruby%Sapphire%Cobalt%Member'),\n"
								+ "(7, 'King', '&6KING', '&b', 'farwatch.help', 'BLUE', 'Ruby%Sapphire%Cobalt%Member'),\n"
								+ "(8, 'HeroPlus', '&dHERO&a+', '&d', 'farwatch.help', 'DARK_RED', 'Sapphire%Cobalt%Member'),\n"
								+ "(9, 'Hero', '&dHERO', '&d', 'farwatch.help', 'DARK_BLUE', 'Cobalt%Member'),\n"
								+ "(10, 'Test', '&1&lCOBALT', '&1', 'farwatch.help', 'DARK_AQUA', 'Member'),\n"
								+ "(11, 'Normal', '&7', '&7', 'farwatch.help', 'GRAY', '');");
			}

			if (!rowExists("settings", "KEY", "MOTD")) {
				executeUpdate(
						"INSERT INTO `settings` VALUES ('MOTD', '           &c&oHerobrine PVP&6&o [1.8 -> 1.16.5]                                         &6&nEvents Server!');");
				executeUpdate("INSERT INTO `settings` VALUES ('MAXPLAYERS', '115');");
			}

		} catch (NullPointerException e) {
			System.out.println("NullPointerException while updating tables -> database connection...");
		}
	}

	public boolean rowExists(String table, String key, String value) {
		try {
			ResultSet rs = executeQuery(
					"SELECT COUNT(1)\n" + "FROM " + table + "\n" + "WHERE " + key + " = " + value + ";");
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public ResultSet executeQuery(String sql) {

		try {

			ResultSet rs = prepareStatement(sql).executeQuery();
			return rs;

		} catch (Exception e) {
			return null;
		}

	}

	public void executeUpdate(String sql) {

		try {

			prepareStatement(sql).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FileManager getFileManager() {
		return fileManager;
	}

	public static void getRank(Player player) {
		HerobrinePVPCore.getFileManager().getRank(player);
	}

	public static ChatColor getRankColor(Player player) {
		Ranks rank = HerobrinePVPCore.getFileManager().getRank(player);
		return rank.getColor();
	}

	public boolean isBuildEnabled(Player player) {
		if (BuildCommand.buildEnabledPlayers.contains(player)) {
			return true;
		}
		return false;
	}

	public static void buildSidebar(Player player) {

		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

		Objective obj = board.registerNewObjective("board", "dummy");
		obj.setDisplayName(ChatColor.RED + "Herobrine PVP");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score title = obj.getScore(ChatColor.translateAlternateColorCodes('&', "&bThe best streamer event server!"));
		title.setScore(7);

		Score blank = obj.getScore(" ");
		blank.setScore(6);
		Team rank = board.registerNewTeam("rank");

		rank.addEntry(ChatColor.WHITE.toString());
		rank.setPrefix(ChatColor.AQUA + "Rank: ");
		if (HerobrinePVPCore.getFileManager().getRank(player).hasPlusColor()) {
			rank.setSuffix(getFileManager().getRank(player).getColor() + getFileManager().getRank(player).getName()
					+ translateString(getFileManager().getPlusColor(player.getUniqueId()) + "+"));
		} else {
			rank.setSuffix(HerobrinePVPCore.getFileManager().getRank(player).getColor()
					+ HerobrinePVPCore.getFileManager().getRank(player).getName());
		}
		obj.getScore(ChatColor.WHITE.toString()).setScore(5);

		Team coins = board.registerNewTeam("coins");

		coins.addEntry(ChatColor.YELLOW.toString());
		coins.setPrefix(ChatColor.YELLOW + "Coins: ");
		coins.setSuffix(
				ChatColor.translateAlternateColorCodes('&', "&e") + HerobrinePVPCore.getFileManager().getCoins(player));
		obj.getScore(ChatColor.YELLOW.toString()).setScore(4);

		Team trophies = board.registerNewTeam("trophies");

		trophies.addEntry(ChatColor.GOLD.toString());
		trophies.setPrefix(ChatColor.GOLD + "Trophies: ");
		trophies.setSuffix(ChatColor.translateAlternateColorCodes('&', "&6")
				+ HerobrinePVPCore.getFileManager().getTrophies(player));
		obj.getScore(ChatColor.GOLD.toString()).setScore(3);

		Score blank2 = obj.getScore("  ");
		blank2.setScore(2);
		Score ip;
		if(getFileManager().getEnvironment().equals("PRODUCTION"))
		ip = obj.getScore(ChatColor.translateAlternateColorCodes('&', "&cherobrinepvp.beastmc.com"));
		else
			ip = obj.getScore(ChatColor.translateAlternateColorCodes('&', "&cDevelopment Server"));
		ip.setScore(1);
		player.setCustomName(HerobrinePVPCore.getFileManager().getRank(player).getColor()
				+ HerobrinePVPCore.getFileManager().getRank(player).getName() + " " + player.getName());
		player.setScoreboard(board);

	}

	public static String dateDifference(Date d1, Date d2) {
		long differenceInTime = d2.getTime() - d1.getTime();

		long differenceInSeconds = (differenceInTime / 1000) % 60;

		long differenceInMinutes = (differenceInTime / (1000 * 60)) % 60;

		long differenceInHours = (differenceInTime / (1000 * 60 * 60)) % 24;

		long differenceInYears = (differenceInTime / (1000l * 60 * 60 * 24 * 365));

		long differenceInDays = (differenceInTime / (1000 * 60 * 60 * 24)) % 365;

		String string = differenceInYears + "y " + differenceInDays + "d " + differenceInHours + "h "
				+ differenceInMinutes + "m " + differenceInSeconds + "s";

		;

		if (differenceInYears <= 0) {
			string.replace(differenceInYears + "y", "");
			System.out.println(string);
		}
		if (differenceInDays <= 0 || differenceInYears > 0) {
			string.replace("0d ", "");
		}

		if (differenceInMinutes <= 0) {
			string.replace("0m ", "");
		}

		if (differenceInSeconds <= 0) {
			string.replace("0s ", "");
		}

		return string;

	}

	public static void buildAPISidebar(Player player, Scoreboard board) {

		player.setScoreboard(board);
	}

	public BlockHuntMain getBlockHuntAPI() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("BlockHunt");
		if (plugin instanceof BlockHuntMain) {
			return (BlockHuntMain) plugin;
		} else {
			return null;
		}

	}

	public GameCoreMain getGameCoreAPI() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("GameCore");
		if (plugin instanceof GameCoreMain) {
			return (GameCoreMain) plugin;
		} else {
			return null;
		}

	}

	public NoteBlockAPI getNBAPI() {
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("NoteBlockAPI");
		if (plugin instanceof NoteBlockAPI) {
			return (NoteBlockAPI) plugin;
		} else {
			return null;
		}
	}

	/**
	 * Only works in 1.8+.
	 *
	 * @param name
	 * @param player
	 */
	public static void changeName(String name, Player player) {
		try {
			Method getHandle = player.getClass().getMethod("getHandle", (Class<?>[]) null);
			try {
				Class.forName("com.mojang.authlib.GameProfile");
			} catch (ClassNotFoundException e) {
				Bukkit.broadcastMessage("CHANGE NAME METHOD DOES NOT WORK IN 1.7 OR LOWER!");
				return;
			}
			Object profile = getHandle.invoke(player).getClass().getMethod("getProfile")
					.invoke(getHandle.invoke(player));
			Field ff = profile.getClass().getDeclaredField("name");
			ff.setAccessible(true);
			ff.set(profile, ChatColor.translateAlternateColorCodes('&', name));
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.hidePlayer(player);
				players.showPlayer(player);
			}
			player.hidePlayer(player);
			player.showPlayer(player);
			player.setPlayerListName(HerobrinePVPCore.getFileManager().getRank(player).getColor() + HerobrinePVPCore.getFileManager().getRank(player).getName() + " " + name);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	public static String translateString(String string) {

		return ChatColor.translateAlternateColorCodes('&', string);
	}

	// public ClashRoyaleMain getCRAPI() {
	// Plugin plugin =
	// Bukkit.getServer().getPluginManager().getPlugin("ClashRoyale");
	// if (plugin instanceof ClashRoyaleMain) {
	// return(ClashRoyaleMain)plugin;
	// }
//
	// else {
	// return null;
	// }
//	}

}
