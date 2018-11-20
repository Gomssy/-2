package LinkRunner;

import java.io.*;
import java.util.*;

public class LinkRunner
{
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		HashMap<String, String> h = new HashMap<String, String>();
		
		while(true)
		{
			System.out.println("1.실행하기\n2.저장하기");
			System.out.print("실행할 옵션을 선택하세요>>");
			int n = scanner.nextInt();
			
			if(n==1)
			{
				System.out.print("실행할 프로그램을 입력하세요>>");
				String s = scanner.next();
				if(h.containsKey(s))
				{	
					try	
					{
						Process oProcess = new ProcessBuilder(h.get(s)).start();
					}
					catch (IOException e)
					{
				  	   System.err.println("에러! 외부 명령 실행에 실패했습니다.\n" + e.getMessage());
				  	   System.exit(-1);}
					}
				}
			
			if(n==2)
			{
				System.out.print("저장할 파일명과 링크를 입력하세요>>");
				String name = scanner.next();
				if(name.equals("stop"))
					continue;
			
				String link = scanner.nextLine();
				link = link.trim();
			
				h.put(name, link);
			}
		}
	}
}
