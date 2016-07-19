package com.goanimal.util;

import com.example.goanimal.R;





/**
 * ������
 * @version 1.0
 */
public final class Constant {
	/** �������Ӷ�ά����Ĵ�С */
	public static final int BEAD_SIZE = 9;
	/** ����������ߡ��ұߵļ�϶ (һ��) */
	public static final float LEFT_RIGHT_SPACE = 6.5f;
	/** ���������ϱߡ��±ߵļ�϶ (һ��) */
	public static final float TOP_BUTTOM_SPACE = 5.5f;
	/** �����ʼ�����ӵ����� */
	public static final int INIT_NUM = 3;
	
	
	/** �������ֶ������ӵ���Դ�ļ���id */
	public static final int[] BEAD_ICONS = {
		R.drawable.bang_1,
		R.drawable.bang_2,
		R.drawable.bang_3,
		R.drawable.bang_4,
		R.drawable.bang_5,
		R.drawable.bang_6
	};
	/**�����һ�ض������ӵ���Դ�ļ�id */
	public static final int[] BEAD_ICONS1 = {
		R.drawable.bang_1,
		R.drawable.bang_2,
	};
	/**����ڶ��ض������ӵ���Դ�ļ�id */
	public static final int[] BEAD_ICONS2 = {
		R.drawable.bang_1,
		R.drawable.bang_2,
		R.drawable.bang_3,
		
	};
	/**��������ض������ӵ���Դ�ļ�id */
	public static final int[] BEAD_ICONS3 = {
		R.drawable.bang_1,
		R.drawable.bang_2,
		R.drawable.bang_4,
		R.drawable.bang_3,
	};
	/**������Ĺض������ӵ���Դ�ļ�id */
	public static final int[] BEAD_ICONS4 = {
		R.drawable.bang_1,
		R.drawable.bang_2,
		R.drawable.bang_3,
		R.drawable.bang_4,
		R.drawable.bang_5,
		
	};
	
	
	/** �����������ӵ���ɫ */
	public static final String[] BEAD_COLORS = {"A","B","C","D","E","F"};
	/** �����������ӵĿ�����ɫ */
	public static final String[] FINAL_COLORS = {"AAAAA", "BBBBB", "CCCCC", "DDDDD", "EEEEE", "FFFFF"};
	/** ����ÿ���������ӵ����� */
	public static final int PER_NUM = 3;
	
	/** �������ű��� */
	public static final float MATRIX_SCALE = 0.8f;
	
	
	/** �������������ı�ʶ����ʱ�� */
	public static final long TIMER_1 = 200;
	public static final int FLAG_1 = 0x110;
	
	/** ���������ӵı�ʶ����ʱ�� */
	public static final long TIMER_2 = 100;
	public static final int FLAG_2 = 0x111;
	
	/** ������ʾ���ӵı�ʶ����ʱ�� */
	public static final long TIMER_3 = 100;
	public static final int FLAG_3 = 0x112;
	
	/** ���������ӵı�ʶ����ʱ�� */
	public static final long TIMER_4 = 300;
	public static final int FLAG_4 = 0x113;
	
	/** �����û���������Ҫ�����ӵı�ʶ�� */
	public static final int SCAN_TYPE_1 = 1;
	/** ����ϵͳ��������������Ҫ�����ӵı�ʶ�� */
	public static final int SCAN_TYPE_2 = 2;
	
	/** ������Ч����Դ���� */
	public static final int[] SOUNDS = {R.raw.selected, R.raw.error, R.raw.clear};
	/**�������ֵ���Դ���� */
	public static final int[] MUSIC={R.raw.piano_for_elise,R.raw.piano_box,R.raw.piano_dream_wedding,R.raw.piano_kiss_the_rain,R.raw.piano_spring,R.raw.piano_summer};
	/** ���忪��������Ч����Դ���� */
	public static final int[] WSOUNDS = {R.raw.logo_music, R.raw.welcome};
}
