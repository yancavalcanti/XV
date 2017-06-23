package com.example.yan.xv;

import java.util.ArrayList;

/**
 * Created by Yan on 27/02/2016.
 */
public class HashGame {

    private ArrayList<HashItem> items = new ArrayList<HashItem>();
    private String gameStatus;

    public HashGame() {
        for (int i = 0; i < 10; i++) {
            HashItem item = new HashItem(i,"Empty");
            items.add(item);
        }
    }

    public ArrayList<HashItem> getItems() {
        return items;
    }

    public String getGameStatus() {
        return gameStatus;
    }
}
