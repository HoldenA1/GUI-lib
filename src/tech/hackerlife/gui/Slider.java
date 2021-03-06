package tech.hackerlife.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Slider extends GUIElement {
	private int value = 0;
	private int sliderPosition, barWidth;

	public Slider(String label, int x, int y) {
		super(label, x, y, (label.length()+2)*CHAR_SIZE, DEFAULT_ELEMENT_SIZE);
		sliderPosition = x;
		barWidth = (int)(height/3);
	}
	
	public Slider(String label, int x, int y, int width, int height) {
		super(label, x, y, width, height);
		sliderPosition = x;
		barWidth = (int)(height/3);
	}
	
	public Slider withColor(Color color) {
		super.withColor(color);
		return this;
	}
	
	public Slider lightText() {
		super.lightText();
		return this;
	}

	public void update(Graphics g, Point mousePos, Mouse mouse) {
		if (isVisible) {
			hover = this.contains(mousePos);
			isHeld = hover && mouse.mouseButtonPressed();
			
			if (isHeld) {
				sliderPosition = mousePos.x - (barWidth / 2);
				value = (int) ((((double)(mousePos.x) - x) / width) * 100);
			}
			
			drawColor = chooseColor(isPressed, hover);
			
			// Draws Bar
			g.setColor(drawColor);
			g.fillRect(x, y + barWidth, width, barWidth);
			g.setColor(textColor);
			g.drawRect(x, y + barWidth, width, barWidth);
			// Draws Slider
			g.fillRect(sliderPosition, y, barWidth, height);
			// Draws text
			g.setFont(normalFont);
			g.drawString(label, width/2-(15*label.length())/2+x, height+y);
		}
	}
	
	/**
	 * @return returns slider value from 0 to 99
	 */
	public int getValue() {
		return value;
	}

}
