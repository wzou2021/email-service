package au.com.ms.util;

public class AppUtils {
	
	public static boolean isNullOrEmpty(String str) {
		if(str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

}
