/**
 * 
 */
package com.goanimal.service;

import java.util.List;

import android.graphics.Point;

import com.goanimal.domain.Bead;

/**
 * @author ңָ����
 *
 */
public interface GameService5 {
	/** ��ȡ��һ��Ҫ��ʾ���������� */
	List<Bead> getPreparedBeads();
	/**
	 * �����û�����������ȡ���Ӧ������
	 * @param x ����
	 * @param y ����
	 * @return Bead
	 */
	Bead getSelectedBead(float x, float y);
	/**
	 * ��ȡ�������ӿ��ߵ���·
	 * @param selectedBead ѡ�е�����
	 * @param targetBead Ŀ�������
	 * @return ������·��ļ���
	 */
	List<Point> getPath(Bead selectedBead, Bead targetBead);
	/**
	 * ��ȡҪ��ʾ����������
	 * @return ���Ӽ���
	 */
	List<Bead> getDisplayBeads();
	/**
	 * ɨ�����ӵķ���
	 * @return
	 */
	boolean scanBead(int scanType);
	/** 
	 * ��ȡ����(ÿ�εĵ÷�) 
	 */
	int getPerScore();
	/** 
	 * ��ȡ�������ӵ����ӵ� 
	 */
	List<Point> getLinkPoints();
	/** 
	 * ������Ϸ�ۼƷ��� 
	 */
	void setTotalScore();
	/**
	 * �����������
	 */
	void clearBead();
	/** 
	 * ��ȡ���������еĿ����� 
	 */
	List<Bead> getEmptyBeads();
	/**
	 * ��Ϸ���¿�ʼ�ķ���
	 */
	void reset();
	
}
