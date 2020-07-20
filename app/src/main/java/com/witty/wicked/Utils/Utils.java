package com.witty.wicked.Utils;

public class Utils {
    private static boolean mStartGame = false;
    private static boolean mJoinGame = false;
    private static String mName = null;

    public static boolean ismJoinGame() {
        return mJoinGame;
    }

    public static boolean ismStartGame() {
        return mStartGame;
    }

    public static void setmStartGame(boolean mStartGame) {
        Utils.mStartGame = mStartGame;
    }

    public static void setmJoinGame(boolean mJoinGame) {
        Utils.mJoinGame = mJoinGame;
    }

    public static void setmName(String name) {
        Utils.mName = name;
    }

    public static String getmName() {
        return Utils.mName;
    }
}
