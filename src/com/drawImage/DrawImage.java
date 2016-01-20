package com.drawImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class DrawImage {
	private static Random random = new Random();
	private static String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//����������ַ���

	public static void main(String[] args) {
		drawImage();

	}

	public static void drawImage(){
		//BufferedImage���Ǿ��л�������Image��,Image������������ͼ����Ϣ����
        BufferedImage image = new BufferedImage(100,100,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//����Image�����Graphics����,�Ķ��������ͼ���Ͻ��и��ֻ��Ʋ���
        g.fillRect(0, 0, 100, 100);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        g.setColor(getRandColor(110, 133));
        //���Ƹ�����
        for(int i=0;i<=20;i++){
            drowLine(g);
        }
        //��������ַ�
        String randomString = "";
        for(int i=1;i<=4;i++){
            randomString=drowString(g,randomString,i);
        }
        
        ImageOutputStream out = null;
		try {
			out = ImageIO.createImageOutputStream(new FileOutputStream("D:/test.jpeg"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        System.out.println(randomString);
        g.dispose();
        try {
            ImageIO.write(image, "JPEG", out);//���ڴ��е�ͼƬͨ��������ʽ������ͻ���
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}
	
	/*
     * �����ɫ
     */
    private static Color getRandColor(int fc,int bc){
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }
    
    /*
     * �����ַ���
     */
    private static String drowString(Graphics g,String randomString,int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString +=rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 12*i-8, 16);
        return randomString;
    }
    /*
     * ���Ƹ�����
     */
    private static void drowLine(Graphics g){
        int x = random.nextInt(100);
        int y = random.nextInt(100);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x+xl, y+yl);
    }
    /*
     * ��ȡ������ַ�
     */
    public static String getRandomString(int num){
        return String.valueOf(randString.charAt(num));
    }
    
    /*
     * �������
     */
    private static Font getFont(){
        return new Font("Fixedsys",Font.CENTER_BASELINE,18);
    }
}
