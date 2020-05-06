package org.runecraft.runecore.db;

import org.runecraft.runecore.db.enums.Table;

public class Atribute {
    public static enum UsersAtributes {

        UUID("uuid", "varchar(32)");

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
}
