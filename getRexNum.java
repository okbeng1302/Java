/*
*
* @desc:获取字符串中的 数字
*
*/

public class getRexNum {
    public String getUrlId(String param) {
	String regEx="[^0-9]"; 
	Pattern p = Pattern.compile(regEx);   
	Matcher paramResult = p.matcher(param);  
	return paramResult.replaceAll("").trim();
    }
}
