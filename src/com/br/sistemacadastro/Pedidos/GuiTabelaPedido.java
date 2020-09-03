/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.Pedidos;


import com.br.sistemacadastro.Clientes.Clientes;
import com.br.sistemacadastro.Pedidos.PedidosDAO;
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
public class GuiTabelaPedido  extends JFrame{
    
    JLabel label1, label2, label3, label4,label5,label6,label7,label8,label9,label10,label11,label12;
    JButton btGravar, btAlterar, btExcluir, btNovo, btLocalizar, btCancelar, btSair;
    JButton btPrim, btAnt, btProx, btUlt,btCons,btLimpar;
    JPanel painel,painelBotoes,painelREG;
    JFrame janela;
    static JTextField tfcodped, tfcodcliente,tfdata, tfnomecliente;
    private PedidosDAO pedidos;
    private ResultSet resultSet;
    private Clientes clientes;
    //private ConsultaAluno consulta; 
    
    public static void main(String args[]){
        JFrame janela = new GuiTabelaPedido();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
    
    public GuiTabelaPedido(){
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
        
        setTitle("Tabela de Pedidos");
        setBounds(600,500,700,500);
        label1 = new JLabel("Cod Pedido : ");
        label2 = new JLabel("Cod Cliente: ");
        label3 = new JLabel("Movimentação de Registros");
        label4 = new JLabel("Data");
        label12 = new JLabel("Nome do Cliente");
        tfcodped = new JTextField(4);
        tfcodcliente= new JTextField(35);
        tfdata= new JTextField(35);
        tfnomecliente= new JTextField(35);
       
        
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
        painel.add(tfcodcliente);
        painel.add(label4);
        painel.add(tfdata);
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
        pedidos = new PedidosDAO();
        if (!pedidos.bd.getConnection()){
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
                    pedidos.bd.close();
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
                    JOptionPane.showMessageDialog(null,"O código não pode ser vazio!");
                    tfcodped.requestFocus();
                    return;
                }
                if (tfcodcliente.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"O Codigo do cliente não pode ser vazio!");
                    tfcodcliente.requestFocus();
                    return;
                }
                if (tfdata.getText().equals("")){
                    JOptionPane.showMessageDialog(null," A data não pode ser vazia!");
                    tfdata.requestFocus();
                    return;
                }
               
              
                pedidos.pedidos.setCodped(Integer.parseInt(tfcodped.getText()));
                pedidos.pedidos.setCodcliente(Integer.parseInt((tfcodcliente.getText())));
                pedidos.pedidos.setData((tfdata.getText()));
                
                
                JOptionPane.showMessageDialog(null,pedidos.atualizar(PedidosDAO.INCLUSAO));
                limparcampos();
                tabelaPedidos();
              
                
            }
        });
        
        btAlterar.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
               
                pedidos.pedidos.setCodped(Integer.parseInt(tfcodped.getText()));
                pedidos.pedidos.setCodcliente(Integer.parseInt((tfcodcliente.getText())));
                pedidos.pedidos.setData((tfdata.getText()));
               
                
                JOptionPane.showMessageDialog(null,pedidos.atualizar(PedidosDAO.ALTERACAO));
                limparcampos();
                tabelaPedidos();
             }
        });
        
        btExcluir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pedidos.pedidos.setCodped(Integer.parseInt(tfcodped.getText()));
                pedidos.localizar();
                int n= JOptionPane.showConfirmDialog(null, pedidos.pedidos.getCodcliente(),
                        "Excluir o Cliente?",JOptionPane.YES_NO_OPTION);
                if (n==JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null,pedidos.atualizar(PedidosDAO.EXCLUSAO));
                    limparcampos();
                    tabelaPedidos();
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
            tfcodped.setText("");
            tfcodcliente.setText("");
            tfdata.setText("");
           
            setBotoes(true,true,false,false,false,false);
        }
        public void limpartodoscampos(){
            tfcodped.setText("");
            tfcodcliente.setText("");
            tfdata.setText("");
            
            setBotoes(true,true,true,true,true,true);
        }
        
        public void atualizarCampos(){
            // clientes.clientes.setCodcliente(Integer.parseInt(tfcodcliente.getText()));
            pedidos.pedidos.setCodped(Integer.parseInt(tfcodped.getText()));
            if (pedidos.localizar()){
                tfcodped.setText(Integer.toString(pedidos.pedidos.getCodped()));
                tfcodcliente.setText(Integer.toString(pedidos.pedidos.getCodcliente()));
                tfdata.setText(pedidos.pedidos.getData());
               
                
                setBotoes(true,true,false,true,true,true);
            } else {
                JOptionPane.showMessageDialog(null,"Cliente não econtrado!");
                limparcampos();
            }
        }
        
        
        public void tabelaPedidos(){
        try{
            String sql="Select * from pedidos";
            PreparedStatement statement = pedidos.bd.connection.prepareStatement(sql);
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
                
                tfcodped.setText(resultSet.getString("codped"));
                tfcodcliente.setText(resultSet.getString("Clientes_codcli"));   
                tfdata.setText(resultSet.getString("data_2"));    
                
                
                
            }
            catch (SQLException erro)
        {
            JOptionPane.showMessageDialog(null,"Problemas na conexão!\n"+ erro.toString());         
        }
        }    
    
}
