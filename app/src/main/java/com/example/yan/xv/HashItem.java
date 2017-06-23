package com.example.yan.xv;

import java.security.PublicKey;

/**
 * Created by Yan on 27/02/2016.
 */
public class HashItem {

    private int position;
    private String status;

    public HashItem(int position, String status) {
        this.position = position;
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }
}
