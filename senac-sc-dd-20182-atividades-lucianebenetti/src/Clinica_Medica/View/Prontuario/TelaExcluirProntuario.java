package Clinica_Medica.View.Prontuario;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import com.toedter.calendar.JDateChooser;

import Clinica_Medica.Controller.ConvenioController;
import Clinica_Medica.Controller.ProntuarioController;
import Clinica_Medica.VO.ConvenioVO;
import Clinica_Medica.VO.ProntuarioVO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaExcluirProntuario extends JPanel {
	private JTextField txtNome;
	private JTextField txtIdProntuario;
	private static final String MASCARA_CPF = "###.###.###-##";
	private JTable tbProntuarios;
	private ProntuarioVO prontuario = new ProntuarioVO();

	/**
	 * Create the panel.
	 */
	public TelaExcluirProntuario() {
		setBackground(new Color(173, 216, 230));
		setLayout(null);

		JButton btnLimparTela = new JButton("Limpar Tela");
		btnLimparTela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparTela();
			}
		});

		btnLimparTela.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLimparTela.setBounds(21, 416, 132, 31);
		add(btnLimparTela);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProntuarioController dao = new ProntuarioController();
				ProntuarioVO prontuarioExcluido = construirProntuario();
				dao.excluirProntuario(prontuarioExcluido);
				JOptionPane.showMessageDialog(null, "Prontu�rio exclu�do!");
				limparTela();
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExcluir.setBounds(282, 416, 97, 31);
		add(btnExcluir);

		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int opcao;
				Object[] BtSair = { "Sim", "N�o" };

				opcao = JOptionPane.showOptionDialog(null, "Deseja sair desta opera��o?", "Fechar",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, BtSair, BtSair[0]);
				if (opcao == JOptionPane.YES_OPTION) {
					setVisible(false);
				}
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSair.setBounds(505, 416, 97, 30);
		add(btnSair);

		JLabel lblNomePaciente = new JLabel("Nome Paciente");
		lblNomePaciente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomePaciente.setBounds(21, 321, 120, 25);
		add(lblNomePaciente);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNome.setBounds(164, 320, 395, 25);
		add(txtNome);
		txtNome.setColumns(10);

		JLabel lblIdProntuario = new JLabel("ID Prontuario");
		lblIdProntuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdProntuario.setBounds(21, 265, 125, 25);
		add(lblIdProntuario);

		txtIdProntuario = new JTextField();
		txtIdProntuario.setEditable(false);
		txtIdProntuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtIdProntuario.setBounds(164, 264, 86, 25);
		add(txtIdProntuario);
		txtIdProntuario.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(21, 235, 606, 2);
		add(separator);

		JButton btnBuscarProntuarios = new JButton("Buscar Prontuarios");
		btnBuscarProntuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				ProntuarioController controlador = new ProntuarioController();
				ArrayList<ProntuarioVO> prontuarios = (ArrayList<ProntuarioVO>) controlador.listarTodosProntuarios();

				DefaultTableModel tabela = (DefaultTableModel) tbProntuarios.getModel();
				for (ProntuarioVO prontuario : prontuarios) {
					tabela.addRow(new Object[] { prontuario.getCodigoProntuario(), prontuario.getMedicamento(),
							prontuario.getExame(), prontuario.getRegistro()

					});
				}
			}
		});
		btnBuscarProntuarios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuscarProntuarios.setBounds(197, 11, 211, 31);
		add(btnBuscarProntuarios);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 71, 543, 128);
		add(scrollPane);

		tbProntuarios = new JTable();
		tbProntuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int selecionado = tbProntuarios.getSelectedRow();

				txtIdProntuario.setText(tbProntuarios.getValueAt(selecionado, 0) + "");
				txtNome.setText((String) tbProntuarios.getValueAt(selecionado, 1));

			}
		});
		tbProntuarios.setModel(
				new DefaultTableModel(new Object[][] { { "ID", "Paciente", "Medicamentos", "Exames", "Registro" }, },
						new String[] { "ID", "Paciente", "Medicamentos", "Exames", "Registro" }));
		tbProntuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(tbProntuarios);

	}

	protected void limparTela() {

		txtIdProntuario.setText("");
		txtNome.setText("");
	
		limparTabela() ;

	}

	protected ProntuarioVO construirProntuario() {

		prontuario.setCodigoProntuario(Integer.parseInt(txtIdProntuario.getText()));

		return prontuario;

	}
	private void limparTabela() {
		int linhas = 0;
		int colunas = 0;
		String zer = null;

		for (linhas = 0; linhas <= tbProntuarios.getRowCount() - 1; linhas++) {
			for (colunas = 0; colunas <= tbProntuarios.getColumnCount() - 1; colunas++) {
				tbProntuarios.setValueAt(zer, linhas, colunas);
			}
		}
	}

}
