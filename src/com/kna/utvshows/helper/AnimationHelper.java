package com.kna.utvshows.helper;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class AnimationHelper {

	public static void showInput(View view) {
		ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
		alpha.setDuration(1000);

		ObjectAnimator show = ObjectAnimator.ofInt(view, "visibility",
				View.GONE, View.VISIBLE);
		show.setDuration(1000);

		AnimatorSet animator = new AnimatorSet();
		animator.play(alpha).with(show);
		animator.start();
	}

	public static void hideInput(View view) {
		ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
		alpha.setDuration(1000);

		ObjectAnimator show = ObjectAnimator.ofInt(view, "visibility",
				View.VISIBLE, View.GONE);
		show.setDuration(1000);

		AnimatorSet animator = new AnimatorSet();
		animator.play(alpha).with(show);
		animator.start();
	}
}
