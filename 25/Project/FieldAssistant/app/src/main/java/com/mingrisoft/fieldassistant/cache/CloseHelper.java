package com.mingrisoft.fieldassistant.cache;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Risky on 15/10/30.
 */
public class CloseHelper{
    public static final void close(Closeable closeable){
        try {
            closeable.close();
            closeable = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
