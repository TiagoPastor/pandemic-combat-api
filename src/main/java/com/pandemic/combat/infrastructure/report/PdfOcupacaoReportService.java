package com.pandemic.combat.infrastructure.report;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.pandemic.combat.api.model.dto.HospitalDTO;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PdfOcupacaoReportService {

	
	
	public byte[] emitirRelatorioEstatiscaOcupacao( List<HospitalDTO> hospitaisDTO ) {
		try {
			
			var inputStream = this.getClass().getResourceAsStream(
					"/relatorios/relatorio_estatistica_ocupacao.jasper");
			
			var parametros = new HashMap<String, Object>();
			parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			var dataSource = new JRBeanCollectionDataSource(hospitaisDTO);
			
			var jasperPrint = JasperFillManager.fillReport(inputStream, null, dataSource);
		
			return JasperExportManager.exportReportToPdf(jasperPrint);
			
		}catch ( Exception e) {
			throw new ReportException( "Não foi possível emitir o relatório", e );
		}
	}

}
