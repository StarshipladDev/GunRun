/**
 * A collection of classes and interfaces that allow a basic first person shooter rip off.
 */
/**
 * @author Ozzy1
 *
 */
/**
 * 	int delay = 200; //milliseconds
	boolean shot=false;
	Timer timer;
	int bulletFired=0;
	Random random;
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			//...Perform a task...
			UnShoot();
		}
	};
	int shit=0;
	BufferedImage img1;
	BufferedImage img2;
	public GunrunPanel() {
		random =new Random();
		timer=new Timer(delay, taskPerformer);
		// TODO Auto-generated constructor stub
		try{
			File file= new File("img/pistol1.png");
			File file1= new File("img/flare1.png");
			img1=ImageIO.read(file);
			img2=ImageIO.read(file1);
		}catch(IOException e){

		}
		setPreferredSize(new Dimension(400,500));
	}
	public void paint(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 0, 400, 500);
		g.drawImage(img1,15,65,img1.getWidth()+15,65+img1.getHeight(),0,0,img1.getWidth(),img1.getHeight(),null);
		for(int i=0;i<bulletFired;i++){
			g.setColor(Color.BLACK);
			g.fillOval(35+random.nextInt(5),35+random.nextInt(5),5,5);
		}
		if(shit==0){
			g.setColor(Color.white);
			g.drawRect(15,15,40,40);
			g.drawLine(0, 0, 15, 15);
			g.drawLine(70, 0, 55,15);
			g.drawLine(0, 70, 15,55);
			g.drawLine(70, 70, 55, 55);
		}

		if(shit==1){
			g.setColor(Color.white);
			g.drawRect(10,10,50,50);
			g.drawLine(0, 0, 10, 10);
			g.drawLine(70, 0, 60,10);
			g.drawLine(0, 70, 10,60);
			g.drawLine(70, 70, 60, 60);
		}
		if(shit==2){
			g.setColor(Color.white);
			g.drawRect(5,5,60,60);
			g.drawLine(0, 0, 5,5);
			g.drawLine(70, 0, 65,5);
			g.drawLine(0, 70, 5,65);
			g.drawLine(70, 70, 65, 65);
		}
		if(shot){
			g.drawImage(img2,15,65,img2.getWidth()+15,65+img2.getHeight(),0,0,img2.getWidth(),img2.getHeight(),null);
			g.setColor(Color.yellow);
			g.drawLine(35+random.nextInt(5),70,35,35);
		}
	}
	public void paintComponent(Graphics g){
		System.out.println("ture");
		super.paintComponent(g);

	}
	public void poop(){
		shit++;
		repaint();
	}
	public void poopReverse(){
		shit--;
		repaint();
	}
	public void Shoot(){
		if(shot){
			return;
		}
		bulletFired++;
		timer.start();
		shot=true;
		System.out.println("shooting");
		repaint();
	}
	public void UnShoot(){
		timer.stop();
		shot=false;
		repaint();
	}

 */
package Gunrun;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Timer;
