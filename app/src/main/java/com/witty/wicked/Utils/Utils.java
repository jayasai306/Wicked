package com.witty.wicked.Utils;

import android.os.Handler;

import java.util.ArrayList;

public class Utils {
    private static boolean mStartGame = false;
    private static boolean mJoinGame = false;
    private static String mName = null;
    private static String mCode = null;
    private static Handler mMainHandler = null;
    private static Handler mJoinHandler = null;
    private static Handler mWickedHandler = null;
    private static ArrayList mQuestionList = null;
    private static ArrayList mAnswerList = null;

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

    public static void setmCode(String code) {
        Utils.mCode = code;
    }

    public static String getmCode() {
        return Utils.mCode;
    }

    public static void setmMainHandler(Handler handler) {
        Utils.mMainHandler = handler;
    }

    public static Handler getmMainHandler() {
        return Utils.mMainHandler;
    }

    public static void setmWickedHandler(Handler handler) {
        Utils.mWickedHandler = handler;
    }

    public static Handler getmWickedHandler() {
        return Utils.mWickedHandler;
    }

    public static void setJoinHandler(Handler handler) {
        Utils.mJoinHandler = handler;
    }

    public static Handler getJoinHandler() {
        return Utils.mJoinHandler;
    }

    public static ArrayList<String> getQuestionList() {
        return mQuestionList;
    }

    public static void setQuestionList(ArrayList<String> arrayList) {
        Utils.mQuestionList = arrayList;
    }

    public static ArrayList<String> getAnswerList() {
        return mAnswerList;
    }

    public static void setAnswerList(ArrayList<String> arrayList) {
        Utils.mAnswerList = arrayList;
    }

    public static String[] getNames() {
    }
}
