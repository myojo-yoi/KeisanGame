package com.myprogram.keisangame;

import java.util.Random;
import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int num1, num2;         //問題式の数
	private String answer;          //解答を確保するString
	private Random rand;            //問題用Random
	private TextView questionText;  //クエスチョン用TextView
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		questionText = (TextView) findViewById(R.id.main_question);
		//問題の数を乱数で作る
		rand = new Random();
		num1 = rand.nextInt(10);
		num2 = rand.nextInt(10);
		//解答を文字列で確保
		answer = String.valueOf(num1 * num2);
		//問題文を表示
		questionText.setText(num1 + " × " + num2 + " = ?");
		Button button = ((Button) findViewById(R.id.main_answer_button));
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.id.main_answer_text);
				String userAnswer = text.getText().toString();
				//ユーザーの解答の正誤確認
				if(userAnswer.equals(answer)) {
					Toast.makeText(getApplicationContext(), "正解", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "不正解", Toast.LENGTH_SHORT).show();
				}
				//問題を新たに作成
				num1 = rand.nextInt(10);
				num2 = rand.nextInt(10);
				answer = String.valueOf(num1 * num2);
				questionText.setText(num1 + " × " + num2 + " = ?");
			}
		});
	}
}
