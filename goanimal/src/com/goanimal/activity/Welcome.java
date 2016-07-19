package com.goanimal.activity;
import com.example.goanimal.R;
import com.goanimal.util.Constant;
import com.goanimal.util.DialogUtil;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
/**
 * ��ӭ������
 * @version 1.0
 */
public class Welcome extends Activity {
	public MediaPlayer[] sound=new MediaPlayer[Constant.WSOUNDS.length];
	public ImageView imageview;
	private Handler mhandler;
	private ProgressBar horizonP;
	private int mProgressStatus=0;
	
	// ����Handler��Ϣ������
	private Handler handler = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			// ����Ϣ��ʶ�����ж�
			if(msg.what==0x1313){
				//��һ�����涯������ʾ������ Logo
				imageview=(ImageView) findViewById(R.id.imageView1);
				AnimationSet Aset1=new AnimationSet(true);
	    		ScaleAnimation scalA=new ScaleAnimation(0.0f, 0.8f, 0.0f, 0.8f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
	    		scalA.setDuration(3000);
	    		Aset1.addAnimation(scalA);
	    		imageview.startAnimation(Aset1);
	    		//��������
	    		sound[0].start();
			}
			if(msg.what==0x1315){
				sound[0].stop();
				sound[1].start();
				runWelcome();
			}
			if (msg.what == 0x1314){
				sound[1].stop();
				// ����Intent��������Ϣ
				Intent intent = new Intent(Welcome.this, MainMenu.class);
				// ����Activity
				startActivity(intent);
			}
			return true;
		}
//�ڶ����������棬��ʾ��ϷԪ��
		private void runWelcome() {
			// TODO Auto-generated method stub
			setContentView(R.layout.an_translate);
			horizonP=(ProgressBar) findViewById(R.id.progressBar1);
			mhandler=new Handler(){
				@SuppressLint("HandlerLeak")
				public void handleMessage(Message msg) {
					if(msg.what==0x110){
						horizonP.setProgress(mProgressStatus);
						
					}else{
						horizonP.setVisibility(View.GONE);
					}
				};
			};
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true){
						mProgressStatus=doWork();
						Message m1=new Message();
						if(mProgressStatus<100){
							m1.what=0x110;
							mhandler.sendMessage(m1);
						}else{
							m1.what=0x111;
							mhandler.sendMessage(m1);
							break;
						}
					}
					
					
				}
				private int doWork(){
					mProgressStatus+=10;
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					return mProgressStatus;
				}
			}).start();
    		AnimationSet Aset1=new AnimationSet(true);
    		ImageView imgv1=(ImageView) findViewById(R.id.img1);
    		//��һ��С�����ƶ�����
    		TranslateAnimation trans1=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,10.5f);
    		trans1.setDuration(1000);
            trans1.setRepeatCount(3);
            trans1.setRepeatMode(Animation.REVERSE);
    		Aset1.addAnimation(trans1);
    		imgv1.startAnimation(Aset1);
    		//�ڶ���С�����ƶ�����
    		AnimationSet Aset2=new AnimationSet(true);
    		ImageView imgv2=(ImageView) findViewById(R.id.img2);
    		TranslateAnimation trans2=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,12.0f);
    		trans2.setDuration(1000);
    	    trans2.setRepeatCount(3);
    		Aset2.addAnimation(trans2);
    		imgv2.startAnimation(Aset2);
    		//������С�����ƶ�����
    		AnimationSet Aset3=new AnimationSet(true);
    		ImageView imgv3=(ImageView) findViewById(R.id.img3);
    		TranslateAnimation trans3=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,9.0f);
    		trans3.setDuration(1500);
    		trans3.setRepeatCount(2);
    		trans3.setRepeatMode(Animation.REVERSE);
    		trans3.setFillAfter(true);
    		Aset3.addAnimation(trans3);
    		imgv3.startAnimation(Aset3);
    		//���ĸ�С�����ƶ�����
    		AnimationSet Aset4=new AnimationSet(true);
    		ImageView imgv4=(ImageView) findViewById(R.id.img4);
    		TranslateAnimation trans4=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF, 1.0f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,12.0f);
    		trans4.setDuration(1000);
    		trans4.setRepeatCount(3);
    		trans4.setFillAfter(true);
    		Aset4.addAnimation(trans4);
    		imgv4.startAnimation(Aset4);
    		//�����С�����ƶ�����
    		AnimationSet Aset5=new AnimationSet(true);
    		ImageView imgv5=(ImageView) findViewById(R.id.img5);
    		TranslateAnimation trans5=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.8f,Animation.RELATIVE_TO_SELF,18.0f);
    		trans5.setDuration(1000);
    		trans5.setRepeatCount(3);
    		trans5.setRepeatMode(Animation.REVERSE);
    		trans5.setFillAfter(true);
    		Aset5.addAnimation(trans5);
    		imgv5.startAnimation(Aset5);
    		//������С�����ƶ�����
    		AnimationSet Aset6=new AnimationSet(true);
    		ImageView imgv6=(ImageView) findViewById(R.id.img6);
    		TranslateAnimation trans6=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,20.0f);
    		trans6.setDuration(500);
    		trans6.setRepeatCount(6);
    		trans6.setFillAfter(true);
    		Aset6.addAnimation(trans6);
    		imgv6.startAnimation(Aset6);
    		//���߸�С�����ƶ�����
    		AnimationSet Aset7=new AnimationSet(true);
    		ImageView imgv7=(ImageView) findViewById(R.id.img7);
    		TranslateAnimation trans7=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF, 1.0f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,20.0f);
    		trans7.setDuration(600);
    		trans7.setRepeatCount(5);
    		trans7.setRepeatMode(Animation.REVERSE);
    		trans7.setFillAfter(true);
    		Aset7.addAnimation(trans7);
    		imgv7.startAnimation(Aset7);
    		//�ڰ˸�С�����ƶ�����
    		AnimationSet Aset8=new AnimationSet(true);
    		ImageView imgv8=(ImageView) findViewById(R.id.img8);
    		TranslateAnimation trans8=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF, 1.0f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,20.0f);
    		trans8.setDuration(1000);
    		trans8.setRepeatCount(3);
    		trans8.setFillAfter(true);
    		Aset8.addAnimation(trans8);
    		imgv8.startAnimation(Aset8);
    		//�ھŸ�С�����ƶ�����
    		AnimationSet Aset9=new AnimationSet(true);
    		ImageView imgv9=(ImageView) findViewById(R.id.img9);
    		TranslateAnimation trans9=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF, 1.5f,Animation.RELATIVE_TO_SELF,1.0f,Animation.RELATIVE_TO_SELF,20.0f);
    		trans9.setDuration(1500);
    		trans9.setRepeatCount(2);
    		trans9.setRepeatMode(Animation.REVERSE);
    		trans9.setFillAfter(true);
    		Aset9.addAnimation(trans9);
    		imgv9.startAnimation(Aset9);
    
		}
	});
	
	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		//�ӳ�10���뷢����Ϣ
		handler.sendEmptyMessageDelayed(0x1313, 10);
		//�ӳ�3010���뷢����Ϣ
		handler.sendEmptyMessageDelayed(0x1315, 3010);
		// �ӳ�6000���뷢����Ϣ
		handler.sendEmptyMessageDelayed(0x1314, 6000);
		//��ʼ����Ч
		for(int i=0;i<2;i++){
			sound[i]=MediaPlayer.create(this, Constant.WSOUNDS[i]);
			// ������Ƶ��������
			sound[i].setAudioStreamType(AudioManager.STREAM_MUSIC);
		}
	}
	/** �����ǲ��ǰ����ؼ� */
	@Override
	public void onBackPressed() {
		DialogUtil.createDialog(this, "��ȷ��Ҫ�˳���Ϸ��?").show();
	}
	/** ��ǰActivity�����ڻ�Ծ״̬ */
	@Override
	protected void onPause() {
		this.finish();
		super.onPause();
	}
	/** ��ǰActivity����ʱ */
	@Override
	protected void onDestroy() {
		
		for (MediaPlayer sound : sound){
			if (sound.isPlaying()){
				sound.stop();
			}
			sound.release();
		}
		super.onDestroy();
	}

}
