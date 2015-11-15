package com.gitlab.alvin_nt.if4073_pengcit.algorithms;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class BitmapColorFinder implements Callable<HashMap<Integer, AtomicInteger>> {

    protected Bitmap image;

    protected HashMap<Integer, AtomicInteger> result = new HashMap<>();

    public BitmapColorFinder(String path) {
        this.image = BitmapFactory.decodeFile(path);
        if (image == null) {
            throw new IllegalArgumentException(path + " is not a valid bitmap image location.");
        }
    }

    public BitmapColorFinder(Bitmap image) {
        this.image = image;
    }

    @Override
    public HashMap<Integer, AtomicInteger> call() throws Exception {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int color = image.getPixel(x, y);

                if (result.containsKey(color)) {
                    AtomicInteger val = result.get(color);
                    val.set(val.intValue() + 1);
                } else {
                    AtomicInteger val = new AtomicInteger(1);
                    result.put(color, val);
                }
            }
        }

        return result;
    }
}
