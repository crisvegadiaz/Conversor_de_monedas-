
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class ConversorDeMoneda extends JFrame implements ActionListener {
	private static final long serialVersionUID = -7081096217572596382L;
	private JLabel texto1, texto2, resutado;
	private JTextField valorIng;
	private JComboBox<String> opciones1;
	private JButton inicio, cancelar, atras;
	private List<String> monedad;
	private List<String> valores;

	public ConversorDeMoneda() {
		Color red = new Color(240, 15, 90);
		Color green = new Color(15, 240, 165);
		Color white = new Color(255, 255, 255);
		Color purple = new Color(112, 82, 255);

		Font fuente = new Font("Arial", Font.PLAIN, 18);

		Container fondo = this.getContentPane();

		setLayout(null);
		this.texto1 = new JLabel("Ingrese la cantidad a convertir");
		this.texto2 = new JLabel("Valor a cambiar");
		this.valorIng = new JTextField();
		this.resutado = new JLabel("Total: 000");
		this.opciones1 = new JComboBox<String>();
		this.inicio = new JButton("Iniciar");
		this.cancelar = new JButton("Cancelar");
		this.atras = new JButton("⇠");

		/* Ubicacion */
		this.atras.setBounds(10, 30, 50, 25);
		this.texto1.setBounds(70, 20, 400, 30);
		this.texto2.setBounds(70, 100, 400, 30);
		this.valorIng.setBounds(70, 50, 150, 30);
		this.opciones1.setBounds(70, 135, 320, 30);
		this.resutado.setBounds(400, 135, 150, 30);
		this.inicio.setBounds(70, 200, 120, 30);
		this.cancelar.setBounds(270, 200, 120, 30);

		this.monedad = new ArrayList<>();
		this.valores = new ArrayList<>();

		Collections.addAll(this.monedad, "Pesos Argentinos a Dolares", "Pesos Argentinos a Euros",
				"Pesos Argentinos a Pesos Mexicanos", "Euros a Dolares", "Euros a Pesos Argentinos",
				"Euros a Pesos Mexicanos", "Pesos Mexicanos a Dolares", "Pesos Mexicanos a Pesos Argentinos",
				"Pesos Mexicanos a Euros", "Dolares a Euros", "Dolares a Pesos Argentinos",
				"Dolares a Pesos Mexicanos");
		Collections.addAll(this.valores, "pAd", "pAe", "pApM", "ed", "epA", "epM", "pMd", "pMpA", "pMe", "de", "dpA",
				"dpM");

		this.monedad.forEach(moneda -> {
			this.opciones1.addItem(moneda);
		});

		this.inicio.addActionListener(this);
		this.cancelar.addActionListener(this);
		this.atras.addActionListener(this);

		add(this.atras);
		add(this.texto1);
		add(this.texto2);
		add(this.valorIng);
		add(this.resutado);
		add(this.opciones1);
		add(this.inicio);
		add(this.cancelar);

		setTitle("Conversor De Moneda");

		/* Colores */
		fondo.setBackground(purple);
		this.texto1.setForeground(white);
		this.texto2.setForeground(white);
		this.resutado.setForeground(white);
		this.cancelar.setForeground(white);
		this.cancelar.setBackground(red);
		this.inicio.setBackground(green);
		this.atras.setBackground(green);

		/* Fuentes */
		this.texto1.setFont(fuente);
		this.texto2.setFont(fuente);
		this.resutado.setFont(fuente);
		this.inicio.setFont(fuente);
		this.cancelar.setFont(fuente);
		this.atras.setFont(fuente);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.inicio) {
			Utilidades util = new Utilidades();
			String elecion = (String) this.opciones1.getSelectedItem();
			String valor = this.valorIng.getText();

			/* Exprecion regular en java */
			Pattern regular = Pattern.compile("^[0-9.]+$");
			Matcher matcher = regular.matcher(valor);
			if (matcher.find()) {
				this.texto1.setText("Valido: " + valor);
				this.texto1.setForeground(new Color(15, 240, 165));

				String clave = "";
				for (int i = 0; i < this.monedad.size(); i++) {
					if (elecion == this.monedad.get(i)) {
						clave = this.valores.get(i);
					}
				}

				Double numero = Double.valueOf(valor);
				this.resutado.setText("Total: " + util.formularMoneda(clave, numero));

			} else {
				this.texto1.setText("Error no es valido: " + valor);
				this.valorIng.setText("");
				this.texto1.setForeground(new Color(240, 15, 90));
			}

		}
		if (e.getSource() == this.cancelar) {
			System.exit(0);
		}
		if (e.getSource() == this.atras) {
			this.setVisible(false);
			VentanaInicio inicio = new VentanaInicio();
			inicio.iniciar();
		}
	}

	public void iniciar() {
		this.setBounds(10, 20, 600, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
