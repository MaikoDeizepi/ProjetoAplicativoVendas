/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.sistemacadastro.TelaGeral;

import com.br.sistemacadastro.Clientes.ClientesDAO;
import com.br.sistemacadastro.Clientes.GuiCadastroClientes;
import com.br.sistemacadastro.Itens_pedidos.GuiTeladePedido;
import com.br.sistemacadastro.Pedidos.GuiTabelaPedido;
import com.br.sistemacadastro.Produtos.GuiCadastroProdutos;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author MAIKOVITORDEIZEPISIL
 */
public class TelaGeral extends JFrame{
     JLabel label1, label2, label3, label4;
    JButton btcadastroclie, btcadastroprodutos, bttabelapedido, btNovo, bttelapedido, btCancelar, btSair;
    JPanel painel,painelBotoes,painelREG;
    JFrame janela;
    private ClientesDAO clientes;
    private GuiCadastroClientes cadastroclientes;
    private GuiTeladePedido telapedido;
    private GuiTabelaPedido tabelapedido;
    private GuiCadastroProdutos cadastroprodutos;
    
    

    
    public static void main(String args[]){
        JFrame janela = new TelaGeral();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
    
    public TelaGeral(){
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
        
        setTitle("Cadastro de alunos");
        setBounds(300,600,750,250);
           
        btcadastroclie = new JButton("Cadastro Clientes");
        btcadastroprodutos = new JButton("Cadastro Produtos");
        bttabelapedido = new JButton("Tabela Pedido");
        bttelapedido = new JButton("Tela de Pedido");
        
       
        btSair = new JButton("Sair");
    
        
        painelBotoes.add(btcadastroclie);
        painelBotoes.add(btcadastroprodutos);
        painelBotoes.add(bttabelapedido);
        painelBotoes.add(bttelapedido);
        painelBotoes.add(btSair);
        setResizable(true);
        
        setBotoes(true,true,true,true,true,true);
        clientes = new ClientesDAO();
        if (!clientes.bd.getConnection()){
            JOptionPane.showMessageDialog(null,"Falha na conexão!");
            System.exit(0);
        }
       
        
    }
        public void setBotoes(boolean bNovo, boolean bLocalizar, boolean bGravar, 
                boolean bAlterar, boolean bExcluir, boolean bCancelar){
        
          
            bttelapedido.setEnabled(bLocalizar);
            btcadastroclie.setEnabled(bGravar);
            btcadastroprodutos.setEnabled(bAlterar);
            bttabelapedido.setEnabled(bExcluir);
            
        }
   
        public void definirEventos(){
            btSair.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    clientes.bd.close();
                    System.exit(0);
                }
            });
        
        btcadastroclie.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               cadastroclientes = new GuiCadastroClientes();
               cadastroclientes.inicializacomponentes();
               cadastroclientes.definirEventos();
               cadastroclientes.setVisible(true);
              
                
            }
        });
        
        btcadastroprodutos.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
              cadastroprodutos = new GuiCadastroProdutos();
              cadastroprodutos.inicializacomponentes();
              cadastroprodutos.definirEventos();
              cadastroprodutos.setVisible(true);
              
              
             }
        });
        
        bttabelapedido.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             tabelapedido = new GuiTabelaPedido();
             tabelapedido.inicializacomponentes();
             tabelapedido.definirEventos();
             tabelapedido.setVisible(true);
                
            }
        });
        
        bttelapedido.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                telapedido = new GuiTeladePedido();
                telapedido.inicializacomponentes();
                telapedido.definirEventos();
                telapedido.setVisible(true);
                 
             }
        });
        
        
        
        }
       
        
        
        
       
}
