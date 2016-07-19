package com.goanimal.domain;

import android.graphics.Bitmap;

/**
 * ����ʵ��
 * @version 1.0
 */
public class Bead {
	
	// �����̶�ά�����е�x����
	public int x;
	// �����̶�ά�����е�y����
	public int y;
	// ���ӵ���ɫ (Ĭ�ϵ���ɫ)
	public String color = "Z";
	// ���ӵ�λͼ
	private Bitmap bitmap;
	
	@Override
	public boolean equals(Object o) {
		// �ж������������(ֻҪ�������ӵ�x��y����Ⱦʹ�����ͬһ������)
		if (o == null || !(o instanceof Bead)){
			return false;
		}
		Bead bead = (Bead)o;
		return bead.x == this.x && bead.y == this.y;
	}

	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		if (bitmap == null){
			this.color = "Z";
		}
		this.bitmap = bitmap;
	}
	
	
	
}
