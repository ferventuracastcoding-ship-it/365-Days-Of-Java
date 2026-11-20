// Code to practive java syntax
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class FileExplorerSystem extends JFrame {

    private JList<String> fileList;
    private DefaultListModel<String> model;

    private File currentDirectory;

    private JLabel pathLabel;

    public FileExplorerSystem(){

        setTitle("Java File Explorer System");

        setSize(1000,600);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(new BorderLayout());
        // Sidebar

        JPanel sidebar =
        new JPanel();

        sidebar.setLayout(
        new GridLayout(6,1,5,5)
        );

        sidebar.setPreferredSize(
        new Dimension(200,600)
        );

        JButton home =
        new JButton("🏠 Home");

        JButton desktop =
        new JButton("🖥 Desktop");

        JButton documents =
        new JButton("📄 Documents");

        JButton downloads =
        new JButton("⬇ Downloads");

        JButton refresh =
        new JButton("🔄 Refresh");

        JButton exit =
        new JButton("❌ Exit");

        sidebar.add(home);
        sidebar.add(desktop);
        sidebar.add(documents);
        sidebar.add(downloads);
        sidebar.add(refresh);
        sidebar.add(exit);

        add(
        sidebar,
        BorderLayout.WEST
        );
        // Main area
        JPanel main =
        new JPanel(
        new BorderLayout()
        );

        pathLabel =
        new JLabel();

        model =
        new DefaultListModel<>();

        fileList =
        new JList<>(model);

        fileList.setFont(
        new Font(
        "Arial",
        Font.PLAIN,
        18
        )
        );

        main.add(
        pathLabel,
        BorderLayout.NORTH
        );

        main.add(
        new JScrollPane(fileList),
        BorderLayout.CENTER
        );

        add(
        main,
        BorderLayout.CENTER
        );

        currentDirectory =
        new File(
        System.getProperty("user.home")
        );

        loadFiles();
        // Sidebar actions
        home.addActionListener(e -> {

            open(
            System.getProperty("user.home")
            );

        });

        desktop.addActionListener(e -> {

            open(
            System.getProperty("user.home")
            +"/Desktop"
            );

        });

        documents.addActionListener(e -> {

            open(
            System.getProperty("user.home")
            +"/Documents"
            );

        });

        downloads.addActionListener(e -> {

            open(
            System.getProperty("user.home")
            +"/Downloads"
            );

        });

        refresh.addActionListener(e -> loadFiles());

        exit.addActionListener(e -> System.exit(0));

        fileList.addMouseListener(
        new MouseAdapter(){

            public void mouseClicked(MouseEvent e){

                if(e.getClickCount()==2){

                    openSelected();

                }

            }

        });

        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void open(String path){

        File file =
        new File(path);


        if(file.exists()
        && file.isDirectory()){


            currentDirectory=file;

            loadFiles();

        }

    }

    private void loadFiles(){

        model.clear();

        pathLabel.setText(
        " Location: "
        +currentDirectory.getAbsolutePath()
        );

        File[] files =
        currentDirectory.listFiles();

        if(files!=null){


            for(File file:files){


                if(file.isDirectory()){

                    model.addElement(
                    "📁 "
                    +file.getName()
                    );

                }
                else{

                    model.addElement(
                    "📄 "
                    +file.getName()
                    );

                }

            }

        }

    }

    private void openSelected(){

        String selected =
        fileList.getSelectedValue();

        if(selected==null)
            return;

        String name =
        selected.substring(2);

        File file =
        new File(
        currentDirectory,
        name
        );

        if(file.isDirectory()){


            currentDirectory=file;

            loadFiles();

        }

        else{

            try{

                Desktop.getDesktop()
                .open(file);

            }
            catch(Exception e){

                JOptionPane.showMessageDialog(
                this,
                "Cannot open file"
                );

            }

        }

    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(
        () -> new FileExplorerSystem()
        );

    }

}
