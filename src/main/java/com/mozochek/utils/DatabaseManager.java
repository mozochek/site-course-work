package com.mozochek.utils;

import java.io.IOException;

public class DatabaseManager {

    public DatabaseManager() {

    }

    public void backup() {
        try {
            Runtime.getRuntime().exec("cmd /c start " + System.getProperty("user.dir") +"\\backup\\backup_db.bat");
        } catch (IOException e) {
            System.out.println("Ошибка бэкапа базы данных!");
        }
    }

    public void restore() {
        try {
            Runtime.getRuntime().exec("cmd /c start " + System.getProperty("user.dir") +"\\backup\\restore_db.bat");
        } catch (IOException e) {
            System.out.println("Ошибка восстановления базы данных!");
        }
    }
}
