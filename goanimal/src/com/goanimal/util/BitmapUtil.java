package com.goanimal.util;

import java.util.Random;

import com.goanimal.domain.Bead;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;


/**
 * ����λͼ�Ĺ�����
 *
 * @version 1.0
 */
public class BitmapUtil {
	// �����������
	private static Random random = new Random();
	// ����Matrix������
	private static Matrix m = new Matrix();
	/**
	 * ������Դ�ļ���ȡλͼ
	 * @param context ��ǰ��Activity (������)
	 * @param resId ��Դ�ļ���id
	 * @return λͼ����
	 */
	public static Bitmap getBitmap(Context context, int resId){
		return BitmapFactory.decodeResource(context.getResources(), resId);
	}
	/**
	 * �������һ������
	 * @param context ��ǰ��Activity (������)
	 * @param scale ���ű���
	 * @return ����
	 */
	public static Bead randomBead(Context context, float scale) {
		// �����ȡһ������ͼƬ��Դid (0-1)
		int cursor;
	
		//��һ����Դ
		
			cursor=random.nextInt(Constant.BEAD_ICONS1.length);
			// �õ�λͼ
			Bitmap source = getBitmap(context, Constant.BEAD_ICONS1[cursor]);
			// �����Ӱ���������
			m.setScale(scale, scale);
			// ���������Ź����λͼ
			source = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), m, true);
			Bead bead = new Bead();
			bead.setBitmap(source);
			bead.color = Constant.BEAD_COLORS[cursor];
			return bead;
		
	}
}