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
			System.out.print("������ ���ϸ�� ��ũ�� �Է��ϼ���>>");
			String name = scanner.next();
			if(name.equals("stop"))
				continue;
			
			String link = scanner.nextLine();
			link = link.trim();
			
			h.put(name, link);
		}
	}
}
