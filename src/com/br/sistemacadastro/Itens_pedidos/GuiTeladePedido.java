/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Itens_pedidos;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MAIKOVITORDEIZEPISIL
 */
public class GuiTeladePedido extends JFrame{
    JLabel label1, label2, label3, label4,label5,label6,label7,label8,label9,label10,label11;
    JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair;
    JButton btPrim, btAnt, btProx, btUlt,btCons,btLimpar;
    JPanel painel,painelBotoes,painelREG;
    JFrame janela;
    static JTextField tfcodped, tfcodprod,tfqtde;
    private Itens_pedidosDAO itenspedidos;
    private ResultSet resultSet;
    //private ConsultaAluno consulta; 
    
    public static void main(String args[]){
        JFrame janela = new GuiTeladePedido();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
    
    public GuiTeladePedido(){
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
        
        setTitle("Tela de pedidos");
        setBounds(600,900,1000,500);
        label1 = new JLabel("Cod Pedido : ");
        label2 = new JLabel("Cod Produto: ");
        label3 = new JLabel("Movimentação de Registros");
        label4 = new JLabel("Quantidade");
        tfcodped = new JTextField(4);
        tfcodprod= new JTextField(35);
        tfqtde= new JTextField(35);
       
        
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
        painel.add(label1);
        painel.add(tfcodped);
        painel.add(label2);
        painel.add(tfcodprod);
        painel.add(label4);
        painel.add(tfqtde);
        painelBotoes.add(btNovo);
        painelBotoes.add(btLocalizar);
        painelBotoes.add(btGravar);
        
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
        itenspedidos = new Itens_pedidosDAO();
        if (!itenspedidos.bd.getConnection()){
            JOptionPane.showMessageDialog(null,"Falha na conexão!");
            System.exit(0);
        }
        tabelaPedidos();
       
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
                    itenspedidos.bd.close();
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
                tfcodped.requestFocus();
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
                if (tfcodped.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O código do pedido não pode ser vazio!");
                    tfcodped.requestFocus();
                    return;
                }
                if (tfcodprod.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O Codigo do produto não pode ser vazio!");
                    tfcodprod.requestFocus();
                    return;
                }
                if (tfqtde.getText().equals("")){
                    JOptionPane.showMessageDialog(null," A Quantidade não pode ser vazia!");
                    tfqtde.requestFocus();
                    return;
                }
               
              
                itenspedidos.itenspedidos.setCodpedidos(Integer.parseInt(tfcodped.getText()));
                itenspedidos.itenspedidos.setCodprodutos(Integer.parseInt((tfcodprod.getText())));
                itenspedidos.itenspedidos.setQtde(Double.parseDouble(tfqtde.getText()));
                
                
                JOptionPane.showMessageDialog(null,itenspedidos.atualizar(Itens_pedidosDAO.INCLUSAO));
                limparcampos();
                tabelaPedidos();
              
                
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
            tfcodped.setText("");
            tfcodprod.setText("");
            tfqtde.setText("");
           
            setBotoes(true,true,false,false,false,false);
        }
        public void limpartodoscampos(){
            tfcodped.setText("");
            tfcodprod.setText("");
            tfqtde.setText("");
            
            setBotoes(true,true,true,true,true,true);
        }
        
        public void atualizarCampos(){
            itenspedidos.itenspedidos.setCodpedidos(Integer.parseInt(tfcodped.getText()));
            if (itenspedidos.localizar()){
           tfcodped.setText(Integer.toString(itenspedidos.itenspedidos.getCodpedidos()));
           tfcodprod.setText(Integer.toString(itenspedidos.itenspedidos.getCodprodutos()));
           tfqtde.setText(Double.toString(itenspedidos.itenspedidos.getQtde()));


//     pedidos.pedidos.setCodped(Integer.parseInt(tfcodped.getText()));
           // if (pedidos.localizar()){
             //   tfcodped.setText(Integer.toString(pedidos.pedidos.getCodped()));
               // tfcodcliente.setText(Integer.toString(pedidos.pedidos.getCodcliente()));
                //tfdata.setText(pedidos.pedidos.getData());
               
                
                setBotoes(true,true,false,true,true,true);
            } else {
                JOptionPane.showMessageDialog(null,"Codigo não econtrado!");
                limparcampos();
            }
        }
        
        
        public void tabelaPedidos(){
        try{
            String sql="Select * from itens_ped";
            PreparedStatement statement = itenspedidos.bd.connection.prepareStatement(sql);
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
                
                tfcodped.setText(resultSet.getString("Produtos_codprod"));
                tfcodprod.setText(resultSet.getString("pedidos_codped"));    
                tfqtde.setText(resultSet.getString("qtde"));    
                
                
                
            }
            catch (SQLException erro)
        {
            JOptionPane.showMessageDialog(null,"Problemas na conexão!\n"+ erro.toString());         
        }
        }    
    
}
