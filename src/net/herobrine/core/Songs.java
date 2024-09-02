package net.herobrine.core;

public enum Songs {
	FIND("find.nbs", "find", "SFX", false), WIN("win.nbs", "win", "MUSIC", false), LOSE("lose.nbs", "lose", "MUSIC", false), WAITING_THEME("wait.nbs", "wait", "MUSIC", true),
	COOL_SONG("cool.nbs", "cool", "MUSIC", false), DRAW("draw.nbs", "draw", "MUSIC", false), FIND_RARE("find_rare.nbs", "find_rare", "SFX", false),
	BCLOSE("bclose.nbs", "bclose", "MUSIC", false), BCWIN("bcwin.nbs", "bcwin", "MUSIC", false), BCDRAW("bcdraw.nbs", "bcdraw", "MUSIC", false),
	WSGWIN("wsgwin.nbs", "wsgwin", "MUSIC", false), WSGLOSE("wsglose.nbs", "wsglose", "MUSIC", false), WSGDRAW("wsgdraw.nbs", "wsgdraw", "MUSIC",  false),
	HOPES_AND_DREAMS("13FieldofHopesandDreams.nbs", "hopes_and_dreams", "MUSIC", true), VRS_LANCER("21VsLancer.nbs", "vrs_lancer", "MUSIC", true),
	SCARLET_FOREST("19Scarlet_Forest.nbs", "scarlet_forest", "MUSIC", true), ULTIMATE_SHOW("The_Ultimate_Show.nbs", "ultimate_show", "MUSIC", true),
	MEGALOVANIA("Megalovania.nbs", "megalovania", "MUSIC", true),
	CHAOS_KING("30ChaosKing.nbs", "chaos_king", "MUSIC", true),
	LEGEND("8TheLegend.nbs", "legend", "MUSIC", false),
	WORLD_REVOLVING("33The_World_Revolving.nbs", "world_revolving", "MUSIC", true),
	TEST_PERF("testPerf.nbs", "test_perf", "MUSIC", false),
	TEST_NOT_PERF("testNotPerf.nbs", "test_not_perf", "MUSIC", false),
	YOU_SAY_RUN("youSayRun.nbs", "you_say_run", "MUSIC", true),
	QBWIN("qbwin.nbs", "qbwin", "MUSIC", false),
	QBDRAW("qbdraw.nbs", "qbdraw", "MUSIC", false),
	QBLOSE("qblose.nbs", "qblose", "MUSIC", false);


	private String songName;
	private String name;

	private String songType;
	private boolean loop;

	private Songs(String songName, String name, String songType, boolean loop) {
		this.songName = songName;
		this.name = name;
		this.songType = songType;
		this.loop = loop;
	}

	public String getSongName() {
		return songName;
	}

	public String getName() {
		return name;
	}

	public String getSongType() {return songType;}

	public boolean isLoop() {return loop;}

}
