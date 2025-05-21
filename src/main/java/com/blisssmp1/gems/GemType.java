package com.blisssmp1.gems;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.Optional;

public enum GemType {
    FIRE("fire", ChatColor.DARK_RED, "Fire Gem"),
    STRENGTH("strength", ChatColor.RED, "Strength Gem"),
    SPEED("speed", ChatColor.AQUA, "Speed Gem"),
    PUFF("puff", ChatColor.WHITE, "Puff Gem"),
    LIFE("life", ChatColor.GREEN, "Life Gem"),
    WEALTH("wealth", ChatColor.GOLD, "Wealth Gem"),
    ASTRA("astra", ChatColor.DARK_PURPLE, "Astra Gem"),
    FLUX("flux", ChatColor.BLUE, "Flux Gem");

    private final String id;
    private final ChatColor color;
    private final String displayName;


    GemType(String id, ChatColor color, String displayName) {
        this.id = id;
        this.color = color;
        this.displayName = displayName;
    }

    public String getId() {return id;}
    public ChatColor getColor() {return color;}
    public String getDisplayName() {return displayName;}

    public static Optional<GemType> fromId(String id) {
        return Arrays.stream(values()).filter(g -> g.id.equalsIgnoreCase(id)).findFirst();
    }
}
