/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadzone;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author giova
 */
public class Window extends JFrame{

	public Window() {

        initUI();
    }

    private void initUI() {
        setTitle("Dead zone");
        add(new Board());
        this.setSize(new Dimension(1000,700));
        setResizable(false);
        setTitle("Dead Zone");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            Window ex = new Window();
            ex.setVisible(true);
        });
    }
}