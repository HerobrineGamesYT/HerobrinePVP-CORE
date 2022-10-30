package net.herobrine.core;

import java.io.File;

import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;

public class SongPlayer {

	public static void playSong(Player player, Songs song) {
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




	}






}
