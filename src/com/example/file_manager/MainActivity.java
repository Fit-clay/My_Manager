package com.example.file_manager;
import java.io.File;
import java.util.ArrayList;

import com.example.file_manager.R.layout;
import com.example.fragment.list_fragment;
import com.example.fragment.list_fragment.getfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements getfile {

	
	FragmentManager manager;
	FragmentTransaction faction;
	ArrayList<File[]> breakfile = new ArrayList<File[]>();
	int breaknum = 0;
	boolean ff = false; // 用于判断是否退到主页面
	list_fragment local;
	ArrayList<Integer> mylist = new ArrayList<Integer>();

	ArrayList<String> name;
	File onclickfile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		manager=getSupportFragmentManager();
		
		faction = manager.beginTransaction();
		local = new list_fragment();
		faction.add(R.id.frame, local);
		faction.commit();
		mylist.add(0);
		
		
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (breakfile.size() - 2 >= 0) {
			File[] mybreakfile = breakfile.get(breaknum);
			
			name = new ArrayList<String>();
			for (int i = 0; i < mybreakfile.length; i++) {
				name.add(mybreakfile[i].getName());
			}
	    	
			local.setListAdapter(new mybase());
			local.My_break(mybreakfile, ff);
			if (breakfile.size() > 0) {
				breakfile.remove(breakfile.size() - 1);
			}

		} else {

			finish();

		}
	}

	@Override
	public void getData(ArrayList<File[]> str) {
		// TODO Auto-generated method stub
		breakfile = str;
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	 class  mybase extends BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return name.size();
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				My_Handler myhandler;
				if(convertView==null){
					myhandler=new My_Handler();
				View flater=LayoutInflater.from(MainActivity.this).inflate(layout.showlist, null);
				convertView=flater;
				myhandler.tv=(TextView)convertView.findViewById(R.id.textView1);
				myhandler.lv=(ImageView)convertView.findViewById(R.id.imageView1);
				convertView.setTag(myhandler);
			}else {
				myhandler=(My_Handler) convertView.getTag();
			}
				String end=name.get(position);
				myhandler.tv.setText(end);
				myhandler.tv.setTextSize(36);
				if(end.endsWith(".mp3")){
					myhandler.lv.setImageResource(R.drawable.music);
				}
				else  if(end.endsWith(".jpg")){
					myhandler.lv.setImageResource(R.drawable.photo);

				}
				else  if(end.endsWith(".txt")){
					myhandler.lv.setImageResource(R.drawable.text);

				}
				else {
					myhandler.lv.setImageResource(R.drawable.file);
				}
				
				return convertView;
			}
			class My_Handler{
				private TextView tv;
				private ImageView lv;
			}
			
		}
	
}
