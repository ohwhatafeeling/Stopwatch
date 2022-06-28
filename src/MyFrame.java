import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class MyFrame extends JFrame implements ActionListener {
	JLabel time;
	JButton startStop;
	JButton reset;
	int elapsedTime = 0;
	int milliseconds = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	boolean started = false;
	String hoursString = String.format("%02d", hours);
	String minutesString = String.format("%02d", minutes);
	String secondsString = String.format("%02d", seconds);
	String millisecondsString = String.format("%03d", milliseconds);
	Timer timer = new Timer(1, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			elapsedTime ++;
			hours = (elapsedTime / 3600000);
			minutes = (elapsedTime / 60000) % 60;
			seconds = (elapsedTime / 1000) % 60;
			milliseconds = (elapsedTime % 1000);
			hoursString = String.format("%02d", hours);
			minutesString = String.format("%02d", minutes);
			secondsString = String.format("%02d", seconds);
			millisecondsString = String.format("%03d", milliseconds);
			time.setText(hoursString + ":" + minutesString + ":" + secondsString + "." + millisecondsString);
		}
		
	});
	
	MyFrame() {
		time = new JLabel();
		time.setBounds(25, 25, 250, 50);
		time.setBackground(Color.BLACK);
		time.setOpaque(true);
		time.setText(hoursString + ":" + minutesString + ":" + secondsString + "." + millisecondsString);
		time.setFont(new Font(null, Font.BOLD, 30));
		time.setForeground(Color.GREEN);
		time.setHorizontalAlignment(JLabel.CENTER);

		reset = new JButton();
		reset.setBounds(40, 90, 100, 50);
		reset.setText("Reset");
		reset.addActionListener(this);
		
		startStop = new JButton();
		startStop.setBounds(160, 90, 100, 50);
		startStop.setText("Start");
		startStop.addActionListener(this);
	
		this.setTitle("Mick's Stopwatch");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(0x90fbfc));
		
		this.add(time);
		this.add(reset);
		this.add(startStop);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startStop) {
			if (started == false) {
				started = true;
				startStop.setText("Stop");
				start();
			} else {
				started = false;
				startStop.setText("Start");
				stop();
			}
		}
		if (e.getSource() == reset) {
			started = false;
			startStop.setText("Start");
			stop();
			reset();
		}
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void reset() {
		elapsedTime = 0;
		hours = 0;
		minutes = 0;
		seconds = 0;
		milliseconds = 0;
		hoursString = String.format("%02d", hours);
		minutesString = String.format("%02d", minutes);
		secondsString = String.format("%02d", seconds);
		millisecondsString = String.format("%03d", milliseconds);
		time.setText(hoursString + ":" + minutesString + ":" + secondsString + "." + millisecondsString);
	}
	
}
