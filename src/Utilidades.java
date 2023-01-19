import java.util.HashMap;
import java.util.Map;

public class Utilidades {

	public Double formularTemp(String clave, Double valor) {
		Map<String, Double> mapa = new HashMap<>() {
			private static final long serialVersionUID = 8459508467819143245L;
			{
				put("kf", 1.8 * (valor - 273) + 32);
				put("kc", valor - 273);
				put("fk", 0.55 * (valor - 32) + 273);
				put("fc", 0.55 * (valor - 32));
				put("cf", 1.8 * valor + 32);
				put("ck", valor + 273);
			}
		};

		if (!mapa.containsKey(clave)) {
			return valor;
		}
		return mapa.get(clave);
	}

	public Double formularMoneda(String clave, Double valor) {
		Map<String, Double> mapa = new HashMap<>() {
			private static final long serialVersionUID = 8459508467819143245L;
			{
				put("pAd", valor * 0.0055);
				put("pAe", valor * 0.0051);
				put("pApM", valor * 0.10);

				put("ed", 1.08 * valor);
				put("epA", 197.66 * valor);
				put("epM", 20.55 * valor);

				put("pMd", valor * 0.049);
				put("pMpA", 9.62 * valor);
				put("pMe", valor * 0.0487);

				put("de", valor * 0.92);
				put("dpA", 182.82 * valor);
				put("dpM", 19.01 * valor);

			}
		};

		return mapa.get(clave);
	}
}
