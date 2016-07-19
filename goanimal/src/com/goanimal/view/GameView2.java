package com.goanimal.view;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.example.goanimal.R;
import com.goanimal.domain.Bead;
import com.goanimal.domain.BeadBoard;

import com.goanimal.service.GameService2;
import com.goanimal.util.Constant;
import com.goanimal.util.FileUtil;

/**
 * ��Ϸ��������
 * @version 1.0
 */
public class GameView2 extends View {
	
	/** �������� */
	private BeadBoard beadBoard;
	/** ����ҵ������ */
	private GameService2 gameService2;
	/** ����Matrix */
	private Matrix m = new Matrix();
	/** ����ѡ�е����� */
	private Bead selectedBead;
	/** ����ѡ�е����ӷŴ������С�ı�ʶ�� */
	private boolean isFlag = true;
	/** ����Ŀ�ĵ����� */
	private Bead targetBead;
	/** ����һ����ʱ������������ѡ�е����� */
	private Bead tempBead;
	/** ����һ�����Ի�����һ���� */
	private Point upPoint;
	
	public GameView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.beadBoard = new BeadBoard(context);
		// ������ʷ����
		this.beadBoard.setHistScore(FileUtil.readScore(context));
		m.setScale(Constant.MATRIX_SCALE, Constant.MATRIX_SCALE);
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		Paint paint = new Paint();
		// ���������С
		paint.setTextSize(30);
		// ������ɫ
		paint.setColor(Color.BLACK);
		// �������ߵ�λ��
		float left = this.getWidth() / 2 - beadBoard.topImage.getWidth() / 2;
		// ������÷��� ��������м䣩
		canvas.drawText(this.getResources().getString(R.string.hist_score), 
				left / 4, 
				beadBoard.topImage.getHeight() / 2
				+ paint.getTextSize() / 2, paint);
		
		// �����м��С����
		canvas.drawBitmap(beadBoard.topImage, left, 0, paint);
		// ������һ��Ҫ��ʾ����������
		List<Bead> lists =  gameService2.getPreparedBeads();
		for (int i = 0; i < lists.size(); i++){
			Bitmap source = lists.get(i).getBitmap();
			source = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), m, true);
			canvas.drawBitmap(source, left + i * beadBoard.topImage.getWidth() / 3 + 2.0f, 0, paint);
		}
		
		// ���Ƶ�ǰ���� (�ұ����м�)
		canvas.drawText(this.getResources().getString(R.string.total_score) + beadBoard.getTotalScore(), 
				left + beadBoard.topImage.getWidth() + left / 4, 
				beadBoard.topImage.getHeight() / 2
				+ paint.getTextSize() / 2, paint);
		
		
		// ��������
		canvas.drawBitmap(beadBoard.boardImage, 0, beadBoard.topImage.getHeight(), paint);
		
		// ��������
		for (int i = 0; i < beadBoard.beads.length; i++){
			for (int j = 0; j < beadBoard.beads.length; j++){
				Bead bead = beadBoard.beads[i][j];
				if (bead.getBitmap() != null){ // ��������
					// ��ȡ�����������ӵ�
					List<Point> points = gameService2.getLinkPoints();
					
					// �ж��ǲ����û�ѡ�е�����
					if (bead.equals(selectedBead) || points.contains(new Point(bead.x, bead.y))){
						if (isFlag){ // ����
							canvas.drawBitmap(bead.getBitmap(), 
									i * beadBoard.gridWidth + Constant.LEFT_RIGHT_SPACE * beadBoard.scale, 
									j * beadBoard.gridHeight + Constant.TOP_BUTTOM_SPACE * beadBoard.scale
									+ beadBoard.topImage.getHeight(), 
									paint);
						}else{ // ��С
							
							Bitmap source= bead.getBitmap();
							Bitmap temp = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), m, true);
							canvas.drawBitmap(temp, 
									i * beadBoard.gridWidth + Constant.LEFT_RIGHT_SPACE * beadBoard.scale
									+ (source.getWidth() - temp.getWidth()) / 2, 
									j * beadBoard.gridHeight + Constant.TOP_BUTTOM_SPACE * beadBoard.scale
									+ beadBoard.topImage.getHeight()
									+ (source.getHeight() - temp.getHeight())/ 2, 
									paint);
						}
					}else{ // �����û�ѡ�е�����
						canvas.drawBitmap(bead.getBitmap(), 
								i * beadBoard.gridWidth + Constant.LEFT_RIGHT_SPACE * beadBoard.scale, 
								j * beadBoard.gridHeight + Constant.TOP_BUTTOM_SPACE * beadBoard.scale
								+ beadBoard.topImage.getHeight(), 
								paint);
					}
				}
			}
		}
	}
	
	/** ��ȡ����ʵ�巽�� */
	public BeadBoard getBeadBoard() {
		return beadBoard;
	}
	public void setGameService(GameService2 gameService2) {
		this.gameService2 = gameService2;
	}
	public void setSelectedBead(Bead selectedBead) {
		this.selectedBead = selectedBead;
	}
	public Bead getSelectedBead(){
		return this.selectedBead;
	}

	public void setIsFlag() {
		this.isFlag = !isFlag;
		// ���»���
		this.postInvalidate();
	}
	// �ƶ�����
	public void moveBead(Point point) {
		if (upPoint != null){
			beadBoard.beads[upPoint.x][upPoint.y].setBitmap(null);
		}
		if (!point.equals(new Point(targetBead.x, targetBead.y))){
			beadBoard.beads[point.x][point.y].setBitmap(tempBead.getBitmap());
			upPoint = point;
		}else{
			// Ŀ�ĵ�
			beadBoard.beads[targetBead.x][targetBead.y].setBitmap(tempBead.getBitmap());
			beadBoard.beads[targetBead.x][targetBead.y].color = tempBead.color;
			upPoint = null;
			targetBead = null;
		}
		this.postInvalidate();
	}
	// ����Ŀ������
	public void setTargetBead(Bead targetBead) {
		this.targetBead = targetBead;
		// ����tempBead������selectedBead
		tempBead = new Bead();
		tempBead.setBitmap(selectedBead.getBitmap());
		tempBead.color = selectedBead.color;
		// ��ѡ�е���������Ϊ��
		selectedBead.setBitmap(null);
		selectedBead = null;
	}
	/** ��ʾһ������ */
	public void displayBead(Bead bead) {
		beadBoard.beads[bead.x][bead.y].setBitmap(bead.getBitmap());
		beadBoard.beads[bead.x][bead.y].color = bead.color;
		this.postInvalidate();
	}
	
}
