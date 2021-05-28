import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class runGUI extends JPanel implements TableModelListener {
    private JButton loadXMLButton;
    private JButton exportXMLButton;
    private JTable maintable;
    private JLabel label;
    private JButton deleteButton;
    public InputStream fisier;

    class MyTableModel extends AbstractTableModel {
        private final String[] columnNames = {"pin_nr", "pin_name", "pin_category"};
        private final List<Object[]> data = dataProcessing();

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            switch (c) {
                case 0:
                    return Integer.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                default:
                    return String.class;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            return true;

        }

        /*
        public void removeRow(int row){
            data.
        }

         */
    }

    public String[] numereProcesate() throws NullPointerException {
        String[] numere = new String[100];
        fisier = getClass().getResourceAsStream("xml.xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fisier);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("pin");
            System.out.println("----------------------------");

            numere = new String[nodeList.getLength()+1];
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nnode = nodeList.item(temp);
                if (nnode.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nnode;
                    numere[temp] = el.getElementsByTagName("pin_nr").item(0).getTextContent();
                    System.out.println("\t"+numere[temp]);
                }
            }
        } catch (Exception e) {
            System.out.println("Nu prea merge la procesat NUMERE!!!!!!!!!!!!");
        }
        return numere;
    }

    public String[] numeProcesate() throws NullPointerException {
        String[] nume = new String[100];
        fisier = getClass().getResourceAsStream("xml.xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fisier);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("pin");
            System.out.println("----------------------------");

            nume = new String[nodeList.getLength()+1];
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nnode = nodeList.item(temp);
                if (nnode.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nnode;
                    nume[temp] = el.getElementsByTagName("pin_name").item(0).getTextContent();
                    System.out.println("\t"+nume[temp]);
                }
            }
        } catch (Exception e) {
            System.out.println("Nu prea merge la procesat NUME!!!!!!!!!!!!");
        }
        return nume;
    }

    public String[] categProcesate() throws NullPointerException {
        String[] categ = new String[100];
        fisier = getClass().getResourceAsStream("xml.xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fisier);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("pin");
            System.out.println("----------------------------");

            categ = new String[nodeList.getLength()+1];
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node nnode = nodeList.item(temp);
                if (nnode.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nnode;
                    categ[temp] = el.getElementsByTagName("pin_category").item(0).getTextContent();
                    System.out.println("\t"+categ[temp]);
                }
            }
        } catch (Exception e) {
            System.out.println("Nu prea merge la procesat CATEGORII!!!!!!!!!!!!");
        }
        return categ;
    }

    //Loading the sample XML file into the JTable:
    public List<Object[]> dataProcessing() throws NullPointerException {
        List<Object[]> data = new ArrayList<>();
        fisier = getClass().getResourceAsStream("xml.xml");
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fisier);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("pin");
            System.out.println("----------------------------");

            for (int temp = 0; temp <= nodeList.getLength()+1; temp++) {
                Object[] row = new Object[3];
                Node nnode = nodeList.item(temp);
                if (nnode.getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nnode;
                    row[0] = Integer.parseInt(el.getElementsByTagName("pin_nr").item(0).getTextContent());
                    row[1] = el.getElementsByTagName("pin_name").item(0).getTextContent();
                    row[2] = el.getElementsByTagName("pin_category").item(0).getTextContent();
                }
                data.add(row);
            }
        } catch (Exception e) {
            System.out.println("Nu prea merge la procesat date!!!!!!!!!!!!");
        }
        return data;
    }

    //Listeners:
    @Override
    public void tableChanged(TableModelEvent tableModelEvent) { }

    private static JTable getNewRenderedTable(JTable maintable) {
        maintable.setDefaultRenderer(Object.class,new DefaultTableCellRenderer(){
        @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
                super.getTableCellRendererComponent(table, value, selected, focused, row, column);
                //setEnabled(table == null || table.isEnabled()); // see question above
                for (int r = 0; r < maintable.getRowCount(); r++) {
                    String status = (String) maintable.getModel().getValueAt(r, 2);
                    if ("DIO".equals(status)) {
                        setBackground(Color.cyan);
                    } else if ("ANA".equals(status)) {
                        setBackground(Color.RED);
                    } else if ("PWM".equals(status)) {
                        setBackground(Color.MAGENTA);
                    } else if ("OTH".equals(status)) {
                        setBackground(Color.lightGray);
                    } else {
                        setBackground(maintable.getBackground());
                    }
                }
                return this;
            }
        });
        return maintable;
    }

    //TODO Background color *WORK IN PROGRESS*:
    /*
    private static JTable getNewRenderedTable(JTable maintable) {
        maintable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = prepareRenderer(renderer, row, col);
                String status = (String) maintable.getModel().getValueAt(0,2);
                if ("DIO".equals(status)) {
                    setBackground(Color.cyan);
                } else if ("ANA".equals(status)) {
                    setBackground(Color.RED);
                } else if ("PWM".equals(status)) {
                    setBackground(Color.MAGENTA);
                } else if ("OTH".equals(status)) {
                    setBackground(Color.lightGray);
                } else {
                    setBackground(maintable.getBackground());
                }
                return c;
            }
        });
        return maintable;
    }

    */



    //Main form method:
    public runGUI() throws NullPointerException {
        DefaultTableModel empty;
        String[] columnNames = {"pin_nr", "pin_name", "pin_category"};
        Object[][] data = {};
        empty = new DefaultTableModel(data,columnNames);
        maintable.setModel(empty);

        maintable.isCellEditable(0,0);
        maintable.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectModel = maintable.getSelectionModel();
        cellSelectModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        maintable.getColumnClass(0);
        maintable.setAutoCreateRowSorter(true);
        maintable.setFillsViewportHeight(true);
        maintable.setAutoCreateRowSorter(true);
        JScrollPane pane = new JScrollPane(maintable);
        add(pane);
        loadXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == loadXMLButton) {
                    System.out.println("Load XML apasat! ¯\\_(ツ)_/¯");
                    try {
                        MyTableModel full;
                        full = new MyTableModel();
                        maintable.setModel(full);

                        //Pentru colorare:
                        TableColumnModel tcm = maintable.getColumnModel();
                        TableColumn tc = tcm.getColumn(2);
                        tc.setCellRenderer(new DefaultTableCellRenderer());

                        getNewRenderedTable(maintable);

                        //Selectare cell:
                        maintable.setCellSelectionEnabled(true);
                        ListSelectionModel cellSelectModel = maintable.getSelectionModel();
                        cellSelectModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        cellSelectModel.addListSelectionListener(new ListSelectionListener() {
                            @Override
                            public void valueChanged(ListSelectionEvent e) {
                                String selectedData = null;
                                int[] selectedRow = maintable.getSelectedRows();
                                int[] selectedCol = maintable.getSelectedColumns();

                                for (int i = 0; i < selectedRow.length; i++) {
                                    for (int j = 0; j < selectedCol.length; j++) {
                                        selectedData = (String) maintable.getValueAt(selectedRow[i], selectedCol[j]);
                                    }
                                }
                                System.out.println("Selected: " + selectedData);
                            }
                        });

                        maintable.isCellEditable(0, 0);
                        maintable.addMouseListener(new MouseAdapter() {
                            KeyEvent pumniCuTaste;
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                    int x = e.getX();
                                    int y = e.getY();
                                    int row = maintable.rowAtPoint(new Point(x, y));
                                    int col = maintable.columnAtPoint(new Point(x, y));

                                    String[] array = new String[maintable.getColumnCount()];
                                    for (int i = 0; i < array.length; i++) {
                                        array[i] = (String) maintable.getValueAt(row, i);
                                    }

                                    for (int i = 0; i < array.length; i++) {
                                        if (pumniCuTaste.getKeyCode() == KeyEvent.VK_ENTER) {
                                            maintable.setValueAt(array[i], row, col);
                                        }
                                    }
                                }
                            }
                        });
                        maintable.getColumnClass(0);
                        maintable.setAutoCreateRowSorter(true);
                        maintable.setFillsViewportHeight(true);
                        maintable.setAutoCreateRowSorter(true);
                        JScrollPane pane = new JScrollPane(maintable);
                        add(pane);
                    } catch (Exception f) {
                        System.out.println("Ajutor mi-e rau");
                    }
                }
                else
                {

                }
            }
        });
        add(label);
        add(loadXMLButton);
        add(exportXMLButton);
        loadXMLButton.setVisible(true);
        loadXMLButton.updateUI();

        //TODO Delete button *WORK IN PROGRESS*:
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowForDeletion = maintable.getSelectedRow();
                int selectedColForDeletion = maintable.getSelectedColumn();

                if(e.getSource()==deleteButton && maintable.getSelectedRow()>=0)
                    System.out.println("Buton stergere rand APASAT ._.");
                try{
                    MyTableModel full;
                    full = new MyTableModel();
                    maintable.setModel(full);
                    maintable.setValueAt("",selectedRowForDeletion,selectedColForDeletion);
                    deleteButton.updateUI();

                }
                catch (Exception exc){
                    System.out.println("Nu se sterge nimica");
                }
            }
        });

        add(deleteButton);
        deleteButton.setVisible(true);
        deleteButton.updateUI();

        //Export XML button that exports Text -_-
        /*
        exportXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==exportXMLButton)
                {
                    String filePath = "ExportedXML.xml";
                    File file = new File(filePath);
                    try{
                        String str;
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);
                        for(int i = 0; i < maintable.getRowCount(); i++)
                            for (int j = 0; j < maintable.getColumnCount(); j++)
                            {
                                str = ""+maintable.getValueAt(i,j);
                                bw.write(str);
                                bw.newLine();
                            }
                        bw.close();fw.close();
                    }
                    catch (IOException nem){
                        System.out.println("E belita pe undeva");
                    }
                }
            }
        });
         */

        exportXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) throws NullPointerException {
                Document dom;
                Element el;
                String[] pin_nr = numereProcesate();
                String[] pin_name = numeProcesate();
                String[] pin_category = categProcesate();

                String[] saveOptions = {"New XML file","Overwrite original"};
                String pickSave = (String) JOptionPane.showInputDialog(exportXMLButton,
                        "Save table info in a new file or overwrite the original?",
                        "File Export",
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        saveOptions,
                        saveOptions[0]);
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    dom = db.newDocument();

                    Element rootEle = dom.createElement("microcontroller");

                    for (int i = 0; i < maintable.getRowCount(); i++) {
                        Element pin = dom.createElement("pin");
                            el = dom.createElement("pin_nr");
                            el.appendChild(dom.createTextNode(pin_nr[i]));
                            pin.appendChild(el);

                            el = dom.createElement("pin_name");
                            el.appendChild(dom.createTextNode(pin_name[i]));
                            pin.appendChild(el);

                            el = dom.createElement("pin_category");
                            el.appendChild(dom.createTextNode(pin_category[i]));
                            pin.appendChild(el);
                        rootEle.appendChild(pin);
                    }

                    dom.appendChild(rootEle);

                    try {
                        Transformer tr = TransformerFactory.newInstance().newTransformer();
                        tr.setOutputProperty(OutputKeys.INDENT, "yes");
                        tr.setOutputProperty(OutputKeys.METHOD, "xml");
                        tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                        tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                        if(e.getSource()==exportXMLButton && pickSave==saveOptions[0]) {
                            tr.transform(new DOMSource(dom),
                                    new StreamResult(new FileOutputStream("ExportedXML.xml")));
                        }else if(e.getSource()==exportXMLButton && pickSave==saveOptions[1])
                        {
                            tr.transform(new DOMSource(dom),new StreamResult(new FileOutputStream("xml.xml")));}
                    } catch (TransformerException te) {
                        System.out.println(te.getMessage());
                    } catch (IOException ioe) {
                        System.out.println(ioe.getMessage());
                    }
                } catch (ParserConfigurationException pce) {
                    System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
                }
            }
        });
        exportXMLButton.setVisible(true);
        exportXMLButton.updateUI();
    }

    //Main main main:
    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("dsPIC33CH128MP508");
            runGUI t = new runGUI();
            frame.setContentPane(new runGUI());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            System.out.println("Number of data rows = "+ t.dataProcessing().size());
            System.out.println("Number of nr rows = "+ t.numereProcesate().length);
            System.out.println("Number of name rows = "+ t.numeProcesate().length);
            System.out.println("Number of category rows = "+ t.categProcesate().length);
        }
        catch (Exception e){
            System.out.println("Nu ca sparg calculatoru");
        }
    }
}

