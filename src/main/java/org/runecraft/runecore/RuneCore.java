package org.runecraft.runecore;

import com.google.inject.Inject;
import org.runecraft.runecore.db.DataBase;
import org.runecraft.runecore.manager.UsersManager;
import org.runecraft.runeguilds.manager.GuildManager;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

import java.sql.SQLException;

@Plugin(
        id = "runecore",
        name = "RuneCore",
        authors = {
                "Azure"
        }
)
public class RuneCore {

    @Inject
    private Logger logger;

    private static UsersManager usersManager;
    private static GuildManager guildManager;

    private static Object plugin;

    @Listener
    public void onServerStart(GameStartedServerEvent event) throws SQLException {
        plugin = Sponge.getPluginManager().getPlugin("runecore").get().getInstance().get();
        usersManager = new UsersManager();
        //DataBase.connect();
        //DataBase.checkForTables();
    }

    @Listener
    public void postInit(GamePostInitializationEvent event){
        //download();
    }

    //WAITING FOR SPONGE SQL CONNECTOR UPDATE
    private void download(){
        usersManager.downloadUsers();
        guildManager.downloadGuilds();
        guildManager.downloadGuildMembers();
    }

    public static Object get() { return plugin; }

    public static UsersManager getUsersManager() { return usersManager; }

    public static GuildManager getGuildManager() { return guildManager; }
}
