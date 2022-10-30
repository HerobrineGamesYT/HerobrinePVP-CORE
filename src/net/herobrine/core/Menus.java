package net.herobrine.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.herobrine.gamecore.ClassTypes;
import net.herobrine.gamecore.Games;

public class Menus {
	public static void applyGameModeSelection(Player player) {
		Inventory selectionMenu = Bukkit.createInventory(null, 27, ChatColor.GRAY + "Game Selection");

		ItemStack sw = new ItemStack(Material.EYE_OF_ENDER, 1);
		ItemMeta swMeta = sw.getItemMeta();
		swMeta.setDisplayName(ChatColor.GREEN + "Skywars");
		sw.setItemMeta(swMeta);
		ItemStack std = new ItemStack(Material.LAVA_BUCKET, 1);
		ItemMeta stdMeta = std.getItemMeta();
		stdMeta.setDisplayName(ChatColor.RED + "Survive The Disaster");
		std.setItemMeta(stdMeta);
		ItemStack oitc = new ItemStack(Material.GRASS, 1);
		ItemMeta oitcMeta = oitc.getItemMeta();
		oitcMeta.setDisplayName(ChatColor.GREEN + "Block Hunt");
		oitc.setItemMeta(oitcMeta);
		ItemStack sg = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta sgMeta = sg.getItemMeta();
		sgMeta.setDisplayName(ChatColor.AQUA + "Battle Clash");
		sg.setItemMeta(sgMeta);
		ItemStack bw = new ItemStack(Material.BED, 1);
		ItemMeta bwMeta = bw.getItemMeta();
		bwMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Bed Wars");
		bw.setItemMeta(bwMeta);

		ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);

		ItemMeta fillerMeta = filler.getItemMeta();
		fillerMeta.setDisplayName(" ");

		filler.setItemMeta(fillerMeta);

		SkullMaker wsg = new SkullMaker(ChatColor.YELLOW + "Walls SG", Arrays.asList(""),
				"http://textures.minecraft.net/texture/7974325fb019d591af4b2e6e4688714b1e95a8dbf981ebec22fa3538e19ec9d0");

		ItemBuilder comingSoon = new ItemBuilder(Material.PAPER);
		comingSoon.setDisplayName(ChatColor.GRAY + "Coming Soon");

		// reserved game slots: 10-16;

		int currentSlots = 0;

		while (currentSlots < selectionMenu.getSize()) {

			if (currentSlots != 10 && currentSlots != 11 && currentSlots != 12 && currentSlots != 13
					&& currentSlots != 14 && currentSlots != 15 && currentSlots != 16) {
				selectionMenu.setItem(currentSlots, filler);
			}

			currentSlots++;
		}

		selectionMenu.setItem(12, comingSoon.build());
		selectionMenu.setItem(13, wsg.getSkull());
		selectionMenu.setItem(14, sg);

