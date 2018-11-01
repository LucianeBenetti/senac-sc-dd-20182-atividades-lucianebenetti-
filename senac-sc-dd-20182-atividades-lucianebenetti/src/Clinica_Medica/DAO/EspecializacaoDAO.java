package Clinica_Medica.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Clinica_Medica.VO.ConvenioVO;
import Clinica_Medica.VO.EspecialidadeVO;
import Clinica_Medica.VO.EspecializacaoVO;
import Clinica_Medica.VO.MedicoVO;

public class EspecializacaoDAO {

	private static ArrayList<EspecializacaoVO> listaEspecializacao = new ArrayList<EspecializacaoVO>();

	public int inserirEspecializacao(EspecializacaoVO especializacao) {

		int novoId = 0;

		String sql = "INSERT INTO especializacao (codigoEspecialidade, codigoMedico, anoEspecializacao)"
				+ " VALUES (?,?,?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);

		try {

			prepStmt.setInt(1, especializacao.getEspecialidadeVO().getCodigoEspecialidade());
			prepStmt.setInt(2, especializacao.getMedicoVO().getCodigoMedico());
			prepStmt.setString(3, especializacao.getAnoEspecializacao());

			prepStmt.executeUpdate();

			ResultSet generatedKeys = prepStmt.getGeneratedKeys();

			if (generatedKeys.next()) {
				novoId = generatedKeys.getInt(1);

			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Cadastro do Especializac�o! Causa: \n: " + e.getMessage());

		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return novoId;

	}

	
	public boolean delete(int codigoEspecializacao) {
		boolean sucessoDelete = false;

		String query = "DELETE from especializacao where codigoEspecializacao = ? ";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {
			prepStmt.setInt(1, codigoEspecializacao);
			int codigoRetorno = prepStmt.executeUpdate();
			if (codigoRetorno == 1) {
				sucessoDelete = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar Query de Exclus�o do Especializac�o! Causa: \n: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return sucessoDelete;
	}

	public EspecializacaoVO atualizarEspecializacao (EspecializacaoVO especializacao, int codigoEspecializacao) {

		String query = "UPDATE especializacao SET codigoEspecialidade=?, codigoMedico=?, anoEspecializacao=? "
				+ " where codigoEspecializacao = ?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);

		try {

			prepStmt.setInt(1, especializacao.getEspecialidadeVO().getCodigoEspecialidade());
			prepStmt.setInt(2, especializacao.getMedicoVO().getCodigoMedico());
			prepStmt.setString(3, especializacao.getAnoEspecializacao());
			prepStmt.setInt(4, especializacao.getCodigoEspecializacao());

			int codigoRetorno = prepStmt.executeUpdate();

			if (codigoRetorno == 1) {

			}
		} catch (SQLException ex) {
			System.out.println("Erro ao executar Query de Atualiza��o do Especializac�o!Causa: \n: " + ex.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return especializacao;
	}

	public ArrayList<EspecializacaoVO> listarEspecializacoesDoMedicoPorEspecialidade(int codigoEspecialidade, int codigoMedico ) {

		String query = " SELECT codigoEspecializacao, e.codigoEspecialidade, med.codigoMedico, anoEspecializacao from especializacao esp" 
				+ " inner join especialidade e on (esp.codigoEspecialidade = e.codigoEspecialidade) "
				+ " inner join medico med on (esp.codigoMedico = med.codigoMedico) "
				+ " where e.codigoEspecialidade = ? "
				+ " and med.codigoMedico = ? "
				+ " order by esp.codigoEspecializacao ";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		try {
			prepStmt.setInt(1, codigoEspecialidade);
			prepStmt.setInt(2, codigoMedico);
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				EspecializacaoVO especializacao = new EspecializacaoVO();
				especializacao.setCodigoEspecializacao(result.getInt(1));
				especializacao.getEspecialidadeVO().setCodigoEspecialidade(result.getInt(2));
				especializacao.getMedicoVO().setCodigoMedico(result.getInt(3));
				especializacao.setAnoEspecializacao(result.getString(4));

				listaEspecializacao.add(especializacao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEspecializacao;
	}


	public ArrayList<EspecializacaoVO> listarTodasEspecializacoes() {
		String query = "SELECT * from especializacao";


		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				EspecializacaoVO especializacao = new EspecializacaoVO();
				especializacao.setCodigoEspecializacao(result.getInt(1));
				especializacao.getEspecialidadeVO().setCodigoEspecialidade(result.getInt(2));
				especializacao.getMedicoVO().setCodigoMedico(result.getInt(3));
				especializacao.setAnoEspecializacao(result.getString(4));

				listaEspecializacao.add(especializacao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaEspecializacao;
	}

	public boolean existeEspecializacao(EspecializacaoVO especializacao) {
		String query = " SELECT count(esp.codigoEspecializacao) from especializacao esp " 
						+ " inner join especialidade e on (esp.codigoEspecialidade = e.codigoEspecialidade) "
						+ " inner join medico med on (esp.codigoMedico = med.codigoMedico) "
						+ " where e.codigoEspecialidade = ? "
						+ " and med.codigoMedico = ? "
						+ " and esp.anoEspecializacao = ? ";
		
		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		boolean existe = false;
		try {
			prepStmt.setInt(1, especializacao.getEspecialidadeVO().getCodigoEspecialidade());
			prepStmt.setInt(2, especializacao.getMedicoVO().getCodigoMedico());
			prepStmt.setString(3, especializacao.getAnoEspecializacao());
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				existe = (result.getInt(1) > 0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
	

	public ArrayList<EspecializacaoVO> existeEspecializacaoPorNome(String nomeMedico, String nomeEspecialidade){
		String query = "SELECT esp.codigoEspecializacao, e.codigoEspecialidade, med.codigoMedico, esp.anoEspecializacao from especializacao esp" 
				+ " inner join especialidade e on (esp.codigoEspecialidade = e.codigoEspecialidade)"
				+ " inner join medico med on (esp.codigoMedico = med.codigoMedico)"
				+ " where med.codigoMedico = ?"
				+ " and e.codigoEspecialidade = ?"
				+ " order by esp.codigoEspecialidade";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, query);
		EspecializacaoVO especializacao = null;
		ArrayList<EspecializacaoVO> especializacoes = new ArrayList<EspecializacaoVO>();
		try {
			prepStmt.setString(1, '%' + nomeMedico + '%');
			prepStmt.setString(2, '%' + nomeEspecialidade + '%');

			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {

				especializacao = new EspecializacaoVO();
				especializacao.getEspecialidadeVO().setCodigoEspecialidade(result.getInt(1));
				especializacao.getMedicoVO().setCodigoMedico(result.getInt(2));
				especializacao.setAnoEspecializacao(result.getString(3));

				especializacoes.add(especializacao);

			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			Banco.closeStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return especializacoes;
}
}