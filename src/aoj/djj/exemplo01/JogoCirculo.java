package aoj.djj.exemplo01;

import java.net.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import aoj.djj.core.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.io.IOException;
import java.io.InterruptedIOException;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;

// Esta é a classe que representa nosso jogo. Ela é derivada de "Game", que
// possui o motor do jogo e chama os métodos abaixo quando necessário.
public class JogoCirculo extends Game implements KeyListener {
	private BufferedImage imagemTemer;
	private BufferedImage imagemMoney;
	private BufferedImage imagemBg;
	private BufferedImage imagemJapa;
	HashMap<Integer, Boolean> keyPool;
	private Point speedTemer;
	private double speedMoney = 5;
	private Point temer;
	private Point money;
	private Point japa;

	int contador = 0;
	
	int ponto;
	// Variáveis necessárias para nosso jogo.
	// Elas armazenam a posição do círculo (x,y) e a velocidade que ela anda.
	int x;
	int y;
	int sx;
	int sy;

	public JogoCirculo() {
		getMainWindow().addKeyListener(this);
		keyPool = new HashMap<Integer, Boolean>();
		speedTemer = new Point(10, 10);
		temer = new Point(10, 100);
		money = new Point(50, 50);
		japa = new Point(200, 200);
		ponto = 0;
	}

	public void onLoad() {
		// Este método é chamado quando o jogo é iniciado.
		// Aqui damos os valores iniciais para as variáveis.
		try {
			
			URL img = getClass().getResource("bg.png");
			if (img == null) {
				throw new RuntimeException("A imagem n foi detectada");
			} else {
				imagemBg = ImageIO.read(img);
			}
			 img = getClass().getResource("temer.jpeg");
			if (img == null) {
				throw new RuntimeException("A imagem n foi detectada");
			} else {
				imagemTemer = ImageIO.read(img);
			}
			 img = getClass().getResource("money.png");
			if (img == null) {
				throw new RuntimeException("A imagem n foi detectada");
			} else {
				imagemMoney = ImageIO.read(img);
			}img = getClass().getResource("japa.jpg");
			if (img == null) {
				throw new RuntimeException("A imagem n foi detectada");
			} else {
				imagemJapa = ImageIO.read(img);
			}
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public void onUnload() {
		// Este método é chamado quando o jogo termina.
		// Não é preciso fazer nada para este jogo.
	}

	public void onUpdate() {
		// Este método é chamado cada vez que a lógica do jogo precisa ser
		// atualizada. Aqui mudamos os valores das variáveis para
		// fazer a bola se mover na tela, rebatendo nas bordas.
		if(temer.x >= 700 || temer.x <=0 || temer.y >=800 || temer.y <=0 ){
			terminate(); 
		}
	
		
		if (keyPool.get(KeyEvent.VK_RIGHT) != null) {
			temer.x += speedTemer.x;
		}if(keyPool.get(KeyEvent.VK_UP)!= null){
			temer.y -= speedTemer.y;
		}if(keyPool.get(KeyEvent.VK_DOWN)!= null ){
			temer.y += speedTemer.y;
		}if(keyPool.get(KeyEvent.VK_LEFT)!= null){
			temer.x -= speedTemer.x;
		}if(intersectsMoney()){
			ponto +=10;
			money.x= random(0,this.getWidth()-55);
			money.y= random(0,this.getHeight()-55);
			
		}if(intersectsJapa()){
			terminate();
		}
		if(contador == 1000){
			japa.x= random(0,this.getWidth()-85);
			japa.y= random(0,this.getHeight()-85);
			contador=0;
		}
		// Toda vez que a posição chega em um limite da tela,
		// a velocidade naquela direção é invertida.

	}

	

	private boolean intersectsJapa() {
		Rectangle r1 = new Rectangle(temer.x-50,temer.y-50,50,50);
		Rectangle r2 = new Rectangle(japa.x-50,japa.y-50,50,50);
		return r1.intersects(r2);
		
	}

	private int random(int max, int min) {
		int range = (max - min)+1;
		return (int) (Math.random()*range)+min;
	}

	public void onRender(Graphics2D g) {
		// Este método é chamado cada vez que é preciso atualizar a imagem
		// do jogo na tela. É aqui que desenyhamos abola na posição
		// armazenada nas variáveis.
		g.drawImage(imagemBg, 0, 0, 800, 600, null);
		g.drawImage(imagemTemer, temer.x, temer.y, 100, 100, null);
		g.drawImage(imagemMoney, money.x, money.y, 50, 50, null);
		g.drawImage(imagemJapa, japa.x, japa.y, 100, 100, null);
		g.drawString("pontos:", 50, 40);
		g.drawString(contador+"", 600, 50);
		g.drawString(ponto +"", 50, 70);
		contador++;

	}

	// Os métodos acima são chamados automaticamente pelo "motor" do jogo que
	// é herdado da classe Game. Abra o arquivo game.java e veja lá o que ocorre
	// quando o programa roda.

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		keyPool.put(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		keyPool.remove(e.getKeyCode());
	}
	
	public boolean intersectsMoney(){
		Rectangle r1 = new Rectangle(temer.x-50,temer.y-50,50,50);
		Rectangle r2 = new Rectangle(money.x-50,money.y-50,50,50);
		return r1.intersects(r2);
	}
}
