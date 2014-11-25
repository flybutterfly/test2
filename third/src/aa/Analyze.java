package aa;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyze extends Thread {

	public static String urltransfer(String url, String cxiangduiurl) {
		String xiangduiurl = cxiangduiurl.substring(1,
				cxiangduiurl.length() - 1);//因为传过来的都是”http://localhost……“两边都是引号的。。
		//System.out.println("传过来的相对路径是="+xiangduiurl);

		StringBuffer jueduiurl = new StringBuffer();
		String splitstring = url.substring(7);

		if (xiangduiurl.length() < 7
				|| !xiangduiurl.substring(0, 7).equals("http://")) {
			String[] array = splitstring.split("/");
			String[] xiangduiarray = xiangduiurl.split("/");
			jueduiurl.append("http://");
			for (int i = 0; i < array.length - xiangduiarray.length; i++) {
				jueduiurl.append(array[i] + "/");
			}

			jueduiurl.append(xiangduiarray[xiangduiarray.length - 1]);
		}
		if (xiangduiurl.length() >= 7
				&& xiangduiurl.substring(0, 7).equals("http://")) {
			jueduiurl.append(xiangduiurl);
		}
		return jueduiurl.toString();

	}

	public void run() {
		synchronized (Main.getQueueurlandstring()) {
			while (true) {

				if(!Main.getQueueurlandstring().isEmpty()){
					Urlandstring uu = Main.getQueueurlandstring().poll();

					String url = uu.url;
					String ss = uu.string;
					try {
						System.out.println("网页的绝对路径是"+url+",网页的大小是"+ss.getBytes("utf-8").length+"字节，也就是"+ss.getBytes("utf-8").length/1024+"kb");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					ArrayList<String> al = new ArrayList<String>();
					String s = ss.toString();
					String regex = "<a.*?/a>";

					Pattern pt = Pattern.compile(regex);
					Matcher mt = pt.matcher(s);

					while (mt.find()) {
						if (true) {
							String s3 = "href=\".*?\"";
							Pattern pt3 = Pattern.compile(s3);
							Matcher mt3 = pt3.matcher(mt.group());
							while (mt3.find()) {
								String u = mt3.group()
										.replaceAll("href=|>", "");
								al.add(u);
							}
						}
					}
					for (int i = 0; i < al.size(); i++) {
						if (!BloomFilter.contains(urltransfer(url, al.get(i)))) {
							Main.getQueueurl().add(urltransfer(url, al.get(i)));
							BloomFilter.add(urltransfer(url, al.get(i)));
						}
					}

//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
			}
		}

	}
}
// }
