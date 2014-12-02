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
        		// �I�u�W�F�N�g���擾
        		EditText etxtNum	= (EditText)findViewById(R.id.etxtNum);
        		EditText etxtMoney	= (EditText)findViewById(R.id.etxtMoney);
        		TextView txtResult	= (TextView)findViewById(R.id.txtResult);
        		TextView txtOneMoney= (TextView)findViewById(R.id.txtOneMoney);
        		TextView txtYen		= (TextView)findViewById(R.id.txtYen);
        		
        		// �ݒ���擾
        		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SplitTheCostActivity.this);
        		String frac            = pref.getString(SettingPrefActivity.PREF_KEY_FRACTION, "500");
        		Boolean roudup		   = pref.getBoolean(SettingPrefActivity.PREF_KEY_ROUNDUP, false);
        		int fracVal			   = Integer.parseInt(frac);
        		
        		// ���͓��e���擾
        		String strNum	= etxtNum.getText().toString();
        		String strMoney	= etxtMoney.getText().toString();
        		
        		// ���l�ɕϊ�
        		int num		= Integer.parseInt(strNum);
        		int money 	= Integer.parseInt(strMoney);
        		
        		// ���芨�v�Z
        		int result 	= money / num;
        		
        		// �؂�グ
        		if (roudup && result % fracVal != 0){
        			result += fracVal;
        		}
        		
        		// �[������
        		result = result / fracVal * fracVal;
        		
        		// ���ʕ\���p�e�L�X�g�ɐݒ�
        		txtResult.setText(Integer.toString(result));
        		
        		// ���ʕ\���p�e�L�X�g��\��
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
    		// �ݒ�{�^����������
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
