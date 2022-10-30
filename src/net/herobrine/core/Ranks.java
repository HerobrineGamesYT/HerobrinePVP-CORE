package net.herobrine.core;

import org.bukkit.ChatColor;

public enum Ranks {

	OWNER("OWNER", ChatColor.RED, 9, false), ADMIN("ADMIN", ChatColor.RED, 9, false),
	MOD("MOD", ChatColor.DARK_GREEN, 8, false), HELPER("HELPER", ChatColor.BLUE, 7, false),
	FAMOUS("FAMOUS", ChatColor.YELLOW, 6, false), OG("BETA", ChatColor.GREEN, 1, false),
	KING_PLUS("KING" + ChatColor.GOLD, ChatColor.GOLD, 5, true), KING("KING", ChatColor.GOLD, 4, false),
	HERO_PLUS("HERO" + ChatColor.GREEN + "+" + ChatColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE, 3, false),
	HERO("HERO", ChatColor.LIGHT_PURPLE, 2, false), MEMBER("MEMBER", ChatColor.GRAY, 1, false);

	private String name;
	private ChatColor color;
	private int permLevel;
	private boolean hasPlusColor;

	private Ranks(String name, ChatColor color, int permLevel, boolean hasPlusColor) {
		this.name = name;
		this.color = color;
		this.permLevel = permLevel;
		this.hasPlusColor = hasPlusColor;
	}

	public String getName() {
		return name;
	}

	public ChatColor getColor() {
		return color;
	}

	public int getPermLevel() {
		return permLevel;
	}

	public boolean hasPlusColor() {
		return hasPlusColor;
	}
}
