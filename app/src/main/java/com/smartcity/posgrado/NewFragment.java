package com.smartcity.posgrado;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.animation.Animation;

/**
 * Created by kevin on 15/04/16.
 */
public class NewFragment extends Fragment {
    public static boolean sDisableFragmentAnimations = false;
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (sDisableFragmentAnimations) {
            Animation a = new Animation() {};
            a.setDuration(0);
            return a;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("mensaje","ggwp xd");
    }
}
