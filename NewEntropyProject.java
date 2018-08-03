package newentropyproject;
//V2 
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner; 
import java.util.Random;
import java.util.ArrayList;

class Particle {

    private int xPosition;
    private int yPosition;
    private int xVelocity;
    private int yVelocity; 
    
    public int getXPosition () {
        return xPosition;
    }
    public int getYPosition () {
        return yPosition;
    }
    public int getXVelocity () {
        return xVelocity;
    }
    public int getYVelocity () {
        return xVelocity;
    }
    
    public void setXPosition (int xPos) {
        xPosition = xPos;
    }
    public void setYPosition (int yPos) {
        yPosition = yPos;
    }
    public void setXVelocity (int xVel) {
        xVelocity = xVel;
    }
    public void setYVelocity (int yVel) {
        yVelocity = yVel;
    }

}


public class NewEntropyProject implements ActionListener {

   //GettingGUI3 GH = new GettingGUI3();   
   JFrame frame;
   MyPanel anipanel;
   
   ArrayList <Particle> listOfParticles = new ArrayList <> ();
   
   private int iterator = 1000; //how many discrete motions 
   private int NumParticles = 1000;
   private int NumBackgroundObjects = 50;
   //private int MaxVelocityX = 10;
   //private int MaxVelocityY = 10;
   private int MaxVelocityX = 12;
   private int MaxVelocityY = 12;
   
   private int InitialTimeForTryCatch = 50;
   private int TimeForTryRate;
   
   private int x = 50; // ignore
   private int y = 50; // ignore
   
   private int ParticleCoordinateXStartVals [] = new int [NumParticles];
   private int ParticleCoordinateYStartVals [] = new int [NumParticles];
   private int ParticleVelocityXVals[] = new int [NumParticles]; 
   private int ParticleVelocityYVals[] = new int [NumParticles];
   
   private int RandomRedVals [] = new int [NumBackgroundObjects]; //Color
   private int RandomGreenVals [] = new int [NumBackgroundObjects]; //Color
   private int RandomBlueVals [] = new int [NumBackgroundObjects]; //Color 
   
   private int BackgroundXVals[] = new int [NumBackgroundObjects];
   private int BackgroundYVals[] = new int [NumBackgroundObjects];
   
