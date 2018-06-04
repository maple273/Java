/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colorbuttons;

/**
 *
 * @author MaryQ 
 * 
 * Toggle is set to ON by default; color changes roughly every second
 * Press on any button to stop the changing of colors
 * To resume the changing of colors, press on any button again
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ColorButtons {
    static int row = 4;
    static int column = 2;
    static int numOfButtons = 8;
    static Changer c = new Changer();
    public static Random rand = new Random();
    public static JButton[] buttonArr = new JButton[numOfButtons];
    
 
    public static void main(String[] args) {
        JFrame theFrame = new JFrame("Colored Buttons");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        theFrame.setSize(400,400);
         
        JPanel thePanel = new JPanel();
       
        GridLayout gLayout = new GridLayout(row,column);        
        thePanel.setLayout(gLayout);
        
        for(int i = 0; i < buttonArr.length; i++){
            JButton button = new JButton("Button " + (i+1));
            button.setBackground(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            buttonArr[i] = button; //used for button comparsion to change color for other buttons
            thePanel.add(button);
            button.addActionListener (new ButtonAction());
        }
        
        theFrame.add(thePanel);
        theFrame.setVisible(true);
        
        
        c.start();
    }
}

class Changer extends Thread{
    boolean status = true;
    
    public synchronized void run(){
        try{
            while(status){
                for(int i = 0; i < ColorButtons.buttonArr.length ; i++){            
                    ColorButtons.buttonArr[i].setBackground(new Color(ColorButtons.rand.nextInt(255), ColorButtons.rand.nextInt(255), ColorButtons.rand.nextInt(255)));
                }
               sleep(1000);  //sleeps for 1 second
               for(int i = 0; i < ColorButtons.buttonArr.length ; i++){            
                    ColorButtons.buttonArr[i].setBackground(new Color(ColorButtons.rand.nextInt(255), ColorButtons.rand.nextInt(255), ColorButtons.rand.nextInt(255)));
                }
               sleep(1000);
              
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}

class ButtonAction implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        JButton jb = (JButton) e.getSource();
        
        if(ColorButtons.c.status == true){  //when button gets clicked it, changes are turned OFF
            ColorButtons.c.status = false;
        }
        else if( ColorButtons.c.status == false){     //when button gets clicked it, changes are turned ON
            ColorButtons.c.status = true;
            ColorButtons.c = new Changer();
            ColorButtons.c.start();
           
        }
    }
}
