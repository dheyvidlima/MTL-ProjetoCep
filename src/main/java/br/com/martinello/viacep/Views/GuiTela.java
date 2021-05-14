package br.com.martinello.viacep.Views;

import br.com.martinello.viacep.Control.CepControl;
import br.com.martinello.viacep.Control.CepGS;
import br.com.martinello.viacep.Domain.CepDAO;
import br.com.martinello.viacep.Domain.ErrosDB;

import java.awt.Color;
import java.awt.Container;
import java.util.List;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GuiTela extends JFrame {

    private final JLabel labelCep;
    private final JLabel labelLocalicade;
    private final JLabel labelLogradouro;
    private final JLabel labelBairro;
    private final JLabel labelUf;
    private final JTable table;
    private final DefaultTableModel model;

    private final JLabel labelComplemento;
    private final JLabel labelIbge;
    private JTextField textCep, textLocalidade, textLogradouro, textBairro, textUf, textComplemento, textIbge;
    private final JButton botaoBuscar;

    public GuiTela() {
        super("Buscar Cep");
        Container container = getContentPane();
        setLayout(null);

        labelCep = new JLabel("Informe o Cep");
        labelLocalicade = new JLabel("Localidade");
        labelLogradouro = new JLabel("Logradouro");
        labelBairro = new JLabel("bairro");
        labelUf = new JLabel("UF");
        labelComplemento = new JLabel("Complemento");
        labelIbge = new JLabel("Código do IBGE");
        table = new JTable();

        labelCep.setBounds(130, 10, 240, 15);
        labelLocalicade.setBounds(130, 50, 240, 15);
        labelLogradouro.setBounds(130, 90, 240, 15);
        labelBairro.setBounds(130, 130, 240, 15);
        labelUf.setBounds(130, 170, 240, 15);
        labelComplemento.setBounds(130, 210, 240, 15);
        labelIbge.setBounds(130, 260, 240, 15);

        labelCep.setForeground(Color.BLACK);
        labelLocalicade.setForeground(Color.BLACK);
        labelLogradouro.setForeground(Color.BLACK);
        labelBairro.setForeground(Color.BLACK);
        labelUf.setForeground(Color.BLACK);
        labelComplemento.setForeground(Color.BLACK);
        labelIbge.setForeground(Color.BLACK);

        table.setAutoResizeMode(WIDTH);
        table.setVisible(true);

        container.add(labelCep);
        container.add(labelLocalicade);
        container.add(labelLogradouro);
        container.add(labelBairro);
        container.add(labelUf);
        container.add(labelComplemento);
        container.add(labelIbge);

        textCep = new JTextField();
        textLocalidade = new JTextField();
        textLogradouro = new JTextField();
        textBairro = new JTextField();
        textUf = new JTextField();
        textComplemento = new JTextField();
        textIbge = new JTextField();

        textCep.setBounds(130, 25, 240, 20);
        textLocalidade.setBounds(130, 65, 240, 20);
        textLogradouro.setBounds(130, 105, 240, 20);
        textBairro.setBounds(130, 145, 240, 20);
        textUf.setBounds(130, 185, 240, 20);
        textComplemento.setBounds(130, 225, 240, 20);
        textIbge.setBounds(130, 275, 240, 20);

        container.add(textCep);
        container.add(textLocalidade);
        container.add(textLogradouro);
        container.add(textBairro);
        container.add(textUf);
        container.add(textComplemento);
        container.add(textIbge);
        container.add(table);

        table.getModel();

        model = (DefaultTableModel) table.getModel();

        model.addColumn("Cep");
        model.addColumn("Localidade");
        model.addColumn("UF");
        model.addColumn("Codigo IBGE");
        model.addColumn("Bairro");
        model.addColumn("Logradouro");
        model.addColumn("Complemento");

        botaoBuscar = new JButton("Buscar");

        botaoBuscar.setBounds(210, 300, 80, 20);

        container.add(botaoBuscar);

        setSize(520, 520);
        setVisible(true);
        setLocationRelativeTo(null);

        botaoBuscar.addActionListener((ActionEvent e) -> {
            CepControl cc = new CepControl();
            try {
                cc.buscarCep(textCep.getText());
                textCep.setText(CepGS.getCEP());
                textBairro.setText(CepGS.getBAIRRO());
                textComplemento.setText(CepGS.getCOMPLEMENTO());
                textIbge.setText(CepGS.getIBGE());
                textLocalidade.setText(CepGS.getLOCALIDADE());
                textLogradouro.setText(CepGS.getLOGRADOURO());
                textUf.setText(CepGS.getUF());

                JOptionPane.showMessageDialog(null, "Concluído");
            } catch (SQLException | IOException ex) {
                Logger.getLogger(GuiTela.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
