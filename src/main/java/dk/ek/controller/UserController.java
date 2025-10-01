package dk.ek.controller;

import app.entities.User;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {

    public static void addRoutes(Javalin app) {
        app.post("/login", ctx -> login(ctx));

    }

    private static void login(Context ctx)
    {
        // 1. find username og password som brugeren har indtastet
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        // 2. tjek om det eksisterer i databasen!
        // 2+ lav en user instans og sæt ctx.currentUser = user.
        User user = UserMapper.login(username, password);

        ctx.json("Welcome "+user.getUsername());
        ctx.sessionAttribute("currentUser", user);

        ctx.redirect("/login");

    }
}
