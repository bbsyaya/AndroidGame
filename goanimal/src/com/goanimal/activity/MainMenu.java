package com.goanimal.activity;


import com.example.goanimal.R;
import com.goanimal.service.MusicService;
import com.goanimal.util.AppManager;
import com.goanimal.util.DialogUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
public class MainMenu extends Activity{
	
	final int CODE=0x717;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_menu);
		final Button buttonStart=(Button) findViewById(R.id.button_start);
		final Button buttonSet=(Button) findViewById(R.id.button_set);
		final Button buttonMenu=(Button) findViewById(R.id.button_menu_start);
		final Button buttonBoard=(Button) findViewById(R.id.button_board);
		final Button buttonHelp=(Button) findViewById(R.id.button_help);
		final Button buttonExit=(Button) findViewById(R.id.button_exit);
		
			
		buttonMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonMenu.setVisibility(View.INVISIBLE);
				buttonStart.setVisibility(View.VISIBLE);
				buttonSet.setVisibility(View.VISIBLE);
				buttonBoard.setVisibility(View.VISIBLE);
				buttonHelp.setVisibility(View.VISIBLE);
				buttonExit.setVisibility(View.VISIBLE);
			}
		});
		buttonHelp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startAniam(buttonHelp);
				
						// ��������ͼƬ
						Toast toast=new Toast(MainMenu.this);
						toast.setDuration(Toast.LENGTH_LONG);
						toast.setGravity(Gravity.CENTER, 0, 0);
						LinearLayout ll=new LinearLayout(MainMenu.this);
						ImageView introduce=new ImageView(MainMenu.this);
						introduce.setImageResource(R.drawable.jieshao_1);
						introduce.setPadding(1, 1, 1, 1);
						ll.addView(introduce);
						toast.setView(ll);
						toast.show();
						
					
				
			}
		});
		buttonStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startAniam(buttonStart);
				//��ʼ��Ϸ
				Intent intents=new Intent(MainMenu.this,selectLevel.class);
				startActivity(intents);
				
			}
		});
		buttonExit.setOnClickListener(new OnClickListener() {
			
			
			public void onClick(View v) {
				startAniam(buttonExit);
				// ������Ϸ
				DialogUtil.createDialog(MainMenu.this, "��ȷ��Ҫ�˳���Ϸ��?").show();
				Intent intent=new Intent(MainMenu.this,MusicService.class);
				stopService(intent);
				
			}
		});
		buttonBoard.setOnClickListener(new OnClickListener() {
			
			
			public  void onClick(View v) {
				startAniam(buttonBoard);
				// ������ҷ����б�
				final String[] items=new String[]{"1.����  ħŮ����  50505","2.����  ˫ǹС˧  50000","3.����  ����С��  45555"};
				Builder builder=new AlertDialog.Builder(MainMenu.this);
				builder.setTitle("����б�");
				builder.setNegativeButton("ȡ��", null);
				builder.setItems(items, null);
				builder.create().show();
				
				
				
			}
		});
		buttonSet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startAniam(buttonSet);
				//�������ֺ���Ч
				Intent set=new Intent(MainMenu.this,setActivity.class);
				startActivityForResult(set, CODE);
			}
		});
	
	}
	
	
	public void startAniam(Button button){
		
		AnimationSet Aset1=new AnimationSet(true);
		ScaleAnimation scalA=new ScaleAnimation(1.0f, 1.0f, 1.5f, 1.5f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		scalA.setDuration(500);
		Aset1.addAnimation(scalA);
		button.startAnimation(Aset1);
	}
	@Override
	public void onBackPressed() {
		DialogUtil.createDialog(this, "��ȷ��Ҫ�˳���Ϸ��?").show();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
	/** ��ǰ�������´��ڻ�Ծ״̬ */
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	}
