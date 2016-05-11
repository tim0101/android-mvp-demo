package com.hailang.app.myasdemo.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Tools {
	public static boolean isNetworkAvailable(Context context) {
		// Context context = mActivity.getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static String decodeContent(String paramString) {
		return URLDecoder.decode(paramString);
	}

	public static void dialTheNumber(Context paramContext, String paramString) {
		Uri localUri = Uri.parse("tel:" + paramString);
		Intent localIntent = new Intent("android.intent.action.DIAL", localUri);
		paramContext.startActivity(localIntent);
	}

	private static byte[] getBytes(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = is.read(buffer, 0, 1024)) > 0) {
			bos.write(buffer, 0, len);
		}
		bos.flush();
		return bos.toByteArray();
	}

	public static boolean isEmail(String paramString) {
		return Pattern
				.compile(
						"^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
				.matcher(paramString).matches();
	}

	public static Bitmap laodImageFromFile(String filePath) {
		File localFile = new File(filePath);
		Bitmap localBitmap = null;
		if (localFile.exists()) {
			localBitmap = BitmapFactory.decodeFile(filePath);
		} else {
			localBitmap = null;// BitmapFactory.decodeFile("item_default_picture");
		}
		return localBitmap;
	}

	public static Bitmap loadImageFromStream(InputStream is) {
		byte[] arrayOfByte;
		Bitmap bitMap = null;
		try {
			arrayOfByte = getBytes(is);
			int i = arrayOfByte.length;
			bitMap = BitmapFactory.decodeByteArray(arrayOfByte, 0, i);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭InputStream
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bitMap;
	}

	public static Bitmap loadImageFromUrl(String strImageUrl) {
		Bitmap bitMap = null;
		URL aryURI;
		try {
			aryURI = new URL(strImageUrl);
			URLConnection conn = aryURI.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			bitMap = BitmapFactory.decodeStream(is);
			is.close();

		} catch (Exception e) {
		}
		return bitMap;
	}

	public static Bitmap loadImageFromUrlWithOpt(String strImageUrl) {
		Bitmap bitMap = null;
		URL aryURI;
		try {
			aryURI = new URL(strImageUrl);
			URLConnection conn = aryURI.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = 4;
			bitMap = BitmapFactory.decodeStream(is, null, opts);
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitMap;
	}

	public static String md5(String value) {
		String result = null;
		if (value != null && value.length() > 0) {
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				md5.update(value.getBytes(), 0, value.length());
				result = String
						.format("%032X", new BigInteger(1, md5.digest()));
			} catch (Exception e) {
				// Not perfect, but trim to 32 chars.
				result = result.substring(0, 32);
			}
		}
		return result.toLowerCase();
	}

	public static boolean saveFile(Bitmap bitmap, String dirPath,
			String filePath) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			if (!dir.mkdirs())
				return false;
		}
		File file = new File(filePath);
		boolean ret = false;
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(file));
			if (bitmap != null) {
				CompressFormat cf = CompressFormat.PNG;
				ret = bitmap.compress(cf, 100, bos);
				bos.flush();
				bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String toHexString(byte[] bytes) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		int i = bytes.length * 2;
		StringBuilder builder = new StringBuilder(i);
		int k = bytes.length;
		for (int j = 0; j < k; j++) {
			int m = (bytes[j] & 0xF0) >>> 4;
			builder.append(chars[m]);
			int n = bytes[j] & 0xF;
			builder.append(chars[n]);
		}
		return builder.toString();
	}

	/**
	 * 将javabean集合转换成json字符串
	 * 
	 * @param e
	 *            javabean 集合对象
	 * @return 返回json字符串
	 */
	public static <E extends Object> String javaBeanListToJson(List<E> e) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<E>>() {
		}.getType();
		return gson.toJson(e, type);
		// beanListToJson;
	}

	public static Map<String, String> json2Map(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		return gson.fromJson(json, type);
		// beanListToJson;
	}

	public static <T> List<T> json2List(TypeToken<List<T>> token, String json) {
		return json2List(token, json, false);
	}

	public static <T> List<T> json2List(TypeToken<List<T>> token, String json,
			boolean withoutField) {
		GsonBuilder bulder = new GsonBuilder();
		if (withoutField) {
			bulder.excludeFieldsWithoutExposeAnnotation();
		}
		Gson gson = bulder.create();
		List<T> retList = gson.fromJson(json, token.getType());
		return retList;
	}

	public static <T> String list2Json(TypeToken<List<T>> token, List<T> list) {
		return list2Json(token, list, false);
	}

	public static <T> String list2Json(TypeToken<List<T>> token, List<T> list,
			boolean withoutField) {
		GsonBuilder bulder = new GsonBuilder();
		if (withoutField) {
			bulder.excludeFieldsWithoutExposeAnnotation();
		}
		Gson gson = bulder.create();
		return gson.toJson(list, token.getType());
	}

	public static List<Map<String, String>> json2MapList(String json) {
		GsonBuilder bulder = new GsonBuilder();
		bulder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = bulder.create();
		List<Map<String, String>> retList = gson.fromJson(json,
				new TypeToken<List<Map<String, String>>>() {
				}.getType());
		return retList;
	}

	public static String map2Json(Map<String, String> m) {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		return gson.toJson(m, type);
		// beanListToJson;
	}

	public static String mapObj2Json(Map<String, Object> m) {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		return gson.toJson(m, type);
		// beanListToJson;
	}

	public static Map<String, Object> json2MapObj(String m) {
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		return gson.fromJson(m, type);
		// beanListToJson;
	}

	public static String obj2Json(Object obj) {
		return obj2Json(obj, false);
	}

	public static String obj2Json(Object obj, boolean withoutField) {
		GsonBuilder bulder = new GsonBuilder();
		if (withoutField) {
			bulder.excludeFieldsWithoutExposeAnnotation();
		}
		Gson gson = bulder.create();
		return gson.toJson(obj);
		// beanListToJson;
	}

	public static <T> T json2Obj(String json, TypeToken<T> token) {
		return json2Obj(json, token, false);
	}

	public static <T> T json2Obj(String json, TypeToken<T> token,
			boolean withoutField) {
		GsonBuilder bulder = new GsonBuilder();
		if (withoutField) {
			bulder.excludeFieldsWithoutExposeAnnotation();
		}
		Gson gson = bulder.create();
		return gson.fromJson(json, token.getType());
		// beanListToJson;
	}

	public static void downloadFile(Context context, String url) {
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(intent);
	}

	// 全角转化为半角的方法
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	// 半角转化为全角的方法
	public static String ToSBC(String input) {
		// 半角转全角：
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127 && c[i] > 32)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]")
				.replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 使用格式<b>_pattern</b>格式化日期输出
	 * 
	 * @param _date
	 *            日期对象
	 * @param _pattern
	 *            日期格式
	 * @return 格式化后的日期
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String stringDate = simpleDateFormat.format(date);

		return stringDate;
	}

	public static void callThePhone(Context context, String tel) {
		// 调用系统方法拨打电话
		Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ tel));
		context.startActivity(dialIntent);
	}

	public static void sendMsg(Context context, String mobile) {
		// 调用系统方法发信息
		Intent dialIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"
				+ mobile));
		context.startActivity(dialIntent);
	}

	public static void sendEmail(Context context, String email) {
		// 调用系统方法发邮件
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
		intent.setType("text/plain");
		// Intent.createChooser(intent, "请选择邮件类型");
		context.startActivity(intent);
	}

	public static String date2String(Date utilDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return f.format(utilDate);
	}

	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	public static void showInfo(Context act, String msg) {
		if (act != null) {
			Toast.makeText(act, msg, Toast.LENGTH_SHORT).show();
		}
	}

	public static void share2Friends(Context context, String title,
			String content) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "好友推荐");
		intent.putExtra(Intent.EXTRA_TEXT, content);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(intent, title));
	}

	public static boolean saveLayout2Pic(View view, String dirPath) {
		// 将布局转换为View类型对象
		boolean flag = false;
		// 打开图像缓存
		view.setDrawingCacheEnabled(true);
		// 必须调用measure和layout方法才能成功保存可视组件的截图到png图像文件
		// 测量View大小
		view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		// 发送位置和尺寸到View及其所有的子View
		view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
		try {
			// 获得可视组件的截图
			Bitmap bitmap = view.getDrawingCache();
			// 将截图保存在SD卡根目录的test.png图像文件中
			FileOutputStream fos = new FileOutputStream(dirPath);
			// 将Bitmap对象中的图像数据压缩成png格式的图像数据，并将这些数据保存在test.png文件中
			bitmap.compress(CompressFormat.PNG, 100, fos);
			// 关闭文件输出流
			fos.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	public static void takePic(Activity activity, String filedir,
			String fileName, int reqestCode) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			File dir = new File(filedir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file=new File(dir, fileName);
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Uri fileurl = Uri.fromFile(file);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, fileurl);
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			activity.startActivityForResult(intent, reqestCode);
		} else {
			Tools.showInfo(activity, "请确保SD卡已插入");
		}
	}

	public static void takePic(Fragment fragmete, String filedir,
			String fileName, int reqestCode) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			File dir = new File(filedir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			Uri fileurl = Uri.fromFile(new File(dir, fileName));
			intent.putExtra(MediaStore.EXTRA_OUTPUT, fileurl);
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			fragmete.startActivityForResult(intent, reqestCode);
		} else {
			Tools.showInfo(fragmete.getActivity(), "请确保SD卡已插入");
		}
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	public static void gotoMap(Context context,double lat,double lng){
		Uri mUri = Uri.parse("geo:"+lat+","+lng);
		Intent mIntent = new Intent(Intent.ACTION_VIEW,mUri);
		context.startActivity(mIntent);
	}
}