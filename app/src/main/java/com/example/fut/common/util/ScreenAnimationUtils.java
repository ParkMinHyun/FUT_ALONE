package com.example.fut.common.util;

import com.example.fut.R;
import com.example.fut.common.enums.AnimationDirection;

public class ScreenAnimationUtils {

    public static int getEnterAnimationId(AnimationDirection direction) {
        switch (direction) {
            case BOTTOM:
                return R.anim.dock_bottom_enter;
            case RIGHT:
                return R.anim.show_from_left;
            case LEFT:
                return R.anim.show_from_right;
            case NONE:
            default:
                return 0;
        }
    }

    public static int getFinishAnimationId(AnimationDirection direction) {
        switch (direction) {
            case BOTTOM:
                return R.anim.dock_bottom_exit;
            case RIGHT:
                return R.anim.hide_to_right;
            case LEFT:
                return R.anim.hide_to_right;
            case NONE:
            default:
                return 0;
        }
    }
}
