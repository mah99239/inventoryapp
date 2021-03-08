package com.example.android.inventoryapp;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.transition.MaterialArcMotion;
import com.google.android.material.transition.MaterialContainerTransform;

public class ContainerTransformConfigurationHelper {
    private boolean arcMotionEnabled;
    private long enterDuration;
    private long returnDuration;
    private Interpolator interpolator;
    private int fadeModeButtonId;
    private boolean drawDebugEnabled;
    private static final String CUBIC_CONTROL_FORMAT = "%.3f";
    private static final String DURATION_FORMAT = "%.0f";
    private static final long NO_DURATION = -1;
    boolean isArcMotionEnabled() {
        return arcMotionEnabled;
    }

    private static final SparseIntArray FADE_MODE_MAP = new SparseIntArray();


/*    static {
        FADE_MODE_MAP.append(R.id.fade_in_button, MaterialContainerTransform.FADE_MODE_IN);
        FADE_MODE_MAP.append(R.id.fade_out_button, MaterialContainerTransform.FADE_MODE_OUT);
        FADE_MODE_MAP.append(R.id.fade_cross_button, MaterialContainerTransform.FADE_MODE_CROSS);
        FADE_MODE_MAP.append(R.id.fade_through_button, MaterialContainerTransform.FADE_MODE_THROUGH);
    }*/
    /** The enter duration to be used by a custom container transform. */
    long getEnterDuration() {
        return enterDuration;
    }

    /** The return duration to be used by a custom container transform. */
    long getReturnDuration() {
        return returnDuration;
    }

    /** The interpolator to be used by a custom container transform. */
    Interpolator getInterpolator() {
        return interpolator;
    }

    /** The fade mode used by a custom container transform. */
    int getFadeMode() {
        return FADE_MODE_MAP.get(fadeModeButtonId);
    }

    /** Whether or not the custom transform should draw debugging lines. */
    boolean isDrawDebugEnabled() {
        return drawDebugEnabled;
    }

    private void setUpDefaultValues() {
        arcMotionEnabled = false;
        enterDuration = NO_DURATION;
        returnDuration = NO_DURATION;
        interpolator = null;
     //   fadeModeButtonId = R.id.fade_in_button;
        drawDebugEnabled = false;
    }
    /** Set up the androidx transition according to the config helper's parameters. */
 public   void configure(MaterialContainerTransform transform, boolean entering) {
        long duration = entering ? getEnterDuration() : getReturnDuration();
        if (duration != NO_DURATION) {
            transform.setDuration(duration);
        }
        transform.setInterpolator(getInterpolator());
        if (isArcMotionEnabled()) {
            transform.setPathMotion(new MaterialArcMotion());
        }
        transform.setFadeMode(getFadeMode());
        transform.setDrawDebugEnabled(isDrawDebugEnabled());
    }
}
