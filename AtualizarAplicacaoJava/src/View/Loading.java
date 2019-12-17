package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import javax.swing.JLabel;

public class Loading extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Loading() {
		
		setBounds(100, 100, 361, 191);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProgressBar loading_bar = new JProgressBar();
		loading_bar.setIndeterminate(true);
		loading_bar.setBounds(30, 97, 294, 25);
		contentPane.add(loading_bar);
		
		JLabel lbl_messagem_carregamento = new JLabel("Por favor aguarde, atualizando aplica\u00E7\u00E3o...");
		lbl_messagem_carregamento.setBounds(69, 61, 254, 25);
		contentPane.add(lbl_messagem_carregamento);
	}
}
