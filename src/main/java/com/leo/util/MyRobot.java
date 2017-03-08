package com.leo.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRobot {
	public static Robot robot;
	public static int globalTime = 50;
	protected static Logger logger = LoggerFactory.getLogger("MyRobot");
	static{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 移动到图像左上角的相对位置
	 * @param fileName
	 * @param x
	 * @param y
	 * @throws Exception 
	 */
	public static void move(String fileName,int x,int y) throws Exception{
		
		ImageUtil image = new ImageUtil(fileName);
		if(image.Finded){
			int[] location = image.getLocation();
			robot.mouseMove(location[0]+x, location[1]+y);
			globalWait(globalTime);
		}else{
			throw new Exception(image.keyImagePath+"没有在屏幕内");
		}
	}
	/**
	 * 移动到图像重心
	 * @param fileName
	 * @throws Exception 
	 */
	public static void move(String fileName) throws Exception{
		ImageUtil image = new ImageUtil(fileName);
		if(image.Finded){
			int[] location = image.getLocation();
			int[] keyImage = image.getKeyImageWH();
			//移动重心位置
			robot.mouseMove(location[0]+(keyImage[0]/2), location[1]+(keyImage[1]/2));
			globalWait(globalTime);
		}else{
			throw new Exception(image.keyImagePath+"没有在屏幕内");
		}
	}
	/**
	 * 移动到绝对位置
	 * @param x
	 * @param y
	 */
	public static void move(int x,int y){
			//移动重心位置
			robot.mouseMove(0,0);
			robot.mouseMove(x,y);
			globalWait(globalTime);
	}
	/**
	 * 移动到相对位置
	 * @param x
	 * @param y
	 */
	public static void move_rela(int x,int y){
		//移动重心位置
		robot.mouseMove(x,y);
		globalWait(globalTime);
	}
	/**
	 * 移动到绝对位置点击一次
	 * @param x
	 * @param y
	 */
	public static void click(int x,int y){
			robot.mouseMove(0,0);
			robot.mouseMove(x,y);
			robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            globalWait(globalTime);
	}
	/**
	 * 移动到相对位置点击一次
	 * @param x
	 * @param y
	 */
	public static void click_rela(int x,int y){
		robot.mouseMove(x,y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        globalWait(globalTime);
	}
	/**
	 * 移动到图像的重心并点击一次
	 * @param fileName
	 * @throws Exception 
	 */
	public static void click(String fileName) throws Exception {
		ImageUtil image = new ImageUtil(fileName);
		if(image.Finded){
			int[] location = image.getLocation();
			int[] keyImage = image.getKeyImageWH();
			//移动重心位置
			robot.mouseMove(location[0]+(keyImage[0]/2), location[1]+(keyImage[1]/2));
			robot.mousePress(InputEvent.BUTTON1_MASK);
	        robot.mouseRelease(InputEvent.BUTTON1_MASK);
	        globalWait(globalTime);
		}else{
			throw new Exception(image.keyImagePath+"没有在屏幕内");
		}
	}
	/**
	 * 移动到图像左上角的相对位置并点击一次
	 * @param fileName
	 * @param x
	 * @param y
	 * @throws Exception 
	 */
	public static void click(String fileName,int x,int y) throws Exception{
		ImageUtil image = new ImageUtil(fileName);
		if(image.Finded){
			int[] location = image.getLocation();
			robot.mouseMove(location[0]+x, location[1]+y);
			robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            globalWait(globalTime);
		}else{
			throw new Exception(image.keyImagePath+"没有在屏幕内");
		}
	}
	/**
	 * 鼠标双击
	 */
	public static void dbClick(){
			robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            globalWait(globalTime);
	}
	/**
	 * 鼠标左键
	 */
	public static void click(){
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            globalWait(globalTime);
	}
	/**
	 * 鼠标右键
	 * @param fileName
	 * @param x
	 * @param y
	 */
	public static void rightClick(String fileName,int x,int y){
			 robot.mousePress(InputEvent.BUTTON3_MASK);
	         robot.mouseRelease(InputEvent.BUTTON3_MASK);
	         globalWait(globalTime);
	}
	/**
	 * 全选ctrl+a
	 * @param afterTime
	 */
	public static void selectAll(int afterTime){
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		globalWait(globalTime);
	}
	/**
	 * 复制ctrl+c
	 * @param afterTime
	 */
	public static void copy(int afterTime){
		globalWait(afterTime);
			robot.keyPress(KeyEvent.VK_CONTROL);
			globalWait(100);
			robot.keyPress(KeyEvent.VK_C);
			globalWait(100);
			robot.keyRelease(KeyEvent.VK_C);
			globalWait(100);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			globalWait(globalTime);
	}
	/**
	 * 粘贴ctrl+v
	 * @param afterTime
	 */
	public static void paste(int afterTime){
		globalWait(afterTime);
			robot.keyPress(KeyEvent.VK_CONTROL);
			globalWait(100);
			robot.keyPress(KeyEvent.VK_V);
			globalWait(100);
			robot.keyRelease(KeyEvent.VK_V);
			globalWait(100);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			globalWait(globalTime);
	}
	/**
	 * 删除
	 * @param count
	 * @param afterTime
	 * @param interval
	 */
	public static void del(int count,int afterTime,int interval){
		globalWait(afterTime);
			try {
				for(int i = 0;i<count;i++){
					robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.keyRelease(KeyEvent.VK_BACK_SPACE);
					Thread.currentThread().sleep(interval);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			globalWait(globalTime);
	}
	/**
	 * 动作完成后停滞时间
	 * @param millSeconds
	 */
	public static void globalWait(int millSeconds){
		try {
			Thread.currentThread().sleep(millSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
