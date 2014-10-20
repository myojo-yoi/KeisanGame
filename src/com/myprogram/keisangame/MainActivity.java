package com.myprogram.keisangame;

import java.util.Random;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int num1, num2; // 問題式の数
	private int result; // 解答を確保する変数
	private int countAnswer; // 回答数
	private int countCorrect; // 正答数
	private Random rand; // 問題用Random
	private TextView textViewQuestion; // クエスチョン用TextView
	private TextView textViewAnswer; // 解答用TextView
	private TextView textViewStatus; // 正答率用TextView
	private Button button1; // 「1」
	private Button button2; // 「2」
	private Button button3; // 「3」
	private Button button4; // 「4」
	private Button button5; // 「5」
	private Button button6; // 「6」
	private Button button7; // 「7」
	private Button button8; // 「8」
	private Button button9; // 「9」
	private Button button10; // 「0」
	private Button button11; // 「Clear」
	private Button button12; // 「Enter」
	private SoundPool soundPool; // 効果音
	private int soundID1; // 正解音のID
	private int soundID2; // 不正解音のID
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// idから要素を取得
		textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
		textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
		textViewStatus = (TextView) findViewById(R.id.textViewStatus);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button8 = (Button) findViewById(R.id.button8);
		button9 = (Button) findViewById(R.id.button9);
		button10 = (Button) findViewById(R.id.button10);
		button11 = (Button) findViewById(R.id.button11);
		button12 = (Button) findViewById(R.id.button12);

		// SoundPool初期化
		soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		// 音ファイルのロード
		soundID1 = soundPool.load(getApplicationContext(), R.raw.correct, 1);
		soundID2 = soundPool.load(getApplicationContext(), R.raw.incorrect, 1);
		//　回答数・正答数の初期化
		countAnswer = 0;
		countCorrect = 0;
		// 正解率の表示
		textViewStatus.setText(countCorrect + "/" + countAnswer);
		
		// 問題の数を乱数で作る
		rand = new Random();
		num1 = rand.nextInt(10);
		num2 = rand.nextInt(10);
		// 解答を確保
		result = num1 * num2;
		// 問題文を表示
		textViewQuestion.setText(num1 + " × " + num2 + " = ?");

		// クリックイベント処理の実装
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputNumber(v);
			}
		});

		button11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputClear(v);
			}
		});

		button12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				inputEnter(v);
			}
		});
	}

	// 0~9の数値入力処理
	public void inputNumber(View view) {
		String str = ((Button) view).getText().toString();
		textViewAnswer.append(str);
	}

	// 数値クリアー処理
	public void inputClear(View view) {
		textViewAnswer.setText("");
	}

	// 解答処理
	public void inputEnter(View view) {
		int answer = Integer.parseInt(textViewAnswer.getText().toString());
		// 正誤判定
		if (result == answer) {
			Toast.makeText(getApplicationContext(), "正解", Toast.LENGTH_SHORT)
					.show();
			// 正解音
			soundPool.play(soundID1, 1.0f, 1.0f, 0, 0, 1.0f);
			// 正答数更新
			countCorrect++;
		} else {
			Toast.makeText(getApplicationContext(), "不正解", Toast.LENGTH_SHORT)
					.show();
			// 不正解音
			soundPool.play(soundID2, 1.0f, 1.0f, 0, 0, 1.0f);
		}
		
		// 回答数更新
		countAnswer++;
		
		// 正解率の表示更新
		textViewStatus.setText(countCorrect + "/" + countAnswer);
		
		// 解答のクリア
		textViewAnswer.setText("");
		// 次の問題の作成
		num1 = rand.nextInt(10);
		num2 = rand.nextInt(10);
		result = num1 * num2;
		// 問題文を表示
		textViewQuestion.setText(num1 + " × " + num2 + " = ?");
	}
}