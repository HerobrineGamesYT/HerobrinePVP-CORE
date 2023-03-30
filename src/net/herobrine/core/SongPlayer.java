package net.herobrine.core;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;

public class SongPlayer {

	public static HashMap<UUID, RadioSongPlayer> activePlayers = new HashMap();
	public static void playSong(Player player, Songs song) {
		if (activePlayers.containsKey(player.getUniqueId()) && song.getSongType().equals("MUSIC")) {
			stopSong(player);
		}

		Song song1 = NBSDecoder
				.parse(new File(Bukkit.getPluginManager().getPlugin("HBPVP-Core").getDataFolder(), song.getSongName()));
		RadioSongPlayer rsp = new RadioSongPlayer(song1);
		rsp.addPlayer(player);
		rsp.setAutoDestroy(true);
		rsp.setPlaying(true);
		rsp.setEnable10Octave(true);
		if (song.isLoop()) {
			rsp.setRepeatMode(RepeatMode.ONE);
		}

		if(song.getSongType().equals("MUSIC")) activePlayers.put(player.getUniqueId(), rsp);

	}

	public static void stopSong(Player player) {
		if (activePlayers.containsKey(player.getUniqueId())) {
			activePlayers.get(player.getUniqueId()).setPlaying(false);
			activePlayers.get(player.getUniqueId()).destroy();
			activePlayers.remove(player.getUniqueId());
		}
	}






}
