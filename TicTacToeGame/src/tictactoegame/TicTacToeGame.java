package tictactoegame;


import java.awt.*;
import java.awt.event.*;
import javax.accessibility.AccessibleContext;
import javax.swing.*;

/**
 *
 * @author MaryQ (mq398)
 * 
 * 
 * Tic Tac Toe Game:
 * Player One is X and goes first
 * Player Two is O and goes second
 * 
 */
public class TicTacToeGame {
    static int row = 3;
    static int column = 3;
    static int numOfButtons = 9;
    public static JButton[] buttonArr = new JButton[numOfButtons];
    
    static String line = "";
    static String acrossOne;
    static String acrossTwo;
    static String acrossThree;
    static String vertOne;
    static String vertTwo;
    static String vertThree;
    static String diaOne;
    static String diaTwo;

    static int winPos1;
    static int winPos2;
    static int winPos3;

   
    public static void main(String[] args) {

        JFrame theFrame = new JFrame("Tic Tac Toe");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        theFrame.setSize(400,400);
        theFrame.setResizable(false);
        
        JPanel thePanel = new JPanel();
        GridLayout gLayout = new GridLayout(row,column);        
        thePanel.setLayout(gLayout);
        
        //adds buttons 
        for(int i = 0; i < buttonArr.length; i++){
            JButton button = new JButton("");
            thePanel.add(button);
            buttonArr[i] = button;
            button.addActionListener (new ButtonAction());            
        }
        resetBoard();
        theFrame.add(thePanel);
        theFrame.setVisible(true);    
    }
    
    //clears board
   public static void resetBoard(){
        for(int i = 0; i < buttonArr.length ; i++){
            buttonArr[i].setText("");
        }
   }
    
    
    public static void updateGame(){
        
        //gets context in array position
        AccessibleContext chara0 = buttonArr[0].getAccessibleContext();
        //gets the string value of the context
        String sChara0 = chara0.getAccessibleName();
        
        AccessibleContext chara1 = buttonArr[1].getAccessibleContext();
        String sChara1 = chara1.getAccessibleName();
        
        AccessibleContext chara2 = buttonArr[2].getAccessibleContext();
        String sChara2 = chara2.getAccessibleName();
        
        AccessibleContext chara3 = buttonArr[3].getAccessibleContext();
        String sChara3 = chara3.getAccessibleName();
        
        AccessibleContext chara4 = buttonArr[4].getAccessibleContext();
        String sChara4 = chara4.getAccessibleName();
        
        AccessibleContext chara5 = buttonArr[5].getAccessibleContext();
        String sChara5 = chara5.getAccessibleName();
        
        AccessibleContext chara6 = buttonArr[6].getAccessibleContext();
        String sChara6 = chara6.getAccessibleName();
        
        AccessibleContext chara7 = buttonArr[7].getAccessibleContext();
        String sChara7 = chara7.getAccessibleName();
        
        AccessibleContext chara8 = buttonArr[8].getAccessibleContext();
        String sChara8 = chara8.getAccessibleName();
        
        
         acrossOne = sChara0 + sChara1 + sChara2;
         acrossTwo = sChara3 + sChara4 + sChara5;;
         acrossThree = sChara6 + sChara7 + sChara8;
         vertOne = sChara0 + sChara3 + sChara6;
         vertTwo = sChara1 + sChara4 + sChara7;
         vertThree = sChara2 + sChara5 + sChara8;
         diaOne = sChara6 + sChara4 + sChara2;
         diaTwo = sChara0 + sChara4 + sChara8;
         
    }
   
