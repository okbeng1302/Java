import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class ZipUtil {

	public static void main(String[] args) {

		boolean result = new ZipUtil().unzip(new File("/Users/apple/Downloads/alipay_record_20180428_1410.zip"),"/Users/apple/Downloads/");
		
		System.out.print(result);
	}

	public boolean unzip(File zipFile,String descDir) {
		
		File pathFile = new File(descDir);
		// 如果指定目录文件不存在 ，那么久创建它
		if(!pathFile.exists())
			pathFile.mkdirs();
		ZipFile zip = null;
		try {
			
			zip = new ZipFile(zipFile,Charset.forName("GBK"));
			
			for(Enumeration entries = zip.entries();entries.hasMoreElements();) {
				
				// 获取每个压缩文件夹中的文件
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipName = entry.getName();
				
				// 输入流, 将压缩包中的文件打到输入流中  
				InputStream in = zip.getInputStream(entry);			
				
				// 输出文件路径
				String outPath = (descDir+zipName).replaceAll("\\*", "/");
				System.out.println(outPath);
				
				// 新建文件路径
				File file = new File(outPath.substring(0, outPath.lastIndexOf("/")));
				
				if(!file.exists())
					file.mkdirs();
				
//				if(new File(outPath).isDirectory()) {
//					System.out.println(new File(outPath).isDirectory());
//					continue;
//				}
				// 输出流
				OutputStream os = new FileOutputStream(outPath);
				
				byte[] buf = new byte[1024];
				int len;
				while((len=in.read(buf))>0) {
					os.write(buf, 0, len);
				}
				
				in.close();
				os.close();
				
			}
			
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
