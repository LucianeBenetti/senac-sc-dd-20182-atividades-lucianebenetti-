package Clinica_Medica.View.Consulta;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import Clinica_Medica.Controller.ConvenioController;
import Clinica_Medica.Controller.EspecializacaoController;
import Clinica_Medica.Controller.MedicoController;
import Clinica_Medica.VO.ConsultaVO;
import Clinica_Medica.VO.ConvenioVO;
import Clinica_Medica.VO.EspecialidadeVO;
import Clinica_Medica.VO.MedicoVO;

import java.awt.Color;

public class TelaExcluirConsulta extends JPanel {
	
	private JComboBox cbConvenio;
	private JComboBox cbNomeMedico;
	private JComboBox cbEspecialidade;
	private JComboBox cbHorarioConsulta;
	private JTextField txtBuscarCPFPaciente;
	private JTextField txtIdPaciente;
	private JTextField txtIdConvenio;
	private JTextField txtNomePaciente;
	private JTextField txtIdEspecializacao;
	private JTextField txtIdProntuario;
	private JDateChooser dateChooserDataConsulta;
	private ConsultaVO consulta = new ConsultaVO();
	private static final String MASCARA_CPF = "###.###.###-##";

	/**
	 * Create the panel.
	 */
	public TelaExcluirConsulta() {
		setBackground(new Color(173, 216, 230));
		setLayout(null);

		JLabel lblBuscarCPFPaciente = new JLabel("CPF Paciente");
		lblBuscarCPFPaciente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarCPFPaciente.setBounds(26, 19, 142, 23);
		add(lblBuscarCPFPaciente);

		try {
			txtBuscarCPFPaciente = new JFormattedTextField(new MaskFormatter(MASCARA_CPF));
			txtBuscarCPFPaciente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtBuscarCPFPaciente.setBounds(199, 15, 399, 27);
		add(txtBuscarCPFPaciente);
		txtBuscarCPFPaciente.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuscar.setBounds(634, 15, 89, 31);
		add(btnBuscar);

		JSeparator separator = new JSeparator();
		separator.setBounds(26, 107, 697, 9);
		add(separator);

		JLabel lblNome = new JLabel("Nome Paciente");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNome.setBounds(26, 127, 142, 23);
		add(lblNome);

		JLabel lblIdPaciente = new JLabel("ID Paciente");
		lblIdPaciente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdPaciente.setBounds(560, 127, 101, 23);
		add(lblIdPaciente);

		JLabel lblConvenio = new JLabel("Convenio");
		lblConvenio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConvenio.setBounds(26, 189, 107, 23);
		add(lblConvenio);

		JLabel lblIdConvenio = new JLabel("ID Convenio");
		lblIdConvenio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdConvenio.setBounds(554, 189, 107, 23);
		add(lblIdConvenio);

		JLabel lblEspecialidade = new JLabel("Especialidade");
		lblEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEspecialidade.setBounds(26, 293, 128, 31);
		add(lblEspecialidade);

		JLabel lblNomeMdico = new JLabel("Nome M\u00E9dico");
		lblNomeMdico.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeMdico.setBounds(25, 245, 130, 23);
		add(lblNomeMdico);

		JLabel lblIdEspecializacao = new JLabel("ID Especializacao");
		lblIdEspecializacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdEspecializacao.setBounds(509, 245, 152, 23);
		add(lblIdEspecializacao);

		JLabel lblData = new JLabel("Data da Consulta");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblData.setBounds(26, 360, 152, 31);
		add(lblData);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnSair.setBounds(616, 448, 107, 35);
		add(btnSair);

		JLabel lblDataRealizao = new JLabel("Hora da Consulta");
		lblDataRealizao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDataRealizao.setBounds(421, 364, 152, 23);
		add(lblDataRealizao);

		txtIdPaciente = new JTextField();
		txtIdPaciente.setEditable(false);
		txtIdPaciente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtIdPaciente.setBounds(671, 127, 52, 31);
		add(txtIdPaciente);
		txtIdPaciente.setColumns(10);

		txtIdConvenio = new JTextField();
		txtIdConvenio.setEditable(false);
		txtIdConvenio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtIdConvenio.setColumns(10);
		txtIdConvenio.setBounds(671, 190, 48, 27);
		add(txtIdConvenio);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExcluir.setBounds(338, 448, 115, 35);
		add(btnExcluir);

		txtNomePaciente = new JTextField();
		txtNomePaciente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNomePaciente.setBounds(165, 124, 379, 26);
		add(txtNomePaciente);
		txtNomePaciente.setColumns(10);

		txtIdEspecializacao = new JTextField();
		txtIdEspecializacao.setEditable(false);
		txtIdEspecializacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtIdEspecializacao.setColumns(10);
		txtIdEspecializacao.setBounds(671, 246, 48, 27);
		add(txtIdEspecializacao);

		String[] convenios = { "----------- Selecione -----------" };
		cbConvenio = new JComboBox(convenios);
		cbConvenio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencherConvenio();
			}
		});
		cbConvenio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbConvenio.setBounds(164, 185, 274, 30);
		add(cbConvenio);

		String[] esp = { "----------- Selecione -----------" };
		cbEspecialidade = new JComboBox(esp);
		cbEspecialidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbEspecialidade.setBounds(164, 293, 274, 30);
		add(cbEspecialidade);

		String[] med = { "----------- Selecione -----------" };
		cbNomeMedico = new JComboBox(med);
		cbNomeMedico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				preencherMedico();
			}
		});
		cbNomeMedico.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbNomeMedico.setBounds(165, 237, 273, 31);
		add(cbNomeMedico);

		dateChooserDataConsulta = new JDateChooser();
		dateChooserDataConsulta.setBounds(188, 360, 168, 31);
		add(dateChooserDataConsulta);

		JLabel lblIdProntuario = new JLabel("ID Prontuario");
		lblIdProntuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIdProntuario.setBounds(26, 55, 130, 22);
		add(lblIdProntuario);

		txtIdProntuario = new JTextField();
		txtIdProntuario.setEditable(false);
		txtIdProntuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtIdProntuario.setBounds(199, 53, 86, 27);
		add(txtIdProntuario);
		txtIdProntuario.setColumns(10);

		JButton btnLimparTela = new JButton("Limpar Tela");
		btnLimparTela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limparTela();
			}
		});
		btnLimparTela.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLimparTela.setBounds(26, 448, 142, 35);
		add(btnLimparTela);
		
		String[] horarios = {"--- Selecione ---", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00"};
		cbHorarioConsulta = new JComboBox(horarios);
		cbHorarioConsulta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbHorarioConsulta.setBounds(579, 360, 128, 28);
		add(cbHorarioConsulta);

	}

	protected void preencherMedico() {
		String nomeEspecializacao = null;
		String nomeMedico = null;
		int codigoEspecializacao = 0;
		MedicoController controlador = new MedicoController();
		EspecializacaoController controladorEspecializacao = new EspecializacaoController();
		ArrayList<MedicoVO> listaMedico = new ArrayList<MedicoVO>();
		ArrayList<EspecialidadeVO> listaEspecializacao = new ArrayList<EspecialidadeVO>();

		listaMedico = controlador.consultarMedico();
		// listaEspecializacao =
		// controladorEspecializacao.ConsultarEspecialidade(nomeEspecializacao,
		// codigoEspecializacao, nomeMedico);
		DefaultComboBoxModel medicos = new DefaultComboBoxModel(listaMedico.toArray());
		DefaultComboBoxModel especialidades = new DefaultComboBoxModel(listaEspecializacao.toArray());
		cbNomeMedico.setModel(medicos);
		cbEspecialidade.setModel(especialidades);

	}

	protected void preencherConvenio() {
		ConvenioController controlador = new ConvenioController();
		ConvenioVO convenio = new ConvenioVO();
		ArrayList<ConvenioVO> listaConvenio = new ArrayList<ConvenioVO>();

		listaConvenio = controlador.ConsultarConvenio();
		DefaultComboBoxModel defaultComboBox = new DefaultComboBoxModel(listaConvenio.toArray());
		cbConvenio.setModel(defaultComboBox);

	}

	protected void limparTela() {
		txtBuscarCPFPaciente.setText("");
		txtIdConvenio.setText("");
		txtIdEspecializacao.setText("");
		txtIdPaciente.setText("");
		txtIdProntuario.setText("");
		txtNomePaciente.setText("");
		cbConvenio.setSelectedIndex(0);
		cbEspecialidade.setSelectedIndex(0);
		cbHorarioConsulta.setSelectedIndex(0);
		cbNomeMedico.setSelectedIndex(0);
		dateChooserDataConsulta.setDate(null);
		
	}
	protected ConsultaVO construirConsulta() {
		
		consulta.getEspecializacaoVO().setCodigoEspecializacao(Integer.parseInt(txtIdEspecializacao.getText()));
		consulta.getConvenioVO().setCodigoConvenio(Integer.parseInt(txtIdConvenio.getText()));
		consulta.getProntuarioVO().setCodigoProntuario(Integer.parseInt(txtIdProntuario.getText()));
		consulta.setDataConsulta(dateChooserDataConsulta.getDate());
		consulta.setHorarioConsulta((Time) cbHorarioConsulta.getSelectedItem());
		
		return consulta;
	}

}
