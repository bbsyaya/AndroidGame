/**
 * 
 */
package com.goanimal.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Point;

import com.goanimal.arithmetic.PathArithmetic;
import com.goanimal.arithmetic.ScanArithmetic;
import com.goanimal.domain.Bead;
import com.goanimal.domain.BeadBoard;
import com.goanimal.service.GameService3;
import com.goanimal.util.BitmapUtil3;
import com.goanimal.util.Constant;

/**
 * @author ңָ����
 *
 */
public class GameServiceImpl3 implements GameService3{
	/** Activity������ */
	private Context context;
	/** ����ʵ�� */
	private BeadBoard beadBoard;
	/** ����������� */
	private Random random = new Random();
	/** ������һ��Ҫ��ʾ���������� */
	private List<Bead> preparedBeads = new ArrayList<Bead>();
	/** ���建����������� */
	private int perScore = 0;
	/** ����������ӵĵ�ļ��� */
	private List<Point> linkPoints = new ArrayList<Point>();
	
	public GameServiceImpl3(Context context, BeadBoard beadBoard) {
		this.context = context;
		this.beadBoard = beadBoard;
		// ���ó�ʼ���ķ���,��ʼ�������ϵ��������
		this.init();
	}
	
	
	/** ��ȡ��һ��Ҫ��ʾ���������� */
	public List<Bead> getPreparedBeads(){
		return preparedBeads;
	}
	/** ������һ��Ҫ��ʾ���������� */
	private void setPreparedBeads(){
		preparedBeads.clear(); // ���
		for (int i = 0; i < Constant.PER_NUM; i++){
			Bead bead = BitmapUtil3.randomBead(context, beadBoard.scale);
			preparedBeads.add(bead);
		}
	}
	/**
	 * �����û�����������ȡ���Ӧ������
	 * @param x ����
	 * @param y ����
	 * @return Bead
	 */
	public Bead getSelectedBead(float x, float y){
		// ��y���������Ч�Ե��ж�
		if (y < beadBoard.topImage.getHeight() + Constant.TOP_BUTTOM_SPACE * beadBoard.scale){
			return null;
		}
		if (y > (beadBoard.topImage.getHeight() + beadBoard.boardImage.getHeight() 
				- Constant.TOP_BUTTOM_SPACE * beadBoard.scale)){
			return null;
		}
		// ��x��y����ת���ɶ�ά�����е�i��j
		int i = Float.valueOf((x - Constant.LEFT_RIGHT_SPACE * beadBoard.scale) / beadBoard.gridWidth).intValue();
		int j = Float.valueOf((y - beadBoard.topImage.getHeight() 
				- Constant.TOP_BUTTOM_SPACE * beadBoard.scale) / beadBoard.gridHeight).intValue();
		
		// ��i����Ч�Է���
		if (i >= 9) i = Constant.BEAD_SIZE - 1;
		
		if (i >= 0 && i < Constant.BEAD_SIZE && j >= 0 && j < Constant.BEAD_SIZE){
			return beadBoard.beads[i][j];
		}
		return null;
	}
	
