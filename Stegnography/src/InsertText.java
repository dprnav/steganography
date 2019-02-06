import java.awt.Color;
import java.awt.image.BufferedImage;
public class InsertText 
{
	static BufferedImage insertText(BufferedImage image,String msg) throws Exception
	{
		int length_size = lengthSize(image);
		int string_size=msg.length();
		int max_string_size = stringSize(image);
		byte msg_byte[] = msg.getBytes();
		
		if(string_size>max_string_size)
			string_size = max_string_size;
		
		String msg_binary=new String("");
		
		int n=length_size - Integer.toBinaryString(string_size).length();
		String size_msg=Integer.toBinaryString(string_size);
		for(int i=0;i<n;i++)
			size_msg="0"+size_msg;
		for(int x:msg_byte)
		{		
			String binary=Integer.toBinaryString(x);
			int len=8-binary.length();
			for(int i=0;i<len;i++)
				binary="0"+binary;
			msg_binary+=binary;
		}
		String final_data=size_msg+msg_binary;
		int length_final_data=final_data.length(),k=0;
		OuterLoop: for(int i=0;i<image.getWidth();i++)
		{
			for(int j=0;j<image.getHeight();j++,k+=3)
			{
				if(k>=length_final_data)
					break OuterLoop;
				int x=image.getRGB(i,j);
				int R=(x>>16) & 0xff;
				int G=(x>>8)  & 0xff;
				int B=(x) & 0xff;
				System.out.print(R+":"+G+":"+B+":"+((x>>24) & 0xff));
				String s=Integer.toBinaryString(G);
				s=s.length()>1?s.substring(0, s.length()-1):"";
				G=Integer.parseInt(s+final_data.charAt(k),2);
				if(k+1<length_final_data)
				{
					s=Integer.toBinaryString(R);
					s=s.length()>1?s.substring(0, s.length()-1):"";
					R=Integer.parseInt(s+final_data.charAt(k+1),2);
				}
				if(k+2<length_final_data)
				{
					s=Integer.toBinaryString(B);
					s=s.length()>1?s.substring(0, s.length()-1):"";
					B=Integer.parseInt(s+final_data.charAt(k+2),2);
				}
				
				image.setRGB(i,j,new Color(R,G,B,((x>>24) & 0xff)).getRGB());
			}
		}
		return image;
	}
	static String extractText(BufferedImage image) throws Exception
	{
		int length_size = lengthSize(image);
		int string_size_bits,k=0,i=0,j=0;
		String temp=new String("");
		OuterLoop: for(i=0;i<image.getWidth();i++)
		{
			for(j=0;j<image.getHeight();j++,k+=3)
			{
				if(k>=length_size)
					break OuterLoop;
				int x=image.getRGB(i,j);
				int R=(x>>16) & 0xff;
				int G=(x>>8)  & 0xff;
				int B=(x) & 0xff;
				temp+=(G & 0b1);
				if(k+1<length_size)
					temp+=(R & 0b1);
				if(k+2<length_size)
					temp+=(B & 0b1);
			}
		}
		string_size_bits=Integer.parseInt(temp, 2)*8+(length_size);
		if(Integer.parseInt(temp, 2) > stringSize(image))
			return "error: No Message Is Encoded In This Image";
		k=0;
		temp="";
		OuterLoop: for(i=0;i<image.getWidth();i++)
		{
			for(j=0;j<image.getHeight();j++,k+=3)
			{
				if(k>=string_size_bits)
					break OuterLoop;
				int x=image.getRGB(i,j);
				int R=(x>>16) & 0xff;
				int G=(x>>8)  & 0xff;
				int B=(x) & 0xff;
				temp+=(G & 0b1);
				if(k+1<string_size_bits)
					temp+=(R & 0b1);
				if(k+2<string_size_bits)
					temp+=(B & 0b1);
			}
		}
		temp=temp.substring(length_size,temp.length());
		string_size_bits=temp.length();
		String msg=new String("");
		int count=0;
		while(count<string_size_bits)
		{
			int ascii=Integer.parseInt(temp.substring(count, count+8),2);	
			if(ascii<0 || ascii>127)
				return "error: No Message Is Encoded In This Image";
			msg+=(char)ascii;
			count+=8;
		}
		return msg;
	}
	/*This function is used to calculate the no of bits required to
	 * store the length of maximum string that can be stored in the 
	 * image.
	 */
	static int lengthSize(BufferedImage image) throws Exception
	{
		int max_length=image.getWidth()*image.getHeight()*3,length_size;
		length_size=Integer.toBinaryString(max_length/8).length();
		return length_size;
	}
	/*This function is used to calculate the maximum size of string
	 * that can be stored. (in characters)
	 */
	static int stringSize(BufferedImage image) throws Exception
	{	
		int max_length=image.getWidth()*image.getHeight()*3;
		return (max_length-Integer.toBinaryString(max_length/8).length())/8;
	}
}
