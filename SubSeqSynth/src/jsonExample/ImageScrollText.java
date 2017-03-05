package jsonExample;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Random;

import javax.swing.*;

public class ImageScrollText extends JPanel implements Scrollable {

    private static final int N = 8;
    private static final int W = 160;
    private static final int H = 500;
    private final FlowLayout layout = new FlowLayout();
    private final int hGap = layout.getHgap();
    private final int vGap = layout.getVgap();
    private Dimension size;

    // Show n of N images in a Scrollable FlowLayout
    public ImageScrollText(int n) {
        setLayout(layout);
        
        //addNewNoteGraph(2);
        this.add(new NoteGraph2());

        
        size = new Dimension(n * W + (n + 1) * hGap, H + 2 * vGap);
 
    }
    public void addNewNoteGraph(int n){
    		this.add(new NoteGraph2());
    		 size = new Dimension(n * W + (n + 1) * hGap, H + 2 * vGap);
    }
  

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return size;
    }

    @Override
    public int getScrollableUnitIncrement(
        Rectangle visibleRect, int orientation, int direction) {
        return getIncrement(orientation);
    }

    @Override
    public int getScrollableBlockIncrement(
        Rectangle visibleRect, int orientation, int direction) {
        return getIncrement(orientation);
    }

    
    private int getIncrement(int orientation) {
        if (orientation == JScrollBar.HORIZONTAL) {
            return W + hGap;
        } else {
            return H + vGap;
        }
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    public static class NoteGraph extends JPanel {

        private static final Random rnd = new Random();
        public int ranGen = rnd.nextInt();
        private Color color = new Color(ranGen);
       
        public NoteGraph() { //Should pass MidiEvent[] buffer
        		int i = 10;
        		int j = 10;
        		System.out.println("NoteGraph Constuctor called.");
        		JPanel[][] panelHolder = new JPanel[i][j];    
        		setLayout(new GridLayout(i,j,0,0));


        		Color rwCol = new Color(rnd.nextInt());
        		for(int m = 0; m < i; m++) {
        		   for(int n = 0; n < j; n++) {
        			  
        		      panelHolder[m][n] = new JPanel();
        		      add(panelHolder[m][n]);
        		      panelHolder[m][n].setBackground(rwCol);
        		   }
            this.setBackground(color);
            this.setBorder(BorderFactory.createLineBorder(Color.black));
        		}
        		//int bfLen = Array.getLength(buffer);
        		System.out.println("Before NoteEvent added.");
        		//System.out.println("MidiNote: buffer");
        		//panelHolder[3][3].add(new NoteEvent());
//        		for (int p = 0; p < bfLen ; p++){
//        			
//        			panelHolder[buffer[p].midiNote % 9][buffer[p].timeStmp %15].add(new NoteEvent());
//        		}
        }
        

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(W, H);
        }
    }
    public static class NoteEvent extends JPanel {

        private static final Random rnd = new Random();
        private Color color = new Color(rnd.nextInt());

        
        public NoteEvent() {
            this.setBackground(color);
            this.setBorder(BorderFactory.createLineBorder(Color.black));
            this.setSize(new Dimension(5,5));
        }
        

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(5,10);
        }
    }
    public static class NoteGraph2 extends JPanel {

        private static final Random rnd = new Random();
        private Color color = new Color(rnd.nextInt());

        public NoteGraph2() {
            this.setBackground(color);
            this.setBorder(BorderFactory.createLineBorder(Color.blue));
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(W, H);
        }
    }
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                //createAndShowGUI();
            }
        });
    }

    public JFrame createAndShowGUI() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageScrollText ist = new ImageScrollText(N / 2);
        JScrollPane sp = new JScrollPane(ist);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        f.add(sp);
        f.pack();
        f.setVisible(true);
        return f;
    }
    

    
    }
   
