package net.herobrine.core;

import org.bukkit.ChatColor;

public enum Emotes {

	HEART(ChatColor.RED + "❤" + ChatColor.RESET, "<3", 5, false),
	STAR(ChatColor.GOLD + "✮" + ChatColor.RESET, ":star:", 5, false),
	YES(ChatColor.GREEN + "✔" + ChatColor.RESET, ":yes:", 5, false),
	NO(ChatColor.RED + "✖" + ChatColor.RESET, ":no:", 5, false),
	HELLO(ChatColor.LIGHT_PURPLE + "( ﾟ◡ﾟ)/" + ChatColor.RESET, "o/", 5, false),
	JAVA(ChatColor.AQUA + "☕" + ChatColor.RESET, ":java:", 5, false),
	ARROW(ChatColor.translateAlternateColorCodes('&', "&e&l➜&r"), ":arrow:", 5, false),
	SHRUG(ChatColor.YELLOW + "¯\\\\_(ツ)_/¯" + ChatColor.RESET, ":shrug:", 5, false),
	TABLEFLIP(ChatColor.RED + "(╯°□°）╯ " + ChatColor.WHITE + "︵" + ChatColor.GRAY + " ┻━┻" + ChatColor.RESET,
			":tableflip:", 5, false),
	NUMBERS(ChatColor.GREEN + "1" + ChatColor.YELLOW + "2" + ChatColor.RED + "3" + ChatColor.RESET, ":123:", 5, false),
	TOTEM(ChatColor.AQUA + "◎" + ChatColor.YELLOW + "_" + ChatColor.AQUA + "◎" + ChatColor.RESET, ":totem:", 5, false),
	TYPING(ChatColor.YELLOW + "✎" + ChatColor.GOLD + "..." + ChatColor.RESET, ":typing:", 5, false),
	THINKING(ChatColor.translateAlternateColorCodes('&', "&6(&a0&6.&ao&c?&6)&r"), ":thinking:", 5, false),
	OOF(ChatColor.translateAlternateColorCodes('&', "&c&lOOF&r"), ":oof:", 5, false),
	LOVESTRUCK(ChatColor.translateAlternateColorCodes('&', "&7ヽ&a(&c❤&a◡&c❤&a)&7ﾉ&r"), ":lovestruck:", 5, false),
	WIZARD(ChatColor.translateAlternateColorCodes('&', "&a(&c´• ◡ •`&a)⊃━&e☆ﾟ&d.*&r"), ":wizard:", 5, false),
	BRUH(ChatColor.translateAlternateColorCodes('&', "&a&lBRUH&r"), ":bruh:", 5, false),
	DJ(ChatColor.translateAlternateColorCodes('&', "&9ヽ&5(&d⌐&c■&6_&e■&b)&3ﾉ&9♫&r"), ":dj:", 5, false),
	MATHS(ChatColor.translateAlternateColorCodes('&', ChatColor.BOLD + "&a√&e&l(&l&aπ&l+x&e&l)&a&l=&c&lL&r"), ":maths:",
			5, false),
	DOGE_LOVE(ChatColor.translateAlternateColorCodes('&', "&6ʕ•́ᴥ•̀ʔっ&c♡&r"), ":doge:", 5, true),
	GIMMIE(ChatColor.translateAlternateColorCodes('&', "&b༼ つ ◕_◕ ༽つ&r"), ":gimmie:", 5, true),
	HUGS(ChatColor.translateAlternateColorCodes('&', "&d\\ (•◡•) /&r"), ":hugs:", 5, true),
	CUTE(ChatColor.translateAlternateColorCodes('&', "&a(◕‿◕&e✿&a)&r"), ":cute:", 5, true),
	LOL(ChatColor.translateAlternateColorCodes('&', "&b&lLOL"), ":lol:", 5, true);

	private String display;
	private String key;
	private int permLevel;
	private boolean unlockRequired;

	private Emotes(String display, String key, int permLevel, boolean unlockRequired) {
		this.display = display;
		this.key = key;
		this.permLevel = permLevel;
		this.unlockRequired = unlockRequired;
	}

	public String getDisplay() {
		return display;
	}

	public String getKey() {
		return key;
	}

	public int getPermLevel() {
		return permLevel;
	}

	public boolean isUnlockRequired() {
		return unlockRequired;
	}

}
