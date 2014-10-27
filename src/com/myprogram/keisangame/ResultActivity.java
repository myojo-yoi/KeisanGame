package com.myprogram.keisangame;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {

	private Button buttonRetry;
	private TextView textViewScore; // スコア表示用
	private TextView textViewUpdateScore; // スコア更新表示用

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		// IDから要素を取得
		textViewScore = (TextView) findViewById(R.id.textViewScore);
		textViewUpdateScore = (TextView) findViewById(R.id.textViewUpdateScore);
		buttonRetry = (Button) findViewById(R.id.buttonRetry);

		// インテントからデータを取得
		Intent intent = getIntent();
		int answer = intent.getIntExtra("ansewr", 0);
		int correct = intent.getIntExtra("correct", 0);
		int preScore = intent.getIntExtra("preScore", 0);
		// スコア表示
		double percentage;
		if (answer == 0) {
			percentage = 0.0;
		} else {
			percentage = (double) correct / answer * 100;
		}
		String result = correct + "/" + answer + "\n" + percentage + "%";
		textViewScore.setText(result);
		if (preScore < correct) {
			String hiScore = "ハイスコア更新" + preScore + "-->" + correct;
			textViewUpdateScore.setText(hiScore);
		}

		buttonRetry.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}
}
