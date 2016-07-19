package com.goanimal.util;

import com.example.goanimal.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

/**
 * ��������Ĺ�����
 * @version 1.0
 */
public class DialogUtil {
	
	/**
	 * ������������ķ���
	 * @param context
	 * @param message
	 * @return
	 */
	public static Dialog createDialog(final Context context, String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("��ʾ");
		builder.setMessage(message);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				//((Activity)context).finish();
				AppManager.getAppManager().AppExit(context);
			}
		});
		builder.setNegativeButton("ȡ��", null);
		return builder.create();
	}
}