    public static String getLine(){
         //check if there is a win and returns the winning string
         //stores positions of the winning line
         if(acrossOne.equals("XXX") || acrossOne.equals("OOO") ){
            winPos1 = 0;
            winPos2 = 1;
            winPos3 = 2;
            return acrossOne;
         }
         else if(acrossTwo.equals("XXX") || acrossTwo.equals("OOO") ){
            winPos1 = 3;
            winPos2 = 4;
            winPos3 = 5; 
            return acrossTwo;
         }
         else if (acrossThree.equals("XXX") || acrossThree.equals("OOO") ){
            winPos1 = 6;
            winPos2 = 7;
            winPos3 = 8;
            return acrossThree;
         }
         else if (vertOne.equals("XXX") || vertOne.equals("OOO") ){
            winPos1 = 0;
            winPos2 = 3;
            winPos3 = 6;
            return vertOne;
         }
         else if (vertTwo.equals("XXX") || vertTwo.equals("OOO")){
            winPos1 = 1;
            winPos2 = 4;
            winPos3 = 7;
            return vertTwo;
         }
         else if (vertThree.equals("XXX") || vertThree.equals("OOO")){
            winPos1 = 2;
            winPos2 = 5;
            winPos3 = 8;
            return vertThree;
         }
         else if (diaOne.equals("XXX") || diaOne.equals("OOO")){
            winPos1 = 6;
            winPos2 = 4;
            winPos3 = 2;
            return diaOne;   
         }
         else if (diaTwo.equals("XXX") || diaTwo.equals("OOO")){
            winPos1 = 0;
            winPos2 = 4;
            winPos3 = 8;
            return diaTwo;
         }
         else{
            return " ";
         }       
    }
}

class Player{
    static boolean turn = true;
    static int count = 0;
    public static void finishTurn(){
        PlayerOne.turn = !PlayerOne.turn; //switches turn between players
    }
}

class PlayerOne extends Player{
    static String symbol = "X";
}

class PlayerTwo extends Player{
    static String symbol = "O";
}

class ButtonAction implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        JButton jb = (JButton) e.getSource();
        if(jb.getText().equals("")){
            if(PlayerOne.turn){  //player one's turn
                jb.setText(PlayerOne.symbol);
            }
            else{ //player two's turn
                jb.setText(PlayerTwo.symbol);
            }
                Player.finishTurn();
        }
        TicTacToeGame.updateGame();
        Player.count++;
        if(Player.count >=5){
            TicTacToeGame.line = TicTacToeGame.getLine();
            if(TicTacToeGame.line.equals("XXX")){ //if Player One wins, winning line will be highlighted in yellow
                TicTacToeGame.buttonArr[TicTacToeGame.winPos1].setBackground(Color.yellow);
                TicTacToeGame.buttonArr[TicTacToeGame.winPos2].setBackground(Color.yellow);
                TicTacToeGame.buttonArr[TicTacToeGame.winPos3].setBackground(Color.yellow);
                
                for(int j = 0; j < TicTacToeGame.buttonArr.length; j++){
                    if(j != TicTacToeGame.winPos1 || j!= TicTacToeGame.winPos2 || j!= TicTacToeGame.winPos3){
                       TicTacToeGame.buttonArr[j].setEnabled(false); 
                    }
                }
  
            }
            else if(TicTacToeGame.line.equals("OOO")){ //if Player Two wins, winning line will be highlighted in cyan
               TicTacToeGame.buttonArr[TicTacToeGame.winPos1].setBackground(Color.cyan);
                TicTacToeGame.buttonArr[TicTacToeGame.winPos2].setBackground(Color.cyan);
                TicTacToeGame.buttonArr[TicTacToeGame.winPos3].setBackground(Color.cyan);
                for(int j = 0; j < TicTacToeGame.buttonArr.length; j++){
                    if(j != TicTacToeGame.winPos1 || j!= TicTacToeGame.winPos2 || j!= TicTacToeGame.winPos3){
                       TicTacToeGame.buttonArr[j].setEnabled(false); 
                    }
                }
          
            }
            else if(Player.count == 9){  //Tie -- All buttons will be disabled
               for(int i = 0; i < TicTacToeGame.buttonArr.length; i++){
                   TicTacToeGame.buttonArr[i].setEnabled(false);
               } 
            }
            
        }  
    }
}

