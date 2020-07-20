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

        TAG("tag", "varchar(10) PRIMARY KEY"),
        NAME("name", "varchar(32)"),
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
        GUILD("guild", "varchar(10)"),
        OFFICE("office", "int");

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

    public static enum CharactersAtributes {

        USER("ownerUid", "varchar(32)"),
        UUID("uid", "varchar(32) PRIMARY KEY"),
        CLASS("class", "int"),
        PRIMARY_ELEMENT("primalelement", "int"),
        HIDDEN_ELEMENT("hiddenelement", "int"),
        LEVEL("level", "int"),
        AGILITY("agility", "int"),
        DEXTERITY("dexterity", "int"),
        STRENGHT("strenght", "int"),
        DEFENSE("defense", "int"),
        INTELLIGENCE("intelligence", "int");

        private final Table table = Table.USERS;
        private String name;
        private String type;

        private CharactersAtributes(String name, String type){
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

    public static enum GuildChunksAtributes {

        GUILD("guild", "varchar(10)"),
        X("x", "int"),
        Z("z", "int, PRIMARY KEY (x,z)");

        private final Table table = Table.USERS;
        private String name;
        private String type;

        private GuildChunksAtributes(String name, String type){
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

    public static enum GuildAllyAtributes {

        GUILD("guild", "varchar(10)"),
        ALLY("ally", "varchar(10), PRIMARY KEY(guild, ally)");

        private final Table table = Table.USERS;
        private String name;
        private String type;

        private GuildAllyAtributes(String name, String type){
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

    public static enum GuildEnemyAtributes {

        GUILD("guild", "varchar(10)"),
        ENEMY("enemy", "varchar(10), PRIMARY KEY(guild, enemy)");

        private final Table table = Table.USERS;
        private String name;
        private String type;

        private GuildEnemyAtributes(String name, String type){
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
