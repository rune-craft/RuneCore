package org.runecraft.runecore.manager;

import org.runecraft.runecore.User;
import org.runecraft.runecore.db.Atribute;
import org.runecraft.runecore.db.DataBase;
import org.runecraft.runecore.db.enums.DatabaseOperation;
import org.runecraft.runecore.db.enums.Table;
import org.spongepowered.api.scheduler.Task;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UsersManager {
    private Set<User> users = new HashSet<>();

    public Optional<User> getUser(UUID uid){
        return users.stream().filter(x -> x.getUUID().equals(uid)).findFirst();
    }

    //WAITING FOR SPONGE MYSQL CONNECTOR UPDATE
    public void downloadUsers(){
        /*try{
            users.clear();
            Statement st = DataBase.getConnection().createStatement();
            st.executeQuery("SELECT * FROM " + Table.USERS.getName());
            ResultSet rs = st.getResultSet();
            while(rs.next()){
                UUID uid = UUID.fromString(rs.getString(Atribute.UsersAtributes.UUID.getName()));
                users.add(new User(uid));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }

    public boolean isRegistered(UUID uid){
        return getUser(uid).isPresent();
    }

    //WAITING FOR SPONGE MYSQL CONNECTOR UPDATE
    public void registerUser(final UUID uid){
        if(!isRegistered(uid)){
            users.add(new User(uid));

            Task.Builder task = Task.builder().async().execute(
                    () -> {
                        /*try{
                            PreparedStatement ps =
                                    DataBase.getConnection().prepareStatement(Table.USERS.getBuildString(DatabaseOperation.INSERT));
                            ps.setString(1, uid.toString());
                            ps.executeUpdate();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }*/
                    }
            );
        }
    }
}
