package br.com.applicationfinancialmanagement.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("applicationfinancialmanagement")
public class ApplicationfinancialmanagementApiProperty {
	
	//private String originPermitida = "https://ub9control-ui.herokuapp.com";
	private String originPermitida = "http://localhost:4200";
	
	private final Seguranca seguranca = new Seguranca();

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}

	public static class Seguranca {

		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	}

}
