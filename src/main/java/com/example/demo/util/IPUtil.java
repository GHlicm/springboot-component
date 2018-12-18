package com.example.demo.util;
/**
 * ip地址和整型格式进行互换
 * @author licm
 *
 */
public class IPUtil {
	
	public static void main(String[] args){
//		Long ipLong = 2130706433L;
//		System.out.println(IPUtil.LongToIp(ipLong));
		
		String ip = "127.0.0.1";
		System.out.println(IPUtil.ipToLong(ip));
	}
	
	public static Long ipToLong(String ip){
		String[] ipSeg = ip.trim().split("\\.");
		StringBuffer sb = new StringBuffer();
		Long ipNum = 0L;
		for(int i=0; i<ipSeg.length; i++){
			if(i==0){
				ipNum += Long.parseLong(ipSeg[i]);
			}else{
				ipNum = ipNum << 8;
				ipNum += Long.parseLong(ipSeg[i]);
			}
		}
		return ipNum;
	}
	
	public static String LongToIp(Long longIp){
		Long[] longs = {0xff000000L, 0x00ff0000L, 0x0000ff00L, 0x000000ffL};
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<longs.length; i++){
			long var1 = longs[i] & longIp;
			Integer var2 = 4*(longs.length-1-i)*2;
			Long var3 = var1 >> var2;
			sb.append(String.valueOf(var3));
			if(i != longs.length-1){
				sb.append(".");
			}
		}
		return sb.toString();
	}
}
