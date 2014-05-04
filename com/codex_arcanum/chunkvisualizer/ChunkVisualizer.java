package com.codex_arcanum.chunkvisualizer;


import java.util.ArrayList;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import com.codex_arcanum.chunkvisualizer.commands.CommandExecutor_Seechunks;
import com.codex_arcanum.chunkvisualizer.listeners.Listener_Player;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;


public class ChunkVisualizer extends JavaPlugin{
	private Logger log;
	private PluginDescriptionFile description;

	private String prefix;

        public static ArrayList<Player> viewers = new ArrayList<Player>();
        public static ArrayList<Location> viewerslocs = new ArrayList<Location>();
	
	@Override
	public void onEnable(){
		log = Logger.getLogger("Minecraft");
		description = getDescription();
		prefix = "["+description.getName()+"] ";

                

		Listener playerlistener = new Listener_Player(this);
                

		
		getCommand("chunkvisualizer").setExecutor(new CommandExecutor_Seechunks(this));

		this.getConfig().options().copyDefaults(true);
                this.saveConfig();

	}
	
	@Override
	public void onDisable(){
		log("disabled "+description.getFullName());

	}
	public void log(String message){
		log.info(prefix+message);
	}



	

}
