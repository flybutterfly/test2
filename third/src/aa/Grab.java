package aa;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


 class Grab extends Thread{
	public void run(){
		synchronized (Main.getQueueurl()) {
		while(true){
			if(!Main.getQueueurl().isEmpty()){
			String ss=Main.getQueueurl().poll();
			System.out.println("出队列的是==========================================================="+ss);
			 BufferedInputStream bufferedInput = null;   
	         HttpURLConnection httpUrl = null;   
	         URL url = null;   
	         StringBuffer buff = new StringBuffer(); 
	         byte[] buffer = new byte[1024];
	         try {
	         url = new URL(ss);   
	         httpUrl = (HttpURLConnection) url.openConnection();   
	         if(httpUrl.getResponseCode()==200){
			httpUrl.connect();
	         bufferedInput = new BufferedInputStream(httpUrl.getInputStream()); 
	         int bytesRead = 0;
	         //从文件中按字节读取内容，到文件尾部时read方法将返回-1
	         while ((bytesRead = bufferedInput.read(buffer)) != -1) {
	         //将读取的字节转为字符串对象
	         String chunk = new String(buffer, 0, bytesRead);
	         buff.append(chunk);
	         }
	     	
				Urlandstring uu=new Urlandstring();
				uu.string=buff.toString();
				uu.url=ss;
				Main.getQueueurlandstring().add(uu);
	         }} catch (IOException e) {
	 			e.printStackTrace();
	 		} 
		
	         }
		}
		}
	}
		
	}
 
