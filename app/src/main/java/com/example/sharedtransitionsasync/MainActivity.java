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
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
	ImageView imageView;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		getWindow().setSharedElementEnterTransition(new DetailsTransition());
		getWindow().setSharedElementReturnTransition(new DetailsTransition());


		imageView = findViewById(R.id.my_image_view1);
		Picasso.get()
				.load("https://i.redd.it/jb1okqafolg21.jpg")
				.fit()
				.into(imageView, new Callback() {
					@Override
					public void onSuccess () {
						startPostponedEnterTransition();
					}

					@Override
					public void onError (Exception e) {
						startPostponedEnterTransition();
					}
				});

	}

	public void gotoActivity2 (View view) {
		Intent intent = new Intent(this, Main2Activity.class);
		ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "profile");
		startActivity(intent, optionsCompat.toBundle());
	}

	public class DetailsTransition extends TransitionSet {
		public DetailsTransition() {
			setOrdering(ORDERING_TOGETHER);
			addTransition(new ChangeBounds()).
					addTransition(new ChangeTransform()).
					addTransition(new ChangeImageTransform());
		}
	}

}
