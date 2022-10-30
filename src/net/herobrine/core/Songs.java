package net.herobrine.core;

public enum Songs {
	FIND("find.nbs", "find", false), WIN("win.nbs", "win", false), LOSE("lose.nbs", "lose", false), WAITING_THEME("wait.nbs", "wait", true),
	COOL_SONG("cool.nbs", "cool", false), DRAW("draw.nbs", "draw", false), FIND_RARE("find_rare.nbs", "find_rare", false),
	BCLOSE("bclose.nbs", "bclose", false), BCWIN("bcwin.nbs", "bcwin", false), BCDRAW("bcdraw.nbs", "bcdraw", false),
	WSGWIN("wsgwin.nbs", "wsgwin", false), WSGLOSE("wsglose.nbs", "wsglose", false), WSGDRAW("wsgdraw.nbs", "wsgdraw", false),
	HOPES_AND_DREAMS("13FieldofHopesandDreams.nbs", "hopes_and_dreams", true), VRS_LANCER("21VsLancer.nbs", "vrs_lancer", true);

	private String songName;
	private String name;
	private boolean loop;

	private Songs(String songName, String name, boolean loop) {
		this.songName = songName;
		this.name = name;
		this.loop = loop;
	}

	public String getSongName() {
		return songName;
	}

	public String getName() {
		return name;
	}

	public boolean isLoop() {return loop;}

}
