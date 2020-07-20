package org.runecraft.runecore.db.enums;

import org.runecraft.runecore.db.Atribute;
import org.runecraft.runecore.db.DataBase;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public enum Table {

    USERS("users", Atribute.UsersAtributes.class),
    CHARACTERS("characters", Atribute.CharactersAtributes.class),
    GUILDS("guilds", Atribute.GuildsAtributes.class),
    GUILD_MEMBERS("guildmembers", Atribute.GuildMembersAtributes.class),
    GUILD_CHUNKS("guildchunks", Atribute.GuildChunksAtributes.class),
    GUILD_ALLY("guildally", Atribute.GuildAllyAtributes.class),
    GUILD_ENEMY("guildenemy", Atribute.GuildEnemyAtributes.class);

    private String name;
    private Class<?> atributesClass;

    private Table(String name, Class<?> atributesClass) {
        this.name = name;
        this.atributesClass = atributesClass;
    }

    public String getName(){
        return name;
    }

    public Object[] getAtributes() {
        try{
            return (Object[]) atributesClass.getDeclaredMethod("values").invoke(null);
        }catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String getBuildString(DatabaseOperation operation){
        List<Object> atributes = Arrays.asList(getAtributes());
        final Map<String, String> atributesMap = new HashMap<>();
        final StringBuilder strBuilder = new StringBuilder();

        atributes.forEach(atribute ->{
            BiFunction<Object, String, String> getMethodReturn =
                    (obj, methodName) -> {
                        try {
                            return (String) atribute.getClass().getDeclaredMethod(methodName + "()").invoke(obj);
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                            ex.printStackTrace();
                        }
                        return null;
                    };

            String name = getMethodReturn.apply(atribute, "getName");
            String type = getMethodReturn.apply(atribute, "getType");
            atributesMap.put(name, type);
        });

        if(operation == DatabaseOperation.CREATE){

            strBuilder.append("CREATE TABLE " + name + "(");

            for(Map.Entry<String, String> entry : atributesMap.entrySet()){
                strBuilder.append(entry.getKey()).append(" ").append(entry.getValue()).append(",");
            }

            strBuilder.substring(0, strBuilder.length() - 1);
            strBuilder.append(")");

            return strBuilder.toString();

        }else if(operation == DatabaseOperation.INSERT){
            strBuilder.append("INSERT INTO ").append(name).append(" (");

            atributesMap.forEach((key1, value1) -> strBuilder.append(key1).append(","));
            strBuilder.substring(0, strBuilder.length() - 1);
            strBuilder.append(") VALUES(");
            atributesMap.forEach((key, value) -> strBuilder.append("?,"));
            strBuilder.substring(0, strBuilder.length() - 1);

            return strBuilder.toString();
        }
        return null;
    }
}
