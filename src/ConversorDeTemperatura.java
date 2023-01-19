
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConversorDeTemperatura extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7866168793165757734L;
	private JLabel texto1, texto2, texto3, resultado;
	private JTextField valorIng;
	private JButton inicio, cancelar, atras;
	private JComboBox<String> opciones1, opciones2;
	private List<String> temperaturas1, temperaturas2;

	public ConversorDeTemperatura() {
		Color red = new Color(240, 15, 90);
		Color green = new Color(15, 240, 165);
		Color white = new Color(255, 255, 255);
		Color purple = new Color(112, 82, 255);

		Font fuente = new Font("Arial", Font.PLAIN, 18);

		Container fondo = this.getContentPane();

		setLayout(null);
		this.texto1 = new JLabel("Ingrese el valor a convertir");
		this.texto2 = new JLabel("Topo Valor");
		this.texto3 = new JLabel("A Convertir");
		this.valorIng = new JTextField();
		this.resultado = new JLabel("Total: 000");
		this.opciones1 = new JComboBox<String>();
		this.opciones2 = new JComboBox<String>();
		this.inicio = new JButton("Iniciar");
		this.cancelar = new JButton("Cancelar");
		this.atras = new JButton("⇠");

		/* Ubicacion */
		this.atras.setBounds(10, 30, 50, 25);
		this.texto1.setBounds(70, 20, 400, 30);
		this.texto2.setBounds(70, 100, 400, 30);
		this.texto3.setBounds(250, 100, 400, 30);
		this.valorIng.setBounds(70, 50, 150, 30);
		this.opciones1.setBounds(250, 135, 100, 30);
		this.opciones2.setBounds(70, 135, 100, 30);
		this.resultado.setBounds(400, 135, 150, 30);
		this.inicio.setBounds(70, 200, 120, 30);
		this.cancelar.setBounds(250, 200, 120, 30);

		this.temperaturas1 = new ArrayList<>();
		this.temperaturas2 = new ArrayList<>();
		Collections.addAll(this.temperaturas1, "Celsius", "kelvin", "Fahrenheit");
		Collections.addAll(this.temperaturas2, "c", "k", "f");

		this.temperaturas1.forEach(temperatura -> {
			this.opciones1.addItem(temperatura);
			this.opciones2.addItem(temperatura);
		});
		this.inicio.addActionListener(this);
		this.cancelar.addActionListener(this);
		this.atras.addActionListener(this);

		add(this.atras);
		add(this.texto1);
		add(this.texto2);
		add(this.texto3);
		add(this.valorIng);
		add(this.resultado);
		add(this.opciones1);
		add(this.opciones2);
		add(this.inicio);
		add(this.cancelar);

		setTitle("Conversor De Temperaturas");

		/* Colores */
		fondo.setBackground(purple);
		this.texto1.setForeground(white);
		this.texto2.setForeground(white);
		this.texto3.setForeground(white);
		this.resultado.setForeground(white);
		this.cancelar.setForeground(white);
		this.cancelar.setBackground(red);
		this.inicio.setBackground(green);
		this.atras.setBackground(green);

		/* Fuentes */
		this.texto1.setFont(fuente);
		this.texto2.setFont(fuente);
		this.texto3.setFont(fuente);
		this.resultado.setFont(fuente);
		this.inicio.setFont(fuente);
		this.cancelar.setFont(fuente);
		this.atras.setFont(fuente);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.inicio) {
			Utilidades util = new Utilidades();
			String elecion2 = (String) this.opciones1.getSelectedItem();
			String elecion1 = (String) this.opciones2.getSelectedItem();
			String valor = this.valorIng.getText();

			/* Exprecion regular en java */
			Pattern regular = Pattern.compile("^[0-9.]+$");
			Matcher matcher = regular.matcher(valor);
			if (matcher.find()) {
				this.texto1.setText("Valido: " + valor);
				this.texto1.setForeground(new Color(15, 240, 165));

				String v1 = "";
				String v2 = "";
				for (int i = 0; i < this.temperaturas1.size(); i++) {
					if (elecion1 == this.temperaturas1.get(i)) {
						v1 = this.temperaturas2.get(i);
					}
					if (elecion2 == this.temperaturas1.get(i)) {
						v2 = this.temperaturas2.get(i);
					}
				}

				String clave = v1 + v2;
				Double numero = Double.valueOf(valor);

				this.resultado.setText("Total: " + util.formularTemp(clave, numero));

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

	public static void main(String[] args) {
		ConversorDeTemperatura inicioT = new ConversorDeTemperatura();
		inicioT.iniciar();
	}

}
