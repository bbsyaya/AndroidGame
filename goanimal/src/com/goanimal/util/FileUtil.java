package com.goanimal.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * �ļ���д������
 * @version 1.0
 */
public class FileUtil {

	/**
	 * д����
	 * @param context
	 * @param score
	 */
	public static void writeScore(Context context,int score){
		// ��һ������: ����xml���ļ���
		// �ڶ�������: ��ʲô���ķ�ʽ��������ļ�
		SharedPreferences shared = context.getSharedPreferences("goanimal_score", Context.MODE_PRIVATE);
		Editor editor = shared.edit();
		editor.putInt("score", score);
		editor.commit(); // �ύ����
	}
	/**
	 * ��ȡ����
	 * @param context
	 * @return
	 */
	public static int readScore(Context context){
		SharedPreferences shared = context.getSharedPreferences("goanimal_score", Context.MODE_PRIVATE);
		return shared.getInt("score", 0);
	}
}
