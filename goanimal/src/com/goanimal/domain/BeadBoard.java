package com.goanimal.domain;


import com.example.goanimal.R;
import com.goanimal.util.BitmapUtil;
import com.goanimal.util.Constant;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.WindowManager;

/**
 * ����ʵ��
 * @version 1.0
 */
public class BeadBoard {
	// ������
	public float gridWidth;
	// ����ĸ߶�
	public float gridHeight;
	// ���̵ı���λͼ
	public Bitmap boardImage;
	// ����ͼƬ���ֻ�֮���������
    public float scale;
    // ���Ӷ�ά���飨��������������)
    public Bead[][] beads = new Bead[Constant.BEAD_SIZE][Constant.BEAD_SIZE];
    // С����(����һ��Ҫ��ʾ����������)
    public Bitmap topImage;
	// ��ʷ����
    private int histScore;
    // ������Ϸ���ܷ���
    private int totalScore;
    public BeadBoard(Context context){
    	// ͨ��Context��ȡ���ڹ�����
    	WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    	// ��ȡ�ֻ�����Ļ�Ŀ��
    	@SuppressWarnings("deprecation")
		int width = wm.getDefaultDisplay().getWidth();
    	// ������ͼƬת����λͼ
    	
    	Bitmap source =BitmapUtil.getBitmap(context, R.drawable.board3);
    	
    	// ���������ͼƬ���ֻ�֮������ű���
    	this.scale = Float.valueOf(width) / Float.valueOf(source.getWidth());
    	
    	// ������ͼƬ��������(������)
    	Matrix m = new Matrix();
    	// �������ű���
    	m.setScale(this.scale, this.scale);
    	// �õ����Ź�������̵�λͼ
    	this.boardImage = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), m, true);
    	
    	// ��������Ŀ��
    	this.gridWidth = (Float.valueOf(source.getWidth() - Constant.LEFT_RIGHT_SPACE * 2)  / Constant.BEAD_SIZE) * this.scale;
    	// ��������ĸ߶�
    	this.gridHeight = (Float.valueOf(source.getHeight() - Constant.TOP_BUTTOM_SPACE * 2) / Constant.BEAD_SIZE) * this.scale;
    	
    	// ��ȡС���̶�Ӧ��λͼ
    	source = BitmapUtil.getBitmap(context, R.drawable.titile2);
    	// �õ����Ź����С���̵�λͼ
    	m.setScale(this.scale * Constant.MATRIX_SCALE , this.scale * Constant.MATRIX_SCALE);
    	this.topImage = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), m, true);
    	// ���ó�ʼ����(��ʼ����������������)
    	
    	this.init();
    }
    
    /** ��ʼ�������ϵ����е����� */
    private void init(){
    	for (int i = 0; i < beads.length; i++){
    		for (int j = 0; j < beads.length; j++){
    			
    			Bead bead = new Bead();
    			bead.x = i;
    			bead.y = j;
    			beads[i][j] = bead;
    		}
    	}
    }

    
	public int getHistScore() {
		return histScore;
	}

	public void setHistScore(int histScore) {
		this.histScore = histScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int perScore) {
		if (perScore == 0){
			this.totalScore = 0;
		}
		this.totalScore += perScore;
	}
}
