/**
 * 
 */
package com.goanimal.util;

import android.app.Application;

/**
 * @author ңָ����
 *
 */
public class Data extends Application{
	private Boolean isSound=false;//�ж���Ч
	public Boolean get_isSound(){
		return isSound;
	}
	public void set_isSound(Boolean isSound){
		this.isSound=isSound;
	}

}
