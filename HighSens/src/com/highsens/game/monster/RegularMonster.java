package com.highsens.game.monster;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.highsens.game.AudioPlayer;
import com.highsens.game.GameData;
import com.highsens.game.GameFigure;
import com.highsens.game.IStrategy;
import com.highsens.game.Missile;
import com.highsens.game.Monster;

public class RegularMonster extends AbstractMonster implements Monster, GameFigure, IStrategy {

	Image fastMonsterImage;
	Point2D.Float target;
	Missile missile;
	GameData gd;
	private int state;
	public float x, y;
	int buffCount = 0;

	public RegularMonster(float x, float y, GameData gd) {
		this.gd = gd;
		this.state = PATH_0;
		this.x = x;
		this.y = y;
		this.maxHealth = 100;
		this.health = maxHealth;
		this.speed = 5;
		String imagePath = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		fastMonsterImage = getImage(imagePath + separator + "images" + separator + "RegularMonster.png");
		AudioPlayer.loadClip("pop", "sounds/pop.wav");
	}

	@Override
	public Image getImage(String fileName) {
		Image image = null;
		try {
			image = ImageIO.read(new File(fileName));
		} catch (Exception ioe) {
			System.out.println("Error: Cannot open image:" + fileName);
			JOptionPane.showMessageDialog(null, "Error: Cannot open image:" + fileName);
		}
		return image;
	}

	@Override
	public void render(Graphics g) {
		drawHealthBar(g, x, y);

		g.drawImage(fastMonsterImage, (int) x, (int) y, null);
	}

	@Override
	public void update() {
		updateState();
		switch (state) {
		case PATH_0:
			moveRight();
			break;
		case PATH_1:
			moveRight();
			break;
		case PATH_2:
			moveRight();
			break;
		case PATH_3:
			moveRight();
			break;
		case PATH_4:
			moveRight();
			break;
		case PATH_5:
			moveRight();
			break;
		case PATH_6:
			moveRight();
			break;
		case STATE_DONE:
			AudioPlayer.play("pop", false);
			gd.moneyManager("regularKill", gd.getMoney());
			gd.monsterManager("regularKill");
			break;
		case LIFE_LOST:
			updateLives();
			state = STATE_DONE;
			gd.monsterManager("regularKill");
			break;
		}
	}

	@Override
	public void updateState() {
		switch (state) {
		case PATH_0:
			if (x >= 69)
				state = PATH_1;
			break;
		case PATH_1:
			if (x >= 87)
				state = PATH_2;
			break;
		case PATH_2:
			if (x >= 200)
				state = PATH_3;
			break;
		case PATH_3:
			if (x >= 240)
				state = PATH_4;
			break;
		case PATH_4:
			if (x >= 350)
				state = PATH_5;
			break;
		case PATH_5:
			if (x >= 560)
				state = PATH_6;
			break;
			
		case PATH_6:
			if (x >= 1250)
				state = LIFE_LOST;
		}
	}

	public void moveRight() {
		x += speed;
	}

	public void moveLeft() {
		x -= speed;
	}

	public void moveUp() {
		y -= speed;
	}

	public void moveDown() {
		y += speed;
	}

	@Override
	public int getState() {
		return state;

	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	@Override
	public void setState(int state) {
		this.state = state;
	}

	@Override
	public double getX() {
		return (float) x;

	}

	@Override
	public double getY() {
		return (float) y;

	}

	public boolean contains(float x, float y) {
		if (x < this.x)
			return false;
		if (x > this.x + 47)
			return false;
		if (y < this.y)
			return false;
		if (y > this.y + 37)
			return false;

		return true;
	}

	@Override
	public void updateHealth() {
		health -= 3;
		if (health <= 0) {
			state = STATE_DONE;
		} else if ((health >= (health / 4) && (health <= (health / 4) + 3))) {
			if (buffCount < 1) {
				getAngry();
			}
			buffCount++;
		}
	}

	public void getAngry() {
		health += 75;
		speed += 2;
	}

	public int getScore() {
		return gd.score += 5;
	}

	@Override
	public boolean collision(GameFigure m) {
		return false;

	}

	@Override
	public void updateLives() {
		gd.minusLives();
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public float getXofMissileShoot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getYofMissileShoot() {
		// TODO Auto-generated method stubs
		return 0;
	}

	
	public void setLevel(int level) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIsAngry(boolean t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsAngry() {
		// TODO Auto-generated method stub
		return false;
	}
}
