package org.runecraft.runecore.db;

import org.runecraft.runecore.db.enums.Table;

public class Atribute {
    public static enum UsersAtributes {

        UUID("uuid", "varchar(32) PRIMARY KEY");

        private final Table table = Table.USERS;
        private String name;
        private String type;

        private UsersAtributes(String name, String type){
            this.name = name;
            this.type = type;
        }

        public String getName(){
            return name;
        }

        public String getType() {
            return type;
        }
    }

    public static enum GuildsAtributes {

        TAG("tag", "varchar(16) PRIMARY KEY"),
        OWNER("owner", "varchar(32)"),
        POWER("power", "int"),
        POINTS("points", "int"),
        ;

        private final Table table = Table.USERS;
        private String name;
        private String type;

        private GuildsAtributes(String name, String type){
            this.name = name;
            this.type = type;
        }

        public String getName(){
            return name;
        }

        public String getType() {
            return type;
        }
    }

    public static enum GuildMembersAtributes {

        USER("user", "varchar(32) PRIMARY KEY"),
        GUILD("guild", "varchar(16)");

        private final Table table = Table.USERS;
        private String name;
        private String type;

        private GuildMembersAtributes(String name, String type){
            this.name = name;
            this.type = type;
        }

        public String getName(){
            return name;
        }

        public String getType() {
            return type;
        }
    }
}
