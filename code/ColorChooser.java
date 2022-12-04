import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooser extends JDialog{
    private JLabel sizeLabel;
    private JTextField sizeText;
    private JLabel shapeLabel;
    private JRadioButton squareButton;
    private JRadioButton circleButton;
    private JColorChooser colorChooser;
    private JButton applyButton;
    private Color colorChanged;
    private int sizeChanged;

    public ColorChooser(JFrame parent){
        super(parent, "AASS", true);
        setLayout(new FlowLayout());
        sizeLabel = new JLabel("size:");
        shapeLabel = new JLabel("shape:");
        squareButton = new JRadioButton("square");
        circleButton = new JRadioButton("circle");
        sizeText = new JTextField(10);
        colorChooser  =  new JColorChooser();
        applyButton = new JButton("Apply");
        add(sizeLabel);
        add(sizeText);
        add(shapeLabel);
        add(squareButton);
        add(circleButton);
        add(colorChooser);
        add(applyButton);
//        setVisible(true);
        setSize(700,500);
        applyButton.addActionListener(e -> {
            colorChanged = colorChooser.getColor();
            System.out.println(colorChanged.getRGB());
            sizeChanged = Integer.parseInt(sizeText.getText());
            System.out.println(sizeChanged);
            setVisible(false);
        });
    }



    public Color getColorChanged() {
        System.out.println(colorChanged.toString());
        return colorChanged;
    }

    public int getSizeChanged() {
        System.out.println(sizeChanged);
        return sizeChanged;
    }
}
