package com.myprogram.keisangame;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
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
	private SoundPool soundPool; //OP音
	private int soundID; //OP音ID

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);

		// SoundPool初期化
		soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		soundID = soundPool.load(getApplicationContext(), R.raw.op, 1);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				soundPool.play(soundID, 1.0f, 1.0f, 0, 0, 1.0f);
			}
		});
		
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
	protected void onDestroy() {
		super.onDestroy();
		soundPool.release();
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
				// 結果画面にデータを渡す
				int answer = data.getIntExtra("answer", 0);
				int correct = data.getIntExtra("correct", 0);
				int preScore = data.getIntExtra("preScore", 0);
				intent.putExtra("ansewr", answer); // 回答数
				intent.putExtra("correct", correct); // 正答数
				intent.putExtra("preScore", preScore); // 前回のハイスコア
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
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				intent.putExtra("gameMode", "normal");
				startActivityForResult(intent, REQUEST_CODE1);
				break;
			}
		}
	}
}
