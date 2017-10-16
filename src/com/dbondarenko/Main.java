package com.dbondarenko;

/**
 * File: Main.java
 * The program that creates avatars. The class that runs the program.
 * Created by Dmitro Bondarenko on 09.08.2017.
 */
public class Main {

    public static void main(String[] args) {
        if (args[0] != null && !args[0].isEmpty()) {
            AvatarCreator avatar = new AvatarCreator(new AvatarOptions(args[0]));
            Writer.writeImageToFile(avatar.create());
        }
    }
}