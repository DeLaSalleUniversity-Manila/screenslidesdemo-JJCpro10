package com.example.janssen.screenslide;

import android.support.v4.view.ViewPager;
import android.view.View;
/**
 * Created by Janssen on 12/7/2015.
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {
private static final float MIN_SCALE = 0.5f;

public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) {
        view.setAlpha(0);

        } else if (position <= 0) {
        view.setAlpha(1);
        view.setTranslationX(0);
        view.setScaleX(1);
        view.setScaleY(1);

        } else if (position <= 1) {
        view.setAlpha(1 - position);

        view.setTranslationX(pageWidth * -position);
        float scaleFactor = MIN_SCALE
        + (1 - MIN_SCALE) * (1 - Math.abs(position));
        view.setScaleX(scaleFactor);
        view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
        view.setAlpha(0);
        }
        }
        }

