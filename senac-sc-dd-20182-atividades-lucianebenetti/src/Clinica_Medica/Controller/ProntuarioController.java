package Clinica_Medica.Controller;

import java.util.ArrayList;
import java.util.List;

import Clinica_Medica.BO.ProntuarioBO;
import Clinica_Medica.VO.ProntuarioVO;

public class ProntuarioController {
	
	ProntuarioBO bo = new ProntuarioBO();

	public List<ProntuarioVO> listarTodosProntuarios() {
		
		return bo.listarProntuarios();
	}

	public String salvar(ProntuarioVO prontuario) {
		
		String validacao = validarProntuario(prontuario);

		if (validacao == "") {

			if (bo.inserir(prontuario)) {
				validacao = "Prontuario salvo com sucesso!";
			} else {
				validacao = "Erro ao salvar prontuario!";
			}
		}
		return validacao;
	}

	private String validarProntuario(ProntuarioVO prontuario) {

		String validacao = "";
		if (prontuario.getMedicamento()== null) {
			validacao = "A �rea de medicamentos est� nula!";
		} else {
			if (prontuario.getMedicamento().trim().equals("") || prontuario.getRegistro().trim().equals("")) {
				validacao += " - Medicamentos e Registro s�o obrigat�rios. \n";
			}

		}
		return validacao;
	}

}
