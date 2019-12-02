package com.deno.myfirebasedatabase;

public class ItemConstructor {
    private String id_column,name_column,email_column,id_number_column;

    public ItemConstructor(String id_column, String name_column, String email_column, String id_number_column) {
        this.id_column = id_column;
        this.name_column = name_column;
        this.email_column = email_column;
        this.id_number_column = id_number_column;
    }

    public ItemConstructor() {
    }

    public String getId_column() {
        return id_column;
    }

    public void setId_column(String id_column) {
        this.id_column = id_column;
    }

    public String getName_column() {
        return name_column;
    }

    public void setName_column(String name_column) {
        this.name_column = name_column;
    }

    public String getEmail_column() {
        return email_column;
    }

    public void setEmail_column(String email_column) {
        this.email_column = email_column;
    }

    public String getId_number_column() {
        return id_number_column;
    }

    public void setId_number_column(String id_number_column) {
        this.id_number_column = id_number_column;
    }
}
