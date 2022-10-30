package net.herobrine.core;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum LevelRewards {


    BASE(100, 0, ChatColor.GRAY + "Coal Prestige", 30, 1.0, 1.0, ChatColor.GRAY, "&7", Material.COAL),
    ONE(200, 10, ChatColor.GREEN + "Emerald Prestige", 30 , 1.2, 1.5, ChatColor.GREEN, "&a", Material.EMERALD),
    TWO(300, 20, ChatColor.GOLD + "Gold Prestige", 30, 1.4, 2.0, ChatColor.GOLD, "&6", Material.GOLD_INGOT),
    THREE(400, 30, ChatColor.AQUA + "Diamond Prestige", 60, 1.6, 2.5, ChatColor.AQUA, "&b", Material.DIAMOND),
    FOUR(500, 40, ChatColor.RED + "Ruby Prestige", 60, 1.8, 3.0, ChatColor.RED, "&c", Material.REDSTONE),
    FIVE(600, 50, ChatColor.DARK_RED + "Dark Ruby Prestige", 100, 2.0, 3.5, ChatColor.DARK_RED, "&4", Material.REDSTONE),
    SIX(700, 60, ChatColor.DARK_BLUE + "Pure Lapis Prestige", 100, 2.2, 4.0, ChatColor.BLUE, "&9", Material.LAPIS_BLOCK),
    SEVEN(800, 70, ChatColor.translateAlternateColorCodes('&', "&a&lPure Emerald Prestige"), 100, 2.4, 4.5, ChatColor.GREEN, "&a&l", Material.EMERALD_BLOCK),
    EIGHT(900, 80, ChatColor.translateAlternateColorCodes('&', "&6&lPure Gold Prestige"), 100, 2.6, 5.0, ChatColor.GOLD, "&6&l", Material.GOLD_BLOCK),
    NINE(1000, 90, ChatColor.translateAlternateColorCodes('&', "&b&lPure Diamond Prestige"), 100, 2.8, 5.5, ChatColor.AQUA, "&b&l", Material.DIAMOND_BLOCK),
    TEN(1500, 100, ChatColor.translateAlternateColorCodes('&', "&c&lPure Ruby Prestige"), 30, 3.0, 6.0, ChatColor.RED, "&c&l", Material.REDSTONE_BLOCK);


    int coins;
    int startingLevel;
    double baseXPBoost;
    // calculation: earnedXP * baseXPBoost = realEarnedXP;

    String name;
    double coinDivisorPerLevel;
    // calculation: level / coinDivisorPerLevel  * 100 || Math.round(result) + coins = levelUpCoins;

    double gameCoinMultiplier;
    // calculation: earnedCoins * gameCoinMultiplier = realEarnedCoins;
    ChatColor prestigeColor;
    String colorString;
    Material guiItem;

    private LevelRewards(int coins, int startingLevel, String name, double coinDivisorPerLevel, double gameCoinMultiplier, double baseXPBoost, ChatColor prestigeColor, String colorString, Material guiItem) {

        this.coins = coins;
        this.startingLevel = startingLevel;
        this.name = name;
        this.coinDivisorPerLevel = coinDivisorPerLevel;
        this.gameCoinMultiplier = gameCoinMultiplier;
        this.baseXPBoost = baseXPBoost;
        this.prestigeColor = prestigeColor;
        this.colorString = colorString;
        this.guiItem = guiItem;

    }

    public int getCoins() { return coins;}

    public int getStartingLevel() { return startingLevel;}

    public String getName() {return name;}

    public double getCoinDivisorPerLevel() {return coinDivisorPerLevel;}

    public double getGameCoinMultiplier() {return gameCoinMultiplier;}

    public double getBaseXPBoost() {return baseXPBoost;}

    public ChatColor getPrestigeColor() {return prestigeColor;}

    public String getColorString() {return colorString;}

    public Material getGuiItem() {return guiItem;}

}
