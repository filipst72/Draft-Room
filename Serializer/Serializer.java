package raf.draft.dsw.model.Serializer;

import raf.draft.dsw.model.structures.Project;

import java.io.File;

public interface Serializer {
    Project loadProject(File filename);
    void saveProject(Project project);

}
