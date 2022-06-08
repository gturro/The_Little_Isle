package main;

 // 2d unity 3d Unreal
 // c# unity c++ unreal


import javax.swing.JFrame;


public class Main{

    public static void main (String[] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Repte Joc M03");

        
        
        GamePanel gP = new GamePanel();
        window.add(gP);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gP.setUpGame();

        gP.startGameThread();

    }
}
