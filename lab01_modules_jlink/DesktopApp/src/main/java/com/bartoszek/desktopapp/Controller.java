package com.bartoszek.desktopapp;

import com.bartoszek.md5library.FileData;
import com.bartoszek.md5library.FileStatus;
import com.bartoszek.md5library.VersionController;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Controller {
    private Model model;
    private ModuleJlinkJFrame view;

    public Controller(Model model, ModuleJlinkJFrame view) {
        this.model = model;
        this.view = view;
        initView();
    }

    private void initView() {
        view.getTrackJTextField().setEnabled(false);
//        view.getNewJTextArea().setEnabled(false);
//        view.getModifiedJTextArea().setEnabled(false);
//        view.getRemovedJTextArea().setEnabled(false);
        view.getChangesJButton().setEnabled(false);
    }

    public void initController() {
        view.getTrackJButton().addActionListener(e -> setPathToTrack());
        view.getAuthorJMenuItem().addActionListener(e -> setAuthorListener());
        view.getChangesJButton().addActionListener(e -> setChangesButtonListener());
    }

    private void setChangesButtonListener() {
        VersionController versionController = model.getVersionController();
        try {
            Map<FileStatus, List<FileData>> changesInRepo = versionController.getChangesInRepo();

            List<FileData> newFiles = changesInRepo.get(FileStatus.NEW);
            StringBuilder newFilesBuilder = new StringBuilder();
            newFiles.forEach(e -> {
                newFilesBuilder.append(e.toString());
                newFilesBuilder.append(System.getProperty("line.separator"));
            });

            List<FileData> editedFiles = changesInRepo.get(FileStatus.EDITED);
            StringBuilder editedFilesBuilder = editedFiles.stream().map(FileData::toString)
                    .collect(StringBuilder::new,
                            (e, v) -> {
                                e.append(v);
                                e.append(System.getProperty("line.separator"));
                            },
                            StringBuilder::append);

            List<FileData> removedFiles = changesInRepo.get(FileStatus.REMOVED);
            StringBuilder removedFilesBuilder = removedFiles.stream().map(FileData::toString)
                    .collect(StringBuilder::new,
                            (e, v) -> {
                                e.append(v);
                                e.append(System.getProperty("line.separator"));
                            },
                            StringBuilder::append);


            view.getNewJTextArea().setText(newFilesBuilder.toString());
            view.getModifiedJTextArea().setText(editedFilesBuilder.toString());
            view.getRemovedJTextArea().setText(removedFilesBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPathToTrack() {

        JFileChooser jFileChooser = view.getjFileChooser();
        int returnValue = jFileChooser.showOpenDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            try {
                Path path = selectedFile.toPath();
                model.createVersionController(path);
                view.getTrackJTextField().setText(path.toString());
                view.getChangesJButton().setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            view.getChangesJButton().setEnabled(false);
            JOptionPane.showMessageDialog(view, "niepoprawna ścieżka", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setAuthorListener() {
        JOptionPane.showMessageDialog(view, "@bainmar", "Autor", JOptionPane.INFORMATION_MESSAGE);
    }
}