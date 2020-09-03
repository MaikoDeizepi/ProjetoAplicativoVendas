/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Clientes;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author luciana
 */
public class GuiCadastroClientes extends JFrame{
    JLabel label1, label2, label3, label4,label5,label6,label7,label8,label9,label10,label11;
    JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair;
    JComboBox   btEstados;
    JButton btPrim, btAnt, btProx, btUlt,btCons,btLimpar;
    JPanel painel,painelBotoes,painelREG;
    JFrame janela;
    static JTextField tfcodcliente, tfNome,tfEndereco,tfBairro,tfCidade,tfCEP,tfUF,tfEmail,tfFone,tfCelular;
    private ClientesDAO clientes;
    private ResultSet resultSet;
    
    //private ConsultaAluno consulta; 
    
    public static void main(String args[]){
        JFrame janela = new GuiCadastroClientes();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
    
    public GuiCadastroClientes(){
        inicializacomponentes();
        definirEventos();
        
    }
    
    
    
    public void inicializacomponentes(){
        setLayout(new BorderLayout());     //define layout da janela
       
        painel = new JPanel();
        painel.setLayout(new BoxLayout(painel,BoxLayout.PAGE_AXIS));    //define layout do painel
        this.add(painel,BorderLayout.NORTH);
        
        painelBotoes = new JPanel(new FlowLayout()); //define layout do painelBotoes    
        this.add(painelBotoes,BorderLayout.CENTER);
        
        painelREG = new JPanel(new FlowLayout());  //define layout do painelREG
        this.add(painelREG,BorderLayout.SOUTH);
        
        setTitle("Cadastro de Clientes");
        setBounds(600,900,1000,500);
        label1 = new JLabel("Cod Cliente : ");
        label2 = new JLabel("Nome: ");
        label3 = new JLabel("Movimentação de Registros");
        label4 = new JLabel("Endereço");
        label5 = new JLabel("Bairro");
        label11 = new JLabel("Cidade");
        label6 = new JLabel("CEP");
        label7 = new JLabel("UF");
        label8 = new JLabel("Email");
        label9 = new JLabel("Fone");
        label10 = new JLabel("Celular");
        tfcodcliente = new JTextField(4);
        tfNome= new JTextField(35);
        tfEndereco= new JTextField(35);
        tfBairro= new JTextField(35);
        tfCidade= new JTextField(35);
        tfCEP= new JTextField(35);
        tfUF= new JTextField(35);
        tfEmail= new JTextField(35);
        tfFone= new JTextField(35);
        tfCelular= new JTextField(35);
        
        btGravar = new JButton("Gravar");
        btAlterar = new JButton("Alterar");
        btExcluir = new JButton("Excluir");
        btLocalizar = new JButton("Localizar");
        btNovo = new JButton("Novo");
        btCancelar = new JButton("Cancelar");
        btCons = new JButton("Consultar");
        btSair = new JButton("Sair");
        btPrim = new JButton("<<");
        btPrim.setToolTipText("Primeiro");
        btAnt = new JButton("<");
        btAnt.setToolTipText("Anterior");
        btProx = new JButton(">");
        btProx.setToolTipText("Próximo");
        btUlt = new JButton(">>");
        btUlt.setToolTipText("Ultimo");
        btLimpar = new JButton("Limpar");
        btEstados = new JComboBox();
        painel.add(label1);
        painel.add(tfcodcliente);
        painel.add(label2);
        painel.add(tfNome);
        painel.add(label4);
        painel.add(tfEndereco);
        painel.add(label5);
        painel.add(tfBairro);
        painel.add(label11);
        painel.add(tfCidade);
        painel.add(label6);
       // painel.add(btEstados);
        painel.add(tfCEP);
        painel.add(label7);
        painel.add(tfUF);
        painel.add(label8);
        painel.add(tfEmail);
        painel.add(label9);
        painel.add(tfFone);
        painel.add(label10);
        painel.add(tfCelular);
        painelBotoes.add(btNovo);
        painelBotoes.add(btLocalizar);
        painelBotoes.add(btGravar);
        painelBotoes.add(btAlterar);
        painelBotoes.add(btExcluir);
        painelBotoes.add(btCancelar);
        painelBotoes.add(btLimpar);
        painelBotoes.add(btSair);
        painelREG.add(label3);
        painelREG.add(btPrim);
        painelREG.add(btAnt);
        painelREG.add(btProx);
        painelREG.add(btUlt);
        setResizable(true);
        
        setBotoes(true,true,false,false,false,false);
        clientes = new ClientesDAO();
        if (!clientes.bd.getConnection()){
            JOptionPane.showMessageDialog(null,"Falha na conexão!");
            System.exit(0);
        }
        tabelaClientes();
        //tfRA.setEnabled(false);
        //tfNome.setEnabled(false);
        //carregaDados();
    }
        public void setBotoes(boolean bNovo, boolean bLocalizar, boolean bGravar, 
                boolean bAlterar, boolean bExcluir, boolean bCancelar){
        
            btNovo.setEnabled(bNovo);
            btLocalizar.setEnabled(bLocalizar);
            btGravar.setEnabled(bGravar);
            btAlterar.setEnabled(bAlterar);
            btExcluir.setEnabled(bExcluir);
            btCancelar.setEnabled(bCancelar);
        }
   
        public void definirEventos(){
            btSair.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    clientes.bd.close();
                    System.exit(0);
                }
            });
        
        btProx.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               try{
                resultSet.next();
                carregaDados();
                        }
            catch (SQLException erro){
                
            }
            }    
        });
        
        btAnt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             try{
                resultSet.previous();
                carregaDados();
                }
            catch (SQLException erro){
                
            }
            }
        });
        
        btPrim.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             try{
                resultSet.first();
                carregaDados();
                }
            catch (SQLException erro){
                
            }
            }
        });
        
        btUlt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             try{
                resultSet.last();
                carregaDados();
                }
            catch (SQLException erro){
                
            }
            }
        });
       
        btNovo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                limparcampos();
            
                setBotoes(false,false,true,false,false,true);
                tfcodcliente.requestFocus();
            }
        });
       
        btCancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //tfRA.setEnabled(false);
                //tfNome.setEnabled(false);
                limparcampos(); 
       
            }  
        });
        
        btGravar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (tfcodcliente.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O código não pode ser vazio!");
                    tfcodcliente.requestFocus();
                    return;
                }
                if (tfNome.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O nome não pode ser vazio!");
                    tfNome.requestFocus();
                    return;
                }
                if (tfEndereco.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O Endereço não pode ser vazio!");
                    tfEndereco.requestFocus();
                    return;
                }
                if (tfBairro.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O Bairro não pode ser vazio!");
                    tfBairro.requestFocus();
                    return;
                }
                if (tfCidade.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"A cidade não pode ser vazio!");
                    tfCidade.requestFocus();
                    return;
                }
                if (tfCEP.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O CEP não pode ser vazio!");
                    tfCEP.requestFocus();
                    return;
                }
                if (tfUF.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"A Sigla UF não pode ser vazia!");
                    tfUF.requestFocus();
                    return;
                }
                if (tfEmail.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O email não pode ser vazio!");
                    tfEmail.requestFocus();
                    return;
                }
                if (tfFone.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O telefone não pode ser vazio!");
                    tfFone.requestFocus();
                    return;
                }
                if (tfCelular.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O celular não pode ser vazio!");
                    tfCelular.requestFocus();
                    return;
                }
              
                clientes.clientes.setCodcliente(Integer.parseInt(tfcodcliente.getText()));
                clientes.clientes.setNome(tfNome.getText());
                clientes.clientes.setEndereco(tfEndereco.getText());
                clientes.clientes.setBairro(tfBairro.getText());
                clientes.clientes.setCidade(tfCidade.getText());
                clientes.clientes.setCep(tfCEP.getText());
                clientes.clientes.setUf(tfUF.getText());
                clientes.clientes.setEmail(tfEmail.getText());
                clientes.clientes.setFone(tfFone.getText());
                clientes.clientes.setCelular(tfCelular.getText());
                
                JOptionPane.showMessageDialog(null,clientes.atualizar(ClientesDAO.INCLUSAO));
                limparcampos();
                tabelaClientes();
              
                
            }
        });
        
        btAlterar.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
               
                clientes.clientes.setCodcliente(Integer.parseInt(tfcodcliente.getText()));
                clientes.clientes.setNome((tfNome.getText()));
                clientes.clientes.setEndereco((tfEndereco.getText()));
                clientes.clientes.setBairro((tfBairro.getText()));
                clientes.clientes.setCidade((tfCidade.getText()));
                clientes.clientes.setCep((tfCEP.getText()));
                clientes.clientes.setUf((tfUF.getText()));
                clientes.clientes.setEmail((tfEmail.getText()));
                clientes.clientes.setFone((tfFone.getText()));
                clientes.clientes.setCelular((tfCelular.getText()));
                
                JOptionPane.showMessageDialog(null,clientes.atualizar(ClientesDAO.ALTERACAO));
                limparcampos();
                tabelaClientes();
             }
        });
        
        btExcluir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clientes.clientes.setCodcliente(Integer.parseInt(tfcodcliente.getText()));
                clientes.localizar();
                int n= JOptionPane.showConfirmDialog(null, clientes.clientes.getCodcliente(),
                        "Excluir o Cliente?",JOptionPane.YES_NO_OPTION);
                if (n==JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null,clientes.atualizar(ClientesDAO.EXCLUSAO));
                    limparcampos();
                    tabelaClientes();
                }
            }
        });
        
        btLocalizar.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 atualizarCampos();
                 
             }
        });
        btLimpar.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 limpartodoscampos();
                 
             }
        });
        
       
        
        }
        public void limparcampos(){
            tfcodcliente.setText("");
            tfNome.setText("");
            tfEndereco.setText("");
            tfBairro.setText("");
            tfCidade.setText("");
            tfCEP.setText("");
            tfUF.setText("");
            tfEmail.setText("");
            tfFone.setText("");
            tfCelular.setText("");
            setBotoes(true,true,false,false,false,false);
        }
        public void limpartodoscampos(){
            tfcodcliente.setText("");
            tfNome.setText("");
            tfEndereco.setText("");
            tfBairro.setText("");
            tfCidade.setText("");
            tfCEP.setText("");
            tfUF.setText("");
            tfEmail.setText("");
            tfFone.setText("");
            tfCelular.setText("");
            setBotoes(true,true,true,true,true,true);
        }
        
        public void atualizarCampos(){
            clientes.clientes.setCodcliente(Integer.parseInt(tfcodcliente.getText()));
            if (clientes.localizar()){
                tfcodcliente.setText(Integer.toString(clientes.clientes.getCodcliente()));
                tfNome.setText(clientes.clientes.getNome());
                tfEndereco.setText(clientes.clientes.getEndereco());
                tfBairro.setText(clientes.clientes.getBairro());
                tfCidade.setText(clientes.clientes.getCidade());
                tfCEP.setText(clientes.clientes.getCep());
                tfUF.setText(clientes.clientes.getUf());
                tfEmail.setText(clientes.clientes.getEmail());
                tfFone.setText(clientes.clientes.getFone());
                tfCelular.setText(clientes.clientes.getCelular());
                
                setBotoes(true,true,false,true,true,true);
            } else {
                JOptionPane.showMessageDialog(null,"Cliente não econtrado!");
                limparcampos();
            }
        }
        
        
        public void tabelaClientes(){
        try{
            String sql="Select * from clientes";
            PreparedStatement statement = clientes.bd.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();            
        }
        catch (SQLException erro)
        {
            JOptionPane.showMessageDialog(null,"Problemas na conexão!\n"+ erro.toString());
        }
        
    }
        
        public void carregaDados(){
            try{
               
                if(resultSet.isAfterLast())
                    resultSet.last();
                if (resultSet.isBeforeFirst())
                    resultSet.first();
                
                tfcodcliente.setText(resultSet.getString("codcli"));
                tfNome.setText(resultSet.getString("nome"));    
                tfEndereco.setText(resultSet.getString("endereco"));    
                tfBairro.setText(resultSet.getString("bairro"));    
                tfCidade.setText(resultSet.getString("cidade"));    
                tfCEP.setText(resultSet.getString("cep"));    
                tfUF.setText(resultSet.getString("uf"));    
                tfEmail.setText(resultSet.getString("email"));    
                tfFone.setText(resultSet.getString("fone"));   
                tfCelular.setText(resultSet.getString("celular"));   
                
                
            }
            catch (SQLException erro)
        {
            JOptionPane.showMessageDialog(null,"Problemas na conexão!\n"+ erro.toString());         
        }
        }
        
    /**
     *
     * @param clientes
     * @return
     */
    public List<String> listarestados(){
        List<String> estados = new ArrayList<>();
        estados.add("Acre - AC");
        estados.add("Alagoas	 AL");
        estados.add("Amapá	 AP");
        estados.add("Amazonas	 AM");
        estados.add("Bahia	 BA");
        estados.add(" Ceará	 CE");
        estados.add("Distrito Federal	 DF");
        estados.add("Espírito Santo	 ES");
        estados.add("Goiás	 GO");
        estados.add("Maranhão	 MA");
        estados.add("Mato Grosso	 MT");
        estados.add("Mato Grosso do Sul	 MS");
        estados.add("Minas Gerais	 MG");
        estados.add("Pará	 PA");
        estados.add("Paraíba 	 PB");
        estados.add("Paraná	 PR");
        estados.add("Pernambuco	 PE");
        estados.add("Piauí	 PI");
        estados.add("Rio de Janeiro	 RJ");
        estados.add("Rio Grande do Norte	 RN");
        estados.add("Rio Grande do Sul 	 RS");
        estados.add("Rondônia	 RO");
        estados.add("Roraima	 RR");
        estados.add("Santa Catarina 	 SC");
        estados.add("São Paulo 	 SP");
        estados.add("Sergipe	 SE");
        estados.add("Tocantins	 TO");
        
        for (String estado : estados){
            System.out.println(estado);
        }
        return estados;
    }
    
     
        }
            
  
        
        
        
    
    
