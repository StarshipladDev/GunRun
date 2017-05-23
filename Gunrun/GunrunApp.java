package Gunrun;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class GunrunApp extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame gunrunApp= new JFrame();
		GunrunPanel gunrunPanel= new GunrunPanel();
		gunrunApp.setBackground(Color.white);
		gunrunApp.setPreferredSize(new Dimension(720,1000));
		gunrunApp.add(gunrunPanel);
		gunrunApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gunrunPanel.requestFocus(true);
		gunrunApp.pack();
		gunrunApp.setVisible(true);
		keyLoader(gunrunPanel);
		

	}
	private static void keyLoader(GunrunPanel gunrunPanel){
		gunrunPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"),"Display");
		gunrunPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_W)),"Forward");
		gunrunPanel.getActionMap().put("Forward",new Forward(gunrunPanel));
		gunrunPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_S)),"Reverse");
		gunrunPanel.getActionMap().put("Reverse",new Reverse(gunrunPanel));
		gunrunPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_E)),"Shoot");
		gunrunPanel.getActionMap().put("Shoot",new Shoot(gunrunPanel));
		gunrunPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_R)),"Reload");
		gunrunPanel.getActionMap().put("Reload",new Reload(gunrunPanel));
		gunrunPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_D)),"Right");
		gunrunPanel.getActionMap().put("Right",new Rotate(gunrunPanel,"right"));
		gunrunPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.getKeyText(KeyEvent.VK_A)),"Left");
		gunrunPanel.getActionMap().put("Left",new Rotate(gunrunPanel,"left"));
	}

	private static class Forward extends AbstractAction {
		GunrunPanel gunner;
		Forward(GunrunPanel gunner) {
			this.gunner=gunner;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			gunner.Forward();
			// Same as the move method in the question code.
			// Player can be detected by e.getSource() instead and call its own move method.
		}

	}
	private static class Reverse extends AbstractAction {
		GunrunPanel gunner;
		Reverse(GunrunPanel gunner) {
			this.gunner=gunner;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			gunner.Reverse();
			// Same as the move method in the question code.
			// Player can be detected by e.getSource() instead and call its own move method.
		}

	}
	private static class Shoot extends AbstractAction {
		GunrunPanel gunner;
		Shoot(GunrunPanel gunner) {
			this.gunner=gunner;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			gunner.Shoot();

			// Same as the move method in the question code.
			// Player can be detected by e.getSource() instead and call its own move method.
		}

	}
	private static class Reload extends AbstractAction {
		GunrunPanel gunner;
		Reload(GunrunPanel gunner) {
			this.gunner=gunner;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			gunner.Reload();

			// Same as the move method in the question code.
			// Player can be detected by e.getSource() instead and call its own move method.
		}

	}
	private static class Rotate extends AbstractAction {
		GunrunPanel gunner;
		String direction;
		Rotate(GunrunPanel gunner,String direction) {
			this.gunner=gunner;
			this.direction=direction;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			gunner.Rotate(direction);

			// Same as the move method in the question code.
			// Player can be detected by e.getSource() instead and call its own move method.
		}

	}
}
