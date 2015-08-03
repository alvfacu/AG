package viajador;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;

import viajador.ProblemaPrincipal;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.border.EtchedBorder;

public class MapaRuta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable datos;
	private JScrollPane tabla;
	String data[][]={};
	String columna[]={"Id.","Ciudades"};
	private final JComboBox comboBox;
	DefaultTableModel md;
	public Image imagenFondo;
	public URL fondo;
	private JLabel lblDistancia;
	private JButton btnMostrarRuta;
	private String path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapaRuta frame = new MapaRuta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MapaRuta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 633);
		setTitle("El Problema del Viajante");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JLabel lblElijaCiudadDe = new JLabel("Elija ciudad de inicio");
		lblElijaCiudadDe.setBounds(10, 20, 156, 14);
		
		String[] ciudades =
			{ "Buenos Aires","San Salvador de Jujuy","Salta","San Miguel de Tucuman","Santiago del Estero","Formosa","Resistencia","Santa Fe",
			  "Corrientes","Posadas","Parana","Cordoba","La Rioja","San Juan","San Luis","Catamarca","Mendonza","Santa Rosa","Neuquen","Viedma",
			  "Rawson","Rio Gallegos","Ushuaia" 
			};
		
		comboBox = new JComboBox(ciudades);
		comboBox.setBounds(134, 17, 175, 20);
				
		JButton btnComenzarViaje = new JButton("Comenzar viaje");
		btnComenzarViaje.setBounds(85, 474, 154, 23);
		btnComenzarViaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ProblemaPrincipal().iniciarViaje(comboBox.getSelectedIndex());
				limpiarTabla();
				mostrarMapa(comboBox.getSelectedIndex());
				llenarTabla(ProblemaPrincipal.recorrido);
				lblDistancia.setText(String.valueOf(ProblemaPrincipal.trayectoria)+" Km.");
				btnMostrarRuta.setEnabled(true);
				
			}

			private void mostrarMapa(int ind) {
				switch(ind)
				{
					case 0:
					{
						path = "/imagenes/BsAs.png";  
						break;
					}
					case 1:
					{
						path = "/imagenes/SanSalvador.png";  
						break;
					}
					case 2:
					{
						path = "/imagenes/Salta.png";  
						break;
					}
					case 3:
					{
						path = "/imagenes/SanMiguel.png";  
						break;
					}
					case 4:
					{
						path = "/imagenes/Santiago.png";  
						break;
					}
					case 5:
					{
						path = "/imagenes/Formosa.png";  
						break;
					}
					case 6:
					{
						path = "/imagenes/Resistencia.png";  
						break;
					}
					case 7:
					{
						path = "/imagenes/StaFe.png";  
						break;
					}
					case 8:
					{
						path = "/imagenes/Corrientes.png";  
						break;
					}
					case 9:
					{
						path = "/imagenes/Posadas.png";  
						break;
					}
					case 10:
					{
						path = "/imagenes/Parana.png";  
						break;
					}
					case 11:
					{
						path = "/imagenes/Cordoba.png";  
						break;
					}
					case 12:
					{
						path = "/imagenes/Rioja.png";  
						break;
					}
					case 13:
					{
						path = "/imagenes/SanJuan.png";  
						break;
					}
					case 14:
					{
						path = "/imagenes/SanLuis.png";  
						break;
					}
					case 15:
					{
						path = "/imagenes/Catamarca.png";  
						break;
					}
					case 16:
					{
						path = "/imagenes/Mendoza.png";  
						break;
					}
					case 17:
					{
						path = "/imagenes/SantaRosa.png";  
						break;
					}
					case 18:
					{
						path = "/imagenes/Neuquen.png";  
						break;
					}
					case 19:
					{
						path = "/imagenes/Viedma.png";  
						break;
					}
					case 20:
					{
						path = "/imagenes/Rawson.png";  
						break;
					}
					case 21:
					{
						path = "/imagenes/RioGallegos.png";  
						break;
					}
					case 22:
					{
						path = "/imagenes/Ushuaia.png";  
						break;
					}
				}
			}

			private void limpiarTabla() {
				md.setRowCount(0);
				
			}

			private void llenarTabla(ArrayList<String> recorrido) {
				for(String r : recorrido){
					for(int i=0;i<23;i++)
					{
						if(r.equalsIgnoreCase(ProblemaPrincipal.ciudades[i][0]))
						{
							String id = String.valueOf((Integer.parseInt(r)+1));
							String[] re = {id,ProblemaPrincipal.ciudades[i][1]};
							md.addRow(re);
						}
					}					
				}				
			}
		});
		
		
		md=new DefaultTableModel(data,columna);
		contentPane.setLayout(null);
		
		tabla = new JScrollPane();
		tabla.setBounds(10, 47, 299, 406);
		
		datos = new JTable();
		tabla.setViewportView(datos);
		datos.setModel(md);
		
		TableColumnModel columnModel = datos.getColumnModel();
		columnModel.getColumn(0).setMaxWidth(25);
		columnModel.getColumn(0).setMinWidth(25);
		
		
		contentPane.add(tabla);
		contentPane.add(lblElijaCiudadDe);
		contentPane.add(comboBox);
		contentPane.add(btnComenzarViaje);
				
		JLabel lblDistanciaTotalRecorrida = new JLabel("Distancia total recorrida:");
		lblDistanciaTotalRecorrida.setForeground(new Color(0, 0, 128));
		lblDistanciaTotalRecorrida.setBounds(15, 513, 180, 14);
		contentPane.add(lblDistanciaTotalRecorrida);
		
		lblDistancia = new JLabel("");
		lblDistancia.setBounds(163, 513, 146, 14);
		contentPane.add(lblDistancia);
		
		btnMostrarRuta = new JButton("Mostrar Ruta");
		btnMostrarRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ruta(path).setVisible(true);
				btnMostrarRuta.setEnabled(false);
			}
		});
		btnMostrarRuta.setEnabled(false);
		btnMostrarRuta.setBounds(85, 545, 154, 23);
		contentPane.add(btnMostrarRuta);
		
	}
}
