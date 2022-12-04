import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Yunhan Qiao-1222800436
 * @version 1.0
 * <p>This class extends the observer and paints components.</p>
 * @Date 08/10/2021
 */
public class Workspace extends JPanel implements Observer, MouseListener, MouseMotionListener {

    private String pressedKey;
    private Boolean pressOut;
    private AbstractFactory cityFactory;
    private Handler tsp;
    private Handler tspPro;
    private Handler kCluster;
    private Handler userConnect;
    private int type;
    private boolean canCreate = true;
    private boolean canConnect = true;
    private boolean canMove = true;

    /**
     * <p>This function is constructor.</p>
     */
    public Workspace() {
        cityFactory = new CityFactory();
        tsp= new TSP();
        tspPro = new TSPpro();
        kCluster = new KClusters();
        tsp.setNextHandler(tspPro);
        tspPro.setNextHandler(kCluster);
        tsp.addObserver(this);
        tspPro.addObserver(this);
        kCluster.addObserver(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * <p>This function is to paint components.</p>
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        int n = PathsList.getInstance().getLength();
        System.out.println("size of path: "+ n);
        System.out.println("size of City: "+CitiesList.getInstance().citiesList.size());
        if (PathsList.getInstance() != null && n >= 1) {
            for (int i = 0; i < n; i++) {
                City city1 = PathsList.getInstance().path.get(i)[0];
                City city2 = PathsList.getInstance().path.get(i)[1];
                city1.drawConnect(city2, graphics2D);
            }
        }
        for (Map.Entry<String, City> entry : CitiesList.getInstance().citiesList.entrySet()) {
            entry.getValue().draw(g);
        }
    }

    /**
     * <p>This function is to receive message from observable and response.</p>
     * @param obs
     * @param obj
     */
    public void update(Observable obs, Object obj) {
        System.out.println("The workspace will repaint");
        repaint();
    }

    /**
     * <p>This function is to create new city.</p>
     * @param x
     * @param y
     */
    private void setNewCity(int x, int y) {

        String name = JOptionPane.showInputDialog("ENTER CITY NAME");
        if(name == null){
            JOptionPane.showMessageDialog(null, "NAME CANNOT BE NULL");
            return;
        }
        City city = cityFactory.getCity(name, x, y);
        CitiesList.getInstance().addNewCity(city);
        repaint();
    }

    /**
     * <p>This function will be triggered when we click on the panel.</p>
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
        if(canCreate==true) {
            int x = e.getX();
            int y = e.getY();
            LinkedHashMap<String, City> map = CitiesList.getInstance().citiesList;
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, City> entry = (Map.Entry) iter.next();
                City city = entry.getValue();
                CitiesName.getInstance().cityNames.add(entry.getKey());
                boolean isContained = city.contain(x, y);
                if (isContained) {
                    JOptionPane.showMessageDialog(null, "CANNOT CREATE CITY HERE!");
                    return;
                }

            }

            setNewCity(x, y);
        }else {
            return;
        }
    }

    /**
     * <p>This function will be triggered when we press the panel.</p>
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if(canMove==true) {
            int x = e.getX();
            int y = e.getY();
            LinkedHashMap<String, City> map = CitiesList.getInstance().citiesList;
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, City> entry = (Map.Entry) iter.next();
                City city = entry.getValue();
                boolean isContained = city.contain(x, y);
                if (isContained) {
                    pressOut = false;
                    System.out.println(city.label);
                    pressedKey = city.label;
                }
            }
        }else {
            return;
        }
    }

    /**
     * <p>This function will be triggered when we drag the city.</p>
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if(canMove==true) {
            if (pressOut != null && !pressOut) {
                System.out.println("Move");
                City city = CitiesList.getInstance().citiesList.get(pressedKey);
                System.out.println(city.label);
                city.move(e.getX(), e.getY());
                this.handleRequest(this.type);
                repaint();
            }
        }else if(canConnect==true){
        		if (pressOut != null && !pressOut) {
                    System.out.println("User Connect");
                    City city = CitiesList.getInstance().citiesList.get(pressedKey);
                    System.out.println(city.label);
                    
        		}
        }
            return;
     }
        
    

    /**
     * <p>This function will be triggered when we release the mouse.</p>
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(canMove==true) {
            pressOut = true;
        }else {
            return;
        }
    }

    /**
     * <p>Empty function calls for interface implementation.</p>
     */
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void handleRequest(int type){
        if(canConnect==true) {
            PathsList.getInstance().clearPath();
            this.type = type;
            tsp.handleRequest(type);
        }else {
            return;
        }
    }

    public void handleUserConnect(){
        PathsList.getInstance().clearPath();
        repaint();
    }

    public void newPanel(){
        PathsList.getInstance().clearPath();
        CitiesList.getInstance().clearCities();
        repaint();
    }

    public void moveAbled(){
        this.canCreate=false;
        this.canConnect = false;
        this.canMove = true;
    }

    public void connectAbled(){
        this.canCreate = false;
        this.canMove = false;
        this.canConnect = true;
    }

    public void createAbled(){
        this.canCreate = true;
        this.canMove = false;
        this.canConnect = false;
    }

    public void setCanCreate(boolean canCreate) {
        this.canCreate = canCreate;
    }

    public void setCanConnect(boolean canConnect) {
        this.canConnect = canConnect;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
