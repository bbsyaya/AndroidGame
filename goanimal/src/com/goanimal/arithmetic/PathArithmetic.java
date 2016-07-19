package com.goanimal.arithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.goanimal.domain.Bead;

import android.graphics.Point;
import android.util.Log;

/**
 * ·��������ɨ���㷨
 * @version 1.0
 */
public class PathArithmetic {
	
	/** ��¼ɨ���Ѿ������ĵ� */
	private List<Point> invalidPoints;
	/** ��¼��Ч·���ĵ� */
	private List<Point> pathPoints;
	
	// ����ģʽ
	private static PathArithmetic pathArithmetic = new PathArithmetic();
	private PathArithmetic(){}
	public static PathArithmetic getInstance(){
		return pathArithmetic; 
	}
	
	public List<Point> getPath(Point from, Point to, Bead[][] beads){
		invalidPoints = new ArrayList<Point>();
		pathPoints = new ArrayList<Point>();
		isLink(from, to, beads);
		return pathPoints;
	}
	
	/**
	 * ���������������
	 * @param from ��ʼ��
	 * @param to ������
	 * @param beads ���Ӷ�ά����
	 * @return
	 */
	private boolean isLink(Point from, final Point to, Bead[][] beads) {
		// ��һ������¼�߹��ĵ�
		invalidPoints.add(from);
		// �ڶ�������ȡ�ϡ��ҡ������ĸ��㡣
		Point[] points = {
			new Point(from.x, from.y - 1), 
			new Point(from.x, from.y + 1),
			new Point(from.x - 1, from.y),
			new Point(from.x + 1, from.y)
		};
		// ���������ж��ĸ����Ƿ���Ч������Ŀ�ĵ㡣
		List<Point> temp = new ArrayList<Point>();
		for (Point p : points){
			// �ǲ��ǵ���Ŀ�ص�
			if (p.equals(to)){
				pathPoints.add(p);
				return true;
			}
			if (isCheck(p,  beads)){
				temp.add(p);
			}
		}
		// ���Ĳ����ж���Ч���Ƿ�ȫ��ռ�ꡣ
		if (temp.isEmpty()) return false;
		
		// ���岽������Ч��������·������
		Collections.sort(temp, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				double r1 = Math.sqrt((p1.x - to.x) * (p1.x - to.x) + (p1.y - to.y) * (p1.y - to.y));
				double r2 = Math.sqrt((p2.x - to.x) * (p2.x - to.x) + (p2.y - to.y) * (p2.y - to.y));
				return r1 < r2 ? -1 : 0;
			}
		});
		// ���������ݹ��ҳ���Ч�㼰��������Ŀ�ĵ����Ч��ȫ��������ϡ�
		for (Point p : temp){
			boolean flag = isLink(p, to, beads);
			if (flag){
				pathPoints.add(p);
				return true;
			}
		}
		return false;
	}
	/**
	 * �������Ч��
	 * @param point
	 * @param beads
	 * @return
	 */
	private boolean isCheck(Point point, Bead[][] beads) {
		if (invalidPoints.contains(point)){
			return false;
		}
		return (point.x >= 0 && point.x < beads.length && point.y >= 0 && point.y < beads.length 
				&& beads[point.x][point.y].getBitmap() == null);
		
	}
}
