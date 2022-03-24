package com.bartoszek.jtzweakreferences;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.imgscalr.Scalr;


public class CacheController {

    private Cache cache;
    private LinkedHashSet<Path> bufferedPaths = new LinkedHashSet<>();
    private CacheAppView cacheAppView;
    private JFileChooser jFileChooser;
    private Viewer personDataViewer;

    public CacheController(CacheAppView cacheAppView, Cache cache){
        this.cacheAppView = cacheAppView;
        this.cache = cache;
        this.jFileChooser = new JFileChooser();
        this.personDataViewer = new PersonDataViewer();
        initView();
    }

    private void initView() {
        cacheAppView.getPersonalDataJTextArea().setFont(personDataViewer.getFont());
    }

    public void initController() {
        //menu set key shortcut
        JMenuItem authorJMenuItem = cacheAppView.getAuthorJMenuItem();
        authorJMenuItem.setMnemonic(KeyEvent.VK_A);
        authorJMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        //add author information
        authorJMenuItem.addActionListener(e -> setAuthorListener());

        //configure JFileChooser
        configureJFileChooser();
    }

    private void configureJFileChooser() {
        cacheAppView.getFolderToChooseJPanel().add(jFileChooser);
        cacheAppView.getFolderToChooseJPanel().setPreferredSize(new Dimension(200,200));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jFileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION)) {
                    File selectedFile = jFileChooser.getSelectedFile();
                    try {
                        Path folderPath = selectedFile.toPath();
                        addLastViewedPath(folderPath);
                        if(containsPersonalDataEntry(folderPath)){
                            PersonalData cachedEntry = cache.getEntry(folderPath);
                            updateCachedFilesJTextArea();
                            updatePanelWithPersonalData(cachedEntry);
                        }else{
                            boolean folderSaved = saveFolder(folderPath);
                            if(folderSaved){
                                if(containsPersonalDataEntry(folderPath)){
                                    PersonalData entry = cache.getEntry(folderPath);
                                    updatePanelWithPersonalData(entry);
                                    updateCachedFilesJTextArea();
                                }
                            }
                        }
                        // to visualize how work WeakHashMap, i run garbage collector
                        System.gc();
                    } catch (IOException ioException) {
                        JOptionPane.showMessageDialog(cacheAppView,"błąd wcztywania"
                                ,"Error IO"
                                ,JOptionPane.ERROR_MESSAGE);
                        ioException.printStackTrace();
                    }
                } else if (e.getActionCommand().equals(javax.swing.JFileChooser.CANCEL_SELECTION)) {
                    System.exit(1);
                }
            }
        });
    }

    private void updatePanelWithPersonalData(PersonalData entry) {
        List<String> recordTxtLines = entry.getRecordTxtLines();
        BufferedImage bufferedImage = entry.getImagePng();
        StringBuilder stringBuilder = new StringBuilder();
        for(String e:recordTxtLines){
            stringBuilder.append(e);
            stringBuilder.append("\n");
        }
        cacheAppView.getPersonalDataJTextArea().setText(stringBuilder.toString());

        Dimension imageSize = personDataViewer.getImageSize();
        cacheAppView.getPersonalDataJLabel()
                .setIcon(new ImageIcon(Scalr.resize(bufferedImage, Scalr.Method.BALANCED, imageSize.width, imageSize.height)));
    }


    private void updateCachedFilesJTextArea() {
        StringBuilder cachedFilesBuilder = new StringBuilder();
        List<String> cachedPaths = getCachedPaths();
        for(String file:cachedPaths){
            cachedFilesBuilder.append(file);
            cachedFilesBuilder.append("\n");
        }

        cacheAppView.getCachedFilesJTextArea()
                .setText(cachedFilesBuilder.toString());
    }

    private void setAuthorListener() {
        JOptionPane.showMessageDialog(null,"@bainmar","Autor",JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean saveFolder(Path folderPath) throws IOException {
        PersonalData entry = PersonalData.createEntry(folderPath);
        if(entry!=null){
            cache.saveFolder(folderPath,entry);
            return true;
        }
        return false;
    }
    public int numberOfElements() {
        return cache.numberOfElements();
    }

    public boolean containsPersonalDataEntry(Path folderPath) {
        return cache.containsPersonalDataEntry(folderPath);
    }
    private void addLastViewedPath(Path folderPath){
        bufferedPaths.add(folderPath);
        if(bufferedPaths.size()>3){
            bufferedPaths.remove(bufferedPaths.stream().findFirst().get());
        }
        System.out.println(bufferedPaths);
    }
    public Set<Path> getLastViewedPaths() {
        return bufferedPaths;
    }

    public List<String> getCachedPaths() {
        return cache.getCachedPaths();
    }
}