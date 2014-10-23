package com.myprogram.keisangame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TitleActivity extends Activity {

	private final int REQUEST_CODE1 = 1; // MainActivityに対するリクエスト
	private final int REQUEST_CODE2 = 2; // ResultActivityに対するリクエスト
	private Button buttonStartNormal;
	private Button buttonStartEndless;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);

		// ノーマルモード移行用ボタン
		buttonStartNormal = (Button) findViewById(R.id.buttonStartNormal);
		buttonStartNormal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent;
				intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.putExtra("gameMode", "normal");
				startActivityForResult(intent, REQUEST_CODE1);
			}
		});
		// エンドレスモード移行ボタン
		buttonStartEndless = (Button) findViewById(R.id.buttonStartEndless);
		buttonStartEndless.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent;
				intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.putExtra("gameMode", "endless");
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// MainActivityからのResult
		if (requestCode == REQUEST_CODE1) {
			switch (resultCode) {
			case RESULT_OK:
				Intent intent = new Intent(getApplicationContext(),
						ResultActivity.class);
				startActivityForResult(intent, REQUEST_CODE2);
				break;
			case RESULT_CANCELED:
				Toast.makeText(getApplicationContext(), "勝手に終わった",
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
		// ResultActivityからのResult
		if (requestCode == REQUEST_CODE2) {
			switch (resultCode) {
			case RESULT_OK:
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				intent.putExtra("gameMode", "normal");
				startActivityForResult(intent, REQUEST_CODE1);
				break;
			}
		}
	}
}