		player.openInventory(selectionMenu);
	}

	public static void applyGamemodeMenu(Player player) {
		Inventory gameMenu = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Which gamemode?");

		ItemBuilder vanilla = new ItemBuilder(Material.DIAMOND_SWORD);

		vanilla.setDisplayName("&bBattle Clash&7: &aOriginal");
		vanilla.setLore(
				Arrays.asList("", HerobrinePVPCore.translateString("&7A TDM gamemode with custom classes based on"),
						HerobrinePVPCore.translateString("&7your favorite Clash Royale characters.")));

		ItemBuilder towers = new ItemBuilder(Material.IRON_AXE);

		towers.setDisplayName("&bBattle Clash&7: &aTowers");
		towers.setLore(
				Arrays.asList("", HerobrinePVPCore.translateString("&7Attack enemy towers and defend your own!")));

		gameMenu.setItem(12, vanilla.build());
		gameMenu.setItem(14, towers.build());

		player.openInventory(gameMenu);
	}

	public static void applyShopMenu(Player player) {

		Inventory shopMenu = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Shop");

		// 12-14

		ItemBuilder cosmetics = new ItemBuilder(Material.DIAMOND);
		cosmetics.setDisplayName("&bCosmetics");
		cosmetics.setLore(Arrays.asList(HerobrinePVPCore.translateString("&7Purchase emotes and cosmetics here!")));
		ItemBuilder classes = new ItemBuilder(Material.DIAMOND_SWORD);
		classes.setDisplayName("&aClasses");
		classes.setLore(Arrays.asList(HerobrinePVPCore.translateString("&7Buy classes for your favorite games!")));

		ItemBuilder ranks = new ItemBuilder(Material.GOLD_INGOT);
		ranks.setDisplayName("&6Ranks");
		ranks.setLore(Arrays.asList(ChatColor.GRAY + "Purchase ranks to support the server! (Coming Soon)"));
		shopMenu.setItem(12, cosmetics.build());
		shopMenu.setItem(13, classes.build());
		shopMenu.setItem(14, ranks.build());

		player.openInventory(shopMenu);
	}

	public static void applyCosmeticShopMenu(Player player) {

		Inventory shopMenu = Bukkit.createInventory(null, 27,
				HerobrinePVPCore.translateString("&aShop &7- &bCosmetics"));

		// 12 and 14

		ItemBuilder cosmetics = new ItemBuilder(Material.DIAMOND);
		cosmetics.setDisplayName("&bCosmetics");
		cosmetics.setLore(
				Arrays.asList(HerobrinePVPCore.translateString("&cCosmetics will be available for purchase soon!")));

		ItemStack emotes = new SkullMaker("&bEmotes",
				Arrays.asList(HerobrinePVPCore.translateString("&7Purchase emotes here!")),
				"http://textures.minecraft.net/texture/3baabe724eae59c5d13f442c7dc5d2b1c6b70c2f83364a488ce5973ae80b4c3")
				.getSkull();

		shopMenu.setItem(12, cosmetics.build());
		shopMenu.setItem(14, emotes);

		ItemBuilder goBack = new ItemBuilder(Material.ARROW);
		goBack.setDisplayName("&cGo back");
		goBack.setLore(Arrays.asList(ChatColor.GRAY + "Return to the previous page"));

		shopMenu.setItem(18, goBack.build());

		player.openInventory(shopMenu);
	}

	public static void applyClassShopMenu(Player player) {

		Inventory shopMenu = Bukkit.createInventory(null, 54, HerobrinePVPCore.translateString("&aShop &7- &aClasses"));

		for (ClassTypes type : ClassTypes.values()) {
			if (type.getGame().equals(Games.CLASH_ROYALE)) {

				ItemStack is = new ItemStack(type.getMaterial());
				ItemMeta isMeta = is.getItemMeta();
				isMeta.setDisplayName(type.getDisplay());
				isMeta.setLore(Arrays.asList(type.getDescription()));
				is.setItemMeta(isMeta);

				if (type.isUnlockable() && HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.CLASS,
						type.toString(), player.getUniqueId())) {

					List<String> lore = new ArrayList<>();

					lore.addAll(Arrays.asList(type.getDescription()));

					lore.add("");
					lore.add(ChatColor.GREEN + "You already have this class unlocked!");

					isMeta.setLore(lore);
					isMeta.addEnchant(Enchantment.DURABILITY, 1, true);
					isMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					isMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					is.setItemMeta(isMeta);
					shopMenu.addItem(is);
				}

				if (type.isUnlockable() && !HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.CLASS,
						type.toString(), player.getUniqueId())) {
					List<String> lore = new ArrayList<>();

					lore.addAll(Arrays.asList(type.getDescription()));

					lore.add("");
					lore.add(ChatColor.YELLOW + "Available In: " + type.getGame().getDisplay());
					lore.add(ChatColor.GRAY + "Cost: " + ChatColor.YELLOW + "6000 Coins");
					lore.add(ChatColor.YELLOW + "Click to buy!");
					isMeta.setLore(lore);
					is.setItemMeta(isMeta);
					shopMenu.addItem(is);

				}

			}
		}
		ItemBuilder goBack = new ItemBuilder(Material.ARROW);
		goBack.setDisplayName("&cGo back");
		goBack.setLore(Arrays.asList(ChatColor.GRAY + "Return to the previous page"));

		shopMenu.setItem(45, goBack.build());
		player.openInventory(shopMenu);
	}

	public static void applyEmoteShopMenu(Player player) {
		Inventory shopMenu = Bukkit.createInventory(null, 54, HerobrinePVPCore.translateString("&aShop &7- &bEmotes"));

		for (Emotes emote : Emotes.values()) {

			if (emote.isUnlockRequired() && !HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.EMOTE,
					emote.toString(), player.getUniqueId())) {

				ItemBuilder item = new ItemBuilder(Material.PAPER);

				item.setDisplayName(ChatColor.GOLD + emote.getKey());
				item.setLore(Arrays.asList(emote.getDisplay(), HerobrinePVPCore.translateString("&7Cost: &e750"),
						ChatColor.YELLOW + "Click to buy!"));
				shopMenu.addItem(item.build());

			}

			else if (emote.isUnlockRequired() && HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.EMOTE,
					emote.toString(), player.getUniqueId())) {
				ItemBuilder item = new ItemBuilder(Material.PAPER);

				item.setDisplayName(ChatColor.GOLD + emote.getKey());
				item.setLore(Arrays.asList(emote.getDisplay(),
						HerobrinePVPCore.translateString("&aYou already have this emote!")));
				item.addEnchant(Enchantment.DURABILITY, 1);
				item.addItemFlag(ItemFlag.HIDE_ENCHANTS);
				item.addItemFlag(ItemFlag.HIDE_ATTRIBUTES);

				shopMenu.addItem(item.build());
			}

		}
		ItemBuilder goBack = new ItemBuilder(Material.ARROW);
		goBack.setDisplayName("&cGo back");
		goBack.setLore(Arrays.asList(ChatColor.GRAY + "Return to the previous page"));

		shopMenu.setItem(45, goBack.build());
		player.openInventory(shopMenu);
	}

	public static void applyProfileMenu(Player player) {

		Inventory profileMenu = Bukkit.createInventory(null, 27,
				HerobrinePVPCore.translateString("&7Profile - &6" + player.getName()));

		ItemStack profile = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta profileMeta = (SkullMeta) profile.getItemMeta();

		profileMeta.setOwner(player.getName());
		profileMeta.setDisplayName(HerobrinePVPCore.getFileManager().getRank(player).getColor() + player.getName());
		profileMeta
				.setLore(Arrays.asList(
						ChatColor.GREEN + "Rank: " + HerobrinePVPCore.getFileManager().getRank(player).getColor()
								+ HerobrinePVPCore.getFileManager().getRank(player).getName(),
						ChatColor.GREEN + "Level: " + HerobrinePVPCore.translateString(HerobrinePVPCore.getFileManager().getPrestige(
								HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId())).colorString + HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()))));
		profile.setItemMeta(profileMeta);


		ItemBuilder stats = new ItemBuilder(Material.PAPER);

		stats.setDisplayName(ChatColor.AQUA + "Stats Viewer");
		stats.setLore(ChatColor.GREEN + "View your in-game stats here!");


		ItemBuilder prestige = new ItemBuilder(Material.DIAMOND);

		prestige.setDisplayName(ChatColor.GOLD + "Prestige Menu");

		prestige.setLore(ChatColor.BLUE + "View information about level prestige here!");





		profileMenu.setItem(4, profile);
		profileMenu.setItem(12, stats.build());
		profileMenu.setItem(14, prestige.build());



		player.openInventory(profileMenu);
	}

	public static void applyRankShopMenu(Player player) {

		player.sendMessage(ChatColor.RED + "Ranks will be available for purchase soon!");
		player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);

	}

	public static void applyStatsMenu(Player player) {



		Inventory statsMenu = Bukkit.createInventory(null, 54, HerobrinePVPCore.translateString("&7Profile - &6Stats"));


		ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);

		ItemMeta fillerMeta = filler.getItemMeta();
		fillerMeta.setDisplayName(" ");

		filler.setItemMeta(fillerMeta);



		int currentSlots = 0;

		while (currentSlots < statsMenu.getSize()) {

			if (currentSlots < 10 || currentSlots > 43 || currentSlots == 36 || currentSlots == 18 || currentSlots == 27 || currentSlots == 35 || currentSlots == 26 || currentSlots == 17) {
				statsMenu.setItem(currentSlots, filler);
			}

			currentSlots++;
		}



		int[] slots = new int[] {11, 13, 15, 29, 31, 33};

		int i = 0;

		for (Games game : Games.values()) {
			int i2 = 0;
			if (game.areStatsShown()) {

				System.out.println(game.getDisplay());
				ItemBuilder item = new ItemBuilder(game.getStatsItem());

				item.setDisplayName(game.getDisplay());


				ArrayList<String> lore = new ArrayList<>();


				for (String stat : game.getStatKeys()) {

					System.out.println(stat);
					lore.add(ChatColor.GRAY + game.getFriendlyStatKeys()[i2] + ": " + ChatColor.GREEN + HerobrinePVPCore.getFileManager().getGameStats(player.getUniqueId(), game, stat));




					i2++;
				}

				item.setLore(lore);

				statsMenu.setItem(slots[i], item.build());
				i++;
			}




		}


		for (int s : slots) {

			if (statsMenu.getItem(s) == null) {
				ItemBuilder filler2 = new ItemBuilder(Material.PAPER);
				filler2.setDisplayName(ChatColor.RED + "???");
				filler2.setLore(ChatColor.RED + "???");

				statsMenu.setItem(s, filler2.build());
			}

		}

		player.openInventory(statsMenu);




	}

	public static void applyPrestigeMenu(Player player) {


		Inventory prestigeMenu = Bukkit.createInventory(null, 54, HerobrinePVPCore.translateString("&7Profile - &6Prestige Menu"));

		// slots
		// 4 = skull
		// 9-14
		// 19

		ItemStack profile = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta profileMeta = (SkullMeta) profile.getItemMeta();

		profileMeta.setOwner(player.getName());
		profileMeta.setDisplayName(HerobrinePVPCore.getFileManager().getRank(player).getColor() + player.getName());
		profileMeta
				.setLore(Arrays.asList(ChatColor.GREEN + "Level: " + HerobrinePVPCore.translateString(HerobrinePVPCore.getFileManager().getPrestige(
								HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId())).colorString) + HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()), ChatColor.DARK_AQUA + "XP: " + ChatColor.AQUA +
								HerobrinePVPCore.getFileManager().getPlayerXP(player.getUniqueId()) + "/" + HerobrinePVPCore.getFileManager().getMaxXP(player.getUniqueId())
						,ChatColor.GREEN + "Prestige: " + HerobrinePVPCore.getFileManager().getPrestige(HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId())).getName()));
		profile.setItemMeta(profileMeta);


		prestigeMenu.setItem(4, profile);
		int i = 18;
		for (LevelRewards prestige : LevelRewards.values()) {




			ItemBuilder pItem = new ItemBuilder(prestige.getGuiItem());

			pItem.setDisplayName(prestige.getName());





			pItem.setLore(Arrays.asList( ChatColor.GREEN + "Starts at: " + prestige.getStartingLevel(),
					ChatColor.YELLOW + "Coin Multiplier: " + prestige.prestigeColor + prestige.getGameCoinMultiplier() + "x",
					ChatColor.AQUA + "XP Multiplier: " + prestige.getPrestigeColor() + prestige.getBaseXPBoost() + "x"));


			if (prestige == HerobrinePVPCore.getFileManager().getPrestige(HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()))) {
				pItem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
				pItem.addItemFlag(ItemFlag.HIDE_ENCHANTS);
				pItem.setLore(Arrays.asList( ChatColor.GREEN + "Starts at: " + prestige.getStartingLevel(),
						ChatColor.YELLOW + "Coin Multiplier: " + prestige.prestigeColor + prestige.getGameCoinMultiplier() + "x",
						ChatColor.AQUA + "XP Multiplier: " + prestige.getPrestigeColor() + prestige.getBaseXPBoost() + "x", "", ChatColor.GREEN + "This is your current prestige!"));
			}

			if (prestige.getStartingLevel() != 0 && prestige.getStartingLevel() != 100) {

				prestigeMenu.setItem(i, pItem.build());
				i++;



			}

			else if (prestige.getStartingLevel() == 100) {

				ItemBuilder pItem2 = new ItemBuilder(prestige.getGuiItem());

				pItem2.setDisplayName(prestige.getName());




				pItem2.setLore(Arrays.asList(new String[] { ChatColor.GREEN + "Starts at: " + prestige.getStartingLevel(),
						ChatColor.YELLOW + "Coin Multiplier: " + prestige.prestigeColor + prestige.getGameCoinMultiplier() + "x",
						ChatColor.AQUA + "XP Multiplier: " + prestige.getPrestigeColor() + prestige.getBaseXPBoost() + "x", "", ChatColor.RED + "This is the max prestige!"}));

				prestigeMenu.setItem(31, pItem2.build());
			}




		}

		ItemBuilder goBack = new ItemBuilder(Material.ARROW);

		goBack.setDisplayName(ChatColor.RED + "Go Back");
		goBack.setLore(ChatColor.GRAY + "Return to your profile");

		ItemBuilder info = new ItemBuilder(Material.BOOK);

		info.setDisplayName(ChatColor.GREEN + "What are prestiges?");
		info.setLore(Arrays.asList(ChatColor.GRAY + "Every 10 levels, you will reach a new " + ChatColor.GREEN + "prestige.", ChatColor.GRAY + "Each prestige will give you a " + ChatColor.YELLOW + "coin " + ChatColor.GRAY + "and " + ChatColor.AQUA + "XP" + ChatColor.GRAY + " multiplier.", ChatColor.GRAY + "Earn " + ChatColor.AQUA + "XP " + ChatColor.GRAY + "by playing games to reach new " + ChatColor.GREEN + "prestiges!"));

		prestigeMenu.setItem(45, goBack.build());
		prestigeMenu.setItem(49, info.build());


		player.openInventory(prestigeMenu);
	}
}
