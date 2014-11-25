package aa;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static  Queue<String> queueurl = new LinkedList<String>();// 只存放url的那个队列
	public static Queue<Urlandstring> queueurlandstring = new LinkedList<Urlandstring>();// url和string都存储的队列

	public static void main(String args[]) throws UnsupportedEncodingException {
	String firstpath = "http://10.108.106.179/techqq/index.htm";
	BloomFilter.init();
	BloomFilter.add(firstpath);
	Main.getQueueurl().add(firstpath);

	
	ExecutorService execc=Executors.newCachedThreadPool();
	execc.execute(new Grab());
		
		
		
		ExecutorService exec=Executors.newCachedThreadPool();
		exec.execute(new Analyze());
		
		
	}
	public static Queue<String> getQueueurl() {
		return queueurl;
	}
	public void setQueueurl(Queue<String> queueurl) {
		Main.queueurl = queueurl;
	}
	public static Queue<Urlandstring> getQueueurlandstring() {
		return queueurlandstring;
	}
	public void setQueueurlandstring(Queue<Urlandstring> queueurlandstring) {
		Main.queueurlandstring = queueurlandstring;
	}
}

class Urlandstring {
	String url;
	String string;
}