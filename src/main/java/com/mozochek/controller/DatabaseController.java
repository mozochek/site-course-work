package com.mozochek.controller;

import com.mozochek.utils.DatabaseManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DatabaseController {

    private DatabaseManager databaseManager;

    public DatabaseController() {
        databaseManager = new DatabaseManager();
    }

    @GetMapping("/dev/db/backup")
    public String backupDatabase(HttpServletRequest httpServletRequest) {
        databaseManager.backup();
        return "redirect:" + httpServletRequest.getHeader("referer");
    }

    @GetMapping("/dev/db/restore")
    public String restoreDatabase(HttpServletRequest httpServletRequest) {
        databaseManager.restore();
        return "redirect:" + httpServletRequest.getHeader("referer");
    }
}