   private int BackgroundXSize[] = new int [NumBackgroundObjects];
   
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in); // in case I want it 
        Random rand = new Random();             // in case I want it 
   
        NewEntropyProject gui = new NewEntropyProject ();
        gui.InitializeBackground();
        gui.InitializeParticles();
        gui.go(); 
  
    }    
    public void InitializeBackground (){ //TOTTALLY IGNOREEE
        
        Random randooo = new Random(); 
        
        int randnum1;
        int randnum2;
        int randnum3; 
        
        for (int i = 0; i < NumBackgroundObjects; i++){
                
                BackgroundXSize[i] = randooo.nextInt(20) + 5;
                BackgroundXVals [i] = randooo.nextInt(200)+450;
                BackgroundYVals [i] = randooo.nextInt(200)+250;;       
                RandomRedVals [i] = randooo.nextInt(255);
                RandomGreenVals [i] = randooo.nextInt(255);
                RandomBlueVals [i] = randooo.nextInt(255);
        }
    
    } 
    
    public void InitializeParticles (){
        
        for (int i = 0; i < NumParticles; i++) {
            
            listOfParticles.add(new Particle()); 
        }  
        
        Random rand = new Random(); 
        
        int VelocityAssignerX;
        int VelocityAssignerY;
        int cointosser1; // 1 heads 0 tails, 1 == positive 0 == negative
        int cointosser2; // 1 heads 0 tails, 1 == positive 0 == negative
        
        System.out.print("\nInitial Coordinates:\n");
        
        for (int i = 0; i < NumParticles; i++) {
            
            cointosser1 = rand.nextInt(2); //tosses coin 
            
            VelocityAssignerX = rand.nextInt(MaxVelocityX)+3;
            VelocityAssignerY = rand.nextInt(MaxVelocityY)+3;
            
            listOfParticles.get(i).setXPosition(rand.nextInt(100)+550); 
            listOfParticles.get(i).setYPosition(rand.nextInt(100)+350);
            
            //System.out.printf("\nX%d = %d, Y%d = %d\n",i,ParticleCoordinateXStartVals [i],i,ParticleCoordinateYStartVals [i]);
            
            if (cointosser1 == 0) { // checks for x negative 
            
                cointosser2 = rand.nextInt(2); //tosses coin 
                
                if (cointosser2 == 0){ // checks for y negative
                    
                    listOfParticles.get(i).setXVelocity(VelocityAssignerX * -1);
                    listOfParticles.get(i).setYVelocity(VelocityAssignerY * -1);
              
                }
                else if (cointosser2 == 1){ // checks for y positive
                    
                    listOfParticles.get(i).setXVelocity(VelocityAssignerX * -1); 
                    listOfParticles.get(i).setYVelocity(VelocityAssignerY *  1); //yes redundadant but easier to read
                
                }
                else {
                    System.out.print("\nSomething went wrong :(\n");
                }
            }
            
            else if (cointosser1 == 1) { // checks for x positive
            
                cointosser2 = rand.nextInt(2); //tosses coin 
                
                if (cointosser2 == 0){ // checks for y negative
                    
                    listOfParticles.get(i).setXVelocity(VelocityAssignerX *  1);  //yes redundadant but easier to read
                    listOfParticles.get(i).setYVelocity(VelocityAssignerY * -1);  
              
                }
                else if (cointosser2 == 1){ // checks for y positive

                    listOfParticles.get(i).setXVelocity(VelocityAssignerX *  1); //yes redundadant but easier to read
                    listOfParticles.get(i).setYVelocity(VelocityAssignerY *  1); //yes redundadant but easier to read
                
                }
                else {
                    System.out.print("\nSomething went wrong :(\n");
                }
            } 
            else {
                System.out.print("\nSomething went wrong :(\n");
            }
            
        }
    
    }
    public void go () {
        
        Scanner input = new Scanner(System.in);
        Random rand = new Random();  
    
        frame = new JFrame();
        anipanel = new MyPanel();

        JButton mybutton;
        mybutton = new JButton("ENTROPY");
        mybutton.addActionListener(this);
        frame.getContentPane().add(BorderLayout.SOUTH, mybutton);
        
        frame.getContentPane().add(BorderLayout.CENTER, anipanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        frame.setVisible(true);
        
       
        //^initializing the X Y coords of mad pariticles
        
        TimeForTryRate = InitialTimeForTryCatch;
                           
        for (int i = 0; i < iterator; i++) {

            for (int j = 1; j < NumParticles; j++) { 
                int newRandA = rand.nextInt(2);
                if (newRandA == 0){newRandA = 1;}
                else if (newRandA == 1){newRandA = -1;}
                int newRandB = rand.nextInt(2);
                if (newRandB == 0){newRandB = 1;}
                else if (newRandB == 1){newRandB = -1;}
                listOfParticles.get(j).setXPosition(listOfParticles.get(j).getXPosition() + newRandA * listOfParticles.get(j).getXVelocity());
                listOfParticles.get(j).setYPosition(listOfParticles.get(j).getYPosition() + newRandB * listOfParticles.get(j).getYVelocity());
                //if (listOfParticles.get(j).getXPosition() && listOfParticles.get(j).getYPosition() == )

             }
        anipanel.repaint();
        
        try {Thread.sleep(TimeForTryRate);} 
        catch (Exception ex){} 
        TimeForTryRate = TimeForTryRate + 2/3;

        }
        
    try {Thread.sleep(250);} 
    catch (Exception ex){}
    
  //--------////--------////--------////--------////--------////--------////--------//      
        /*
        for (int i = 0; i < iterator; i++) {

            for (int j = 1; j < NumParticles; j++) { 
                
                 
                listOfParticles.get(j).setXPosition(listOfParticles.get(j).getXPosition() - listOfParticles.get(j).getXVelocity());
                listOfParticles.get(j).setYPosition(listOfParticles.get(j).getYPosition() - listOfParticles.get(j).getYVelocity());  
            }

            anipanel.repaint();
            try {Thread.sleep(TimeForTryRate);} 
            catch (Exception ex){} 
            TimeForTryRate = TimeForTryRate - 2/3;
        }
        */
  //--------////--------////--------////--------////--------////--------////--------//      
        
    }        
    
    
    class MyPanel extends JPanel {
    
    Color ListOfRandColors [] = new Color [NumBackgroundObjects];    
       
        public void paintComponent (Graphics g) {
            
            g.setColor(Color.black); 
            g.fillRect(0,0,this.getWidth(), this.getHeight());
        /*  
            for (int a = 0; a < NumBackgroundObjects; a++ ){ //List of Random Color
                
                ListOfRandColors [a] = new Color(RandomRedVals[a],RandomGreenVals[a],RandomBlueVals[a]);
                
            } 
            for (int k = 0; k < NumBackgroundObjects; k++) { //BACKGROUND
                
                g.setColor(ListOfRandColors[k]);
                g.fillOval(BackgroundXVals[k], BackgroundYVals[k], BackgroundXSize[k], BackgroundXSize[k]);
          
            }
        */    
            for (int i = 0; i < NumParticles; i++){
                
                g.setColor(Color.red);
                g.drawOval(listOfParticles.get(i).getXPosition(), listOfParticles.get(i).getYPosition(), 7, 7);
                
            }
        }
        
        
    }

    
    public void actionPerformed (ActionEvent E) {
        
        anipanel.repaint();
        
    }
    
}









