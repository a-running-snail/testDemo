package com.runningsnail.androiddemo.dialog;

import com.runningsnail.androiddemo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class DialogActivity extends Activity implements OnClickListener{
	
	private Button normal,list,single,mutli;
	private AlertDialog.Builder builder;
	private DialogInterface.OnClickListener listener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		normal = (Button) findViewById(R.id.dialog_normal);
		list = (Button) findViewById(R.id.dialog_list);
		single = (Button) findViewById(R.id.dialog_single);
		mutli = (Button) findViewById(R.id.dialog_mutli);
		listener();
		normal.setOnClickListener(this);
		list.setOnClickListener(this);
		single.setOnClickListener(this);
		mutli.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.dialog_normal:
            normalDialog();
			break;
		case R.id.dialog_list:
			listDialog();
			break;
		case R.id.dialog_single:
			singleDialog();
			break;
		case R.id.dialog_mutli:
			mutliDialog();
			break;
		default:
			break;
		}
	}

	private void mutliDialog() {
		// TODO Auto-generated method stub
		
		final String[] favourite = new String[]{"篮球","足球","羽毛球","游泳","跑步"};
		boolean[] bool = new boolean[]{false,false,true,true,true};
		builder = new AlertDialog.Builder(DialogActivity.this);
		builder.setMultiChoiceItems(favourite, bool, new DialogInterface.OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				String str = isChecked?"选中":"未选中";
                Toast.makeText(getApplicationContext(),favourite[which]+str , Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		builder.setPositiveButton("确定", listener);
		builder.create().show();
	}

	private void singleDialog() {
		// TODO Auto-generated method stub
		final String[] fruit = new String[]{"苹果","香蕉","橘子","西瓜","葡萄"};
		builder = new AlertDialog.Builder(DialogActivity.this);
		builder.setSingleChoiceItems(fruit, 0, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),fruit[which] , Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		builder.setPositiveButton("确定", listener);
		builder.create().show();
	}

	private void listDialog() {
		// TODO Auto-generated method stub
		final String[] sex = new String[]{"男","女"};
		builder = new AlertDialog.Builder(DialogActivity.this);
		builder.setItems(sex, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
                Toast.makeText(getApplicationContext(),sex[which] , Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		builder.create().show();
	}

	private void normalDialog() {
		// TODO Auto-generated method stub
		builder = new AlertDialog.Builder(DialogActivity.this);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("一般弹出框");
		builder.setTitle("提示");
		builder.setNegativeButton("取消", listener);
		builder.setNeutralButton("忽略", listener);
		builder.setPositiveButton("确定", listener);
		builder.create().getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		builder.create().show();
	}

	private void listener() {
		 listener = new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case Dialog.BUTTON_POSITIVE:
					dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "确定", Toast.LENGTH_SHORT).show();
                    break;

				case Dialog.BUTTON_NEGATIVE:
					dialog.dismiss();
					Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_SHORT).show();
					break;

				case Dialog.BUTTON_NEUTRAL:
					dialog.dismiss();
					Toast.makeText(getApplicationContext(), "忽略", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		};
	}
    
}
