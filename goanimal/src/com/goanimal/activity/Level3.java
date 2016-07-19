/**
 * 
 */
package com.goanimal.activity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.example.goanimal.R;
import com.goanimal.domain.Bead;
import com.goanimal.service.GameService3;
import com.goanimal.service.impl.GameServiceImpl3;
import com.goanimal.util.AppManager;
import com.goanimal.util.Constant;
import com.goanimal.util.Data;
import com.goanimal.util.DialogUtil;
import com.goanimal.util.FileUtil;
import com.goanimal.view.GameView3;


/**
 * @author ңָ����
 *
 */
@SuppressLint("ShowToast")
public class Level3 extends Activity{

	// ������Ϸ��ҵ�����
		private GameService3 gameService3;
		// ����GameView
		private GameView3 gameView3;
		// ���嶨ʱ��
		private Timer timer;
		// ������ߵ�ļ���
		private List<Point> points;
		// ����Ҫ��ʾ������
		private List<Bead> displayBeads;
		// ���������ӵĴ���
		private int count = 0;
		// ����һ����
		private boolean lock = true;
		// �����ֻ�����
		private Vibrator vibrator;
		/// ������Ч��������
		private MediaPlayer[] soundPlayer = new MediaPlayer[Constant.SOUNDS.length];
		
		// ������Ч�Ƿ񲥷ŵı�ʶ��
		private boolean isSoundPlay =false;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			AppManager.getAppManager().addActivity(this);
			Data is=(Data) getApplicationContext();
			setContentView(R.layout.level3);
			isSoundPlay=is.get_isSound();
			// ��ȡ��Ϸ��������
			Level3.this.gameView3 = (GameView3)findViewById(R.id.gameView3);
			  

			// ����ҵ�����
			gameService3 = new GameServiceImpl3(this, gameView3.getBeadBoard());
			// ����ҵ�����
			gameView3.setGameService(gameService3);
			
