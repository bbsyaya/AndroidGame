package com.goanimal.arithmetic;

import java.util.ArrayList;
import java.util.List;

import com.goanimal.domain.Bead;
import com.goanimal.util.Constant;

import android.graphics.Point;

/**
 * ͷ��ɨ��Ĺ�����(���򡢼�����б����б)
 * @version 1.0
 */
public class ScanArithmetic {
	
	/** ����ɨ������ս�� */
	private static List<List<Point>> lists = new ArrayList<List<Point>>();
	/**
	 * �����ĸ���λɨ�跽��
	 * @param beads
	 * @return
	 */
	public static List<List<Point>> scan(Bead[][] beads) {
		lists.clear(); // ��ռ���
		List<Point> points_1 = horizontalScan(beads);
		List<Point> points_2 = verticalScan(beads);
		List<Point> points_3 = leftSlantingScan(beads);
		List<Point> points_4 = rightSlantingScan(beads);
		if (points_1 != null && points_1.size() > 0){
			lists.add(points_1);
		}
		if (points_2 != null && points_2.size() > 0){
			lists.add(points_2);
		}
		if (points_3 != null && points_3.size() > 0){
			lists.add(points_3);
		}
		if (points_4 != null && points_4.size() > 0){
			lists.add(points_4);
		}
		return lists;
	}
	
	/**
	 * ����ɨ��
	 * @param beads
	 * @return
	 */
	private static List<Point> horizontalScan(Bead[][] beads){
		for (int i = 0; i < beads.length; i++){
			// ���������ƴ����һ�������ӵ���ɫ
			StringBuilder color = new StringBuilder();
			// ���������������һ���е����Ӷ�Ӧ�ĵ�ļ���
			List<Point> points = new ArrayList<Point>();
			// ɨ��һ��
			for (int j = 0; j < beads.length; j++){
				Bead bead = beads[j][i];
				color.append(bead.color);
				points.add(new Point(j, i));
			}
			// �ж��Ƿ��������������ϵ�ͬɫ��������һ��
			for (String str : Constant.FINAL_COLORS){
				// �����ͬɫ����������һ��
				if (color.toString().contains(str)){
					// ��ȡ����һ��������������ϵ����Ӷ�Ӧ�ĵ�
					int begin = color.indexOf(str);
					int end = color.lastIndexOf(str) + str.length();
					List<Point> temps = new ArrayList<Point>();
					for (int k = begin; k < end; k++){
						temps.add(points.get(k));
					}
					return temps;
				}
			}
		}
		return null;
	}
	/**
	 * ����ɨ��
	 * @param beads
	 * @return
	 */
	private static List<Point> verticalScan(Bead[][] beads){
		for (int i = 0; i < beads.length; i++){
			// ���������ƴ����һ�������ӵ���ɫ
			StringBuilder color = new StringBuilder();
			// ���������������һ���е����Ӷ�Ӧ�ĵ�ļ���
			List<Point> points = new ArrayList<Point>();
			// ɨ��һ��
			for (int j = 0; j < beads.length; j++){
				Bead bead = beads[i][j];
				color.append(bead.color);
				points.add(new Point(i, j));
			}
			// �ж��Ƿ��������������ϵ�ͬɫ��������һ��
			for (String str : Constant.FINAL_COLORS){
				// �����ͬɫ����������һ��
				if (color.toString().contains(str)){
					// ��ȡ����һ��������������ϵ����Ӷ�Ӧ�ĵ�
					int begin = color.indexOf(str);
					int end = color.lastIndexOf(str) + str.length();
					List<Point> temps = new ArrayList<Point>();
					for (int k = begin; k < end; k++){
						temps.add(points.get(k));
					}
					return temps;
				}
			}
		}
		return null;
	}
	/**
	 * ��бɨ��
	 * @param beads
	 * @return
	 */
	private static List<Point> leftSlantingScan(Bead[][] beads){
		// ������(0,4-0,0-4,0)
		for (int i = -4; i <= 4; i++){
			// ���������ƴ����һ�������ӵ���ɫ
			StringBuilder color = new StringBuilder();
			// ���������������һ���е����Ӷ�Ӧ�ĵ�ļ���
			List<Point> points = new ArrayList<Point>();
			if (i <= 0){ // ����0,4��������ɨ
				for (int j = 0; j < beads.length + i; j++){
					Bead bead = beads[j][j - i];
					color.append(bead.color);
					points.add(new Point(j, j - i));
				}
			}else{ // ����8,7��������ɨ
				for (int j = beads.length - 1; j >= i; j--){
					Bead bead = beads[j][j - i];
					color.append(bead.color);
					points.add(new Point(j, j - i));
				}
			}
			// �ж��Ƿ��������������ϵ�ͬɫ��������һ��
			for (String str : Constant.FINAL_COLORS){
				// �����ͬɫ����������һ��
				if (color.toString().contains(str)){
					// ��ȡ����һ��������������ϵ����Ӷ�Ӧ�ĵ�
					int begin = color.indexOf(str);
					int end = color.lastIndexOf(str) + str.length();
					List<Point> temps = new ArrayList<Point>();
					for (int k = begin; k < end; k++){
						temps.add(points.get(k));
					}
					return temps;
				}
			}
		}
		return null;
	}
	/**
	 * ��бɨ��
	 * @param beads
	 * @return
	 */
	private static List<Point> rightSlantingScan(Bead[][] beads){
		// ������(4,0-8,0-8,4)
		for (int i = 4; i <= 12; i++){
			
			// ���������ƴ����һ�������ӵ���ɫ
			StringBuilder color = new StringBuilder();
			// ���������������һ���е����Ӷ�Ӧ�ĵ�ļ���
			List<Point> points = new ArrayList<Point>();
			if (i <= 8){ // ����0,4��������ɨ
				for (int j = 0; j <= i; j++){
					Bead bead = beads[j][i - j];
					color.append(bead.color);
					points.add(new Point(j, i - j));
				}
			}else{ // ����8,1��������ɨ
				for (int j = beads.length - 1; j > i - beads.length; j--){
					// i = 12
					Bead bead = beads[j][i - j]; 
					color.append(bead.color);
					points.add(new Point(j, i - j));
				}
			}
			// �ж��Ƿ��������������ϵ�ͬɫ��������һ��
			for (String str : Constant.FINAL_COLORS){
				// �����ͬɫ����������һ��
				if (color.toString().contains(str)){
					// ��ȡ����һ��������������ϵ����Ӷ�Ӧ�ĵ�
					int begin = color.indexOf(str);
					int end = color.lastIndexOf(str) + str.length();
					List<Point> temps = new ArrayList<Point>();
					for (int k = begin; k < end; k++){
						temps.add(points.get(k));
					}
					return temps;
				}
			}
		}
		return null;
	}
}