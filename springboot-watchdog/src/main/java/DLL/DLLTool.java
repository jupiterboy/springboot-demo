package DLL;

public class DLLTool {

	public static void main(String[] args)
	{
		UT_DLL dll = new UT_DLL();
		System.out.println(System.getProperty("java.library.path"));
		// 检测加密锁
		int key1 = 7446;
		int key2 = 10631;
		int key3 = 16985;
		int key4 = 7839;
		int x = (int) (1073741824 * (Math.random()-0.5)+107374182 * (Math.random()-0.5));
		int lock[] = new int[1];
		try{
			
			dll.InitiateLock(0);  //调用加密锁函数之前必须调用初始化函数打开锁，否者其他函数调用不成功。
			
			boolean DogVal = dll.Lock32_Function(x, lock, 0);
			int FunVal = shieldPC(x, key1, key2, key3, key4);
			String str="";
			if (DogVal == true && lock[0] == FunVal) {
				str = "算法返回值=" + FunVal + "\n硬件返回值=" + lock[0] + "\n加密锁验证成功\n";
			} else {
				str = "算法返回值=" + FunVal + "\n硬件返回值=" + lock[0] + "\n加密锁验证失败,错误代码为"
				+ Long.toHexString(dll.LYFGetLastErr()) + "\n";
			}
			System.out.println(str);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public static int shieldPC(int uRandomNum, int uKey1, int uKey2, int uKey3, int uKey4)
	{
		int ReTurnData;

		int y,y1,y2,x1,x2,y11,y22;
		int outdata1,outdata2;
		//outdata2 = //保存X的高16位
		outdata2 = (int)((uRandomNum & 0xffff0000) >> 16);
		outdata1 = (int)(0xffff & uRandomNum); //保存X的低16位
		outdata2=(0x0000FFFF+outdata2+1);
		x1=outdata1;
		x2=outdata2;
		x2=(x2&0x0000ffff);

		y1 = (int)(x1 ^ uKey2);
		y11 = (int)(x2 ^ uKey1);
		y11 = (y11&0x0000ffff);

		y1 = y1 + y11;
		y1=(y1&0x0000ffff);
		if (y1 > 32767)
			y1 = y1 - 32767;

		y1 = (int)(y1 ^ 16) ;
		if (y1 > 32767)
			y1 = y1 - 32767;

		y1 = (int)(y1 % uKey4);
		y = (int)(y1 ^ uKey3);
		if (y > 50000)
			y = y - 50000-1;

		y11 = x1 + uKey1;
		y11=(y11&0x0000ffff);
		if (y11 > 32767)
			y11 = y11 - 32767;

		y22 = (int)(y11 % uKey3);
		y11 = (int)(uKey4 ^ x2);
		y2 = y22 ^ y11;

		if (y2 > 50000)
			y2 = y2 - 50000-1;
		y = y ^ y2;

		ReTurnData = y2;

		ReTurnData = ReTurnData << 16; //返回的数据为y2和y拼成的长整型数据
		ReTurnData = ReTurnData ^ y;
		return ReTurnData;
	}
}
