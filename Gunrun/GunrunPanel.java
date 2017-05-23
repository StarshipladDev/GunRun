package Gunrun;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.io.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class GunrunPanel extends JPanel{
	int hallWayLength=0;
	int unitSize=15;
	int size=10;
	int overallSize=((2*unitSize)*(2*size));
	int playerLocationX=0;
	int playerLocationY=3;
	int reloading=0;
	int bulletFired=0;
	int delay = 200; //milliseconds

	Map map;

	boolean shot=false;

	Timer timer;

	Random random;

	String currentAction="";
	String direction="up";

	BufferedImage img1;
	BufferedImage img2;
	BufferedImage img3;

	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			//...Perform a task...
			if(currentAction.equals("")){
				return;		
			}
			else if(currentAction.equals("Reload")){
				if(reloading==0){
					playSound("audio/Reload1.wav");
					reloading++;

				}
				else if(reloading==1){
					reloading++;
				}
				else if(reloading==2){
					reloading++;
				}	
				else if(reloading==3){
					reloading=0;
					currentAction="";
				}
			}
			else if(currentAction.equals("Shoot")){
				if(!shot){
					bulletFired++;
					shot=true;
					playSound("audio/PistolShot.wav");

				}else{
					shot=false;
					currentAction="";
				}

			}
			repaint();
		}
	};

	public GunrunPanel() {
		map= new Map(size);
		random =new Random();
		timer=new Timer(delay, taskPerformer);
		timer.start();
		map.setGrid(playerLocationX, playerLocationY,false);
		// TODO Auto-generated constructor stub
		try{
			File file= new File("img/pistol1.png");
			File file1= new File("img/flare1.png");
			File file2= new File("img/pistolReload1.png");
			img1=ImageIO.read(file);
			img2=ImageIO.read(file1);
			img3=ImageIO.read(file2);
		}catch(IOException e){

		}
		setPreferredSize(new Dimension(400,500));
	}
	private void getHallwayLength(){
		int i = 0;
		hallWayLength=0;
		if (direction.equals("up")){
			i=playerLocationY;
			while(i>0){
				if(!map.getIsFull(playerLocationX,i)){
					hallWayLength++;
					i--;
				}else{
					i=0;
				}

			}
		}

		if (direction.equals("down")){
			i=playerLocationY;
			while(i<size-1){
				if(!map.getIsFull(playerLocationX,i)){
					hallWayLength++;
					i++;
				}else{
					i=size;
				}

			}
		}
		if (direction.equals("left")){
			i=playerLocationX;
			while(i>0){
				if(!map.getIsFull(i,playerLocationY)){
					hallWayLength++;
					i--;
				}else{
					i=0;
				}

			}
		}
		if (direction.equals("right")){
			i=playerLocationX;
			while(i<size-1){
				if(!map.getIsFull(i,playerLocationY)){
					hallWayLength++;
					i++;
				}else{
					i=size;
				}

			}
		}

	}
	public void paint(Graphics g){
		getHallwayLength();
		boolean leftRoom=false;
		boolean rightRoom=false;
		int hallWayDraw=((hallWayLength+1)*unitSize);
		//Background
		g.setColor(Color.gray);
		g.fillRect(0, 0,overallSize,overallSize);
		//Backboard
		g.setColor(Color.black);
		g.fillRect(hallWayDraw,hallWayDraw,overallSize-(2*hallWayDraw),overallSize-(2*hallWayDraw));
		g.setColor(Color.black);


		//Side rooms
		g.setColor(Color.black);
		if(direction.equals("up")){
			if((playerLocationX>0)&&(!map.getIsFull(playerLocationX-1, playerLocationY))){
				g.fillRect(0, hallWayDraw,hallWayDraw, overallSize-(2*hallWayDraw));
				leftRoom=true;
			}
			if((playerLocationX<size-1)&&(!map.getIsFull(playerLocationX+1, playerLocationY))){
				g.fillRect(overallSize-hallWayDraw, hallWayDraw,hallWayDraw, overallSize-(2*hallWayDraw));
				rightRoom=true;
			}
		}
		else if(direction.equals("left")){
			if((playerLocationY>0)&&(!map.getIsFull(playerLocationX, playerLocationY+1)))
			{
				g.fillRect(0, hallWayDraw,hallWayDraw, overallSize-(2*hallWayDraw));
				leftRoom=true;
			}
			if((playerLocationY<size-1)&&(!map.getIsFull(playerLocationX, playerLocationY-1))){
				g.fillRect(overallSize-hallWayDraw, hallWayDraw,hallWayDraw, overallSize-(2*hallWayDraw));
				rightRoom=true;
			}
		}
		else if(direction.equals("down")){
			if((playerLocationX<size-1)&&(!map.getIsFull(playerLocationX+1, playerLocationY))){
				g.fillRect(0, hallWayDraw,hallWayDraw, overallSize-(2*hallWayDraw));
				leftRoom=true;

			}

			if((playerLocationX>0)&&(!map.getIsFull(playerLocationX-1, playerLocationY))){
				g.fillRect(overallSize-hallWayDraw, hallWayDraw,hallWayDraw, overallSize-(2*hallWayDraw));
				rightRoom=true;
			}
		}
		else if(direction.equals("right")){

			if((playerLocationY>0)&&(!map.getIsFull(playerLocationX, playerLocationY-1))){
				g.fillRect(0, hallWayDraw,hallWayDraw, overallSize-(2*hallWayDraw));
				leftRoom=true;

			}
			if((playerLocationY<size-1)&&(!map.getIsFull(playerLocationX, playerLocationY+1))){
				g.fillRect(overallSize-hallWayDraw, hallWayDraw,hallWayDraw, overallSize-(2*hallWayDraw));
				rightRoom=true;
			}
		}
		g.setColor(Color.black);
		if(!leftRoom){
			g.drawLine(hallWayDraw, hallWayDraw,hallWayDraw,overallSize-hallWayDraw);
			//top left
			g.drawLine(0,0,hallWayDraw,hallWayDraw);

			//Bottom Left
			g.drawLine(0,overallSize,hallWayDraw,overallSize-hallWayDraw);

		}if(!rightRoom){
			g.drawLine(overallSize-hallWayDraw,hallWayDraw,overallSize-hallWayDraw,overallSize-hallWayDraw);
			//Top Right
			g.drawLine(overallSize,0,overallSize-hallWayDraw,hallWayDraw);
			//bottom Right
			g.drawLine(overallSize,overallSize,overallSize-hallWayDraw,overallSize-hallWayDraw);
		}
		//Top
		g.drawLine(hallWayDraw,hallWayDraw,overallSize-hallWayDraw,hallWayDraw);
		//Bottom
		g.drawLine(hallWayDraw,overallSize-hallWayDraw,overallSize-hallWayDraw,overallSize-hallWayDraw);

		if(shot){
			g.drawImage(img2,(overallSize/2)-(img2.getWidth()/2),overallSize-img2.getHeight(),(overallSize/2)+(img2.getWidth()/2),overallSize,0,0,img2.getWidth(),img2.getHeight(),null);
			g.setColor(Color.yellow);
			g.drawLine(((overallSize-5)/2)+random.nextInt(5),overallSize-10,overallSize/2,overallSize/2);
			g.fillOval((overallSize/2)-5,(overallSize/2)-5,10,10);
		}
		if(reloading==0){
			g.drawImage(img1,(overallSize/2)-(img1.getWidth()/2),overallSize-img1.getHeight(),(overallSize/2)+(img1.getWidth()/2),overallSize,0,0,img1.getWidth(),img1.getHeight(),null);
		}
		else if(reloading==1){
			g.drawImage(img3,(overallSize/2)-(img1.getWidth()/2),overallSize-img1.getHeight(),(overallSize/2)+(img1.getWidth()/2),overallSize,0,0,img1.getWidth(),img1.getHeight(),null);
		}
		else if(reloading==2){
			g.drawImage(img3,(overallSize/2)-(img1.getWidth()/2),overallSize-img1.getHeight(),(overallSize/2)+(img1.getWidth()/2),overallSize,img1.getWidth(),0,2*img1.getWidth(),img1.getHeight(),null);
		}
		else if(reloading==3){
			g.drawImage(img3,(overallSize/2)-(img1.getWidth()/2),overallSize-img1.getHeight(),(overallSize/2)+(img1.getWidth()/2),overallSize,2*img1.getWidth(),0,3*img1.getWidth(),img1.getHeight(),null);
		}
		for(int y=0;y<size;y++){
			for(int x=0;x<size;x++){
				if (map.getIsFull(x,y)){
					g.setColor(Color.red);
				}else{
					g.setColor(Color.blue);
				}
				g.fillRect(x*5,y*5,5,5);

			}
		}
		g.setColor(Color.yellow);
		g.fillRect(playerLocationX*5,playerLocationY*5,5,5);
		g.setColor(Color.black);
		if(direction.equals("up")){
			g.drawLine(playerLocationX*5,playerLocationY*5,(playerLocationX*5)+5,playerLocationY*5);
		}
		else if(direction.equals("left")){
			g.drawLine(playerLocationX*5,playerLocationY*5,playerLocationX*5, (playerLocationY*5)+5);
		}
		else if(direction.equals("down")){
			g.drawLine(playerLocationX*5,(playerLocationY*5)+5,(playerLocationX*5)+5, (playerLocationY*5)+5);
		}
		else if(direction.equals("right")){
			g.drawLine((playerLocationX*5)+5,playerLocationY*5,(playerLocationX*5)+5,(playerLocationY*5)+5);
		}

		g.setColor(Color.cyan);
		g.drawLine(0,0,size*5,0);
		g.setColor(Color.red);
		g.drawLine(size*5,0,size*5,size*5);
		g.setColor(Color.YELLOW);
		g.drawLine(0,size*5,size*5,size*5);
		g.setColor(Color.MAGENTA);
		g.drawLine(0,0,0,size*5);

		System.out.println("HallwayLength:"+hallWayLength);
		System.out.print("X:"+playerLocationX);
		System.out.println("Y:"+playerLocationY);
		System.out.println("Direction"+direction+"\n");

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);

	}
	static void playSound(String sound) {
		File f = new File(sound);
		Clip clip=null;
		try{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(f));
			clip.start();
			clip.drain();

		}
		catch(IOException e){
			System.out.println("Couldn't paly audio");
		}
		catch(UnsupportedAudioFileException e){
			System.out.println("Couldn't paly audio");
		}
		catch(LineUnavailableException e){
			System.out.println("Couldn't play audio");
		}
		try {
			clip.setFramePosition(0);

		} catch (Exception exp) {
		}




	}

	public void Shoot(){
		if(currentAction.equals("")){
			currentAction="Shoot";
		}
	}
	public void Forward(){
		if(direction.equals("up")){
			if((playerLocationY>0)&&(!map.getIsFull(playerLocationX, playerLocationY-1))){
				playerLocationY--;
			}
		}
		else if(direction.equals("left")){
			if(playerLocationX>0&&(!map.getIsFull(playerLocationX-1, playerLocationY))){
				playerLocationX--;
			}
		}
		else if(direction.equals("down")){
			if(playerLocationY<size-1&&(!map.getIsFull(playerLocationX, playerLocationY+1))){
				playerLocationY++;
			}
		}
		else if(direction.equals("right")){
			if(playerLocationX<size-1&&(!map.getIsFull(playerLocationX+1, playerLocationY))){
				playerLocationX++;
			}
		}System.out.println("forward");
		playSound("audio/Step.wav");
		repaint();
	}
	public void Rotate(String faceDirection){
		if(faceDirection.equals("left")){
			if(direction.equals("up")){
				direction="left";
			}
			else if(direction.equals("left")){
				direction="down";
			}
			else if(direction.equals("down")){
				direction="right";
			}
			else if(direction.equals("right")){
				direction="up";
			}
		}
		else if(faceDirection.equals("right")){
			if(direction.equals("up")){
				direction="right";
			}
			else if(direction.equals("right")){
				direction="down";
			}
			else if(direction.equals("down")){
				direction="left";
			}
			else if(direction.equals("left")){
				direction="up";
			}
		}
		bulletFired=0;
		playSound("audio/Turn.wav");
		repaint();


	}
	public void Reverse(){
		if(direction.equals("up")){
			if(playerLocationY<size-1&&(!map.getIsFull(playerLocationX, playerLocationY+1))){
				playerLocationY++;
			}
		}
		else if(direction.equals("left")){
			if(playerLocationX<size-1&&(!map.getIsFull(playerLocationX+1, playerLocationY))){
				playerLocationX++;
			}
		}
		else if(direction.equals("down")){
			if(playerLocationY>0&&(!map.getIsFull(playerLocationX, playerLocationY-1))){
				playerLocationY--;
			}
		}
		else if(direction.equals("right")){
			if(playerLocationX>0&&(!map.getIsFull(playerLocationX-1, playerLocationY))){
				playerLocationX--;
			}
		}

		System.out.println("Reverse");
		playSound("audio/Step.wav");
		repaint();

	}
	public void Reload(){
		if(currentAction.equals("")){
			currentAction="Reload";
		}


	}

}
