package com.ics.dew.help;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class Copy
{
	public static void main(String args[]) throws Exception
	{
		String file = "Load MSg06.sql";
		String src = "//129.227.32.216/w$/DEW/06MD01 New/";
		String dest = "D:/DEW/";
		boolean status;
		status = GenericMethodUtilis.CopyFileToDestinationPath(file, src, dest);
		System.out.println(status);
	}
}
