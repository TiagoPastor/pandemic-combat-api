package com.pandemic.combat.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandemic.combat.api.model.dto.HospitalDTO;
import com.pandemic.combat.domain.service.ReportService;
import com.pandemic.combat.infrastructure.report.PdfOcupacaoReportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( "/api/v1/estatisticas" )
@Api( value = "Estatisticas", tags = "Estatisticas" )
public class EstatisticasController {
	
	
	@Autowired
	private PdfOcupacaoReportService pdfService;
	
	@Autowired
	private ReportService reporteService;
	
	
	
	@ApiOperation( value = "Relatório dos hospitais com super lotação" )
	@GetMapping(path = "/ocupacao/superlotacao", produces =  MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> relatorioSuperLotacao(){
		
       return gerarRelatorio(reporteService.hospitaisSuperLotado());
		
	}

	
	@ApiOperation( value = "Relatório dos hospitais com baixa lotação" )
	@GetMapping(path = "/ocupacao/baixalotacao", produces =  MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> relatorioBaixaLotacao(){
		
       return gerarRelatorio(reporteService.hospitaisBaixaLotado());
		
	}
	
	
	
	private ResponseEntity<byte[]> gerarRelatorio(List<HospitalDTO> hospitaisDTO) {
		byte[] bytesPdf = pdfService.emitirRelatorioEstatiscaOcupacao( hospitaisDTO );
		
		var headers = new HttpHeaders();
		headers.add( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=estatistica-lotacao.pdf" );
		
		return ResponseEntity.ok()
				.contentType( MediaType.APPLICATION_PDF )
				.headers( headers )
				.body( bytesPdf );
	}
	

}
