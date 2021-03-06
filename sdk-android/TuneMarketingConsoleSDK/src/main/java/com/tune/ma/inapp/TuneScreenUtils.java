package com.tune.ma.inapp;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebView;

/**
 * Created by johng on 6/20/17.
 */

public class TuneScreenUtils {
    /**
     * Gets screen density
     * @param context Context
     * @return screen density
     */
    public static float getScreenDensity(Context context) {
        if (context == null) {
            return 0;
        }

        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * Gets screen width in pixels
     * @param context Context
     * @return screen width in pixels
     */
    public static int getScreenWidthPixels(Context context) {
        if (context == null) {
            return 0;
        }
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
            return size.x;
        } else if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(size);
            return size.x;
        } else {
            return display.getWidth();
        }
    }

    /**
     * Gets screen height in pixels
     * @param context Context
     * @return screen height in pixels
     */
    public static int getScreenHeightPixels(Context context) {
        if (context == null) {
            return 0;
        }
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
            return size.y;
        } else if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(size);
            return size.y;
        } else {
            return display.getHeight();
        }
    }

    /**
     * On KITKAT, there's an issue with using vmin
     * Invalidate the view so it gets redrawn correctly
     * @param view WebView to redraw
     */
    public static void redrawWebView(final WebView view) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            view.post(new Runnable() {
                @Override
                public void run() {
                    view.invalidate();
                    view.postDelayed(this, 100);
                }
            });
        }
    }
}
