package viajador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;

public class Ruta extends JFrame {

	private JPanel contentPane;
	private JLabel lblMapa;
	private ImageIcon imagen;

	/**
	 * Create the frame.
	 */
	public Ruta(String path) {
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 368, 586);
		setLocationRelativeTo(null);
		setTitle("Ruta");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		URL url = this.getClass().getResource(path);  
		imagen = new ImageIcon(url);
		lblMapa = new JLabel(imagen);
		lblMapa.setBounds(10, 11, 330, 528);
		contentPane.add(lblMapa);
		setVisible(true);
	}

}
