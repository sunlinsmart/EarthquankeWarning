package practice;
import processing.core.PApplet;
import processing.core.PImage;

public class ProcessingDraw extends PApplet{
	
	private PImage backgroundImg;
	
	public void setup(){
		
		size(200,200);
		backgroundImg=loadImage("LaJollaBeach.jpg");
		
	}
	
	//draw
	public void draw(){
		image(backgroundImg,0,0);
		backgroundImg.resize(0,height);
		fill(255,209,0);

		ellipse(width/4,height/5,width/5,height/5);//draw a circle
	}
	

}
