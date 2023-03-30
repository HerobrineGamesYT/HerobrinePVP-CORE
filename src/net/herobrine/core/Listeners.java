package net.herobrine.core;

import java.util.Date;
import java.util.UUID;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.herobrine.deltacraft.game.Missions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import net.herobrine.gamecore.ClassTypes;
import net.herobrine.gamecore.GameCoreMain;
import net.herobrine.gamecore.GameState;
import net.herobrine.gamecore.GameType;
import net.herobrine.gamecore.Games;
import net.herobrine.gamecore.Manager;
import net.herobrine.gamecore.Teams;

public class Listeners implements Listener {

	@EventHandler
	public void onHunger(FoodLevelChangeEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			e.setCancelled(true);
			player.setFoodLevel(20);
		}
	}

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {

		if (HerobrinePVPCore.getFileManager().isUserRegistered(e.getPlayer())) {

			System.out.println("CONNECTION WITH IP:" + e.getRealAddress());

			String ip = e.getRealAddress().toString().replaceAll("/", "");

			if (HerobrinePVPCore.getFileManager().isBannedIP(ip)) {

				if (!HerobrinePVPCore.getFileManager().isUserRegistered(e.getPlayer())) {
					HerobrinePVPCore.getFileManager().registerUser(e.getPlayer());
				}
				HerobrinePVPCore.getFileManager().createBan(e.getPlayer().getUniqueId(), "CONSOLE", "Ban Evasion");

				e.disallow(Result.KICK_OTHER, HerobrinePVPCore
						.translateString("&cYou have been permanently banned from this server!\n&7Reason: &f"
								+ HerobrinePVPCore.getFileManager().getBanReason(e.getPlayer().getUniqueId(),
										HerobrinePVPCore.getFileManager()
												.getActivePunishmentId(e.getPlayer().getUniqueId()))
								+ "\n&7To find out more and appeal: &b&nhttps://discord.gg/dvyz32pMuQ\n&7Ban ID: "
								+ HerobrinePVPCore.getFileManager()
										.getActivePunishmentId(e.getPlayer().getUniqueId())));

			}

			if (!HerobrinePVPCore.getFileManager().isUserRegistered(e.getPlayer())) {
				HerobrinePVPCore.getFileManager().registerUser(e.getPlayer());
			}

			HerobrinePVPCore.getFileManager().updateIP(e.getPlayer(), ip);

			if (HerobrinePVPCore.getFileManager().isIPBanned(e.getPlayer().getUniqueId())) {

				if (!HerobrinePVPCore.getFileManager()
						.isBannedIP(HerobrinePVPCore.getFileManager().getIP(e.getPlayer()))) {

				}

			}

			if (HerobrinePVPCore.getFileManager().isBanned(e.getPlayer().getUniqueId())) {

				if (HerobrinePVPCore.getFileManager()
						.getBanType(e.getPlayer().getUniqueId(),
								HerobrinePVPCore.getFileManager().getActivePunishmentId(e.getPlayer().getUniqueId()))
						.equals(PunishmentType.PERMANENT)) {
					e.disallow(Result.KICK_OTHER, HerobrinePVPCore
							.translateString("&cYou have been permanently banned from this server!\n&7Reason: &f"
									+ HerobrinePVPCore.getFileManager().getBanReason(e.getPlayer().getUniqueId(),
											HerobrinePVPCore.getFileManager()
													.getActivePunishmentId(e.getPlayer().getUniqueId()))
									+ "\n&7To find out more and appeal: &b&nhttps://discord.gg/dvyz32pMuQ\n&7Ban ID: "
									+ HerobrinePVPCore.getFileManager()
											.getActivePunishmentId(e.getPlayer().getUniqueId())));
				} else if (HerobrinePVPCore.getFileManager()
						.getBanType(e.getPlayer().getUniqueId(),
								HerobrinePVPCore.getFileManager().getActivePunishmentId(e.getPlayer().getUniqueId()))
						.equals(PunishmentType.TEMPORARY)) {

					if (System.currentTimeMillis() >= HerobrinePVPCore.getFileManager().getBanExpiryTime(
							e.getPlayer().getUniqueId(),
							HerobrinePVPCore.getFileManager().getActivePunishmentId(e.getPlayer().getUniqueId()))
							* 1000) {

						HerobrinePVPCore.getFileManager().removeBan(e.getPlayer().getUniqueId(), "CONSOLE",
								"Ban Expired");
						e.allow();
					}

					else {
						Date d1 = new Date(System.currentTimeMillis());

						Date d2 = new Date(
								HerobrinePVPCore
										.getFileManager().getBanExpiryTime(e.getPlayer().getUniqueId(), HerobrinePVPCore
												.getFileManager().getActivePunishmentId(e.getPlayer().getUniqueId()))
										* 1000);

						e.disallow(Result.KICK_OTHER,
								HerobrinePVPCore.translateString("&cYou have been temporarily banned for &f"
										+ HerobrinePVPCore.dateDifference(d1, d2)
										+ "&c from this server!\n &7Reason: &f"
										+ HerobrinePVPCore.getFileManager().getBanReason(e.getPlayer().getUniqueId(),
												HerobrinePVPCore.getFileManager()
														.getActivePunishmentId(e.getPlayer().getUniqueId()))
										+ "\n&7Find out more: &bhttps://discord.gg/dvyz32pMuQ\n&7Ban ID: &f"
										+ HerobrinePVPCore.getFileManager()
												.getActivePunishmentId(e.getPlayer().getUniqueId())));
					}

				}

			}

		}

		else if (!HerobrinePVPCore.getFileManager().isUserRegistered(e.getPlayer())) {
			HerobrinePVPCore.getFileManager().registerUser(e.getPlayer());
			String ip = e.getRealAddress().toString().replaceAll("/", "");
			HerobrinePVPCore.getFileManager().updateIP(e.getPlayer(), ip);

		}

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		Player player = e.getPlayer();
		player.setWalkSpeed(.2F);
		player.setMaxHealth(20);
		player.setHealth(20);
		ItemStack selector = new ItemStack(Material.COMPASS, 1);
		ItemMeta selectorMeta = selector.getItemMeta();
		selectorMeta.setDisplayName(ChatColor.GREEN + "Game Selector");
		selector.setItemMeta(selectorMeta);

		ItemBuilder shop = new ItemBuilder(Material.EMERALD);
		shop.setDisplayName(ChatColor.AQUA + "Shop " + ChatColor.GRAY + "(Right Click)");
		player.setHealth(20.0);
		player.setGameMode(GameMode.SURVIVAL);

		ItemStack profile = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta profileMeta = (SkullMeta) profile.getItemMeta();

		profileMeta.setOwner(player.getName());
		profileMeta.setDisplayName(ChatColor.GREEN + "My Profile " + ChatColor.GRAY + "(Right Click)");
		profile.setItemMeta(profileMeta);

		ItemBuilder cosmetics = new ItemBuilder(Material.CHEST);

		cosmetics.setDisplayName(ChatColor.AQUA + "Cosmetics " + ChatColor.GRAY + "(Coming Soon)");
		for (PotionEffect effect : player.getActivePotionEffects()) {

			player.removePotionEffect(effect.getType());
		}
		if (!HerobrinePVPCore.getFileManager().isUserRegistered(player)) {
			HerobrinePVPCore.getFileManager().registerUser(player);

			player.getInventory().setItem(0, selector);
			player.getInventory().setItem(1, profile);
			player.getInventory().setItem(3, shop.build());
			player.getInventory().setItem(4, cosmetics.build());
			player.updateInventory();

			e.setJoinMessage(ChatColor.GRAY + "Welcome to Herobrine PVP, " + ChatColor.GOLD + player.getName()
					+ ChatColor.GRAY + "!" + "\n" + ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] "
					+ ChatColor.GRAY + player.getName());
			HerobrinePVPCore.getFileManager().updateLastJoin(player);
			player.setLevel(HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()));
			player.setExp(HerobrinePVPCore.getFileManager().getPlayerXP(player.getUniqueId())
					/ HerobrinePVPCore.getFileManager().getMaxXP(player.getUniqueId()));
			// send welcome message


		} else {

			if (HerobrinePVPCore.getFileManager().getPlusColor(player.getUniqueId()) == null) {
				HerobrinePVPCore.getFileManager().setPlusColor(player, ChatColor.LIGHT_PURPLE);
			}

			if (player.getName() != HerobrinePVPCore.getFileManager().getNameFromUUID(player.getUniqueId())) {
				HerobrinePVPCore.getFileManager().updatePlayerNames(player);
			}

			HerobrinePVPCore.getFileManager().updateLastJoin(player);
			player.getInventory().clear();
			player.getEquipment().setHelmet(null);
			player.getEquipment().setChestplate(null);
			player.getEquipment().setLeggings(null);
			player.getEquipment().setBoots(null);
			e.getPlayer().getEnderChest().clear();
			player.getInventory().setItem(0, selector);
			player.getInventory().setItem(1, profile);
			player.getInventory().setItem(3, shop.build());
			player.getInventory().setItem(4, cosmetics.build());
			player.updateInventory();
			player.setLevel(HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()));
			//player.setExp(HerobrinePVPCore.getFileManager().getPlayerXP(player.getUniqueId())
			//		/ HerobrinePVPCore.getFileManager().getMaxXP(player.getUniqueId()));
			e.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.GRAY
					+ player.getName());
			GameCoreMain.getInstance().resetTitle(player);

			if (HerobrinePVPCore.getFileManager().getRank(player).getPermLevel() >= 6) {
				for (Player players : Bukkit.getOnlinePlayers()) {
					if (HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.OWNER)
							|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.ADMIN)
							|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.MOD)
							|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.HELPER)) {
						Ranks rank = HerobrinePVPCore.getFileManager().getRank(player);
						players.sendMessage(ChatColor.AQUA + "[STAFF] " + rank.getColor() + rank.getName() + " "
								+ player.getName() + ChatColor.YELLOW + " joined.");

					}

				}
			}
			if (player.getEquipment() != null) {
				player.getEquipment().clear();
				player.getInventory().setItem(0, selector);
				player.getInventory().setItem(1, profile);
				player.getInventory().setItem(3, shop.build());
				player.getInventory().setItem(4, cosmetics.build());
				player.updateInventory();
			}
		}
		GameCoreMain.getInstance().sendTitle(player, "&cWelcome to Herobrine PVP",
				"&7Enjoy your stay, " + HerobrinePVPCore.getFileManager().getRank(player).getColor() + player.getName(),
				1, 3, 1);
		HerobrinePVPCore.buildSidebar(player);
		if (HerobrinePVPCore.getFileManager().getRank(player).hasPlusColor()) {
			player.setPlayerListName(HerobrinePVPCore.getFileManager().getRank(player).getColor()
					+ HerobrinePVPCore.getFileManager().getRank(player).getName()
					+ HerobrinePVPCore
							.translateString(HerobrinePVPCore.getFileManager().getPlusColor(player.getUniqueId()) + "+")
					+ " " + HerobrinePVPCore.getFileManager().getRank(player).getColor() + player.getName());
		} else {
			player.setPlayerListName(HerobrinePVPCore.getFileManager().getRank(player).getColor()
					+ HerobrinePVPCore.getFileManager().getRank(player).getName() + " " + player.getName());
		}

	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.GRAY
				+ e.getPlayer().getName());
		e.getPlayer().getEnderChest().clear();
		HerobrinePVPCore.getFileManager().updateLastDisconnect(e.getPlayer());
		if (HerobrinePVPCore.getFileManager().getRank(e.getPlayer()).getPermLevel() >= 6) {
			for (Player players : Bukkit.getOnlinePlayers()) {
				if (HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.OWNER)
						|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.ADMIN)
						|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.MOD)
						|| HerobrinePVPCore.getFileManager().getRank(players).equals(Ranks.HELPER)) {
					Ranks rank = HerobrinePVPCore.getFileManager().getRank(e.getPlayer());
					players.sendMessage(ChatColor.AQUA + "[STAFF] " + rank.getColor() + rank.getName() + " "
							+ e.getPlayer().getName() + ChatColor.YELLOW + " disconnected.");

				}

			}
		}
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {

		Player player = e.getPlayer();
		Ranks rank = HerobrinePVPCore.getFileManager().getRank(player);

		if (HerobrinePVPCore.getFileManager().isMuted(player.getUniqueId())) {
			if (HerobrinePVPCore.getFileManager()
					.getMuteType(player.getUniqueId(),
							HerobrinePVPCore.getFileManager().getActivePunishmentId(player.getUniqueId()))
					.equals(PunishmentType.TEMPORARY)) {

				if (System.currentTimeMillis() >= HerobrinePVPCore.getFileManager().getMuteExpiryTime(
						e.getPlayer().getUniqueId(),
						HerobrinePVPCore.getFileManager().getActivePunishmentId(e.getPlayer().getUniqueId())) * 1000) {
					HerobrinePVPCore.getFileManager().removeMute(player.getUniqueId(), "CONSOLE", "Mute Expired");
				}

			}
		}

		if (!HerobrinePVPCore.getFileManager().isMuted(player.getUniqueId())) {

			if (!(rank.getPermLevel() >= 7) && MuteChatCMD.isChatToggled()) {

				e.setCancelled(true);

				return;

			}

			for (Emotes emote : Emotes.values()) {

				if (e.getMessage().contains(emote.getKey())) {

					if (emote.isUnlockRequired()) {

						if (HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.EMOTE, emote.toString(),
								player.getUniqueId())) {
							e.setMessage(e.getMessage().replaceAll(emote.getKey(), emote.getDisplay()));
						}

					}

					else {
						if (emote.getPermLevel() <= rank.getPermLevel()) {
							e.setMessage(e.getMessage().replaceAll(emote.getKey(), emote.getDisplay()));
						}
					}

				}

			}
			if (Manager.isPlaying(player)) {
				if (Manager.getArena(player).getState().equals(GameState.LIVE)
						&& Manager.getGame(Manager.getArena(player)).isTeamGame()) {
					for (UUID uuid : Manager.getArena(player).getPlayers()) {
						Player arenaPlayers = Bukkit.getPlayer(uuid);

						if (Manager.getArena(player).getTeam(arenaPlayers)
								.equals(Manager.getArena(player).getTeam(player))) {
							if (Manager.getArena(player).getTeam(player).equals(Teams.BLUE)) {

								if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.MEMBER)) {
									arenaPlayers
											.sendMessage(ChatColor.BLUE + "[BLUE] " + rank.getColor() + rank.getName()
													+ " " + player.getName() + ": " + ChatColor.GRAY + e.getMessage());
								} else if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
										|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

									arenaPlayers.sendMessage(ChatColor.BLUE + "[BLUE] " + rank.getColor()
											+ rank.getName() + " " + player.getName() + ChatColor.RESET + ": "
											+ ChatColor.translateAlternateColorCodes('&', e.getMessage()));

								} else {
									if (rank.hasPlusColor()) {
										arenaPlayers.sendMessage(ChatColor.BLUE + "[BLUE] " + rank.getColor()
												+ rank.getName()
												+ HerobrinePVPCore.translateString(HerobrinePVPCore.getFileManager()
														.getPlusColor(player.getUniqueId()) + "+")
												+ rank.getColor() + " " + player.getName() + ": " + ChatColor.RESET
												+ e.getMessage());
									} else {
										arenaPlayers.sendMessage(
												ChatColor.BLUE + "[BLUE] " + rank.getColor() + rank.getName() + " "
														+ player.getName() + ": " + ChatColor.RESET + e.getMessage());
									}

								}
							}

							else if (Manager.getArena(player).getTeam(player).equals(Teams.YELLOW)) {

								if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.MEMBER)) {
									arenaPlayers.sendMessage(
											ChatColor.YELLOW + "[YELLOW] " + rank.getColor() + rank.getName() + " "
													+ player.getName() + ": " + ChatColor.GRAY + e.getMessage());
								} else if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
										|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

									arenaPlayers.sendMessage(ChatColor.YELLOW + "[YELLOW] " + rank.getColor()
											+ rank.getName() + " " + player.getName() + ChatColor.RESET + ": "
											+ ChatColor.translateAlternateColorCodes('&', e.getMessage()));

								} else {
									if (rank.hasPlusColor()) {
										arenaPlayers.sendMessage(ChatColor.YELLOW + "[YELLOW] " + rank.getColor()
												+ rank.getName()
												+ HerobrinePVPCore.translateString(HerobrinePVPCore.getFileManager()
														.getPlusColor(player.getUniqueId()) + "+")
												+ rank.getColor() + " " + player.getName() + ": " + ChatColor.RESET
												+ e.getMessage());
									} else {
										arenaPlayers.sendMessage(
												ChatColor.YELLOW + "[YELLOW] " + rank.getColor() + rank.getName() + " "
														+ player.getName() + ": " + ChatColor.RESET + e.getMessage());
									}

								}
							}

							else if (Manager.getArena(player).getTeam(player).equals(Teams.GREEN)) {

								if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.MEMBER)) {
									arenaPlayers
											.sendMessage(ChatColor.GREEN + "[GREEN] " + rank.getColor() + rank.getName()
													+ " " + player.getName() + ": " + ChatColor.GRAY + e.getMessage());
								} else if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
										|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

									arenaPlayers.sendMessage(ChatColor.GREEN + "[GREEN] " + rank.getColor()
											+ rank.getName() + " " + player.getName() + ChatColor.RESET + ": "
											+ ChatColor.translateAlternateColorCodes('&', e.getMessage()));

								} else {
									if (rank.hasPlusColor()) {
										arenaPlayers.sendMessage(ChatColor.GREEN + "[GREEN] " + rank.getColor()
												+ rank.getName()
												+ HerobrinePVPCore.translateString(HerobrinePVPCore.getFileManager()
														.getPlusColor(player.getUniqueId()) + "+")
												+ rank.getColor() + " " + player.getName() + ": " + ChatColor.RESET
												+ e.getMessage());
									} else {
										arenaPlayers.sendMessage(
												ChatColor.GREEN + "[GREEN] " + rank.getColor() + rank.getName() + " "
														+ player.getName() + ": " + ChatColor.RESET + e.getMessage());
									}

								}
							}

							else {
								if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.MEMBER)) {

									arenaPlayers.sendMessage(ChatColor.RED + "[RED] " + rank.getColor() + rank.getName()
											+ " " + player.getName() + ": " + ChatColor.GRAY + e.getMessage());
								} else if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
										|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

									arenaPlayers.sendMessage(ChatColor.RED + "[RED] " + rank.getColor() + rank.getName()
											+ " " + player.getName() + ChatColor.RESET + ": "
											+ ChatColor.translateAlternateColorCodes('&', e.getMessage()));

								} else {

									if (rank.hasPlusColor()) {
										arenaPlayers.sendMessage(ChatColor.RED + "[RED] " + rank.getColor()
												+ rank.getName()
												+ HerobrinePVPCore.translateString(HerobrinePVPCore.getFileManager()
														.getPlusColor(player.getUniqueId()) + "+")
												+ rank.getColor() + " " + player.getName() + ": " + ChatColor.RESET
												+ e.getMessage());
									} else {
										arenaPlayers.sendMessage(
												ChatColor.RED + "[RED] " + rank.getColor() + rank.getName() + " "
														+ player.getName() + ": " + ChatColor.RESET + e.getMessage());
									}

								}
							}
						}

					}
				} else {

					for (UUID uuid : Manager.getArena(player).getPlayers()) {
						Player arenaPlayers = Bukkit.getPlayer(uuid);
						Games arenaGame = Manager.getGame(Manager.getArena(player));
						if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.MEMBER)) {
							arenaPlayers.sendMessage(arenaGame.getColor() + "[" + arenaGame.getKey()
									+ Manager.getArena(player).getID() + "] " + rank.getColor() + rank.getName() + " "
									+ player.getName() + ": " + ChatColor.GRAY + e.getMessage());
						} else if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
								|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

							arenaPlayers.sendMessage(arenaGame.getColor() + "[" + arenaGame.getKey()
									+ Manager.getArena(player).getID() + "] " + rank.getColor() + rank.getName() + " "
									+ player.getName() + ChatColor.RESET + ": "
									+ ChatColor.translateAlternateColorCodes('&', e.getMessage()));

						} else {

							if (rank.hasPlusColor()) {
								arenaPlayers
										.sendMessage(arenaGame.getColor() + "[" + arenaGame.getKey()
												+ Manager.getArena(player).getID() + "] " + rank.getColor()
												+ rank.getName()
												+ HerobrinePVPCore.translateString(HerobrinePVPCore.getFileManager()
														.getPlusColor(player.getUniqueId()) + "+")
												+ rank.getColor() + " " + player.getName() + ": " + ChatColor.RESET
												+ e.getMessage());
							} else {
								arenaPlayers.sendMessage(arenaGame.getColor() + "[" + arenaGame.getKey()
										+ Manager.getArena(player).getID() + "] " + rank.getColor() + rank.getName()
										+ " " + player.getName() + ": " + ChatColor.RESET + e.getMessage());
							}

						}

					}

				}
			} else {
				for (Player onlinePlayers : e.getRecipients()) {

					if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.MEMBER)) {
						onlinePlayers.sendMessage( HerobrinePVPCore.translateString("&7<" + HerobrinePVPCore.getFileManager().getPrestige(HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId())).colorString + HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()) + "&7> ") +  rank.getColor() + rank.getName() + " " + player.getName() + ": "
								+ ChatColor.GRAY + e.getMessage());
					} else if (HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.OWNER)
							|| HerobrinePVPCore.getFileManager().getRank(player).equals(Ranks.ADMIN)) {

						onlinePlayers.sendMessage(HerobrinePVPCore.translateString("&7<" + HerobrinePVPCore.getFileManager().getPrestige(HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId())).colorString + HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()) + "&7> ") + rank.getColor() + rank.getName() + " " + player.getName()
								+ ChatColor.RESET + ": " + ChatColor.translateAlternateColorCodes('&', e.getMessage()));

					} else {
						if (rank.hasPlusColor()) {
							onlinePlayers.sendMessage(HerobrinePVPCore.translateString("&7<" + HerobrinePVPCore.getFileManager().getPrestige(HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId())).colorString + HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()) + "&7> ") + rank.getColor() + rank.getName()
									+ HerobrinePVPCore.translateString(
											HerobrinePVPCore.getFileManager().getPlusColor(player.getUniqueId()) + "+")
									+ rank.getColor() + " " + player.getName() + ": " + ChatColor.RESET
									+ e.getMessage());
						} else {
							onlinePlayers.sendMessage(HerobrinePVPCore.translateString("&7<" + HerobrinePVPCore.getFileManager().getPrestige(HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId())).colorString + HerobrinePVPCore.getFileManager().getPlayerLevel(player.getUniqueId()) + "&7> ") + rank.getColor() + rank.getName() + " " + player.getName() + ": "
									+ ChatColor.RESET + e.getMessage());
						}

					}
				}
			}

		}

		else {
			if (HerobrinePVPCore.getFileManager()
					.getMuteType(player.getUniqueId(),
							HerobrinePVPCore.getFileManager().getActivePunishmentId(player.getUniqueId()))
					.equals(PunishmentType.PERMANENT)) {

				player.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&c&m&l----------------------------------------"));
				player.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "                   &cYou are currently muted!"));
				player.sendMessage(
						ChatColor.translateAlternateColorCodes('&',
								"\n&7Reason: &f"
										+ HerobrinePVPCore.getFileManager().getMuteReason(
												player.getUniqueId(),
												HerobrinePVPCore.getFileManager()
														.getActivePunishmentId(player.getUniqueId()))
										+ "\n&7Find out more here: &ehttps://discord.gg/Ny5gvF9XyY\n&7Mute ID: &f"
										+ HerobrinePVPCore.getFileManager()
												.getActivePunishmentId(player.getUniqueId())));
				player.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&c&m&l----------------------------------------"));

			}

			else if (HerobrinePVPCore.getFileManager()
					.getMuteType(player.getUniqueId(),
							HerobrinePVPCore.getFileManager().getActivePunishmentId(player.getUniqueId()))
					.equals(PunishmentType.TEMPORARY)) {
				Date d1 = new Date(System.currentTimeMillis());
				Date d2 = new Date(HerobrinePVPCore.getFileManager().getMuteExpiryTime(player.getUniqueId(),
						HerobrinePVPCore.getFileManager().getActivePunishmentId(player.getUniqueId())) * 1000);
				player.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&c&m&l----------------------------------------"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"                   &cYou are currently muted!\n&7Your mute will expire in &c"
								+ HerobrinePVPCore.dateDifference(d1, d2)));
				player.sendMessage(
						ChatColor.translateAlternateColorCodes('&',
								"\n&7Reason: &f"
										+ HerobrinePVPCore.getFileManager().getMuteReason(
												player.getUniqueId(),
												HerobrinePVPCore.getFileManager()
														.getActivePunishmentId(player.getUniqueId()))
										+ "\n&7Find out more here: &ehttps://discord.gg/Ny5gvF9XyY\n&7Mute ID: &f"
										+ HerobrinePVPCore.getFileManager()
												.getActivePunishmentId(player.getUniqueId())));
				player.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&c&m&l----------------------------------------"));

			}
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void onItemClick(PlayerInteractEvent e) {

		if (e.getClickedBlock() != null) {
			if (e.getClickedBlock().getType().equals(Material.JUKEBOX)) {
				Menus.applyElectionMenu(e.getPlayer());
			}
		}

		if (e.getPlayer().getItemInHand() != null && e.getPlayer().getItemInHand().hasItemMeta()
				&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null) {
			if (e.getPlayer().getItemInHand().getType().equals(Material.COMPASS)
					&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName()
							.equals(ChatColor.GREEN + "Game Selector")
					&& e.getPlayer().getItemInHand().getItemMeta().getEnchants().isEmpty()) {
				Menus.applyGameModeSelection(e.getPlayer());

			}

			else if (e.getPlayer().getItemInHand().getType().equals(Material.EMERALD)
					&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName()
							.equals(ChatColor.AQUA + "Shop " + ChatColor.GRAY + "(Right Click)")) {
				Menus.applyShopMenu(e.getPlayer());
			}

			else if (e.getPlayer().getItemInHand().getType().equals(Material.SKULL_ITEM) && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Profile")) {
				Menus.applyProfileMenu(e.getPlayer());
			}

			else if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("Coming Soon")) {

				e.getPlayer().sendMessage(ChatColor.RED + "This will be available to use soon!");

			}

		} else {
			return;
		}
	}

	@EventHandler
	public void onRightClick(NPCRightClickEvent e) {

		if (e.getNPC().getName().contains("CLICK")) {
			e.getClicker().sendMessage(ChatColor.LIGHT_PURPLE + "[NPC] Voidley" + ChatColor.WHITE + ": The world is coming to an end, but I can help stop it.");
			e.getClicker().sendMessage(ChatColor.LIGHT_PURPLE + "[NPC] Voidley" + ChatColor.WHITE + ": By making" + ChatColor.LIGHT_PURPLE +  ChatColor.BOLD + " The End"  + ChatColor.RESET  + " " + ChatColor.WHITE + "much better!");
			e.getClicker().sendMessage(ChatColor.LIGHT_PURPLE + "[NPC] Voidley"  + ChatColor.WHITE + ": You won't regret voting for me. The dragons will reward you handsomely.");
		}

	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		Player player = (Player) e.getWhoClicked();
		if (Manager.isPlaying(player)) {
			if (!Manager.getArena(player).getState().equals(GameState.LIVE)) {
				if (!BuildCommand.buildEnabledPlayers.contains(player)) {
					e.setCancelled(true);
				}

			}
		} else {

			if (BuildCommand.buildEnabledPlayers.contains(player)) {
				e.setCancelled(false);
			} else {
				e.setCancelled(true);
			}
		}
		if (e.getClickedInventory() != null && e.getClickedInventory().getName() != null
				&& ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle())
						.equals(ChatColor.GRAY + "Game Selection")) {
			if (e.getCurrentItem() != null) {
				switch (e.getCurrentItem().getType()) {
				case DIAMOND_SWORD:

					Menus.applyGamemodeMenu(player);
					break;
				case BOW:
					player.sendMessage(
							ChatColor.RED + "This game is not currently available! Reason: Not developed yet");
					player.closeInventory();
					// player.sendMessage(
					// ChatColor.GRAY + "Sending you to a game of " + ChatColor.GOLD + "One In The
					// Chamber");
					break;
				case BED:
					player.sendMessage(
							ChatColor.RED + "This game is not currently available! Reason: Not developed yet");
					player.closeInventory();
					// player.sendMessage(
					// ChatColor.GRAY + "Sending you to a game of " + ChatColor.LIGHT_PURPLE + "Bed
					// Wars");
					break;
				case LAVA_BUCKET:
					player.sendMessage(
							ChatColor.RED + "This game is not currently available! Reason: Not developed yet");
					player.closeInventory();
					// player.sendMessage(
					// ChatColor.GRAY + "Sending you to a game of " + ChatColor.RED + "Survive The
					// Disaster");
					break;

				case EYE_OF_ENDER:
					Menus.applyMissionSelectMenu(player);
					break;
				case GRASS:
					GameCoreMain.getInstance().startQueue(player, Games.BLOCK_HUNT, GameType.VANILLA);
					player.closeInventory();
					break;

				case SKULL_ITEM:
					GameCoreMain.getInstance().startQueue(player, Games.WALLS_SG, GameType.VANILLA);
					player.closeInventory();
					break;

				case PAPER:
					player.sendMessage(ChatColor.RED + "This game is coming soon!");
					player.closeInventory();
					break;

				default:
					return;
				}

			}
		}

		else if (e.getClickedInventory() != null && e.getClickedInventory().getTitle() != null
				&& ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle())
				.equals(HerobrinePVPCore.translateString("&c&lDELTACRAFT &r&7- &6Missions"))) {

			if(e.getCurrentItem() == null) return;
			if(e.getCurrentItem().getType().equals(Material.AIR)) return;

			for(Missions mission: Missions.values()) {
				if (mission.getItem().equals(e.getCurrentItem().getType())) {
					player.closeInventory();
					GameCoreMain.getInstance().startQueue(player, Games.DELTARUNE, GameType.valueOf(mission.name()));
					return;
				}
			}
			player.closeInventory();
			player.sendMessage(ChatColor.RED + "Sorry! But there was no mission found for that item type.");

		}

		else if (e.getClickedInventory() != null && e.getClickedInventory().getTitle() != null
				&& ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle())
						.equals(ChatColor.GREEN + "Shop")) {

			switch (e.getCurrentItem().getType()) {

			case DIAMOND:
				Menus.applyCosmeticShopMenu(player);

				break;

			case DIAMOND_SWORD:

				Menus.applyClassShopMenu(player);
				break;

			case GOLD_INGOT:
				Menus.applyRankShopMenu(player);
				break;
			default:
				return;

			}

			e.setCancelled(true);
		} else if (e.getClickedInventory() != null && e.getClickedInventory().getTitle() != null
				&& ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle())
						.equals(ChatColor.GREEN + "Which gamemode?")) {

			switch (e.getCurrentItem().getType()) {

			case DIAMOND_SWORD:

				GameCoreMain.getInstance().startQueue(player, Games.CLASH_ROYALE, GameType.VANILLA);
				break;

			case IRON_AXE:
				GameCoreMain.getInstance().startQueue(player, Games.CLASH_ROYALE, GameType.CLASH_ROYALE);
				break;

			default:
				return;
			}

		}

		else if (e.getClickedInventory() != null && e.getClickedInventory().getTitle() != null
				&& ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle())
						.equals(HerobrinePVPCore.translateString("&aShop &7- &bCosmetics"))) {

			switch (e.getCurrentItem().getType()) {

			case DIAMOND:
				player.sendMessage(ChatColor.RED + "Cosmetics will be available for purchase soon!");
				player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
				break;

			case SKULL_ITEM:

				Menus.applyEmoteShopMenu(player);
				break;

			case ARROW:

				Menus.applyShopMenu(player);
				break;
			default:
				return;

			}

			e.setCancelled(true);
		}

		else if (e.getClickedInventory() != null && e.getClickedInventory().getTitle() != null
				&& ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle())
						.equals(HerobrinePVPCore.translateString("&aShop &7- &aClasses"))) {

			if (e.getCurrentItem() != null) {

				if (e.getCurrentItem().getType() != Material.ARROW) {
					for (ClassTypes type : ClassTypes.values()) {

						if (type.getMaterial() == e.getCurrentItem().getType()) {
							if (HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.CLASS, type.toString(),
									player.getUniqueId())) {

								player.sendMessage(ChatColor.GREEN + "You already have this class!");
							} else {
								if (HerobrinePVPCore.getFileManager().getCoins(player) >= 6000) {

									HerobrinePVPCore.getFileManager().unlockItem(ItemTypes.CLASS, type.toString(),
											player.getUniqueId());
									player.sendMessage(ChatColor.GREEN + "You have purchased the " + type.getDisplay()
											+ ChatColor.GREEN + " class! You can now use it in "
											+ type.getGame().getDisplay() + ChatColor.GREEN + " !");
									player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
									HerobrinePVPCore.getFileManager().removeCoins(player, 6000);
									Menus.applyClassShopMenu(player);
									if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getDisplayName()
											.equalsIgnoreCase(
													ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

										player.getScoreboard().getTeam("coins")
												.setSuffix(ChatColor.translateAlternateColorCodes('&',
														"&e" + HerobrinePVPCore.getFileManager().getCoins(player)));
									}

								} else {
									player.sendMessage(ChatColor.RED + "You do not have enough coins!");
									player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
								}

							}
						}

					}

				} else {
					Menus.applyShopMenu(player);
				}

			}

			e.setCancelled(true);
		}

		else if (e.getClickedInventory() != null && e.getClickedInventory().getTitle() != null
				&& ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle())
						.equals(HerobrinePVPCore.translateString("&aShop &7- &bEmotes"))) {

			if (e.getCurrentItem().getType().equals(Material.PAPER)) {

				ItemStack currentItem = e.getCurrentItem();

				for (Emotes emote : Emotes.values()) {

					if (emote.getKey().equals(ChatColor.stripColor(currentItem.getItemMeta().getDisplayName()))) {

						if (HerobrinePVPCore.getFileManager().isItemUnlocked(ItemTypes.EMOTE, emote.toString(),
								player.getUniqueId())) {

							player.sendMessage(ChatColor.GREEN + "You already have this emote!");
						} else {
							if (HerobrinePVPCore.getFileManager().getCoins(player) >= 750) {

								HerobrinePVPCore.getFileManager().unlockItem(ItemTypes.EMOTE, emote.toString(),
										player.getUniqueId());
								player.sendMessage(ChatColor.GREEN + "You have purchased the " + emote.getDisplay()
										+ ChatColor.GREEN + " emote! You can now use it in chat with " + emote.getKey()
										+ "!");
								player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
								HerobrinePVPCore.getFileManager().removeCoins(player, 750);
								Menus.applyEmoteShopMenu(player);
								if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getDisplayName()
										.equalsIgnoreCase(
												ChatColor.translateAlternateColorCodes('&', "&cHerobrine PVP"))) {

									player.getScoreboard().getTeam("coins")
											.setSuffix(ChatColor.translateAlternateColorCodes('&',
													"&e" + HerobrinePVPCore.getFileManager().getCoins(player)));
								}

							} else {
								player.sendMessage(ChatColor.RED + "You do not have enough coins!");
								player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 1f);
							}

						}

					}

				}

			} else if (e.getCurrentItem().getType().equals(Material.ARROW)) {
				Menus.applyCosmeticShopMenu(player);
			}

			e.setCancelled(true);
		}

		else if (e.getClickedInventory() != null && e.getClickedInventory().getTitle() != null && ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle()).equals(HerobrinePVPCore.translateString("&7Profile - &6" + player.getName()))) {

			if (e.getCurrentItem() != null) {

				switch (e.getCurrentItem().getType()) {
					case PAPER:

					 Menus.applyStatsMenu(player);

						break;


					case DIAMOND:

						Menus.applyPrestigeMenu(player);
						break;
					default:
						return;

				}

			}
		}

		if(e.getClickedInventory() != null && e.getClickedInventory().getTitle() != null && ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle()).equals(HerobrinePVPCore.translateString("&7Profile - &6Prestige Menu"))) {


			if (e.getCurrentItem() != null) {
				if (e.getCurrentItem().getType().equals(Material.ARROW)) {

					Menus.applyProfileMenu(player);
				}
			}

		}

		else {
			return;
		}

	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (e.getEntity() instanceof Player) {

		}
	}

	@EventHandler

	public void onWeatherChange(WeatherChangeEvent e) {
		e.setCancelled(e.toWeatherState());

	}

}