	/**
	 * ��ȡ�������ӿ��ߵ���·
	 * @param selectedBead ѡ�е�����
	 * @param targetBead Ŀ�������
	 * @return ������·��ļ���
	 */
	public List<Point> getPath(Bead selectedBead, Bead targetBead){
		if (selectedBead == null || targetBead == null){
			return null;
		}
		Point from = new Point(selectedBead.x, selectedBead.y);
		Point to = new Point(targetBead.x, targetBead.y);
		// ����㵽�յ�
		List<Point> points = PathArithmetic.getInstance().getPath(from, to, beadBoard.beads);
		if (points.size() <= 5){
			// ��ת�����е�Ԫ��
			Collections.reverse(points);
			return points;
		}else{
			// ���յ㵽���
			List<Point> tempPoints = PathArithmetic.getInstance().getPath(to, from, beadBoard.beads);
			if (points.size() < tempPoints.size()){
				// ��ת�����е�Ԫ��
				Collections.reverse(points);
				return points;
			}else{
				tempPoints.remove(from);
				tempPoints.add(to);
				return tempPoints;
			}
		}
	}
	/**
	 * ��ȡҪ��ʾ����������
	 * @return ���Ӽ���
	 */
	public List<Bead> getDisplayBeads(){
		// Ԥ���ص��������� (С�����ϵ���������)
		List<Bead> preparedBeads = this.getPreparedBeads();
		// ��ȡ���������еĿ�����
		List<Bead> emptyBeads = this.getEmptyBeads();
		// �ж������Ͽ����ӵ����� (����С��3)
		if (emptyBeads.size() < 3){
			return null;
		}
		List<Bead> lists = new ArrayList<Bead>();
		for (Bead preBead : preparedBeads){
			int cursor = random.nextInt(emptyBeads.size());
			Bead bead = emptyBeads.remove(cursor);
			// �����������
			preBead.x = bead.x;
			preBead.y = bead.y;
			lists.add(preBead);
		}
		// ���¼�����������(��һ��)
		this.setPreparedBeads();
		return lists;
	}
	/**
	 * ɨ�����ӵķ���
	 * @return
	 */
	public boolean scanBead(int scanType){
		linkPoints.clear(); // ��ռ���
		perScore = 0; // ��շ���
		// �ĸ���λ��ɨ��
		List<List<Point>> lists = ScanArithmetic.scan(beadBoard.beads);
		// �ж��Ƿ������ӿ���
		if (lists.isEmpty()){
			return false;
		}
		// ͳ��һ��һ���ж��ٸ����ӿ�����
		int count = 0;
		// ��¼�������ӵĵ�
		for (List<Point> points : lists){
			for (Point point : points){
				if (!linkPoints.contains(point)){
					linkPoints.add(point);
				}
			}
			count += points.size();
		}
		
		// ��������� (һ������2��) ���ķ��� * ��λ
		if (scanType == Constant.SCAN_TYPE_1){
			// �û������Ӻ����þ������
			perScore = count * 2 * lists.size();
		}
		return true;
	}
	
	/** ��ȡ����(ÿ�εĵ÷�) */
	public int getPerScore(){
		return this.perScore;
	}
	/** ��ȡ�������ӵ����ӵ� */
	public List<Point> getLinkPoints(){
		return this.linkPoints;
	}
	/** ������Ϸ�ۼƷ��� */
	public void setTotalScore(){
		beadBoard.setTotalScore(this.perScore);
	}
	/**
	 * �����������
	 */
	public void clearBead(){
		for (Point p : linkPoints){
			beadBoard.beads[p.x][p.y].setBitmap(null);
		}
		linkPoints.clear();
	}
	/** ��ȡ���������еĿ����� */
	public List<Bead> getEmptyBeads(){
		List<Bead> emptyBeads = new ArrayList<Bead>();
		for (int i = 0; i < beadBoard.beads.length; i++){
			for (int j = 0; j < beadBoard.beads.length; j++){
				Bead bead = beadBoard.beads[i][j];
				// ֻҪ����ʵ��û��λͼ,�Ǿ���һ������
				if (bead.getBitmap() == null){
					emptyBeads.add(bead);
				}
			}
		}
		return emptyBeads;
	}
	/**
	 * ��Ϸ���¿�ʼ�ķ���
	 */
	public void reset(){
		for (int i = 0; i < beadBoard.beads.length; i++){
			for (int j = 0; j < beadBoard.beads.length; j++){
				beadBoard.beads[i][j].setBitmap(null);
			}
		}
		this.perScore = 0;
		this.setTotalScore();
		this.init();
	}
	
	


	/** ��ʼ���ķ��� */
	private void init(){
		// ��ȡ���еĿ�����
		List<Bead> emptyBeads = this.getEmptyBeads();
		// ��ʼ���������
		for (int i = 0; i < Constant.INIT_NUM; i++){
			Bead temp = BitmapUtil3.randomBead(context, beadBoard.scale);
			// �����������п����ӵĵط������ȡһ������
			int cursor = random.nextInt(emptyBeads.size());
			Bead bead = emptyBeads.remove(cursor);
			bead.setBitmap(temp.getBitmap());
			bead.color = temp.color;
		}
		// ������һ��Ҫ��ʾ����������
		this.setPreparedBeads();
	}

}
