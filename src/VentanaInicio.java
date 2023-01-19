
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

public class VentanaInicio extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1684385619419304658L;
	private JLabel texto;
	private JComboBox<String> opciones;
	private JButton inicio, cancelar;

	public VentanaInicio() {
		Color red = new Color(240, 15, 90);
		Color green = new Color(15, 240, 165);
		Color white = new Color(255, 255, 255);
		Color purple = new Color(112, 82, 255);

		Font fuente = new Font("Arial", Font.PLAIN, 18);

		Container fondo = this.getContentPane();

		setLayout(null);
		this.texto = new JLabel("Selecione una opcion de conversión");
		this.opciones = new JComboBox<String>();
		this.inicio = new JButton("Inicio");
		this.cancelar = new JButton("Cancelar");

		/* Ubicacion */
		this.texto.setBounds(150, 20, 400, 30);
		this.opciones.setBounds(150, 50, 300, 30);
		this.inicio.setBounds(150, 200, 120, 30);
		this.cancelar.setBounds(320, 200, 120, 30);

		this.opciones.addItem("Conversor de Moneda");
		this.opciones.addItem("Conversor de Temperatura");

		/* Fuentes */
		this.texto.setFont(fuente);
		this.cancelar.setFont(fuente);
		this.inicio.setFont(fuente);

		this.inicio.addActionListener(this);
		this.cancelar.addActionListener(this);

		add(this.texto);
		add(this.opciones);
		add(this.inicio);
		add(this.cancelar);

		setTitle("Menu");

		/* Colores */
		fondo.setBackground(purple);
		this.texto.setForeground(white);
		this.cancelar.setBackground(red);
		this.cancelar.setForeground(white);
		this.inicio.setBackground(green);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.inicio) {
			String elecion = (String) this.opciones.getSelectedItem();
			System.out.println(elecion);
			
			switch (elecion) {
			case "Conversor de Moneda":
				ConversorDeMoneda conversorDeMoneda = new ConversorDeMoneda();
				conversorDeMoneda.iniciar();
				this.setVisible(false);
				break;
			case "Conversor de Temperatura":
				ConversorDeTemperatura conversorDeTemperatura = new ConversorDeTemperatura();
				conversorDeTemperatura.iniciar();
				this.setVisible(false);
				break;
			}
		}
		if (e.getSource() == this.cancelar) {
			System.exit(0);
		}
	}

	public void iniciar() {
		this.setBounds(10, 20, 600, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
