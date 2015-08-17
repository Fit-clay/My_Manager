package com.example.tool;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class serach {

	private File[] filelist;

	public File[] isfile(File str) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			if (!str.isFile()) {
				filelist = str.listFiles();

			} else {
				System.out.println(str.getName());
				
			}
			
           
		} else {
			System.out.println("sd不卡存在");
		}
		return filelist;

	}

}
