package com.example.fragment;

	import java.io.File;
import java.util.ArrayList;

import com.example.file_manager.R;
import com.example.file_manager.R.layout;
import com.example.tool.serach;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class list_fragment extends ListFragment {

		getfile one;
		File[] filename;
		ArrayList<String> name = new ArrayList<String>();
		File file,str;

		ArrayAdapter<String> adapter2;

		ArrayList<File[]> mybreak = new ArrayList<File[]>();
		mybase adapter;
		
		public interface getfile {
			public void getData(ArrayList<File[]> str);
		}

		int count=0;
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			file = Environment.getExternalStorageDirectory();// 获取SD跟目录
			serach ch = new serach();
			filename = ch.isfile(file);
			for (int i = 0; i < filename.length; i++) {
				name.add(filename[i].getName());
			}
			mybreak.add(filename);
			adapter=new mybase();
			
			adapter.notifyDataSetChanged();
			setListAdapter(adapter);

			return super.onCreateView(inflater, container, savedInstanceState);
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			super.onListItemClick(l, v, position, id);

			serach ch = new serach();
             if(ch.isfile(filename[position])!=null){
            	 name.clear();
            	 filename = ch.isfile(filename[position]);
            	 
			mybreak.add(filename);
			for (int i = 0; i < filename.length; i++) {
				name.add(filename[i].getName());
			}
			mybase myadAdapter=new mybase();
			setListAdapter(myadAdapter);

			one.getData(mybreak);// 会退栈数据传出
		}
             else {
				one.getData(mybreak);// 会退栈数据传出
				Intent in=new Intent();
				str=filename[position];
				if(str.getName().endsWith(".mp3")){
					in.setAction(android.content.Intent.ACTION_VIEW);
					in.setDataAndType(Uri.fromFile(str), "audio/*");
					startActivity(in);

				}
				else if(str.getName().endsWith(".jpg")){
					in.setAction(android.content.Intent.ACTION_VIEW);
					in.setDataAndType(
					Uri.fromFile(str),
					"image/*");
					startActivity(in);

				}
				else  if(str.getName().endsWith(".txt")){
					in.setAction(android.content.Intent.ACTION_VIEW);
					in.setDataAndType(
					Uri.fromFile(str),
					"text/*");
					startActivity(in);
				}
				else {
					Toast.makeText(getActivity(), "无可执行文件", Toast.LENGTH_LONG).show();
				}
			}
             
		}
		public void My_break(File[] fname, boolean flag) {
			filename = fname;
		}

		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			one = (getfile) activity;
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
				View flater=LayoutInflater.from(getActivity()).inflate(layout.showlist, null);
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




