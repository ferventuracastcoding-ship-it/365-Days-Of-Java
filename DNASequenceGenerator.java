import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class DNAGenerator extends JFrame {


    JTextArea dnaOutput;
    JLabel stats;

    String bases = "ATCG";


    public DNAGenerator(){

        setTitle("DNA Sequence Generator");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        dnaOutput = new JTextArea();
        dnaOutput.setFont(new Font("Monospaced",Font.BOLD,18));


        stats = new JLabel("DNA Stats");


        JButton generate = new JButton("Generate DNA");


        add(new JScrollPane(dnaOutput),
                BorderLayout.CENTER);

        add(stats,BorderLayout.NORTH);

        add(generate,BorderLayout.SOUTH);


        generate.addActionListener(e -> generateDNA());


        setVisible(true);

    }


    void generateDNA(){

        Random random = new Random();

        StringBuilder dna = new StringBuilder();


        for(int i=0;i<100;i++){

            dna.append(
                bases.charAt(
                random.nextInt(4))
            );
        }


        dnaOutput.setText(dna.toString());


        int a=0,t=0,c=0,g=0;


        for(char b:dna.toString().toCharArray()){

            if(b=='A') a++;
            if(b=='T') t++;
            if(b=='C') c++;
            if(b=='G') g++;

        }


        stats.setText(
            "Length: 100 | A:"+a+
            " T:"+t+
            " C:"+c+
            " G:"+g
        );
    }


    public static void main(String[] args){

        new DNAGenerator();

    }
}
