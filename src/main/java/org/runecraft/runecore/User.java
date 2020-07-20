package org.runecraft.runecore;
import net.luckperms.api.LuckPerms;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class User {

    private UUID uid;

    public User(UUID uid){
        this.uid = uid;
    }

    public static Optional<User> by(UUID uid){
        return RuneCore.getUsersManager().getUser(uid);
    }

    public static Optional<User> by(String name){
        if(Sponge.getGame().getServer().getPlayer(name).isPresent()){
            return by(Sponge.getGame().getServer().getPlayer(name).get());
        }
        Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
        if(userStorage.isPresent()){
            if(userStorage.get().get(name).isPresent()){
                return by(userStorage.get().get(name).get().getUniqueId());
            }
        }
        return Optional.empty();
    }

    public String getName() {
        Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
        return userStorage.get().get(uid).get().getName();
    }

    public boolean isOnline(){
        return getPlayer().isPresent();
    }

    public static Optional<User> by(Player player){
        return by(player.getUniqueId());
    }

    public UUID getUUID(){
        return uid;
    }

    public Optional<Player> getPlayer(){
        return Sponge.getServer().getPlayer(uid);
    }
}
