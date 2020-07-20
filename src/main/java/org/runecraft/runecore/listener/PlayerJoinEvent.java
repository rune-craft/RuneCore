package org.runecraft.runecore.listener;

import org.runecraft.runecore.RuneCore;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

public class PlayerJoinEvent implements EventListener<ClientConnectionEvent.Join> {

    @Override
    public void handle(ClientConnectionEvent.Join event) {
        if(event.getCause().root() instanceof Player){
            Player p = (Player) event.getCause().root();
            if(!RuneCore.getUsersManager().isRegistered(p.getUniqueId())){
                RuneCore.getUsersManager().registerUser(p.getUniqueId());
            }
        }
    }
}
