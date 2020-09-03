/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Produtos;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author luciana
 */
public class GuiCadastroProdutos extends JFrame{
    JLabel label1, label2, label3, label4,label5,label6,label7,label8;
    JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair,btLimpar;
    JButton btPrim, btAnt, btProx, btUlt;
    JPanel painel,painelBotoes,painelREG;
    JFrame janela;
    static JTextField tfcodprod, tfdescri,tfpreco,tfunidade,tfqtde_inicial,tfdata,tfqtde_atual;
    private ProdutosDAO produtos;
    private ResultSet resultSet;
    //private ConsultaAluno consulta; 
    
    public static void main(String args[]){
        JFrame janela = new GuiCadastroProdutos();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
    
    public GuiCadastroProdutos(){
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
        
        setTitle("Cadastro de produtos");
        setBounds(600,900,1000,500);
        label1 = new JLabel("Cod produto : ");
        label2 = new JLabel("Descrição: ");
        label3 = new JLabel("Movimentação de Registros");
        label4 = new JLabel("Preço: ");
        label5 = new JLabel("Unidade: ");
        label6 = new JLabel("Quantidade Inicial: ");
        label7 = new JLabel("Data Cadastro: ");
        label8 = new JLabel("Quantidade Final: ");
        tfcodprod = new JTextField(4);
        tfdescri= new JTextField(35);
        tfpreco= new JTextField(35);
        tfunidade= new JTextField(35);
        tfqtde_inicial= new JTextField(35);
        tfdata= new JTextField(35);
        tfqtde_atual= new JTextField(35);
        btGravar = new JButton("Gravar");
        btAlterar = new JButton("Alterar");
        btExcluir = new JButton("Excluir");
        btLocalizar = new JButton("Localizar");
        btNovo = new JButton("Novo");
        btCancelar = new JButton("Cancelar");
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
        painel.add(label1);
        painel.add(tfcodprod);
        painel.add(label2);
        painel.add(tfdescri);
        painel.add(label4);
        painel.add(tfpreco);
        painel.add(label5);
        painel.add(tfunidade);
        painel.add(label6);
        painel.add(tfqtde_inicial);
        painel.add(label7);
        painel.add(tfdata);
        painel.add(label8);
        painel.add(tfqtde_atual);
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
        produtos = new ProdutosDAO();
        if (!produtos.bd.getConnection()){
            JOptionPane.showMessageDialog(null,"Falha na conexão!");
            System.exit(0);
        }
        tabelaProdutos();
        
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
                    produtos.bd.close();
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
                tfcodprod.requestFocus();
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
                if (tfcodprod.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O código não pode ser vazio!");
                    tfcodprod.requestFocus();
                    return;
                }
                if (tfdescri.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O nome não pode ser vazio!");
                    tfdescri.requestFocus();
                    return;
                }
                if (tfpreco.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O preço não pode ser vazio!");
                    tfpreco.requestFocus();
                    return;
                }
                if (tfunidade.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O tipo de unidade não pode ser vazio!");
                    tfunidade.requestFocus();
                    return;
                }
                if (tfqtde_inicial.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"A quantidade inicial não pode ser vazia!");
                    tfqtde_inicial.requestFocus();
                    return;
                }
                
                if (tfdata.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O nome não pode ser vazio!");
                    tfdata.requestFocus();
                    return;
                }
                if (tfqtde_atual.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"A quantidade atual não pode ser vazia!");
                    tfqtde_atual.requestFocus();
                    return;
                }
                produtos.produtos.setCodprod(Integer.parseInt(tfcodprod.getText()));
                produtos.produtos.setDescricao(tfdescri.getText());
                produtos.produtos.setPreco(Double.parseDouble(tfpreco.getText()));
                produtos.produtos.setUnidade(tfunidade.getText());
                produtos.produtos.setQtde_inicial(Double.parseDouble(tfqtde_inicial.getText()));
                produtos.produtos.setData_cad((tfdata.getText()));
                produtos.produtos.setQtde_atual(Double.parseDouble(tfqtde_atual.getText()));
                JOptionPane.showMessageDialog(null,produtos.atualizar(ProdutosDAO.INCLUSAO));
                limparcampos();
                tabelaProdutos();
              
                
            }
        });
        
        btAlterar.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
               
                produtos.produtos.setCodprod(Integer.parseInt(tfcodprod.getText()));
                produtos.produtos.setDescricao(tfdescri.getText());
                produtos.produtos.setPreco(Double.parseDouble(tfpreco.getText()));
                produtos.produtos.setUnidade(tfunidade.getText());
                produtos.produtos.setQtde_inicial(Double.parseDouble(tfqtde_inicial.getText()));
                produtos.produtos.setData_cad(tfdata.getText());
                produtos.produtos.setQtde_atual(Double.parseDouble(tfqtde_atual.getText()));
                JOptionPane.showMessageDialog(null,produtos.atualizar(ProdutosDAO.ALTERACAO));
                limparcampos();
                tabelaProdutos();
             }
        });
        
        btExcluir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                produtos.produtos.setCodprod(Integer.parseInt(tfcodprod.getText()));
                produtos.localizar();
                int n= JOptionPane.showConfirmDialog(null, produtos.produtos.getDescricao(),
                        "Excluir o produto?",JOptionPane.YES_NO_OPTION);
                if (n==JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null,produtos.atualizar(ProdutosDAO.EXCLUSAO));
                    limparcampos();
                    tabelaProdutos();
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
            tfcodprod.setText("");
            tfdescri.setText("");
            tfpreco.setText("");
            tfunidade.setText("");
            tfqtde_inicial.setText("");
            tfdata.setText("");
            tfqtde_atual.setText("");
            setBotoes(true,true,false,false,false,false);
        }
        
        public void limpartodoscampos(){
            tfcodprod.setText("");
            tfdescri.setText("");
            tfpreco.setText("");
            tfunidade.setText("");
            tfqtde_inicial.setText("");
            tfdata.setText("");
            tfqtde_atual.setText("");
            setBotoes(true,true,true,true,true,true);
        }
        public void atualizarCampos(){
            produtos.produtos.setCodprod(Integer.parseInt(tfcodprod.getText()));
            if (produtos.localizar()){
                tfcodprod.setText(Integer.toString(produtos.produtos.getCodprod()));
                tfdescri.setText(produtos.produtos.getDescricao());
                tfpreco.setText((produtos.produtos.getPreco().toString()));
                tfunidade.setText(produtos.produtos.getUnidade());
                tfqtde_inicial.setText(Double.toString(produtos.produtos.getQtde_inicial()));
                tfdata.setText(produtos.produtos.getData_cad());
                tfqtde_atual.setText(Double.toString(produtos.produtos.getQtde_atual()));
                setBotoes(true,true,false,true,true,true);
            } else {
                JOptionPane.showMessageDialog(null,"Produto não econtrado!");
                limparcampos();
            }
        }
        
        
        public void tabelaProdutos(){
        try{
            String sql="Select * from produtos";
            PreparedStatement statement = produtos.bd.connection.prepareStatement(sql);
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
                
                tfcodprod.setText(resultSet.getString("codprod"));
                tfdescri.setText(resultSet.getString("descricao"));
                tfpreco.setText(resultSet.getString("preco"));
                tfunidade.setText(resultSet.getString("unidade"));
                tfqtde_inicial.setText(resultSet.getString("qtde_inicial"));
                tfdata.setText(resultSet.getString("data_cad"));
                tfqtde_atual.setText(resultSet.getString("qtde_atual"));
            }
            catch (SQLException erro)
        {
            JOptionPane.showMessageDialog(null,"Problemas na conexão!\n"+ erro.toString());         
        }
        }      
        }
            
  
        
        
        
    
    
