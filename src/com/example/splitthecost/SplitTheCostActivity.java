package com.example.splitthecost;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SplitTheCostActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_the_cost);
        
        Button btn = (Button)findViewById(R.id.btnCalc);
        btn.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v) {
        		// オブジェクトを取得
        		EditText etxtNum	= (EditText)findViewById(R.id.etxtNum);
        		EditText etxtMoney	= (EditText)findViewById(R.id.etxtMoney);
        		TextView txtResult	= (TextView)findViewById(R.id.txtResult);
        		TextView txtOneMoney= (TextView)findViewById(R.id.txtOneMoney);
        		TextView txtYen		= (TextView)findViewById(R.id.txtYen);
        		
        		// 設定を取得
        		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SplitTheCostActivity.this);
        		String frac            = pref.getString(SettingPrefActivity.PREF_KEY_FRACTION, "500");
        		Boolean roudup		   = pref.getBoolean(SettingPrefActivity.PREF_KEY_ROUNDUP, false);
        		int fracVal			   = Integer.parseInt(frac);
        		
        		// 入力内容を取得
        		String strNum	= etxtNum.getText().toString();
        		String strMoney	= etxtMoney.getText().toString();
        		
        		// 数値に変換
        		int num		= Integer.parseInt(strNum);
        		int money 	= Integer.parseInt(strMoney);
        		
        		// 割り勘計算
        		int result 	= money / num;
        		
        		// 切り上げ
        		if (roudup && result % fracVal != 0){
        			result += fracVal;
        		}
        		
        		// 端数処理
        		result = result / fracVal * fracVal;
        		
        		// 結果表示用テキストに設定
        		txtResult.setText(Integer.toString(result));
        		
        		// 結果表示用テキストを表示
        		txtOneMoney.setVisibility(View.VISIBLE);
        		txtResult.setVisibility(View.VISIBLE);
        		txtYen.setVisibility(View.VISIBLE);
        	}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.split_the_cost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()){
    	case R.id.action_settings:
    		// 設定ボタン押下処理
    		Intent intent = new Intent(SplitTheCostActivity.this, SettingPrefActivity.class);
    		startActivity(intent);
    		break;
    	default:
    		break;
    	}
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}
