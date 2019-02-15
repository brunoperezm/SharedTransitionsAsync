package com.example.sharedtransitionsasync;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
	ImageView imageView;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);


		getWindow().setSharedElementReturnTransition(new DetailsTransition());


		postponeEnterTransition();

		imageView = findViewById(R.id.my_image_view2);
		Picasso.get()
				.load("https://i.redd.it/jb1okqafolg21.jpg")
				.fit()
				.centerCrop()
				.into(imageView, new Callback() {
					@Override
					public void onSuccess () {
						scheduleStartPostponedTransition(imageView);
					}

					@Override
					public void onError (Exception e) {

					}
				});
	}

	private void scheduleStartPostponedTransition(final View sharedElement) {
		sharedElement.getViewTreeObserver().addOnPreDrawListener(
				new ViewTreeObserver.OnPreDrawListener() {
					@Override
					public boolean onPreDraw () {
						sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
						startPostponedEnterTransition();
						return true;
					}
				});
	}

	@Override
	public void onBackPressed () {
		super.onBackPressed();
		supportFinishAfterTransition();
	}
	public class DetailsTransition extends TransitionSet {
		public DetailsTransition() {
			setOrdering(ORDERING_TOGETHER);
			addTransition(new ChangeBounds()).
			addTransition(new ChangeTransform()).
			addTransition(new ChangeImageTransform()).
			setDuration(5000);
		}
	}

}