			// ΪgameView�󶨴����¼�
			gameView3.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View view, MotionEvent event) {
					// ����
					if (event.getAction() == MotionEvent.ACTION_DOWN){
						System.out.println(event.getX() + "===" + event.getY());
						// �����û�����������ȡ���Ӧ������
						Bead bead = gameService3.getSelectedBead(event.getX(), event.getY());
						// �û�ѡ�е�����(Ҫ�ߵ�����)
						if (bead != null && bead.getBitmap() != null && lock){
							// ΪgameView����ѡ�е�����
							gameView3.setSelectedBead(bead);
							// Ҫ��ѡ�е���������
							startAnim(Constant.FLAG_1, Constant.TIMER_1);
							// ������Ч
							if (isSoundPlay) soundPlayer[0].start();
						}else{
							// ���Ӵ���,����û��λͼ
							if (bead != null && gameView3.getSelectedBead() != null){
								// ��ȡ��������֮�����·
								points = gameService3.getPath(gameView3.getSelectedBead(), bead);
								if (!points.isEmpty()){
									// ΪgameView����Ŀ������
									gameView3.setTargetBead(bead);
									// ����
									lock = false;
									// ��������Ч��������
									startAnim(Constant.FLAG_2, Constant.TIMER_2);
								}else{
									// ������Ч
									if (isSoundPlay) soundPlayer[1].start();
								}
							}
						}
					}
					return true;
				}
			});
			// ��ȡ�ֻ�����
			vibrator = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
			// ��ʼ����Ч
			for (int i = 0; i < Constant.SOUNDS.length; i++){
				soundPlayer[i] = MediaPlayer.create(this, Constant.SOUNDS[i]);
				// ������Ƶ��������
				soundPlayer[i].setAudioStreamType(AudioManager.STREAM_MUSIC);
			}
			
		}
		
		/** ��Ϣ������� */
		private Handler handler = new Handler(new Handler.Callback() {
			@Override
			public boolean handleMessage(Message msg) {
				switch (msg.what){
					case Constant.FLAG_1: // ����ѡ����������
						gameView3.setIsFlag();
						break;
					case Constant.FLAG_2: // ����������
						Point point = (Point)msg.obj;
						if (point != null){
							// ��һ��
							gameView3.moveBead(point);
						}else{
							// �������������� --->�����ӻ�����ʾ��������
							autoScan(Constant.SCAN_TYPE_1);
						}
						break;
					case Constant.FLAG_3: // ����������ʾ����
						Bead bead = (Bead)msg.obj;
						if (bead != null){
							// ��ʾһ������
							gameView3.displayBead(bead);
						}else{
							// ����ϵͳ�������������� --->������
							autoScan(Constant.SCAN_TYPE_2);
						}
						break;
					case Constant.FLAG_4: // ����������
						if (msg.obj != null){
							// ������ͬʱ��˸
							gameView3.setIsFlag();
						}else{
							// ��ȡ����
							int score = gameService3.getPerScore();
							if (score > 0){
								// ��ʾ�÷�
								Toast.makeText(Level3.this, "+" + score, Toast.LENGTH_SHORT).show();
								// ���÷����ۼ�
								gameService3.setTotalScore();
								int totalScore = gameView3.getBeadBoard().getTotalScore();
								if(totalScore>=50){
									Toast.makeText(Level3.this, "��ϲ������һ��", Toast.LENGTH_SHORT).show();
									Intent intent=new Intent(Level3.this,Level4.class);
									startActivity(intent);
									

								}
							}
							
							// ��������
							gameService3.clearBead();
							// ����ػ�
							gameView3.postInvalidate();
							// ����
							lock = true;
						}
						break;
				}
				return true;
			}
		});
		
		/**
		 * ��������Ч���ķ���
		 * @param flag ���ʶ
		 * @param time ʱ��
		 */
		private void startAnim(final int flag, long time){
			if (timer != null){
				timer.cancel(); // �رն�ʱ��
			}
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Message msg = new Message();
					msg.what = flag;
					switch (flag){
						case Constant.FLAG_1: // ����ѡ����������
							handler.sendMessage(msg);
							break;
						case Constant.FLAG_2: // ����������
							if (!points.isEmpty()){
								// ÿ��ɾ����һ��
								Point point = points.remove(0);
								msg.obj = point;
							}else{
								msg.obj = null;
								timer.cancel(); // ȡ����ʱ��
							}
							handler.sendMessage(msg);
							break;
						case Constant.FLAG_3: // ����������ʾ����
							if (!displayBeads.isEmpty()){
								// ÿ��ɾ����һ��
								Bead bead = displayBeads.remove(0);
								msg.obj = bead;
							}else{
								msg.obj = null;
								timer.cancel(); // ȡ����ʱ��
							}
							handler.sendMessage(msg);
							break;
						case Constant.FLAG_4: // ����������
							if (count++ < Constant.PER_NUM){
								msg.obj = true;
							}else{
								msg.obj = null;
								count = 0;
								timer.cancel(); // ȡ����ʱ��
							}
							handler.sendMessage(msg);
							break;
					}
				}
			}, 0, time);
		}
		
		/**
		 * �Զ�ɨ������
		 * @param scanType 1 : �û��������� 2. ϵͳ������������
		 */
		private void autoScan(int scanType) {
			// ������(�����ӿ������Ͳ�Ҫ��ʾ��������)
			if (gameService3.scanBead(scanType)){
				// ���������Ӷ���Ч��
				startAnim(Constant.FLAG_4, Constant.TIMER_4);
				// ������Ч
				if (isSoundPlay) soundPlayer[2].start();
			}else{// û�����ӿ�������������������
				if (scanType == 1){
					// ��ʾ����
					displayBeads = gameService3.getDisplayBeads();
					if (displayBeads != null){
						// ��������Ч��������ʾ����
						startAnim(Constant.FLAG_3, Constant.TIMER_3);
					}else{
						// ��Ϸ����
						gameOver();
						// ����
						lock = true;
					}
					
				}else{
					// �ж��������Ƿ��п�����
					if (gameService3.getEmptyBeads().size() == 0){
						// ��Ϸ����
						gameOver();
					}
					// ����
					lock = true;
				}
			}
		}
		
		/** ��Ϸ���� */
		private void gameOver() {
			// �ֻ��� (1000����)
			vibrator.vibrate(1000);
			// ��ȡ������Ϸ�ܵ÷�
			int totalScore = gameView3.getBeadBoard().getTotalScore();
			// ��ȡ��ʷ�ɼ�
			int histScore = gameView3.getBeadBoard().getHistScore();
			// ��ʾ������Ϸ�ܵ÷�
			Toast.makeText(this, "������Ϸ�ܵ÷�: " + totalScore, 500).show();
			// ��¼��óɼ�
			if (totalScore > histScore){
				// ��¼����(д��xml�ļ���)
				FileUtil.writeScore(this, totalScore);
				// ������ʷ����
				gameView3.getBeadBoard().setHistScore(totalScore);
			}
			// ��Ϸ���¿�ʼ
			gameService3.reset();
			gameView3.postInvalidate();
		}

		/** �����ǲ��ǰ����ؼ� */
		@Override
		public void onBackPressed() {
			DialogUtil.createDialog(this, "��ȷ��Ҫ�˳���Ϸ��?").show();
			
			
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()){
				case R.id.returnMenu: // ���ز˵�
					Intent intent1=new Intent(Level3.this,MainMenu.class);
					startActivity(intent1);
					break;
				case R.id.nextLevel: // ѡ��
					Intent intent2=new Intent(Level3.this,selectLevel.class);
					startActivity(intent2);
					break;
				case R.id.menu_close: // ��Ϸ�˳�
					DialogUtil.createDialog(this, "��ȷ��Ҫ�˳���Ϸ��?").show();
					break;
			}
			return true;
		}
		/** ��ǰ���ڲ����ڻ�Ծ״̬ */
		@Override
		protected void onPause() {
			super.onPause();
		}
		/** ��ǰ�������´��ڻ�Ծ״̬ */
		@Override
		protected void onResume() {
			
			super.onResume();
		}
		/** ��ǰActivity����ʱ */
		@Override
		protected void onDestroy() {
			for (MediaPlayer sound : soundPlayer){
				if (sound.isPlaying()){
					sound.stop();
				}
				sound.release();
			}
			super.onDestroy();
		}
}
